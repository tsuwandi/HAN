package module.production.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.ProdRM;

public class ProdRMDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getProdResultByProductionCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getAllForSearchStatement;
	private PreparedStatement getAllForSearchByPalletCardCodeStatement;
	
	private String getAllQuery = "SELECT a.id, a.pallet_card_code, b.length, b.width, b.thickness, b.total, b.volume  FROM prod_rm a "
			+ "INNER JOIN pallet_card b ON a.pallet_card_code = b.pallet_card_code WHERE deleted_date IS NULL ";
	
	private String insertQuery = "INSERT INTO prod_result_dtl (production_code, pallet_card_code, input_by, input_date) "
			+ "VALUES (?,?,?,?)";
	private String deleteQuery = "DELETE FROM prod_rm WHERE production_code = ?";
	
	private String getAllForSearchQuery = "SELECT a.id, a.input_date, b.pallet_card_code, b.length, b.width, b.thickness, b.total, b.volume  FROM dry_out_pallet a "
			+ "INNER JOIN pallet_card b ON a.pallet_card_code = b.pallet_card_code WHERE NOT EXISTS(SELECT pallet_card_code FROM prod_rm)";
	
	public ProdRMDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	
	public List<ProdRM> getAll() throws SQLException {
		List<ProdRM> prodRMs = new ArrayList<ProdRM>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProdRM prodRM = new ProdRM();
				prodRM.setId(rs.getInt("id"));
				prodRM.setPalletCardCode(rs.getString("pallet_card_code"));
				prodRM.setLength(rs.getDouble("length"));
				prodRM.setWidth(rs.getDouble("width"));
				prodRM.setThick(rs.getDouble("thickness"));
				prodRM.setLog(rs.getInt("total"));
				prodRM.setVolume(rs.getDouble("volume"));
				prodRMs.add(prodRM);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodRMs;
	}
	
	public List<ProdRM> getAllSearch() throws SQLException {
		List<ProdRM> prodRMs = new ArrayList<ProdRM>();

		try {
			getAllForSearchStatement = connection.prepareStatement(getAllForSearchQuery);

			ResultSet rs = getAllForSearchStatement.executeQuery();
			while (rs.next()) {
				ProdRM prodRM = new ProdRM();
				prodRM.setId(rs.getInt("id"));
				prodRM.setDateDryOut(rs.getDate("input_date"));
				prodRM.setPalletCardCode(rs.getString("pallet_card_code"));
				prodRM.setLength(rs.getDouble("length"));
				prodRM.setWidth(rs.getDouble("width"));
				prodRM.setThick(rs.getDouble("thickness"));
				prodRM.setLog(rs.getInt("total"));
				prodRM.setVolume(rs.getDouble("volume"));
				prodRMs.add(prodRM);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodRMs;
	}
	
	public List<ProdRM> getAllByProductionCode(String productionCode) throws SQLException {
		List<ProdRM> prodRMs = new ArrayList<ProdRM>();

		try {
			StringBuffer sb  = new StringBuffer(getAllQuery);
			sb.append(" AND production_code = ?");
			
			getProdResultByProductionCodeStatement = connection.prepareStatement(sb.toString());
			getProdResultByProductionCodeStatement.setString(1, productionCode);
			
			ResultSet rs = getProdResultByProductionCodeStatement.executeQuery();
			while (rs.next()) {
				ProdRM prodRM = new ProdRM();
				prodRM.setId(rs.getInt("id"));
				prodRM.setPalletCardCode(rs.getString("pallet_card_code"));
				prodRM.setLength(rs.getDouble("length"));
				prodRM.setWidth(rs.getDouble("width"));
				prodRM.setThick(rs.getDouble("thickness"));
				prodRM.setLog(rs.getInt("total"));
				prodRM.setVolume(rs.getDouble("volume"));
				prodRMs.add(prodRM);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodRMs;
	}
	
	public ProdRM getProdRMByPalletCard(String palletCardCode) throws SQLException {
		ProdRM prodRM = null;
		try {
			StringBuffer sb  = new StringBuffer(getAllForSearchQuery);
			sb.append(" AND pallet_card_code = ?");
			
			getAllForSearchByPalletCardCodeStatement = connection.prepareStatement(sb.toString());
			getAllForSearchByPalletCardCodeStatement.setString(1, palletCardCode);
			
			ResultSet rs = getAllForSearchByPalletCardCodeStatement.executeQuery();
			rs.next(); 
			prodRM = new ProdRM();
			prodRM.setId(rs.getInt("id"));
			prodRM.setPalletCardCode(rs.getString("pallet_card_code"));
			prodRM.setLength(rs.getDouble("length"));
			prodRM.setWidth(rs.getDouble("width"));
			prodRM.setThick(rs.getDouble("thickness"));
			prodRM.setLog(rs.getInt("total"));
			prodRM.setVolume(rs.getDouble("volume"));
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodRM;
	}
	

	public void save(ProdRM productionResultDetail) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, productionResultDetail.getProductionCode());
			insertStatement.setString(2, productionResultDetail.getPalletCardCode());
			insertStatement.setString(3, "Michael");
			insertStatement.setDate(4, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(String productionCode) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setString(1, productionCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
