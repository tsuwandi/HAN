package module.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.customer.model.Customer;
import module.util.DateUtil;

public class CustomerDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement isCustCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = "select c.id, c.cust_code, c.cust_name, c.pt, c.npwp, "
			+ "c.cust_type, c.default_tax,"
			+ "c.top, c.country, c.note from customer c "
			+ "left join cust_addr ca on c.cust_code = ca.cust_code "
			+ "where c.deleted_date is null ";

	private String isCustCodeExistsQuery = "select count(*) as is_exists from customer where cust_code = ? and deleted_date is null ";

	private String insertQuery = "insert into customer (cust_code, cust_name, pt, npwp, "
			+ "cust_type, default_tax, top, country, note, "
			+ "input_date, input_by) values (?,?,?,?,?,?,?,?,?,?,?)";

	private String updateQuery = "update customer set cust_name=?, pt=?, npwp=?, "
			+ "cust_type=?, default_tax=?, top=?, country=?, note=?, "
			+ "edit_date=?, edited_by=? where cust_code=?";

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
				customer.setTop(rs.getInt("top"));
				customer.setCountry(rs.getString("country"));
				customer.setNote(rs.getString("note"));

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
				customer.setTop(rs.getInt("top"));
				customer.setCountry(rs.getString("country"));
				customer.setNote(rs.getString("note"));

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
				customer.setTop(rs.getInt("top"));
				customer.setCountry(rs.getString("country"));
				customer.setNote(rs.getString("note"));

				customers.add(customer);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return customers;
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

	public void save(Customer customer) throws SQLException {
		try {
			
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, customer.getCustCode());
			insertStatement.setString(2, customer.getCustName());
			insertStatement.setString(3, customer.getPt());
			insertStatement.setString(4, customer.getNpwp());
			insertStatement.setString(5, customer.getCustType());
			insertStatement.setDouble(6, customer.getDefaultTax());
			insertStatement.setInt(7, customer.getTop());
			insertStatement.setString(8, customer.getCountry());
			insertStatement.setString(9, customer.getNote());
			insertStatement.setDate(10, DateUtil.getCurrentDate());
			insertStatement.setString(11, "Sandy");
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
			updateStatement.setInt(6, customer.getTop());
			updateStatement.setString(7, customer.getCountry());
			updateStatement.setString(8, customer.getNote());
			updateStatement.setDate(9, DateUtil.getCurrentDate());
			updateStatement.setString(10, "Sandy");
			updateStatement.setString(11, customer.getCustCode());
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
				customer.setTop(rs.getInt("top"));
				customer.setCountry(rs.getString("country"));
				customer.setNote(rs.getString("note"));
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return customer;
	}
}
