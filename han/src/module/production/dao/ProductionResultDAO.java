package module.production.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.ProductionResult;

public class ProductionResultDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getProdResultByProductionCodeStatement;
	private PreparedStatement getLastCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	
	private String getAllQuery = "SELECT id, prod_result_code, production_code, prod_result_date, total_output, total_repair_klem, total_repair_protol, total_fine_a, total_fine_b, total_fine_result "
			+ "FROM prod_result WHERE deleted_date IS NULL";
	
	private String getLastCodeQuery = "SELECT prod_result_code FROM prod_result WHERE deleted_date IS NULL ORDER BY id DESC LIMIT 1";
	
	private String insertQuery = "INSERT INTO prod_result (prod_result_code, production_code, prod_result_date, "
			+ "total_output, total_repair_klem, total_repair_protol, total_fine_a, total_fine_b, total_fine_result, input_by, input_date) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "UPDATE prod_result SET prod_result_date =?, "
			+ "total_output=?, total_repair_klem=?, total_repair_protol=?, total_fine_a=?, total_fine_b=?, total_fine_result=?, edited_by=?, edited_date=?  "
			+ "WHERE prod_result_code = ? AND production_code =?";
	
	public ProductionResultDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public String getLastCode() throws SQLException{
		String lastCode = null;
		try {
			getLastCodeStatement = connection.prepareStatement(getLastCodeQuery);
			ResultSet rs = getLastCodeStatement.executeQuery();
			if(rs.next()) lastCode =  rs.getString("prod_result_code");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return lastCode;
	}
	
	public List<ProductionResult> getAll() throws SQLException {
		List<ProductionResult> productionResults = new ArrayList<ProductionResult>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionResult productionResult = new ProductionResult();
				productionResult.setId(rs.getInt("id"));
				productionResult.setProdResultCode(rs.getString("prod_result_code"));
				productionResult.setProductionCode(rs.getString("production_code"));
				productionResult.setProdResultDate(rs.getDate("prod_result_date"));
				productionResult.setTotalOutput(rs.getInt("total_output"));
				productionResult.setTotalRepairKlem(rs.getInt("total_repair_klem"));
				productionResult.setTotalRepairProtol(rs.getInt("total_repair_protol"));
				productionResult.setTotalFineA(rs.getInt("total_fine_a"));
				productionResult.setTotalFineB(rs.getInt("total_fine_b"));
				productionResult.setTotalFineResult(rs.getInt("total_fine_result"));
				productionResults.add(productionResult);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productionResults;
	}
	
	public ProductionResult getAllByProductionCode(String productionCode) throws SQLException {
		ProductionResult productionResult = null;
		try {
			StringBuffer sb  = new StringBuffer(getAllQuery);
			sb.append(" AND production_code = ?");
			
			getProdResultByProductionCodeStatement = connection.prepareStatement(sb.toString());
			getProdResultByProductionCodeStatement.setString(1, productionCode);
			
			ResultSet rs = getProdResultByProductionCodeStatement.executeQuery();
			if(rs.next()){
				productionResult = new ProductionResult();
				productionResult.setId(rs.getInt("id"));
				productionResult.setProdResultCode(rs.getString("prod_result_code"));
				productionResult.setProductionCode(rs.getString("production_code"));
				productionResult.setProdResultDate(rs.getDate("prod_result_date"));
				productionResult.setTotalOutput(rs.getInt("total_output"));
				productionResult.setTotalRepairKlem(rs.getInt("total_repair_klem"));
				productionResult.setTotalRepairProtol(rs.getInt("total_repair_protol"));
				productionResult.setTotalFineA(rs.getInt("total_fine_a"));
				productionResult.setTotalFineB(rs.getInt("total_fine_b"));
				productionResult.setTotalFineResult(rs.getInt("total_fine_result"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productionResult;
	}
	

	public void save(ProductionResult productionResult) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, productionResult.getProdResultCode());
			insertStatement.setString(2, productionResult.getProductionCode());
			insertStatement.setDate(3, new Date(productionResult.getProdResultDate().getTime()));
			insertStatement.setInt(4, productionResult.getTotalOutput());
			insertStatement.setInt(5, productionResult.getTotalRepairKlem());
			insertStatement.setInt(6, productionResult.getTotalRepairProtol());
			insertStatement.setInt(7, productionResult.getTotalFineA());
			insertStatement.setInt(8, productionResult.getTotalFineB());
			insertStatement.setInt(9, productionResult.getTotalFineResult());
			insertStatement.setString(10, "Michael");
			insertStatement.setDate(11, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void update(ProductionResult productionResult) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setDate(1, new Date(productionResult.getProdResultDate().getTime()));
			updateStatement.setInt(2, productionResult.getTotalOutput());
			updateStatement.setInt(3, productionResult.getTotalRepairKlem());
			updateStatement.setInt(4, productionResult.getTotalRepairProtol());
			updateStatement.setInt(5, productionResult.getTotalFineA());
			updateStatement.setInt(6, productionResult.getTotalFineB());
			updateStatement.setInt(7, productionResult.getTotalFineResult());
			updateStatement.setString(8, "Michael");
			updateStatement.setDate(9, new Date(new java.util.Date().getTime()));
			updateStatement.setString(10, productionResult.getProdResultCode());
			updateStatement.setString(11, productionResult.getProductionCode());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}

