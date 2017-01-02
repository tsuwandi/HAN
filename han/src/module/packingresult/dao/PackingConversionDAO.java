package module.packingresult.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.packingresult.model.PackingConversion;

public class PackingConversionDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	
	private String getAllQuery = "SELECT id, product_code_from, qty_from, product_code_to, qty_to FROM packing_conversion"
			+ " WHERE deleted_date IS NULL AND NOW() BETWEEN effective_start_date AND effective_end_date";
	
	public PackingConversionDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<PackingConversion> getAll() throws SQLException {
		List<PackingConversion> packings = new ArrayList<PackingConversion>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PackingConversion packingConversion = new PackingConversion();
				packingConversion.setId(rs.getInt("id"));
				packingConversion.setProductCodeFrom(rs.getString("product_code_from"));
				packingConversion.setQtyFrom(rs.getInt("qty_from"));
				packingConversion.setProductCodeTo(rs.getString("product_code_to"));
				packingConversion.setQtyTo(rs.getInt("qty_to"));
				packings.add(packingConversion);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return packings;
	}
}
