package module.productionwaste.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.productionwaste.model.ProductionResultWaste;



public class ProductWasteResultDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getProdResultByProductionCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement getLastIDStatement;
	
	private String getAllQuery = "SELECT id, pw_code, pressed_no, start_time, total_fine_a, total_fine_b, total_protol, total_klem "
			+ "FROM prod_waste_result WHERE deleted_date IS NULL";
	private String getLastIDQuery = "SELECT id FROM prod_waste_result ORDER BY id DESC LIMIT 1";
	private String insertQuery = "INSERT INTO prod_waste_result (pw_code,pressed_no, start_time, total_fine_a, total_fine_b, total_protol, total_klem , input_by, input_date,id) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
	private String deleteQuery = "UPDATE prod_waste_result SET deleted_date = ? , delete_by=? WHERE pw_code = ? AND id=?";
	private String updateQuery = "UPDATE prod_waste_result SET pressed_no =?, "
			+ "start_time=?, total_fine_a=?, total_fine_b=?, total_protol=?,total_klem=?, edited_by=?, edited_date=?  "
			+ "WHERE pw_code =? AND id=?";
	
	public ProductWasteResultDAO(Connection connection) throws SQLException {
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
	
	public List<ProductionResultWaste> getAllByCode(String prodCode) throws SQLException {
		List<ProductionResultWaste> prodPKResults = new ArrayList<ProductionResultWaste>();
		try {
			StringBuffer sb  = new StringBuffer(getAllQuery);
			sb.append(" AND pw_code = ?");
			
			getProdResultByProductionCodeStatement = connection.prepareStatement(sb.toString());
			getProdResultByProductionCodeStatement.setString(1, prodCode);
			

			ResultSet rs = getProdResultByProductionCodeStatement.executeQuery();
			while (rs.next()) {
				ProductionResultWaste prodPKResult = new ProductionResultWaste();
				prodPKResult.setId(rs.getInt("id"));
				prodPKResult.setProdCode(rs.getString("pw_code"));
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
	
	public List<ProductionResultWaste> getAll() throws SQLException {
		List<ProductionResultWaste> prodPKResults = new ArrayList<ProductionResultWaste>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionResultWaste prodPKResult = new ProductionResultWaste();
				prodPKResult.setId(rs.getInt("id"));
				prodPKResult.setProdCode(rs.getString("pw_code"));
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
	
	public void save(ProductionResultWaste ProductionResultWaste) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, ProductionResultWaste.getProdCode());
			insertStatement.setInt(2, ProductionResultWaste.getPressedNo());
			insertStatement.setString(3, ProductionResultWaste.getStartTime());
			insertStatement.setDouble(4, ProductionResultWaste.getTotalFineA());
			insertStatement.setDouble(5, ProductionResultWaste.getTotalFineB());
			insertStatement.setDouble(6, ProductionResultWaste.getTotalProtol());
			insertStatement.setDouble(7, ProductionResultWaste.getTotalKlem());
			insertStatement.setString(8, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(9, new Date(new java.util.Date().getTime()));
			insertStatement.setInt(10, ProductionResultWaste.getId());
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void update(ProductionResultWaste ProductionResultWaste) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, ProductionResultWaste.getPressedNo());
			updateStatement.setString(2, ProductionResultWaste.getStartTime());
			updateStatement.setDouble(3, ProductionResultWaste.getTotalFineA());
			updateStatement.setDouble(4, ProductionResultWaste.getTotalFineB());
			updateStatement.setDouble(5, ProductionResultWaste.getTotalProtol());
			updateStatement.setDouble(6, ProductionResultWaste.getTotalKlem());
			updateStatement.setString(7, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(8, new Date(new java.util.Date().getTime()));
			updateStatement.setString(9, ProductionResultWaste.getProdCode());
			updateStatement.setInt(10, ProductionResultWaste.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(ProductionResultWaste ProductionResultWaste) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setString(3, ProductionResultWaste.getProdCode());
			deleteStatement.setInt(4, ProductionResultWaste.getId());
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
