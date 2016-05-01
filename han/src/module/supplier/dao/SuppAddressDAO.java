package module.supplier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.city.model.City;
import module.sn.province.model.Province;
import module.supplier.model.SuppAddress;
import module.util.DateUtil;

public class SuppAddressDAO {
	private Connection connection;

	private PreparedStatement getAllBySuppCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllBySuppCodeQuery = "select sa.id, sa.supp_code, sa.address_type, sa.address, sa.zip_code,"
			+ "sa.city_id, sa.phone, sa.fax, c.id, c.city, c.province_id, p.id, p.province " + "from supp_address sa "
			+ "left join city c on sa.city_id = c.id "
			+ "left join province p on p.id = c.province_id where sa.supp_code = ? "
			+ "and sa.deleted_date is null and c.deleted_date is null and p.deleted_date is null";

	private String insertQuery = "insert into supp_address (supp_code, address_type, address, zip_code, "
			+ "city_id, phone, fax, input_date, input_by) values (?,?,?,?,?,?,?,?,?)";

	private String updateQuery = "update supp_address set address_type=?, address=?, zip_code=?, "
			+ "city_id=?, phone=?, fax=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update supp_address set deleted_date=?, deleted_by=? ";

	public SuppAddressDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<SuppAddress> getAllBySuppCode(String suppCode) throws SQLException {
		List<SuppAddress> suppAddresses = new ArrayList<SuppAddress>();

		try {
			getAllBySuppCodeStatement = connection.prepareStatement(getAllBySuppCodeQuery);
			getAllBySuppCodeStatement.setString(1, suppCode);

			ResultSet rs = getAllBySuppCodeStatement.executeQuery();
			while (rs.next()) {
				SuppAddress supplier = new SuppAddress();
				supplier.setId(rs.getInt("id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setAddressType(rs.getString("address_type"));
				supplier.setAddress(rs.getString("address"));
				supplier.setZipCode(rs.getString("zip_code"));
				supplier.setCityId(rs.getInt("city_id"));
				supplier.setPhone(rs.getString("phone"));
				supplier.setFax(rs.getString("fax"));

				Province province = new Province();
				province.setId(rs.getInt("id"));
				province.setProvince(rs.getString("province"));

				City city = new City();
				city.setId(rs.getInt("id"));
				city.setCity(rs.getString("city"));
				city.setProvinceId(rs.getInt("province_id"));
				city.setProvince(province);

				supplier.setCity(city);

				suppAddresses.add(supplier);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return suppAddresses;
	}

	public void save(SuppAddress suppAddress) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, suppAddress.getSuppCode());
			insertStatement.setString(2, suppAddress.getAddressType());
			insertStatement.setString(3, suppAddress.getAddress());
			insertStatement.setString(4, suppAddress.getZipCode());
			if (suppAddress.getCityId() == 0) {
				insertStatement.setNull(5, java.sql.Types.INTEGER);
			} else {
				insertStatement.setInt(5, suppAddress.getCityId());
			}
			insertStatement.setString(6, suppAddress.getPhone());
			insertStatement.setString(7, suppAddress.getFax());
			insertStatement.setDate(8, DateUtil.getCurrentDate());
			insertStatement.setString(9, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(SuppAddress suppAddress) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, suppAddress.getAddressType());
			updateStatement.setString(2, suppAddress.getAddress());
			updateStatement.setString(3, suppAddress.getZipCode());
			if (suppAddress.getCityId() == 0) {
				updateStatement.setNull(4, java.sql.Types.INTEGER);
			} else {
				updateStatement.setInt(4, suppAddress.getCityId());
			}
			updateStatement.setString(5, suppAddress.getPhone());
			updateStatement.setString(6, suppAddress.getFax());
			updateStatement.setDate(7, DateUtil.getCurrentDate());
			updateStatement.setString(8, "timotius");
			updateStatement.setInt(9, suppAddress.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
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
			ex.printStackTrace();
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
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
}
