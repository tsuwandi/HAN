package module.pembelian.dao;

import java.sql.Connection;
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

	private String getAllQuery = "SELECT a.id, a.delivery_note, a.wood_domicile, a.wood_resource_id, b.wood_resource FROM delivery a INNER JOIN wood_resource b ON a.wood_resource_id = b.id WHERE 1 = 1 ";

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
}
