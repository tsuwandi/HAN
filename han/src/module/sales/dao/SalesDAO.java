package module.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import module.customer.model.CustAddress;
import module.customer.model.Customer;
import module.pembelian.model.Product;
import module.product.model.Uom;
import module.sales.model.Sales;
import module.sn.bank.model.Bank;
import module.sn.country.model.Country;
import module.sn.currency.model.Currency;
import module.sn.supptype.model.SuppType;
import module.supplier.model.Supplier;
import module.util.DateUtil;

public class SalesDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement isCustCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getAllByCustCodeStatement;

	private String getAllQuery = "select s.id, s.customer_id, s.cust_addr_id, s.currency_id, s.freight_cost_currency_id, "
			+ "s.insurance_cost_currency_id, s.po_no, s.po_date, s.so_no, s.so_date, s.surcharge, s.discount, "
			+ "s.freight_cost, s.insurance_cost, s.vat, s.description, "
			+ "c.id as customer_id, c.cust_name as customer_name, c.cust_code as customer_code from sales s "
			+ "left join customer c on s.customer_id = c.id " + "where s.deleted_date is null "
			+ "and c.deleted_date is null";

	private String isCustCodeExistsQuery = "select count(*) as is_exists from customer where cust_code = ? and deleted_date is null ";

	private String insertQuery = "insert into sales (customer_id, cust_addr_id, currency_id, freight_cost_currency_id, insurance_cost_currency_id, "
			+ "po_no, po_date, so_no, so_date, surcharge, discount, freight_cost, insurance_cost, vat, description, "
			+ "input_by, input_date) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private String updateQuery = "update customer set cust_name=?, pt=?, npwp=?, "
			+ "cust_type=?, default_tax=?, account_no=?, bank_id=?, account_name=?, currency_id=?, top=?, country=?, note=?, "
			+ "edit_date=?, edited_by=? where cust_code=?";

	private String deleteQuery = "update customer set deleted_date=?, deleted_by=? where id=?";

	private String lastRecordQuery = "SELECT * FROM sales ORDER BY ID DESC LIMIT 1";

	private String selectCustomerByCodeQuery = "SELECT id, cust_name, cust_code FROM customer WHERE cust_code=? AND deleted_date is null";

	private String selectProductByCodeQuery = "SELECT id, product_code, product_name, product_uom_id, length, width, thickness FROM product WHERE product_code=? AND deleted_date is null";

	private String selectUomByProductUomQuery = "SELECT id, uom FROM uom WHERE id=?";

	private String selectCustomerAddressByCustomerCodeQuery = "SELECT id, cust_code, name, address FROM cust_addr WHERE cust_code=? AND deleted_date is null";

	private String getAllByCustCodeQuery = "select id, cust_code, cust_id, name, "
			+ "addr_type, address, zip_code, email, " + "city, phone, fax, province "
			+ "from cust_addr where cust_code = ? " + "and deleted_date is null";

	public SalesDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Sales> getAll() throws SQLException {
		List<Sales> sales = new ArrayList<Sales>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Sales sale = new Sales();
				sale.setId(rs.getInt("id"));
				sale.setCustomerId(rs.getInt("customer_id"));
				sale.setCustAddrId(rs.getInt("cust_addr_id"));
				sale.setCurrencyId(rs.getInt("currency_id"));
				sale.setFreightCostCurrencyId(rs.getInt("freight_cost_currency_id"));
				sale.setInsuranceCostCurrencyId(rs.getInt("insurance_cost_currency_id"));
				sale.setPoNo(rs.getString("po_no"));
				sale.setPoDate(rs.getDate("po_date"));
				sale.setSoNo(rs.getString("so_no"));
				sale.setSoDate(rs.getDate("so_date"));
				sale.setSurcharge(rs.getDouble("surcharge"));
				sale.setDiscount(rs.getDouble("discount"));
				sale.setFreightCost(rs.getDouble("freight_cost"));
				sale.setInsuranceCost(rs.getDouble("insurance_cost"));
				sale.setVat(rs.getDouble("vat"));
				sale.setDescription(rs.getString("description"));

				Customer customer = new Customer();
				customer.setId(rs.getInt("customer_id"));
				customer.setCustName(rs.getString("customer_name"));

				sale.setCustomer(customer);

				sales.add(sale);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return sales;
	}

	public List<Sales> getAllBySimpleSearch(String value) throws SQLException {
		List<Sales> sales = new ArrayList<Sales>();
		try {
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value).append("%").toString();
				String query = new StringBuilder().append(getAllQuery).append(" and")
						.append(" (lower(s.so_no) like lower('%s')").append(" or lower(s.so_date) like lower('%s')")
						.append(" or lower(c.cust_name) like lower('%s'))").toString();
				getAllStatement = connection.prepareStatement(String.format(query, keyword, keyword, keyword));
			} else {
				getAllStatement = connection.prepareStatement(getAllQuery);
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Sales sale = new Sales();
				sale.setId(rs.getInt("id"));
				sale.setCustomerId(rs.getInt("customer_id"));
				sale.setCustAddrId(rs.getInt("cust_addr_id"));
				sale.setCurrencyId(rs.getInt("currency_id"));
				sale.setFreightCostCurrencyId(rs.getInt("freight_cost_currency_id"));
				sale.setInsuranceCostCurrencyId(rs.getInt("insurance_cost_currency_id"));
				sale.setPoNo(rs.getString("po_no"));
				sale.setPoDate(rs.getDate("po_date"));
				sale.setSoNo(rs.getString("so_no"));
				sale.setSoDate(rs.getDate("so_date"));
				sale.setSurcharge(rs.getDouble("surcharge"));
				sale.setDiscount(rs.getDouble("discount"));
				sale.setFreightCost(rs.getDouble("freight_cost"));
				sale.setInsuranceCost(rs.getDouble("insurance_cost"));
				sale.setVat(rs.getDouble("vat"));
				sale.setDescription(rs.getString("description"));

				Customer customer = new Customer();
				customer.setId(rs.getInt("customer_id"));
				customer.setCustName(rs.getString("customer_name"));

				sale.setCustomer(customer);

				sales.add(sale);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return sales;
	}

	public List<Sales> getAllByAdvancedSearch(Sales salesSearch) throws SQLException {
		List<Sales> sales = new ArrayList<Sales>();
		try {
			StringBuilder query = new StringBuilder().append(getAllQuery);
			query.append(" and lower(s.so_no) like lower('%");
			query.append(salesSearch.getSoNo());
			query.append("%') ");
			query.append(" and lower(s.so_date) like lower('%");
			if (salesSearch.getSoDate() != null) {
				query.append(new SimpleDateFormat("yyyy-MM-dd").format(salesSearch.getSoDate()));
			} else {
				query.append("");
			}
			query.append("%') ");
			query.append(" and lower(c.cust_name) like lower('%");
			query.append(salesSearch.getCustomer().getCustName());
			query.append("%') ");

			getAllStatement = connection.prepareStatement(query.toString());

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Sales sale = new Sales();
				sale.setId(rs.getInt("id"));
				sale.setCustomerId(rs.getInt("customer_id"));
				sale.setCustAddrId(rs.getInt("cust_addr_id"));
				sale.setCurrencyId(rs.getInt("currency_id"));
				sale.setFreightCostCurrencyId(rs.getInt("freight_cost_currency_id"));
				sale.setInsuranceCostCurrencyId(rs.getInt("insurance_cost_currency_id"));
				sale.setPoNo(rs.getString("po_no"));
				sale.setPoDate(rs.getDate("po_date"));
				sale.setSoNo(rs.getString("so_no"));
				sale.setSoDate(rs.getDate("so_date"));
				sale.setSurcharge(rs.getDouble("surcharge"));
				sale.setDiscount(rs.getDouble("discount"));
				sale.setFreightCost(rs.getDouble("freight_cost"));
				sale.setInsuranceCost(rs.getDouble("insurance_cost"));
				sale.setVat(rs.getDouble("vat"));
				sale.setDescription(rs.getString("description"));

				Customer customer = new Customer();
				customer.setId(rs.getInt("customer_id"));
				customer.setCustName(rs.getString("customer_name"));

				sale.setCustomer(customer);

				sales.add(sale);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return sales;
	}

	public int isCustCodeExists(String custCode) throws SQLException {
		int count = 0;
		try {
			isCustCodeExistsStatement = connection.prepareStatement(isCustCodeExistsQuery);
			isCustCodeExistsStatement.setString(1, custCode);

			ResultSet rs = isCustCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(Sales sales) throws SQLException {
		try {

			insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, sales.getCustomerId());
			insertStatement.setInt(2, sales.getCustAddrId());
			insertStatement.setInt(3, sales.getFreightCostCurrencyId());
			insertStatement.setInt(4, sales.getFreightCostCurrencyId());
			insertStatement.setInt(5, sales.getInsuranceCostCurrencyId());
			insertStatement.setString(6, sales.getPoNo());
			insertStatement.setDate(7, new java.sql.Date(sales.getPoDate().getTime()));
			insertStatement.setString(8, sales.getSoNo());
			insertStatement.setDate(9, new java.sql.Date(sales.getSoDate().getTime()));
			insertStatement.setDouble(10, sales.getSurcharge());
			insertStatement.setDouble(11, sales.getDiscount());
			insertStatement.setDouble(12, sales.getFreightCost());
			insertStatement.setDouble(13, sales.getInsuranceCost());
			insertStatement.setDouble(14, sales.getVat());
			insertStatement.setString(15, sales.getDescription());
			insertStatement.setString(16, "Sandy");
			insertStatement.setDate(17, DateUtil.getCurrentDate());
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(Customer customer) throws SQLException {
		try {

			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, customer.getCustName());
			updateStatement.setString(2, customer.getPt());
			updateStatement.setString(3, customer.getNpwp());
			updateStatement.setString(4, customer.getCustType());
			updateStatement.setDouble(5, customer.getDefaultTax());
			updateStatement.setString(6, customer.getAccountNo());
			if (customer.getBankId() == 0) {
				updateStatement.setNull(7, java.sql.Types.INTEGER);
			} else {
				updateStatement.setInt(7, customer.getBankId());
			}
			updateStatement.setString(8, customer.getAccountName());
			if (customer.getCurrencyId() == 0) {
				updateStatement.setNull(9, java.sql.Types.INTEGER);
			} else {
				updateStatement.setInt(9, customer.getCurrencyId());
			}
			updateStatement.setInt(10, customer.getTop());
			updateStatement.setString(11, customer.getCountry());
			updateStatement.setString(12, customer.getNote());
			updateStatement.setDate(13, DateUtil.getCurrentDate());
			updateStatement.setString(14, "Sandy");
			updateStatement.setString(15, customer.getCustCode());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void delete(int id) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "Sandy");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public Customer getById(int id) throws SQLException {
		Customer customer = null;
		String query = new StringBuilder().append(getAllQuery).append(" and c.id=?").toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, id);
			ResultSet rs = getAllStatement.executeQuery();

			while (rs.next()) {
				customer = new Customer();
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
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return customer;
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

	public Integer getLatestIncrementSalesId() throws SQLException {
		Sales sales = null;
		String query = new StringBuilder().append(lastRecordQuery).toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			ResultSet rs = getAllStatement.executeQuery();

			while (rs.next()) {
				sales = new Sales();
				sales.setId(rs.getInt("id"));
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return sales.getId();
	}

	public Customer getCustomerByCode(String custCode) throws SQLException {
		Customer customer = null;
		String query = new StringBuilder().append(selectCustomerByCodeQuery).toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setString(1, custCode);
			ResultSet rs = getAllStatement.executeQuery();

			while (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setCustName(rs.getString("cust_name"));
				customer.setCustCode(rs.getString("cust_code"));
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return customer;
	}

	public List<CustAddress> getCustAddressByCustCode(int custCode) {
		List<CustAddress> listOfCustAddress = new ArrayList<CustAddress>();
		String query = new StringBuilder().append(selectCustomerAddressByCustomerCodeQuery).toString();

		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, custCode);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				CustAddress custAddress = new CustAddress();
				custAddress.setId(rs.getInt("id"));
				custAddress.setCustCode(rs.getString("cust_code"));
				custAddress.setName(rs.getString("name"));
				custAddress.setAddress(rs.getString("address"));

				listOfCustAddress.add(custAddress);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return listOfCustAddress;
	}

	public List<CustAddress> getAllByCustCode(String custCode) throws SQLException {
		List<CustAddress> custAddresses = new ArrayList<CustAddress>();

		try {
			getAllByCustCodeStatement = connection.prepareStatement(getAllByCustCodeQuery);

			getAllByCustCodeStatement.setString(1, custCode);

			ResultSet rs = getAllByCustCodeStatement.executeQuery();
			while (rs.next()) {
				CustAddress customerAddress = new CustAddress();
				customerAddress.setId(rs.getInt("id"));
				customerAddress.setCustCode(rs.getString("cust_code"));
				customerAddress.setCustId(rs.getInt("cust_id"));
				customerAddress.setName(rs.getString("name"));
				customerAddress.setAddressType(rs.getString("addr_type"));
				customerAddress.setAddress(rs.getString("address"));
				customerAddress.setZipCode(rs.getString("zip_code"));
				customerAddress.setProvince(rs.getString("province"));
				customerAddress.setCity(rs.getString("city"));
				customerAddress.setPhone(rs.getString("phone"));
				customerAddress.setFax(rs.getString("fax"));
				customerAddress.setEmail(rs.getString("email"));

				custAddresses.add(customerAddress);

			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return custAddresses;
	}

	public Product getProductByCode(String productCode) throws SQLException {
		Product product = null;
		String query = new StringBuilder().append(selectProductByCodeQuery).toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setString(1, productCode);
			ResultSet rs = getAllStatement.executeQuery();

			while (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductCode(rs.getString("product_code"));
				product.setProductUomId(rs.getInt("product_uom_id"));
				product.setWidth(rs.getDouble("width"));
				product.setLength(rs.getDouble("length"));
				product.setThickness(rs.getDouble("thickness"));
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return product;
	}

	public Uom getUomByProductUomId(int productUomId) throws SQLException {
		Uom uom = null;
		String query = new StringBuilder().append(selectUomByProductUomQuery).toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, productUomId);
			ResultSet rs = getAllStatement.executeQuery();

			while (rs.next()) {
				uom = new Uom();
				uom.setId(rs.getInt("id"));
				uom.setUom(rs.getString("uom"));
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return uom;
	}
}
