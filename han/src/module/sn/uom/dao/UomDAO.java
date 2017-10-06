package module.sn.uom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.uom.model.Uom;

public class UomDAO {
	private Connection connection;
	
	private PreparedStatement getAllUom;

	private String uomQuery = "select * from uom where delete_date is null order by id asc";

	public UomDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Uom> getAllUom() throws SQLException {
		List<Uom> units = new ArrayList<Uom>();

		try {
			getAllUom = connection.prepareStatement(uomQuery);
			ResultSet rs = getAllUom.executeQuery();
			while (rs.next()) {
				Uom uom = new Uom();
				uom.setId(rs.getInt("id"));
				uom.setUom(rs.getString("uom"));
				units.add(uom);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return units;
	}
}
