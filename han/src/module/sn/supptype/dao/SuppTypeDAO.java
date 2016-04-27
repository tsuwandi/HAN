package module.sn.supptype.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import module.sn.supptype.model.SuppType;
import module.sn.vehicletype.model.VehicleType;

public class SuppTypeDAO {
	private Connection connection;
	//private PreparedStatement insertStatement;
	//private PreparedStatement updateStatement;
	//private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;

	//private String insertQuery = "insert into bank (bank_abbr, bank, input_date, input_by) values (?,?,?,?)";
	//private String updateQuery = "update bank set bank_abbr=?, bank=?, edit_date=?, edited_by=? where id=?";
	//private String deleteQuery = "update bank set deleted_date=?, deleted_by=? where id=?";
	private String getAllQuery = "select id, supp_type from supp_type order by supp_type";

	public SuppTypeDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

//	public List<SuppType> getAll() throws SQLException {
//		Connection con = null;
//		List<SuppType> suppTypes = new ArrayList<SuppType>();
//
//		try {
//			con = dataSource.getConnection();
//			getAllStatement = con.prepareStatement(getAllQuery);
//
//			ResultSet rs = getAllStatement.executeQuery();
//			while (rs.next()) {
//				SuppType suppType = new SuppType();
//				suppType.setId(rs.getInt("id"));
//				suppType.setSuppType(rs.getString("supp_type"));
//
//				suppTypes.add(suppType);
//			}
//
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new SQLException(ex.getMessage());
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//			}
//		}
//
//		return suppTypes;
//	}
	
	public HashMap<String, Integer> getAll() throws SQLException {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				SuppType suppType = new SuppType();
				suppType.setId(rs.getInt("id"));
				suppType.setSuppType(rs.getString("supp_type"));
				map.put(suppType.getSuppType(), suppType.getId());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return map;
	}
}
