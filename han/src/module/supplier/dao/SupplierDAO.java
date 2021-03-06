package module.supplier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.bank.model.Bank;
import module.sn.currency.model.Currency;
import module.sn.supptype.model.SuppType;
import module.supplier.model.Supplier;
import module.util.DateUtil;

public class SupplierDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement isSuppCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = "select s.id, s.supp_code, s.supp_name, s.pt, s.npwp, "
			+ "s.supp_type_id, s.default_tax, s.account_no,"
			+ "s.bank_id, s.account_name, s.currency_id, s.top, sa.city, "
			+ "st.supp_type, b.bank, b.bank_abbr, c.currency, c.currency_abbr from supplier s "
			+ "inner join supp_type st on s.supp_type_id = st.id left join bank b on s.bank_id = b.id "
			+ "left join supp_address sa on s.supp_code = sa.supp_code "
			+ "left join currency c on s.currency_id = c.id "
			+ "where s.deleted_date is null and st.deleted_date is null "
			+ "and b.deleted_date is null and c.deleted_date is null ";
	
	private String isSuppCodeExistsQuery = "select count(*) as is_exists from supplier where supp_code = ? and deleted_date is null ";

	private String insertQuery = "insert into supplier (supp_code, supp_name, pt, npwp, "
			+ "supp_type_id, default_tax, account_no, bank_id, account_name, currency_id, top, "
			+ "input_date, input_by) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private String updateQuery = "update supplier set supp_name=?, pt=?, npwp=?, "
			+ "supp_type_id=?, default_tax=?, account_no=?, bank_id=?, account_name=?, currency_id=?, top=?, "
			+ "edit_date=?, edited_by=? where supp_code=?";

	private String deleteQuery = "update supplier set deleted_date=?, deleted_by=? where id=?";

	public SupplierDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Supplier> getAll() throws SQLException {
		List<Supplier> suppliers = new ArrayList<Supplier>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				supplier.setPt(rs.getString("pt"));
				supplier.setNpwp(rs.getString("npwp"));
				supplier.setSuppTypeId(rs.getInt("supp_type_id"));
				//supplier.setSuppStatus(rs.getString("supp_status"));
				supplier.setDefaultTax(rs.getInt("default_tax"));
				supplier.setAccountNo(rs.getString("account_no"));
				supplier.setBankId(rs.getInt("bank_id"));
				supplier.setAccountName(rs.getString("account_name"));
				supplier.setCurrencyId(rs.getInt("currency_id"));
				supplier.setTop(rs.getInt("top"));
				supplier.setCity(rs.getString("city"));

				SuppType suppType = new SuppType();
				suppType.setId(rs.getInt("supp_type_id"));
				suppType.setSuppType(rs.getString("supp_type"));

				Bank bank = new Bank();
				bank.setId(rs.getInt("bank_id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				Currency currency = new Currency();
				currency.setId(rs.getInt("currency_id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrency(rs.getString("currency"));

				supplier.setSuppType(suppType);
				supplier.setBank(bank);
				supplier.setCurrency(currency);

				suppliers.add(supplier);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return suppliers;
	}

	public List<Supplier> getAllBySimpleSearch(String value) throws SQLException {
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value).append("%").toString();
				String query = new StringBuilder().append(getAllQuery).append(" and")
						.append(" (lower(s.supp_code) like lower('%s')").append(" or lower(s.supp_name) like lower('%s')")
						.append(" or lower(st.supp_type) like lower('%s'))").toString();
				getAllStatement = connection.prepareStatement(String.format(query, keyword, keyword, keyword));
			} else {
				getAllStatement = connection.prepareStatement(getAllQuery);
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				supplier.setPt(rs.getString("pt"));
				supplier.setNpwp(rs.getString("npwp"));
				supplier.setSuppTypeId(rs.getInt("supp_type_id"));
				//supplier.setSuppStatus(rs.getString("supp_status"));
				supplier.setDefaultTax(rs.getInt("default_tax"));
				supplier.setAccountNo(rs.getString("account_no"));
				supplier.setBankId(rs.getInt("bank_id"));
				supplier.setAccountName(rs.getString("account_name"));
				supplier.setCurrencyId(rs.getInt("currency_id"));
				supplier.setTop(rs.getInt("top"));
				supplier.setCity(rs.getString("city"));
				
				SuppType suppType = new SuppType();
				suppType.setId(rs.getInt("supp_type_id"));
				suppType.setSuppType(rs.getString("supp_type"));

				Bank bank = new Bank();
				bank.setId(rs.getInt("bank_id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				Currency currency = new Currency();
				currency.setId(rs.getInt("currency_id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrency(rs.getString("currency"));

				supplier.setSuppType(suppType);
				supplier.setBank(bank);
				supplier.setCurrency(currency);

				suppliers.add(supplier);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return suppliers;
	}

	public List<Supplier> getAllByAdvancedSearch(Supplier supplierSearch) throws SQLException {
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			StringBuilder query = new StringBuilder().append(getAllQuery);
			query.append(" and lower(s.supp_code) like lower('%");
			query.append(supplierSearch.getSuppCode());
			query.append("%') ");
			query.append(" and lower(s.supp_name) like lower('%");
			query.append(supplierSearch.getSuppName());
			query.append("%') ");
			query.append(" and lower(s.pt) like lower('%");
			query.append(supplierSearch.getPt());
			query.append("%') ");
			query.append(" and lower(s.npwp) like lower('%");
			query.append(supplierSearch.getNpwp());
			query.append("%') ");
			query.append(" and lower(st.supp_type) like lower('%");
			if(supplierSearch.getSuppType().getSuppType() != null)
				query.append(supplierSearch.getSuppType().getSuppType());
			query.append("%') ");
//			query.append(" and lower(s.supp_status) like lower('%");
//			if(supplierSearch.getSuppStatus() != null)
//				query.append(supplierSearch.getSuppStatus());
//			query.append("%') ");
			
			getAllStatement = connection.prepareStatement(query.toString());

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				supplier.setPt(rs.getString("pt"));
				supplier.setNpwp(rs.getString("npwp"));
				supplier.setSuppTypeId(rs.getInt("supp_type_id"));
				//supplier.setSuppStatus(rs.getString("supp_status"));
				supplier.setDefaultTax(rs.getInt("default_tax"));
				supplier.setAccountNo(rs.getString("account_no"));
				supplier.setBankId(rs.getInt("bank_id"));
				supplier.setAccountName(rs.getString("account_name"));
				supplier.setCurrencyId(rs.getInt("currency_id"));
				supplier.setTop(rs.getInt("top"));
				supplier.setCity(rs.getString("city"));
				
				SuppType suppType = new SuppType();
				suppType.setId(rs.getInt("supp_type_id"));
				suppType.setSuppType(rs.getString("supp_type"));

				Bank bank = new Bank();
				bank.setId(rs.getInt("bank_id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				Currency currency = new Currency();
				currency.setId(rs.getInt("currency_id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrency(rs.getString("currency"));

				supplier.setSuppType(suppType);
				supplier.setBank(bank);
				supplier.setCurrency(currency);

				suppliers.add(supplier);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return suppliers;
	}

	public int isSuppCodeExists(String suppCode) throws SQLException {
		int count = 0;
		try {
			isSuppCodeExistsStatement = connection.prepareStatement(isSuppCodeExistsQuery);
			isSuppCodeExistsStatement.setString(1, suppCode);

			ResultSet rs = isSuppCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(Supplier supplier) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, supplier.getSuppCode());
			insertStatement.setString(2, supplier.getSuppName());
			insertStatement.setString(3, supplier.getPt());
			insertStatement.setString(4, supplier.getNpwp());
			insertStatement.setInt(5, supplier.getSuppTypeId());
			//insertStatement.setString(6, supplier.getSuppStatus());
			insertStatement.setDouble(6, supplier.getDefaultTax());
			insertStatement.setString(7, supplier.getAccountNo());
			insertStatement.setInt(8, supplier.getBankId());
			insertStatement.setString(9, supplier.getAccountName());
			if (supplier.getCurrencyId() == 0) {
				insertStatement.setNull(10, java.sql.Types.INTEGER);
			} else {
				insertStatement.setInt(10, supplier.getCurrencyId());
			}
			insertStatement.setInt(11, supplier.getTop());
			insertStatement.setDate(12, DateUtil.getCurrentDate());
			insertStatement.setString(13, "timotius");
			insertStatement.executeUpdate();
			
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(Supplier supplier) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, supplier.getSuppName());
			updateStatement.setString(2, supplier.getPt());
			updateStatement.setString(3, supplier.getNpwp());
			updateStatement.setInt(4, supplier.getSuppTypeId());
			//updateStatement.setString(5, supplier.getSuppStatus());
			updateStatement.setDouble(5, supplier.getDefaultTax());
			updateStatement.setString(6, supplier.getAccountNo());
			updateStatement.setInt(7, supplier.getBankId());
			updateStatement.setString(8, supplier.getAccountName());
			if (supplier.getCurrencyId() == 0) {
				updateStatement.setNull(9, java.sql.Types.INTEGER);
			} else {
				updateStatement.setInt(9, supplier.getCurrencyId());
			}
			updateStatement.setInt(10, supplier.getTop());
			updateStatement.setDate(11, DateUtil.getCurrentDate());
			updateStatement.setString(12, "timotius");
			updateStatement.setString(13, supplier.getSuppCode());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void delete(int id) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public Supplier getById(int id) throws SQLException {
		Supplier supplier = null;
		String query = new StringBuilder().append(getAllQuery).append(" and s.id=?").toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, id);
			ResultSet rs = getAllStatement.executeQuery();

			while (rs.next()) {
				supplier = new Supplier();
				supplier.setId(rs.getInt("id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				supplier.setPt(rs.getString("pt"));
				supplier.setNpwp(rs.getString("npwp"));
				supplier.setSuppTypeId(rs.getInt("supp_type_id"));
				//supplier.setSuppStatus(rs.getString("supp_status"));
				supplier.setDefaultTax(rs.getInt("default_tax"));
				supplier.setAccountNo(rs.getString("account_no"));
				supplier.setBankId(rs.getInt("bank_id"));
				supplier.setAccountName(rs.getString("account_name"));
				supplier.setCurrencyId(rs.getInt("currency_id"));
				supplier.setTop(rs.getInt("top"));
				supplier.setCity(rs.getString("city"));
				
				SuppType suppType = new SuppType();
				suppType.setId(rs.getInt("supp_type_id"));
				suppType.setSuppType(rs.getString("supp_type"));

				Bank bank = new Bank();
				bank.setId(rs.getInt("bank_id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				Currency currency = new Currency();
				currency.setId(rs.getInt("currency_id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrency(rs.getString("currency"));

				supplier.setSuppType(suppType);
				supplier.setBank(bank);
				supplier.setCurrency(currency);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return supplier;
	}

	public List<Supplier> getAllSupplierBySuppTypeId(int suppTypeId) throws SQLException {
		List<Supplier> suppliers = new ArrayList<Supplier>();
		String query = new StringBuilder().append(getAllQuery).append(" and s.supp_type_id =?").toString();
		
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, suppTypeId);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				supplier.setPt(rs.getString("pt"));
				supplier.setNpwp(rs.getString("npwp"));
				supplier.setSuppTypeId(rs.getInt("supp_type_id"));
				//supplier.setSuppStatus(rs.getString("supp_status"));
				supplier.setDefaultTax(rs.getInt("default_tax"));
				supplier.setAccountNo(rs.getString("account_no"));
				supplier.setBankId(rs.getInt("bank_id"));
				supplier.setAccountName(rs.getString("account_name"));
				supplier.setCurrencyId(rs.getInt("currency_id"));
				supplier.setTop(rs.getInt("top"));
				supplier.setCity(rs.getString("city"));
				
				SuppType suppType = new SuppType();
				suppType.setId(rs.getInt("supp_type_id"));
				suppType.setSuppType(rs.getString("supp_type"));

				Bank bank = new Bank();
				bank.setId(rs.getInt("bank_id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				Currency currency = new Currency();
				currency.setId(rs.getInt("currency_id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrency(rs.getString("currency"));

				supplier.setSuppType(suppType);
				supplier.setBank(bank);
				supplier.setCurrency(currency);

				suppliers.add(supplier);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return suppliers;
	}
	
	private PreparedStatement getOrdinalOfCodeNumberStatement;
	
	private String getOrdinalOfCodeNumberQuery = "SELECT CONVERT(SUBSTRING(p.supp_code, 3, 3),UNSIGNED INTEGER) AS ordinal FROM supplier p "
			+ "WHERE SUBSTRING(p.supp_code, 1, 3) = ? "
			+ "ORDER BY ordinal DESC LIMIT 1 ";
	
	public int getOrdinalOfCodeNumber(String suppType) throws SQLException {
		int ordinal = 0;
		try {
			getOrdinalOfCodeNumberStatement = connection.prepareStatement(getOrdinalOfCodeNumberQuery);
			getOrdinalOfCodeNumberStatement.setString(1, suppType);
			
			ResultSet rs = getOrdinalOfCodeNumberStatement.executeQuery();
			while (rs.next()) {
				ordinal = rs.getInt("ordinal");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return ordinal;
	}
	
	private String getBySupplierQuery = "select s.id, s.supp_code, s.supp_name, s.pt, s.npwp, "
			+ "s.supp_type_id, s.default_tax, s.account_no,"
			+ "scp.name as contact_person, sa.city, sa.address, sa.phone, "
			+ "s.bank_id, s.account_name, s.currency_id, s.top, sa.city, "
			+ "st.supp_type, b.bank, b.bank_abbr, c.currency, c.currency_abbr from supplier s "
			+ "inner join supp_type st on s.supp_type_id = st.id left join bank b on s.bank_id = b.id "
			+ "left join supp_address sa on s.supp_code = sa.supp_code "
			+ "left join supp_cp scp on scp.supp_address_id = sa.id "
			+ "left join currency c on s.currency_id = c.id "
			+ "where s.deleted_date is null and st.deleted_date is null "
			+ "and b.deleted_date is null and c.deleted_date is null ";
	
	public List<Supplier> getBySupplier(Supplier supplierSearch) throws SQLException {
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			StringBuilder query = new StringBuilder().append(getBySupplierQuery);
			query.append(" and lower(s.supp_code) like lower('%");
			query.append(supplierSearch.getSuppCode());
			query.append("%') ");
			query.append(" and lower(s.supp_name) like lower('%");
			query.append(supplierSearch.getSuppName());
			query.append("%') ");
			query.append(" and lower(sa.city) like lower('%");
			query.append(supplierSearch.getCity());
			query.append("%') ");
			query.append(" and lower(sa.address) like lower('%");
			query.append(supplierSearch.getAddress());
			query.append("%') ");
			query.append(" and lower(sa.phone) like lower('%");
			query.append(supplierSearch.getPhone());
			query.append("%') ");
			query.append(" and lower(scp.name) like lower('%");
			query.append(supplierSearch.getContactPerson());
			query.append("%') ");
			
			getAllStatement = connection.prepareStatement(query.toString());

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				supplier.setPt(rs.getString("pt"));
				supplier.setNpwp(rs.getString("npwp"));
				supplier.setSuppTypeId(rs.getInt("supp_type_id"));
				supplier.setDefaultTax(rs.getInt("default_tax"));
				supplier.setAccountNo(rs.getString("account_no"));
				supplier.setBankId(rs.getInt("bank_id"));
				supplier.setAccountName(rs.getString("account_name"));
				supplier.setCurrencyId(rs.getInt("currency_id"));
				supplier.setTop(rs.getInt("top"));
				supplier.setCity(rs.getString("city"));
				supplier.setAddress(rs.getString("address"));
				supplier.setPhone(rs.getString("phone"));
				supplier.setContactPerson(rs.getString("contact_person"));
				
				SuppType suppType = new SuppType();
				suppType.setId(rs.getInt("supp_type_id"));
				suppType.setSuppType(rs.getString("supp_type"));

				Bank bank = new Bank();
				bank.setId(rs.getInt("bank_id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				Currency currency = new Currency();
				currency.setId(rs.getInt("currency_id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrency(rs.getString("currency"));

				supplier.setSuppType(suppType);
				supplier.setBank(bank);
				supplier.setCurrency(currency);

				suppliers.add(supplier);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return suppliers;
	}
}
