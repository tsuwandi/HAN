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

public class DeliveryDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;

	private String getAllQuery = "SELECT a.id, a.delivery_note, a.doc_issued_date, a.document_type, a.wood_domicile, a.wood_resource_id, b.wood_resource, a.wood_type_id, c.wood_type, total_log, total_volume FROM delivery a INNER JOIN wood_resource b ON a.wood_resource_id = b.id "
			+ "INNER JOIN wood_type c ON a.wood_type_id = c.id WHERE 1 = 1 ";


	private String updateQuery = "UPDATE delivery SET delivery_note=?, doc_issued_date = ?, document_type=?, wood_domicile=?, "
			+ "wood_resource_id=?, wood_type_id=?,total_log=?, total_volume=?, edit_date=?, edited_by=? WHERE id=?";
	
	private String insertQuery = "INSERT INTO delivery "
			+ "(delivery_note, doc_issued_date, document_type, wood_domicile, wood_resource_id, wood_type_id, total_log, total_volume, input_by, input_date) VALUES (?,?,?,?,?,?,?,?,?,?)";
	
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
				delivery.setDocumentType(rs.getString("document_type"));
				delivery.setDocIssuedDate(rs.getDate("doc_issued_date"));
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
			delivery.setDocumentType(rs.getString("document_type"));
			delivery.setDocIssuedDate(rs.getDate("doc_issued_date"));
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
    		insertStatement.setDate(2, new Date(delivery.getDocIssuedDate().getTime()));
    		insertStatement.setString(3, delivery.getDocumentType());
    		insertStatement.setString(4, delivery.getWoodDomicile());
    		insertStatement.setInt(5, delivery.getWoodResourceId());
    		insertStatement.setInt(6, delivery.getWoodTypeID());
    		insertStatement.setInt(7, delivery.getTotalLog());
    		insertStatement.setDouble(8, delivery.getTotalVolume());
    		insertStatement.setString(9, "Michael");
    		insertStatement.setDate(10, new Date(new java.util.Date().getTime()));
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
    		updateStatement.setDate(2, new Date(delivery.getDocIssuedDate().getTime()));
    		updateStatement.setString(3, delivery.getDocumentType());
    		updateStatement.setString(4, delivery.getWoodDomicile());
    		updateStatement.setInt(5, delivery.getWoodResourceId());
    		updateStatement.setInt(6, delivery.getWoodTypeID());
    		updateStatement.setInt(7, delivery.getTotalLog());
    		updateStatement.setDouble(8, delivery.getTotalVolume());
    		updateStatement.setDate(9, new Date(new java.util.Date().getTime()));
    		updateStatement.setString(10, "Michael");
    		updateStatement.setInt(11, delivery.getId());
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