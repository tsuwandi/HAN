package module.pembelian.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.PalletCard;
import module.pembelian.model.ReceivedDetail;

public class ReceivedDetailDAO {
	private DataSource dataSource;

	private PreparedStatement getAllReceivedDetails;
	private PreparedStatement insertStatement;
	private PreparedStatement detailPalletStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getLastIDStatement;
	
	private String insertQuery = "INSERT INTO received_detail (received_code,"
	 		+ " grade_id, total_volume, total_log, input_date, input_by) "
	 		+ " VALUES (?,?,?,?,?,?)";

	private String detailPalletQuery = "SELECT a.id, a.received_detail_id, a.pallet_card_code, a.length, a.width, a.thickness, a.total, a.volume, a.description, a.product_code, product_name FROM "
			+ "pallet_card a INNER JOIN product b ON a.product_code = b.product_code WHERE received_detail_id = ?";

	private String getReceivedDetailsQuery = "SELECT a.id, received_code, grade_id, total_volume, total_log, grade  "
			+ "FROM received_detail a INNER JOIN grade b ON a.grade_id = b.id WHERE received_code = ?";
	
	
	private String deletePalletQuery = "DELETE FROM received_detail WHERE received_code = ?";
	
	private String getLastIDQuery ="SELECT ID FROM received_detail ORDER BY ID DESC LIMIT 1";
	
	public ReceivedDetailDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}
	
	public int getLastID() throws SQLException{
		int lastID = 0;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			getLastIDStatement = con.prepareStatement(getLastIDQuery);
	
			ResultSet rs = getLastIDStatement.executeQuery();
			rs.next();
			lastID = rs.getInt("ID");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		
		return lastID;
	}
	
	public List<ReceivedDetail> getAllReceivedDetail(String receivedCode) throws SQLException{
		List<ReceivedDetail> receivedDetails = new ArrayList<ReceivedDetail>();
		Connection con = null;
		try {
	
			con = dataSource.getConnection();
			
			getAllReceivedDetails = con.prepareStatement(getReceivedDetailsQuery);
			getAllReceivedDetails.setString(1, receivedCode);
	
			ResultSet rs = getAllReceivedDetails.executeQuery();
			while (rs.next()) {
				ReceivedDetail receivedDetail = new ReceivedDetail();
				receivedDetail.setId(rs.getInt("id"));
				receivedDetail.setGradeID(rs.getInt("grade_id"));
				receivedDetail.setGrade(rs.getString("grade"));
				receivedDetail.setTotalLog(rs.getInt("total_log"));
				receivedDetail.setTotalVolume(rs.getDouble("total_volume"));
				List<PalletCard> pcds = new ArrayList<>();
				detailPalletStatement = con.prepareStatement(detailPalletQuery);
				detailPalletStatement.setInt(1, rs.getInt("id"));
				ResultSet rsDetail = detailPalletStatement.executeQuery();
				while (rsDetail.next()) {
					PalletCard pcd = new PalletCard();
					pcd.setId(rsDetail.getInt("id"));
					pcd.setReceivedDetailID(rsDetail.getInt("received_detail_id"));
					pcd.setPalletCardCode(rsDetail.getString("pallet_card_code"));
					pcd.setLength(rsDetail.getDouble("length"));
					pcd.setWidth(rsDetail.getDouble("width"));
					pcd.setThickness(rsDetail.getDouble("thickness"));
					pcd.setProductCode(rsDetail.getString("product_code"));
					pcd.setProductName(rsDetail.getString("product_name"));
					pcd.setTotal(rsDetail.getInt("total"));
					pcd.setVolume(rsDetail.getDouble("volume"));
					pcd.setDescription(rsDetail.getString("description"));
					pcds.add(pcd);
				}
				receivedDetail.setPallets(pcds);
				receivedDetails.add(receivedDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		
		return receivedDetails;
	}
	
	public void save(ReceivedDetail receivedDetail) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		insertStatement = con.prepareStatement(insertQuery);
    		insertStatement.setString(1, receivedDetail.getReceivedCode());
    		insertStatement.setInt(2, receivedDetail.getGradeID());
    		insertStatement.setDouble(3, receivedDetail.getTotalVolume());
    		insertStatement.setInt(4, receivedDetail.getTotalLog());
    		insertStatement.setDate(5, new Date(new java.util.Date().getTime()));
    		insertStatement.setString(6, "Michael");
    		insertStatement.executeUpdate();
            
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException(ex.getMessage());
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
			}
        }
	}
	
	public void delete(String receivedCode) throws SQLException {
		  Connection con = null;
	    	try {
	    		con = dataSource.getConnection();
	    		
	    		deleteStatement = con.prepareStatement(deletePalletQuery);
	    		deleteStatement.setString(1, receivedCode);
	    		deleteStatement.executeUpdate();
	            
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        	throw new SQLException(ex.getMessage());
	        } finally {
	        	try {
					con.close();
				} catch (SQLException e) {
				}
	        }
	}
}
