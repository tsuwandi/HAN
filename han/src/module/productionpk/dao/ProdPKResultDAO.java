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
	
	private String getAllQuery = "SELECT id, prod_pk_code, pressed_no, start_time, total_protol, total_klem "
			+ "FROM prod_pk_result WHERE deleted_date IS NULL";

	private String insertQuery = "INSERT INTO prod_pk_result (prod_pk_code,pressed_no, start_time, total_protol, total_klem , input_by, input_date) "
			+ "VALUES (?,?,?,?,?,?,?)";
	private String updateQuery = "UPDATE prod_pk_result SET pressed_no =?, "
			+ "start_time=?, total_protol=?,total_klem=?, edited_by=?, edited_date=?  "
			+ "WHERE prod_pk_code =?";
	
	public ProdPKResultDAO(Connection connection) throws SQLException {
		this.connection = connection;
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
			insertStatement.setDouble(4, productionResult.getTotalProtol());
			insertStatement.setDouble(5, productionResult.getTotalKlem());
			insertStatement.setString(6, "Michael");
			insertStatement.setDate(7, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
