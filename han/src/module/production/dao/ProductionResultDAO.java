package module.production.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.Production;
import module.production.model.ProductionResult;
import module.productionpk.model.ProdPKResult;
import module.util.DateUtil;

public class ProductionResultDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getProdResultByProductionCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement getLastIDStatement;
	
	private String getAllQuery = "SELECT id, prod_code, pressed_no, start_time, total_fine_a, total_fine_b, total_protol, total_klem "
			+ "FROM prod_result WHERE deleted_date IS NULL";
	private String getLastIDQuery = "SELECT id FROM prod_result ORDER BY id DESC LIMIT 1";
	private String insertQuery = "INSERT INTO prod_result (prod_code,pressed_no, start_time, total_fine_a, total_fine_b, total_protol, total_klem , input_by, input_date,id) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
	private String deleteQuery = "UPDATE prod_result SET deleted_date = ? , delete_by=? WHERE prod_code = ? AND id=?";
	private String updateQuery = "UPDATE prod_result SET pressed_no =?, "
			+ "start_time=?, total_fine_a=?, total_fine_b=?, total_protol=?,total_klem=?, edited_by=?, edited_date=?  "
			+ "WHERE prod_code =? AND id=?";
	
	public ProductionResultDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public int getLastID() throws SQLException{
		int id = 0;
		try {
			getLastIDStatement = connection.prepareStatement(getLastIDQuery);
			ResultSet rs = getLastIDStatement.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return id;
	}
	
	public List<ProductionResult> getAllByCode(String prodCode) throws SQLException {
		List<ProductionResult> prodPKResults = new ArrayList<ProductionResult>();
		try {
			StringBuffer sb  = new StringBuffer(getAllQuery);
			sb.append(" AND prod_code = ?");
			
			getProdResultByProductionCodeStatement = connection.prepareStatement(sb.toString());
			getProdResultByProductionCodeStatement.setString(1, prodCode);
			

			ResultSet rs = getProdResultByProductionCodeStatement.executeQuery();
			while (rs.next()) {
				ProductionResult prodPKResult = new ProductionResult();
				prodPKResult.setId(rs.getInt("id"));
				prodPKResult.setProdCode(rs.getString("prod_code"));
				prodPKResult.setPressedNo(rs.getInt("pressed_no"));
				prodPKResult.setStartTime(rs.getString("start_time"));
				prodPKResult.setTotalFineA(rs.getDouble("total_fine_a"));
				prodPKResult.setTotalFineB(rs.getDouble("total_fine_b"));
				prodPKResult.setTotalProtol(rs.getDouble("total_protol"));
				prodPKResult.setTotalKlem(rs.getDouble("total_klem"));
				prodPKResults.add(prodPKResult);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodPKResults;
	}
	
	public List<ProductionResult> getAll() throws SQLException {
		List<ProductionResult> prodPKResults = new ArrayList<ProductionResult>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionResult prodPKResult = new ProductionResult();
				prodPKResult.setId(rs.getInt("id"));
				prodPKResult.setProdCode(rs.getString("prod_code"));
				prodPKResult.setPressedNo(rs.getInt("pressed_no"));
				prodPKResult.setStartTime(rs.getString("start_time"));
				prodPKResult.setTotalFineA(rs.getDouble("total_fine_a"));
				prodPKResult.setTotalFineB(rs.getDouble("total_fine_b"));
				prodPKResult.setTotalProtol(rs.getDouble("total_protol"));
				prodPKResult.setTotalKlem(rs.getDouble("total_klem"));
				prodPKResults.add(prodPKResult);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodPKResults;
	}
	
	public void save(ProductionResult productionResult) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, productionResult.getProdCode());
			insertStatement.setInt(2, productionResult.getPressedNo());
			insertStatement.setString(3, productionResult.getStartTime());
			insertStatement.setDouble(4, productionResult.getTotalFineA());
			insertStatement.setDouble(5, productionResult.getTotalFineB());
			insertStatement.setDouble(6, productionResult.getTotalProtol());
			insertStatement.setDouble(7, productionResult.getTotalKlem());
			insertStatement.setString(8, "Michael");
			insertStatement.setDate(9, new Date(new java.util.Date().getTime()));
			insertStatement.setInt(10, productionResult.getId());
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void update(ProductionResult productionResult) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, productionResult.getPressedNo());
			updateStatement.setString(2, productionResult.getStartTime());
			updateStatement.setDouble(3, productionResult.getTotalFineA());
			updateStatement.setDouble(4, productionResult.getTotalFineB());
			updateStatement.setDouble(5, productionResult.getTotalProtol());
			updateStatement.setDouble(6, productionResult.getTotalKlem());
			updateStatement.setString(7, "Michael");
			updateStatement.setDate(8, new Date(new java.util.Date().getTime()));
			updateStatement.setString(9, productionResult.getProdCode());
			updateStatement.setInt(10, productionResult.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(ProductionResult productionResult) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			deleteStatement.setString(2, "Michael");
			deleteStatement.setString(3, productionResult.getProdCode());
			deleteStatement.setInt(4, productionResult.getId());
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}

	public List<ProductionResult> getAllProductionResultForDailyClosing() throws SQLException {
		
		List<ProductionResult> prodPKResults = new ArrayList<ProductionResult>();
		String query = new StringBuilder().append(getAllQuery)
				.append(" AND confirm_date is NOT NULL")
				.append(" AND input_date <= CURDATE()").toString();
		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionResult prodPKResult = new ProductionResult();
				prodPKResult.setId(rs.getInt("id"));
				prodPKResult.setProdCode(rs.getString("prod_code"));
				prodPKResult.setPressedNo(rs.getInt("pressed_no"));
				prodPKResult.setStartTime(rs.getString("start_time"));
				prodPKResult.setTotalFineA(rs.getDouble("total_fine_a"));
				prodPKResult.setTotalFineB(rs.getDouble("total_fine_b"));
				prodPKResult.setTotalProtol(rs.getDouble("total_protol"));
				prodPKResult.setTotalKlem(rs.getDouble("total_klem"));
				prodPKResults.add(prodPKResult);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodPKResults;
		
		
	}
	
	private PreparedStatement updateDailyClosingStatement;
	private String updateDailyClosingQuery = "update prod_result set confirm_date=?, status=?, "
			+ "edit_date=?, edited_by=? where prod_code=? ";
	
	public void updateDailyClosing(String code, String status) throws SQLException {
		try {
			updateDailyClosingStatement = connection.prepareStatement(updateDailyClosingQuery);
			
			updateDailyClosingStatement.setDate(1, DateUtil.getCurrentDate());
			updateDailyClosingStatement.setString(2, status);
			updateDailyClosingStatement.setDate(3, DateUtil.getCurrentDate());
			updateDailyClosingStatement.setString(4, "timotius");
			updateDailyClosingStatement.setString(5, code);
			updateDailyClosingStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
