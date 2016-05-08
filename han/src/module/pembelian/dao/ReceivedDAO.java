package module.pembelian.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.Received;

public class ReceivedDAO {
	private DataSource dataSource;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;

	 private String insertQuery = "INSERT INTO received (received_code, received_date,"
	 		+ " rit_no, license_plate, driver, delivery_note, wood_type_id, driver_id, received_status, input_date, input_by) "
	 		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	 private String updateQuery = "UPDATE received set received_date=?, rit_no=?,"
	 		+ " license_plate=?, driver=?, delivery_note=?, wood_type_id=?, edit_date=?, edited_by=?"
	 		+ " WHERE id=? AND received_code=?";
	 private String deleteQuery = "update bank set deleted_date=?,"
	 		+ "deleted_by=? where id=?";
	private String getAllQuery = "select a.id, received_code, received_date, rit_no, a.license_plate, driver, delivery_note, wood_type_id, supp_name, driver_id, received_status FROM received a " +
			"INNER JOIN supp_vehicle b  ON a.license_plate = b.license_plate INNER JOIN supplier c ON b.supp_code = c.supp_code";


	public ReceivedDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}
	
	
	public List<Received> getAll() throws SQLException {
		Connection con = null;
		List<Received> receiveds = new ArrayList<Received>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Received received = new Received();
				received.setId(rs.getInt("id"));
				received.setReceivedCode(rs.getString("received_code"));
				received.setReceivedDate(rs.getDate("received_date"));
				received.setRitNo(rs.getString("rit_no"));
				received.setLicensePlate(rs.getString("license_plate"));
				received.setDriver(rs.getString("driver"));
				received.setDeliveryNote(rs.getString("delivery_note"));
				received.setWoodTypeID(rs.getInt("wood_type_id"));
				received.setSupplier(rs.getString("supp_name"));
				received.setReceivedStatus(rs.getString("received_status"));
				received.setDriverID(rs.getString("driver_id"));
				receiveds.add(received);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return receiveds;
	}
	
	public void save(Received received) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		insertStatement = con.prepareStatement(insertQuery);
    		insertStatement.setString(1, received.getReceivedCode());
    		insertStatement.setDate(2, new Date(received.getReceivedDate().getTime()));
    		insertStatement.setString(3, received.getRitNo());
    		insertStatement.setString(4, received.getLicensePlate());
    		insertStatement.setString(5, received.getDriver());
    		insertStatement.setString(6, received.getDeliveryNote());
    		insertStatement.setInt(7, received.getWoodTypeID());
    		insertStatement.setString(8, received.getDriverID());
    		insertStatement.setString(9, "Baru");
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
	
	public void update(Received received) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		updateStatement = con.prepareStatement(updateQuery);
    		updateStatement.setDate(1, new Date(received.getReceivedDate().getTime()));
    		updateStatement.setString(2, received.getRitNo());
    		updateStatement.setString(3, received.getLicensePlate());
    		updateStatement.setString(4, received.getDriver());
    		updateStatement.setString(5, received.getDeliveryNote());
    		updateStatement.setInt(6, received.getWoodTypeID());
    		updateStatement.setDate(7, new Date(new java.util.Date().getTime()));
    		updateStatement.setString(8, "Michael");
    		updateStatement.setInt(9, received.getId());
    		updateStatement.setString(10, received.getReceivedCode());
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
	
/*	public void delete(String employeeId) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		deleteStatement = con.prepareStatement(deleteQuery);

    		deleteStatement.setString(1, employeeId);
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
	}*/

}
