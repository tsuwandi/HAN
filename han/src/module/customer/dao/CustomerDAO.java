package module.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.customer.model.Customer;
import module.sn.bank.model.Bank;
import module.sn.currency.model.Currency;
import module.sn.supptype.model.SuppType;
import module.supplier.model.Supplier;
import module.util.DateUtil;

public class CustomerDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement isSuppCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = "select c.id, c.cust_code, c.cust_name, c.pt, c.npwp, "
			+ "c.cust_type, c.default_tax, c.account_no,"
			+ "c.bank_id, c.account_name, c.currency_id, c.top, c.country, c.note, "
			+ "b.bank, b.bank_abbr, cu.currency, cu.currency_abbr from customer c "
			+ "left join bank b on c.bank_id = b.id "
			+ "left join cust_addr ca on c.cust_code = ca.cust_code "
			+ "left join currency cu on c.currency_id = cu.id "
			+ "where c.deleted_date is null "
			+ "and b.deleted_date is null and cu.deleted_date is null ";

	private String isCustCodeExistsQuery = "select count(*) as is_exists from customer where cust_code = ? and deleted_date is null ";

	private String insertQuery = "insert into customer (cust_code, cust_name, pt, npwp, "
			+ "cust_type, default_tax, account_no, bank_id, account_name, currency_id, top, country, note, "
			+ "input_date, input_by) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private String updateQuery = "update customer set cust_name=?, pt=?, npwp=?, "
			+ "cust_type=?, default_tax=?, account_no=?, bank_id=?, account_name=?, currency_id=?, top=?, country=?, note=?, "
			+ "edit_date=?, edited_by=? where supp_code=?";

	private String deleteQuery = "update customer set deleted_date=?, deleted_by=? where id=?";

	public CustomerDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Customer> getAll() throws SQLException {
		List<Customer> customers = new ArrayList<Customer>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setCustCode(rs.getString("cust_code"));
				customer.setCustName(rs.getString("cust_name"));
				customer.setPt(rs.getString("pt"));
				customer.setNpwp(rs.getString("npwp"));
				customer.setCustType(rs.getString("cust_type"));
				customer.setDefaultTax(rs.getInt("default_tax"));
				customer.setAccountNo(rs.getString("account_no"));
				customer.setBankId(rs.getInt("bank_id"));
				customer.setAccountName(rs.getString("account_name"));
				customer.setCurrencyId(rs.getInt("currency_id"));
				customer.setTop(rs.getInt("top"));
				customer.setCountry(rs.getString("country"));
				customer.setNote(rs.getString("note"));

				Bank bank = new Bank();
				bank.setId(rs.getInt("bank_id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				Currency currency = new Currency();
				currency.setId(rs.getInt("currency_id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrency(rs.getString("currency"));

				customer.setBank(bank);
				customer.setCurrency(currency);

				customers.add(customer);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return customers;
	}

	public List<Customer> getAllBySimpleSearch(String value) throws SQLException {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value).append("%").toString();
				String query = new StringBuilder().append(getAllQuery).append(" and")
						.append(" (lower(c.cust_code) like lower('%s')")
						.append(" or lower(c.cust_name) like lower('%s')").append(" or lower(c.pt) like lower('%s'))")
						.toString();
				getAllStatement = connection.prepareStatement(String.format(query, keyword, keyword, keyword));
			} else {
				getAllStatement = connection.prepareStatement(getAllQuery);
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setCustCode(rs.getString("cust_code"));
				customer.setCustName(rs.getString("cust_name"));
				customer.setPt(rs.getString("pt"));
				customer.setNpwp(rs.getString("npwp"));
				customer.setCustType(rs.getString("cust_type"));
				customer.setDefaultTax(rs.getInt("default_tax"));
				customer.setAccountNo(rs.getString("account_no"));
				customer.setBankId(rs.getInt("bank_id"));
				customer.setAccountName(rs.getString("account_name"));
				customer.setCurrencyId(rs.getInt("currency_id"));
				customer.setTop(rs.getInt("top"));
				customer.setCountry(rs.getString("country"));
				customer.setNote(rs.getString("note"));

				Bank bank = new Bank();
				bank.setId(rs.getInt("bank_id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				Currency currency = new Currency();
				currency.setId(rs.getInt("currency_id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrency(rs.getString("currency"));

				customer.setBank(bank);
				customer.setCurrency(currency);

				customers.add(customer);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return customers;
	}

	public List<Customer> getAllByAdvancedSearch(Customer customerSearch) throws SQLException {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			StringBuilder query = new StringBuilder().append(getAllQuery);
			query.append(" and lower(c.cust_code) like lower('%");
			query.append(customerSearch.getCustCode());
			query.append("%') ");
			query.append(" and lower(c.cust_name) like lower('%");
			query.append(customerSearch.getCustName());
			query.append("%') ");
			query.append(" and lower(c.pt) like lower('%");
			query.append(customerSearch.getPt());
			query.append("%') ");

			getAllStatement = connection.prepareStatement(query.toString());

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setCustCode(rs.getString("cust_code"));
				customer.setCustName(rs.getString("cust_name"));
				customer.setPt(rs.getString("pt"));
				customer.setNpwp(rs.getString("npwp"));
				customer.setCustType(rs.getString("cust_type"));
				customer.setDefaultTax(rs.getInt("default_tax"));
				customer.setAccountNo(rs.getString("account_no"));
				customer.setBankId(rs.getInt("bank_id"));
				customer.setAccountName(rs.getString("account_name"));
				customer.setCurrencyId(rs.getInt("currency_id"));
				customer.setTop(rs.getInt("top"));
				customer.setCountry(rs.getString("country"));
				customer.setNote(rs.getString("note"));

				Bank bank = new Bank();
				bank.setId(rs.getInt("bank_id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				Currency currency = new Currency();
				currency.setId(rs.getInt("currency_id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrency(rs.getString("currency"));

				customer.setBank(bank);
				customer.setCurrency(currency);

				customers.add(customer);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return customers;
	}

	public int isSuppCodeExists(String suppCode) throws SQLException {
		int count = 0;
		try {
			isSuppCodeExistsStatement = connection.prepareStatement(isCustCodeExistsQuery);
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
			// insertStatement.setString(6, supplier.getSuppStatus());
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
			// updateStatement.setString(5, supplier.getSuppStatus());
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
				// supplier.setSuppStatus(rs.getString("supp_status"));
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
				// supplier.setSuppStatus(rs.getString("supp_status"));
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
			+ "WHERE SUBSTRING(p.supp_code, 1, 3) = ? " + "ORDER BY ordinal DESC LIMIT 1 ";

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
}
