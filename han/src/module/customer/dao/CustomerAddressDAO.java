package module.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.customer.model.CustAddress;
import module.util.DateUtil;

public class CustomerAddressDAO {
	private Connection connection;

	private PreparedStatement getAllByCustCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByCustCodeQuery = "select id, cust_code, cust_id, name, "
			+ "addr_type, address, zip_code, email, "
			+ "city, phone, fax, province "
			+ "from cust_addr where cust_code = ?"
			+ "and deleted_date is null";

	private String insertQuery = "insert into cust_addr (cust_code, cust_id, name, addr_type, phone, fax, address, zip_code, email, "
			+ "province, city, input_date, input_by) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private String updateQuery = "update cust_addr set name=?, addr_type=?, phone=?, "
			+ "fax=?, address=?, zip_code=?, email=?, province=?, city=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update cust_addr set deleted_date=?, deleted_by=? ";

	public CustomerAddressDAO(Connection connection) throws SQLException {
		this.connection = connection;
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

	public CustAddress save(CustAddress custAddress) throws SQLException {
		ResultSet generatedKeys = null;

		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, custAddress.getCustCode());
			insertStatement.setInt(2, custAddress.getCustId());
			insertStatement.setString(3, custAddress.getName());
			insertStatement.setString(4, custAddress.getAddressType());
			insertStatement.setString(5, custAddress.getPhone());
			insertStatement.setString(6, custAddress.getFax());
			insertStatement.setString(7, custAddress.getAddress());
			insertStatement.setString(8, custAddress.getZipCode());
			insertStatement.setString(9, custAddress.getEmail());
			insertStatement.setString(10, custAddress.getProvince());
			insertStatement.setString(11, custAddress.getCity());
			insertStatement.setDate(12, DateUtil.getCurrentDate());
			insertStatement.setString(13, "Sandy");
			insertStatement.executeUpdate();

			generatedKeys = insertStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				custAddress.setId(generatedKeys.getInt(1));
				generatedKeys.close();
			}
			return custAddress;

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(CustAddress custAddress) throws SQLException {
		try {
			
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, custAddress.getName());
			updateStatement.setString(2, custAddress.getAddressType());
			updateStatement.setString(3, custAddress.getPhone());
			updateStatement.setString(4, custAddress.getFax());
			updateStatement.setString(5, custAddress.getAddress());
			updateStatement.setString(6, custAddress.getZipCode());
			updateStatement.setString(7, custAddress.getEmail());
			updateStatement.setString(8, custAddress.getProvince());
			updateStatement.setString(9, custAddress.getCity());
			updateStatement.setDate(10, DateUtil.getCurrentDate());
			updateStatement.setString(11, "Sandy");
			updateStatement.setInt(12, custAddress.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(String custCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where cust_code = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "Sandy");
			deleteStatement.setString(3, custCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where id = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
}
