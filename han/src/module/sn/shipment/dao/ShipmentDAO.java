package module.sn.shipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.bank.model.Bank;
import module.sn.insurance.model.Insurance;
import module.sn.shipment.model.Shipment;

public class ShipmentDAO {
	private Connection connection;
	// private PreparedStatement insertStatement;
	// private PreparedStatement updateStatement;
	// private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;

	// private String insertQuery = "insert into bank (bank_abbr, bank,
	// input_date, input_by) values (?,?,?,?)";
	// private String updateQuery = "update bank set bank_abbr=?, bank=?,
	// edit_date=?, edited_by=? where id=?";
	// private String deleteQuery = "update bank set deleted_date=?,
	// deleted_by=? where id=?";
	private String getAllQuery = "select id, shipment_agent, shipment_type from shipment order by shipment_agent";

	public ShipmentDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Shipment> getAll() throws SQLException {
		List<Shipment> shipments = new ArrayList<Shipment>();
		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Shipment shipment = new Shipment();
				shipment.setId(rs.getInt("id"));
				shipment.setShipmentAgent(rs.getString("shipment_agent"));
				shipment.setShipmentType(rs.getString("shipment_type"));

				shipments.add(shipment);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return shipments;
	}
}
