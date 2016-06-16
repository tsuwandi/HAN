package module.pembelian.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import module.pembelian.model.PalletCardDetail;
import module.pembelian.model.PalletCard;

public class PalletCardDAO {
	private DataSource dataSource;
	
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String insertQuery = "INSERT INTO pallet_card (received_detail_id, pallet_card_code, length, width, thickness,"
			+ " total, volume, product_code, description, input_date, input_by) "
	 		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private String updateQuery = "INSERT INTO pallet_card (received_detail_id, pallet_card_code, length, width, thickness,"
			+ " total, volume, product_code, description, edit_date, edited_by) "
	 		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private String deleteQuery = "DELETE FROM pallet_card WHERE received_detail_id = ?";

	
	public PalletCardDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}
	
	public void save(PalletCard palletCard) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		insertStatement = con.prepareStatement(insertQuery);
    		insertStatement.setInt(1, palletCard.getReceivedDetailID());
    		insertStatement.setString(2, palletCard.getPalletCardCode());
    		insertStatement.setDouble(3, palletCard.getLength());
    		insertStatement.setDouble(4, palletCard.getWidth());
    		insertStatement.setDouble(5, palletCard.getThickness());
    		insertStatement.setInt(6, palletCard.getTotal());
    		insertStatement.setDouble(7, palletCard.getVolume());
    		insertStatement.setString(8, palletCard.getProductCode());
    		insertStatement.setString(9, palletCard.getDescription());
    		insertStatement.setDate(10, new Date(new java.util.Date().getTime()));
    		insertStatement.setString(11, "Michael");
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
	
	public void update(PalletCard palletCard) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		updateStatement = con.prepareStatement(updateQuery);
    		updateStatement.setInt(1, palletCard.getReceivedDetailID());
    		updateStatement.setString(2, palletCard.getPalletCardCode());
    		updateStatement.setDouble(3, palletCard.getLength());
    		updateStatement.setDouble(4, palletCard.getWidth());
    		updateStatement.setDouble(5, palletCard.getThickness());
    		updateStatement.setInt(6, palletCard.getTotal());
    		updateStatement.setDouble(7, palletCard.getVolume());
    		updateStatement.setString(8, palletCard.getProductCode());
    		updateStatement.setString(9, palletCard.getDescription());
    		updateStatement.setDate(10, new Date(new java.util.Date().getTime()));
    		updateStatement.setString(11, "Michael");
    		updateStatement.executeUpdate();
            
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
	
	public void delete(int receivedDetailID) throws SQLException{
		  Connection con = null;
	    	try {
	    		con = dataSource.getConnection();
	    		
	    		deleteStatement = con.prepareStatement(deleteQuery);
	    		deleteStatement.setInt(1, receivedDetailID);
	    		deleteStatement.executeUpdate();
	            
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        	
	        } finally {
	        	try {
					con.close();
				} catch (SQLException e) {
				}
	        }
	}
}
