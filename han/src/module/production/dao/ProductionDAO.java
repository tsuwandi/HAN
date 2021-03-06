package module.production.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.pembelian.model.PalletCard;
import module.pembelian.model.Received;
import module.production.model.Production;
import module.production.model.ProductionResult;
import module.production.model.ProductionResultProduct;
import module.util.DateUtil;

public class ProductionDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getLastCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllQuery = "SELECT a.id, production_code, a.group_shift_code, d.description AS group_shift_description, "
			+ "a.line_code, b.description AS line_description, a.shift_code, c.shift_name, a.type, "
			+ "production_date, information, total_pallet_card, total_log, total_volume, status, a.production_type_code, e.production_type AS production_type_description "
			+ "FROM production a INNER JOIN line b ON a.line_code = b.line_code "
			+ "INNER JOIN shift c ON a.shift_code = c.shift_code INNER JOIN group_shift d ON a.group_shift_code = d.group_shift_code "
			+ "INNER JOIN production_type e ON a.production_type_code = e.production_type_code WHERE a.deleted_date IS NULL";
	
	private String getLastCodeQuery = "SELECT production_code FROM production WHERE deleted_date IS NULL ORDER BY id DESC LIMIT 1";
	
	private String insertQuery = "INSERT INTO production (production_code, group_shift_code, line_code, shift_code, "
			+ "production_date, information, total_pallet_card, total_log, total_volume, status , input_by, input_date, production_type_code, type) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "UPDATE production SET group_shift_code =?, "
			+ "line_code=?, shift_code=?, production_date=?, information=?, total_pallet_card=?, total_log=?, total_volume=?, status=?, edited_by=?, edited_date=?, production_type_code=?  "
			+ "WHERE production_code =?";
	
	private String deleteQuery = "UPDATE production SET deleted_date = ? , delete_by=? WHERE production_code=?";
	
	public ProductionDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public String getLastCode() throws SQLException{
		String lastCode = null;
		try {
			getLastCodeStatement = connection.prepareStatement(getLastCodeQuery);
			ResultSet rs = getLastCodeStatement.executeQuery();
			if(rs.next()) lastCode =  rs.getString("production_code");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return lastCode;
	}
	
	public List<Production> getAll(String sql) throws SQLException {
		List<Production> productions = new ArrayList<Production>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery + sql);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Production production = new Production();
				production.setId(rs.getInt("id"));
				production.setProductionCode(rs.getString("production_code"));
				production.setGroupShiftCode(rs.getString("group_shift_code"));
				production.setGroupShiftDescription(rs.getString("group_shift_description"));
				production.setLineCode(rs.getString("line_code"));
				production.setLineDescription(rs.getString("line_description"));
				production.setShiftCode(rs.getString("shift_code"));
				production.setShiftName(rs.getString("shift_name"));
				production.setProductionDate(rs.getDate("production_date"));
				production.setInformation(rs.getString("information"));
				production.setTotalPalletCard(rs.getInt("total_pallet_card"));
				production.setTotalLog(rs.getInt("total_log"));
				production.setTotalVolume(rs.getDouble("total_volume"));
				production.setStatus(rs.getString("status"));
				production.setType(rs.getString("type"));
				production.setProductionTypeCode(rs.getString("production_type_code"));
				production.setProductionTypeDescription(rs.getString("production_type_description"));
				productions.add(production);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productions;
	}
	
	public List<Production> getSearchAll(String sql) throws SQLException {
		List<Production> productions = new ArrayList<Production>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+sql);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Production production = new Production();
				production.setId(rs.getInt("id"));
				production.setProductionCode(rs.getString("production_code"));
				production.setGroupShiftCode(rs.getString("group_shift_code"));
				production.setGroupShiftDescription(rs.getString("group_shift_description"));
				production.setLineCode(rs.getString("line_code"));
				production.setLineDescription(rs.getString("line_description"));
				production.setShiftCode(rs.getString("shift_code"));
				production.setShiftName(rs.getString("shift_name"));
				production.setProductionDate(rs.getDate("production_date"));
				production.setInformation(rs.getString("information"));
				production.setTotalPalletCard(rs.getInt("total_pallet_card"));
				production.setTotalLog(rs.getInt("total_log"));
				production.setTotalVolume(rs.getDouble("total_volume"));
				production.setStatus(rs.getString("status"));
				production.setType(rs.getString("type"));
				production.setProductionTypeCode(rs.getString("production_type_code"));
				production.setProductionTypeDescription(rs.getString("production_type_description"));
				productions.add(production);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productions;
	}


	public void save(Production production) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, production.getProductionCode());
			insertStatement.setString(2, production.getGroupShiftCode());
			insertStatement.setString(3, production.getLineCode());
			insertStatement.setString(4, production.getShiftCode());
			insertStatement.setDate(5, new Date(production.getProductionDate().getTime()));
			insertStatement.setString(6, production.getInformation());
			insertStatement.setInt(7, production.getTotalPalletCard());
			insertStatement.setInt(8, production.getTotalLog());
			insertStatement.setDouble(9, production.getTotalVolume());
			insertStatement.setString(10, production.getStatus());
			insertStatement.setString(11, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(12, new Date(new java.util.Date().getTime()));
			insertStatement.setString(13, production.getProductionTypeCode());
			insertStatement.setString(14, production.getType());
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(Production production) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setString(3, production.getProductionCode());
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void update(Production production) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, production.getGroupShiftCode());
			updateStatement.setString(2, production.getLineCode());
			updateStatement.setString(3, production.getShiftCode());
			updateStatement.setDate(4, new Date(production.getProductionDate().getTime()));
			updateStatement.setString(5, production.getInformation());
			updateStatement.setInt(6, production.getTotalPalletCard());
			updateStatement.setInt(7, production.getTotalLog());
			updateStatement.setDouble(8, production.getTotalVolume());
			updateStatement.setString(9, production.getStatus());
			updateStatement.setString(10, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(11, new Date(new java.util.Date().getTime()));
			updateStatement.setString(12, production.getProductionTypeCode());
			updateStatement.setString(13, production.getProductionCode());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public List<Production> advancedSearchProduction(String sql, List<Object> objs) throws SQLException{
		List<Production> productions = new ArrayList<Production>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery + sql);

			int i = 1;
			for (int j = 0; j < objs.size(); j++) {
				Object obj = objs.get(j);
				if (obj instanceof String) {
					if (obj != null) {
						getAllStatement.setString(i, (String) "%" + obj + "%");
						i++;
					}
				} else {
					if (obj != null) {
						getAllStatement.setDate(i, new Date(((java.util.Date) obj).getTime()));
						i++;
					}
				}
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Production production = new Production();
				production.setId(rs.getInt("id"));
				production.setProductionCode(rs.getString("production_code"));
				production.setGroupShiftCode(rs.getString("group_shift_code"));
				production.setGroupShiftDescription(rs.getString("group_shift_description"));
				production.setLineCode(rs.getString("line_code"));
				production.setLineDescription(rs.getString("line_description"));
				production.setShiftCode(rs.getString("shift_code"));
				production.setShiftName(rs.getString("shift_name"));
				production.setProductionDate(rs.getDate("production_date"));
				production.setInformation(rs.getString("information"));
				production.setTotalPalletCard(rs.getInt("total_pallet_card"));
				production.setTotalLog(rs.getInt("total_log"));
				production.setTotalVolume(rs.getDouble("total_volume"));
				production.setStatus(rs.getString("status"));
				production.setType(rs.getString("type"));
				production.setProductionTypeCode(rs.getString("production_type_code"));
				production.setProductionTypeDescription(rs.getString("production_type_description"));
				productions.add(production);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return productions;
	}
}
