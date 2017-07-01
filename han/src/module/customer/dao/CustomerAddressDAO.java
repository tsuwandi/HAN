package module.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.city.model.City;
import module.sn.province.model.Province;
import module.supplier.model.SuppAddress;
import module.supplier.model.SuppCp;
import module.util.DateUtil;

public class CustomerAddressDAO {
	private Connection connection;

	private PreparedStatement getAllBySuppCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllBySuppCodeQuery = "select sa.id, sa.supp_code, sa.address_type, sa.address, sa.zip_code,"
			+ "sa.city, sa.phone, sa.fax, sa.province_id, p.id, p.province, sc.id as supp_cp_id, sc.supp_code as supp_cp_code, sc.supp_address_id, sc.name, sc.email "
			+ "from supp_address sa "
			+ "left join province p on p.id = sa.province_id "
			+ "inner join supp_cp sc on sa.id = sc.supp_address_id where sa.supp_code = ? "
			+ "and sa.deleted_date is null and p.deleted_date is null";

	private String insertQuery = "insert into supp_address (supp_code, address_type, address, zip_code, "
			+ "province_id, city, phone, fax, input_date, input_by) values (?,?,?,?,?,?,?,?,?,?)";

	private String updateQuery = "update supp_address set address_type=?, address=?, zip_code=?, "
			+ "province_id=?, city=?, phone=?, fax=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update supp_address set deleted_date=?, deleted_by=? ";

	public CustomerAddressDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<SuppAddress> getAllBySuppCode(String suppCode) throws SQLException {
		List<SuppAddress> suppAddresses = new ArrayList<SuppAddress>();

		try {
			getAllBySuppCodeStatement = connection.prepareStatement(getAllBySuppCodeQuery);

			getAllBySuppCodeStatement.setString(1, suppCode);

			ResultSet rs = getAllBySuppCodeStatement.executeQuery();
			while (rs.next()) {
				SuppAddress supplierAddress = new SuppAddress();
				supplierAddress.setId(rs.getInt("id"));
				supplierAddress.setSuppCode(rs.getString("supp_code"));
				supplierAddress.setAddressType(rs.getString("address_type"));
				supplierAddress.setAddress(rs.getString("address"));
				supplierAddress.setZipCode(rs.getString("zip_code"));
				supplierAddress.setProvinceId(rs.getInt("province_id"));
				supplierAddress.setCity(rs.getString("city"));
				supplierAddress.setPhone(rs.getString("phone"));
				supplierAddress.setFax(rs.getString("fax"));

				Province province = new Province();
				province.setId(rs.getInt("province_id"));
				province.setProvince(rs.getString("province"));

				supplierAddress.setProvince(province);

				SuppCp suppCp = new SuppCp();
				suppCp.setId(rs.getInt("supp_cp_id"));
				suppCp.setSuppCode(rs.getString("supp_cp_code"));
				suppCp.setSuppAddressId(rs.getInt("supp_address_id"));
				suppCp.setName(rs.getString("name"));
				suppCp.setEmail(rs.getString("email"));
				supplierAddress.setSuppCp(suppCp);

				suppAddresses.add(supplierAddress);

			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return suppAddresses;
	}

	public SuppAddress save(SuppAddress suppAddress) throws SQLException {
		ResultSet generatedKeys = null;

		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, suppAddress.getSuppCode());
			insertStatement.setString(2, suppAddress.getAddressType());
			insertStatement.setString(3, suppAddress.getAddress());
			insertStatement.setString(4, suppAddress.getZipCode());
			insertStatement.setInt(5, suppAddress.getProvinceId());
			insertStatement.setString(6, suppAddress.getCity());
			insertStatement.setString(7, suppAddress.getPhone());
			insertStatement.setString(8, suppAddress.getFax());
			insertStatement.setDate(9, DateUtil.getCurrentDate());
			insertStatement.setString(10, "timotius");
			insertStatement.executeUpdate();

			generatedKeys = insertStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				suppAddress.setId(generatedKeys.getInt(1));
				generatedKeys.close();
			}
			return suppAddress;

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(SuppAddress suppAddress) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, suppAddress.getAddressType());
			updateStatement.setString(2, suppAddress.getAddress());
			updateStatement.setString(3, suppAddress.getZipCode());
			updateStatement.setInt(4, suppAddress.getProvinceId());
			updateStatement.setString(5, suppAddress.getCity());
			updateStatement.setString(6, suppAddress.getPhone());
			updateStatement.setString(7, suppAddress.getFax());
			updateStatement.setDate(8, DateUtil.getCurrentDate());
			updateStatement.setString(9, "timotius");
			updateStatement.setInt(10, suppAddress.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(String suppCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where supp_code = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, suppCode);
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
