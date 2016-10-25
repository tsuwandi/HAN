package module.productionpk.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.ProductionResult;
import module.productionpk.model.ProdPKResult;

public class ProdPKResultDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getProdResultByProductionCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement getLastIDStatement;
	
	private String getAllQuery = "SELECT id, prod_pk_code, pressed_no, start_time, total_fine_a, total_fine_b, total_protol, total_klem "
			+ "FROM prod_pk_result WHERE deleted_date IS NULL";
	private String getLastIDQuery = "SELECT id FROM prod_pk_result ORDER BY id DESC LIMIT 1";
	private String insertQuery = "INSERT INTO prod_pk_result (prod_pk_code,pressed_no, start_time, total_fine_a, total_fine_b, total_protol, total_klem , input_by, input_date) "
			+ "VALUES (?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "UPDATE prod_pk_result SET pressed_no =?, "
			+ "start_time=?, total_fine_a=?, total_fine_b=?, total_protol=?,total_klem=?, edited_by=?, edited_date=?  "
			+ "WHERE prod_pk_code =? AND id=?";
	
	public ProdPKResultDAO(Connection connection) throws SQLException {
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
	
	public List<ProdPKResult> getAllByCode(String prodCode) throws SQLException {
		List<ProdPKResult> prodPKResults = new ArrayList<ProdPKResult>();
		try {
			StringBuffer sb  = new StringBuffer(getAllQuery);
			sb.append(" AND prod_pk_code = ?");
			
			getProdResultByProductionCodeStatement = connection.prepareStatement(sb.toString());
			getProdResultByProductionCodeStatement.setString(1, prodCode);
			

			ResultSet rs = getProdResultByProductionCodeStatement.executeQuery();
			while (rs.next()) {
				ProdPKResult prodPKResult = new ProdPKResult();
				prodPKResult.setId(rs.getInt("id"));
				prodPKResult.setProdPKCode(rs.getString("prod_pk_code"));
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
	
	public List<ProdPKResult> getAll() throws SQLException {
		List<ProdPKResult> prodPKResults = new ArrayList<ProdPKResult>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProdPKResult prodPKResult = new ProdPKResult();
				prodPKResult.setId(rs.getInt("id"));
				prodPKResult.setProdPKCode(rs.getString("prod_pk_code"));
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
	
	public void save(ProdPKResult productionResult) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, productionResult.getProdPKCode());
			insertStatement.setInt(2, productionResult.getPressedNo());
			insertStatement.setString(3, productionResult.getStartTime());
			insertStatement.setDouble(4, productionResult.getTotalFineA());
			insertStatement.setDouble(5, productionResult.getTotalFineB());
			insertStatement.setDouble(6, productionResult.getTotalProtol());
			insertStatement.setDouble(7, productionResult.getTotalKlem());
			insertStatement.setString(8, "Michael");
			insertStatement.setDate(9, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void update(ProdPKResult productionResult) throws SQLException {
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
			updateStatement.setString(9, productionResult.getProdPKCode());
			updateStatement.setInt(10, productionResult.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
