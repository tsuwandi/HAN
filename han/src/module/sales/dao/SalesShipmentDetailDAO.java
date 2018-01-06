package module.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sales.model.ShipmentSalesOrder;
import module.util.DateUtil;

public class SalesShipmentDetailDAO {
	private Connection connection;

	private PreparedStatement getAllBySalesIdStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllBySalesIdQuery = "select id, sales_order_id, shipment_agent, shipment_type, origin_address, "
			+ "destination_address, date_of_shipment, pickup_date, shipment_cost "
			+ "from shipment_sales_order "
			+ "where sales_order_id = ? ";

	private String insertQuery = "insert into shipment_sales_order (sales_order_id, shipment_agent, shipment_type, origin_address, "
			+ "destination_address, date_of_shipment, pickup_date, shipment_cost) values (?,?,?,?,?,?,?,?)";

	private String updateQuery = "update shipment_sales_order set sales_order_id=?, shipment_agent=?, shipment_type=?, "
			+ "origin_address=?, destination_address=?, date_of_shipment=?, pickup_date=?, shipment_cost=? where id=?";

	private String deleteQuery = "update shipment_sales_order set deleted_date=?, deleted_by=? ";

	public SalesShipmentDetailDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ShipmentSalesOrder> getAllBySalesId(int salesId) throws SQLException {
		List<ShipmentSalesOrder> salesShipmentDetails = new ArrayList<ShipmentSalesOrder>();

		try {

			getAllBySalesIdStatement = connection.prepareStatement(getAllBySalesIdQuery);

			getAllBySalesIdStatement.setInt(1, salesId);

			ResultSet rs = getAllBySalesIdStatement.executeQuery();
			while (rs.next()) {
				ShipmentSalesOrder salesShipmentDetail = new ShipmentSalesOrder();
				salesShipmentDetail.setId(rs.getInt("id"));
				salesShipmentDetail.setSalesOrderId(rs.getInt("sales_order_id"));
				salesShipmentDetail.setShipmentAgent(rs.getString("shipment_agent"));
				salesShipmentDetail.setShipmentType(rs.getString("shipment_type"));
				salesShipmentDetail.setOriginAddress(rs.getString("origin_address"));
				salesShipmentDetail.setDestinationAddress(rs.getString("destination_address"));
				salesShipmentDetail.setDateOfShipment(rs.getDate("date_of_shipment"));
				salesShipmentDetail.setPickupDate(rs.getDate("pickup_date"));
				salesShipmentDetail.setShipmentCost(rs.getDouble("shipment_cost"));			
				
				salesShipmentDetails.add(salesShipmentDetail);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return salesShipmentDetails;
	}

	public ShipmentSalesOrder save(ShipmentSalesOrder salesShipmentDetail) throws SQLException {
		ResultSet generatedKeys = null;

		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, salesShipmentDetail.getSalesOrderId());
			insertStatement.setString(2, salesShipmentDetail.getShipmentAgent());
			insertStatement.setString(3, salesShipmentDetail.getShipmentType());
			insertStatement.setString(4, salesShipmentDetail.getOriginAddress());
			insertStatement.setString(5, salesShipmentDetail.getDestionationAddress());
			insertStatement.setDate(6, new java.sql.Date(salesShipmentDetail.getDateOfShipment().getTime()));
			insertStatement.setDate(7, new java.sql.Date(salesShipmentDetail.getPickupDate().getTime()));
			insertStatement.setDouble(8, salesShipmentDetail.getShipmentCost());
			insertStatement.executeUpdate();

			generatedKeys = insertStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				salesShipmentDetail.setId(generatedKeys.getInt(1));
				generatedKeys.close();
			}
			return salesShipmentDetail;

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(ShipmentSalesOrder salesShipmentDetail) throws SQLException {
		try {

			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, salesShipmentDetail.getSalesOrderId());
			updateStatement.setString(2, salesShipmentDetail.getShipmentAgent());
			updateStatement.setString(3, salesShipmentDetail.getShipmentType());
			updateStatement.setString(4, salesShipmentDetail.getOriginAddress());
			updateStatement.setString(5, salesShipmentDetail.getDestionationAddress());
			updateStatement.setDate(6, new java.sql.Date(salesShipmentDetail.getDateOfShipment().getTime()));
			updateStatement.setDate(7, new java.sql.Date(salesShipmentDetail.getPickupDate().getTime()));
			updateStatement.setDouble(8, salesShipmentDetail.getShipmentCost());
			updateStatement.setInt(9, salesShipmentDetail.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(int salesId) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where sales_order_id = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "Sandy");
			deleteStatement.setInt(3, salesId);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where id = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "Sandy");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
}
