package module.pembelian.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.Delivery;
import module.pembelian.model.Received;

public class DeliveryDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;

	private String getAllQuery = "SELECT a.id, a.delivery_note, a.document_type_id, d.document_type, a.wood_domicile, a.wood_resource_id, b.wood_resource, a.wood_type_id, c.wood_type, total_log, total_volume FROM delivery a INNER JOIN wood_resource b ON a.wood_resource_id = b.id "
			+ "INNER JOIN wood_type c ON a.wood_type_id = c.id INNER JOIN document_type d ON a.document_type_id = d.id WHERE 1 = 1 ";

	private String updateQuery = "UPDATE delivery SET delivery_note=?, document_type_id=?, wood_domicile=?, "
			+ "wood_resource_id=?, wood_type_id=?,total_log=?, total_volume=?, edit_date=?, edited_by=? WHERE id=?";
	
	private String insertQuery = "INSERT INTO delivery "
			+ "(delivery_note, document_type_id, wood_domicile, wood_resource_id, wood_type_id, total_log, total_volume, input_by, input_date) VALUES (?,?,?,?,?,?,?,?,?)";
	
	public DeliveryDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}

	public List<Delivery> getDeliveryNote() throws SQLException {
		Connection con = null;
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Delivery delivery = new Delivery();
				delivery.setId(rs.getInt("id"));
				delivery.setDocumentTypeID(rs.getInt("document_type_id"));
				delivery.setDocumentType(rs.getString("document_type"));
				delivery.setTotalLog(rs.getInt("total_log"));
				delivery.setTotalVolume(rs.getDouble("total_volume"));
				delivery.setWoodTypeID(rs.getInt("wood_type_id"));
				delivery.setWoodType(rs.getString("wood_type"));
				delivery.setDeliveryNote(rs.getString("delivery_note"));
				delivery.setWoodDomicile(rs.getString("wood_domicile"));
				delivery.setWoodResource(rs.getString("wood_resource"));
				delivery.setWoodResourceId(rs.getInt("wood_resource_id"));
				deliveries.add(delivery);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return deliveries;
	}
	
	public Delivery getDeliveryNoteByCode(String deliveryNote) throws SQLException {
		Connection con = null;
		Delivery delivery = new Delivery();
		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery+" AND delivery_note = ?");
			getAllStatement.setString(1, deliveryNote);
			ResultSet rs = getAllStatement.executeQuery();
			rs.next();
			delivery.setId(rs.getInt("id"));
			delivery.setDocumentTypeID(rs.getInt("document_type_id"));
			delivery.setDocumentType(rs.getString("document_type"));
			delivery.setTotalLog(rs.getInt("total_log"));
			delivery.setTotalVolume(rs.getDouble("total_volume"));
			delivery.setWoodTypeID(rs.getInt("wood_type_id"));
			delivery.setWoodType(rs.getString("wood_type"));
			delivery.setDeliveryNote(rs.getString("delivery_note"));
			delivery.setWoodDomicile(rs.getString("wood_domicile"));
			delivery.setWoodResource(rs.getString("wood_resource"));
			delivery.setWoodResourceId(rs.getInt("wood_resource_id"));
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return delivery;
	}
	
	
	
	public void save(Delivery delivery) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		insertStatement = con.prepareStatement(insertQuery);
    		insertStatement.setString(1, delivery.getDeliveryNote());
    		insertStatement.setInt(2, delivery.getDocumentTypeID());
    		insertStatement.setString(3, delivery.getWoodDomicile());
    		insertStatement.setInt(4, delivery.getWoodResourceId());
    		insertStatement.setInt(5, delivery.getWoodTypeID());
    		insertStatement.setInt(6, delivery.getTotalLog());
    		insertStatement.setDouble(7, delivery.getTotalVolume());
    		insertStatement.setString(8, "Michael");
    		insertStatement.setDate(9, new Date(new java.util.Date().getTime()));
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
	
	public void update(Delivery delivery) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		updateStatement = con.prepareStatement(updateQuery);
    		updateStatement.setString(1, delivery.getDeliveryNote());
    		updateStatement.setInt(2, delivery.getDocumentTypeID());
    		updateStatement.setString(3, delivery.getWoodDomicile());
    		updateStatement.setInt(4, delivery.getWoodResourceId());
    		updateStatement.setInt(5, delivery.getWoodTypeID());
    		updateStatement.setInt(6, delivery.getTotalLog());
    		updateStatement.setDouble(7, delivery.getTotalVolume());
    		updateStatement.setDate(8, new Date(new java.util.Date().getTime()));
    		updateStatement.setString(9, "Michael");
    		updateStatement.setInt(10, delivery.getId());
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
}