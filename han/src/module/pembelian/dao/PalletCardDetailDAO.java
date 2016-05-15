package module.pembelian.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.PalletCardDetail;
import module.pembelian.model.PicDocking;

public class PalletCardDetailDAO {
	private DataSource dataSource;
	
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String insertQuery = "INSERT INTO pallet_card_dtl (pallet_card_code, length, width, thickness,"
			+ " total, volume, product_code, input_date, input_by) "
	 		+ " VALUES (?,?,?,?,?,?,?,?,?)";
	
	private String updateQuery = "INSERT INTO pallet_card_dtl (pallet_card_code, length, width, thickness,"
			+ " total, volume, product_code, edit_date, edited_by) "
	 		+ " VALUES (?,?,?,?,?,?,?,?,?)";
	
	private String deleteQuery = "DELETE FROM pallet_card_dtl WHERE pallet_card_code = ?";

	
	public PalletCardDetailDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}
	
	public void save(PalletCardDetail palletCardDetail) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		insertStatement = con.prepareStatement(insertQuery);
    		insertStatement.setString(1, palletCardDetail.getPalletCardCode());
    		insertStatement.setDouble(2, palletCardDetail.getLength());
    		insertStatement.setDouble(3, palletCardDetail.getWidth());
    		insertStatement.setDouble(4, palletCardDetail.getThickness());
    		insertStatement.setInt(5, palletCardDetail.getTotal());
    		insertStatement.setDouble(6, palletCardDetail.getVolume());
    		insertStatement.setString(7, palletCardDetail.getProductCode());
    		insertStatement.setDate(8, new Date(new java.util.Date().getTime()));
    		insertStatement.setString(9, "Michael");
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
	
	public void update(PalletCardDetail palletCardDetail) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		updateStatement = con.prepareStatement(updateQuery);
    		updateStatement.setString(1, palletCardDetail.getPalletCardCode());
    		updateStatement.setDouble(2, palletCardDetail.getLength());
    		updateStatement.setDouble(3, palletCardDetail.getWidth());
    		updateStatement.setDouble(4, palletCardDetail.getThickness());
    		updateStatement.setInt(5, palletCardDetail.getTotal());
    		updateStatement.setDouble(6, palletCardDetail.getVolume());
    		updateStatement.setString(7, palletCardDetail.getProductCode());
    		updateStatement.setDate(8, new Date(new java.util.Date().getTime()));
    		updateStatement.setString(9, "Michael");
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
	
	public void delete(String palletCardCode) throws SQLException{
		  Connection con = null;
	    	try {
	    		con = dataSource.getConnection();
	    		
	    		deleteStatement = con.prepareStatement(deleteQuery);
	    		deleteStatement.setString(1, palletCardCode);
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
