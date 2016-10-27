package module.production.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.ProductionResultProduct;

public class ProductionResultDetailDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getProdResultByProductionCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllQuery = "SELECT id, prod_result_code, pressed_no, start_time, repair_klem_a, repair_klem_b, "
			+ "repair_protol_a, repair_protol_b, fine_a, fine_b, total FROM prod_result_dtl WHERE deleted_date IS NULL";
	
	private String insertQuery = "INSERT INTO prod_result_dtl (prod_result_code, pressed_no, start_time, repair_klem_a, repair_klem_b, "
			+ "repair_protol_a, repair_protol_b, fine_a, fine_b, total , input_by, input_date) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private String deleteQuery = "DELETE FROM prod_result_dtl WHERE prod_result_code = ?";
	
	public ProductionResultDetailDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	
	public List<ProductionResultProduct> getAll() throws SQLException {
		List<ProductionResultProduct> productionResultDetails = new ArrayList<ProductionResultProduct>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionResultProduct productionResultDetail = new ProductionResultProduct();
				productionResultDetail.setId(rs.getInt("id"));
				productionResultDetail.setProdResultCode(rs.getString("prod_result_code"));
				productionResultDetail.setPressedNo(rs.getInt("pressed_no"));
				productionResultDetail.setStartTime(rs.getString("start_time"));
				productionResultDetail.setRepairKlemA(rs.getInt("repair_klem_a"));
				productionResultDetail.setRepairKlemB(rs.getInt("repair_klem_b"));
				productionResultDetail.setRepairProtolA(rs.getInt("repair_protol_a"));
				productionResultDetail.setRepairProtolB(rs.getInt("repair_protol_b"));
				productionResultDetail.setFineA(rs.getInt("fine_a"));
				productionResultDetail.setFineB(rs.getInt("fine_b"));
				productionResultDetail.setTotal(rs.getInt("total"));
				productionResultDetails.add(productionResultDetail);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productionResultDetails;
	}
	
	public List<ProductionResultProduct> getAllByProdResultCode(String productionCode) throws SQLException {
		List<ProductionResultProduct> productionResultDetails = new ArrayList<ProductionResultProduct>();

		try {
			StringBuffer sb  = new StringBuffer(getAllQuery);
			sb.append(" AND prod_result_code = ?");
			
			getProdResultByProductionCodeStatement = connection.prepareStatement(sb.toString());
			getProdResultByProductionCodeStatement.setString(1, productionCode);
			
			ResultSet rs = getProdResultByProductionCodeStatement.executeQuery();
			while (rs.next()) {
				ProductionResultProduct productionResultDetail = new ProductionResultProduct();
				productionResultDetail.setId(rs.getInt("id"));
				productionResultDetail.setProdResultCode(rs.getString("prod_result_code"));
				productionResultDetail.setPressedNo(rs.getInt("pressed_no"));
				productionResultDetail.setStartTime(rs.getString("start_time"));
				productionResultDetail.setRepairKlemA(rs.getInt("repair_klem_a"));
				productionResultDetail.setRepairKlemB(rs.getInt("repair_klem_b"));
				productionResultDetail.setRepairProtolA(rs.getInt("repair_protol_a"));
				productionResultDetail.setRepairProtolB(rs.getInt("repair_protol_b"));
				productionResultDetail.setFineA(rs.getInt("fine_a"));
				productionResultDetail.setFineB(rs.getInt("fine_b"));
				productionResultDetail.setTotal(rs.getInt("total"));
				productionResultDetails.add(productionResultDetail);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productionResultDetails;
	}
	
	
	/*id, prod_result_code, pressed_no, start_time, repair_klem_a, repair_klem_b, "
	+ "repair_protol_a, repair_protol_b, fine_a, fine_b, total FROM prod_result_dtl*/

	public void save(ProductionResultProduct productionResultDetail) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, productionResultDetail.getProdResultCode());
			insertStatement.setInt(2, productionResultDetail.getPressedNo());
			insertStatement.setString(3, productionResultDetail.getStartTime());
			insertStatement.setInt(4, productionResultDetail.getRepairKlemA());
			insertStatement.setInt(5, productionResultDetail.getRepairKlemB());
			insertStatement.setInt(6, productionResultDetail.getRepairProtolA());
			insertStatement.setInt(7, productionResultDetail.getRepairProtolB());
			insertStatement.setInt(8, productionResultDetail.getFineA());
			insertStatement.setInt(9, productionResultDetail.getFineB());
			insertStatement.setInt(10, productionResultDetail.getTotal());
			insertStatement.setString(11, "Michael");
			insertStatement.setDate(12, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(String prodResultCode) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setString(1, prodResultCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
