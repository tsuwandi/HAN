package module.sn.production.type.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.production.type.model.ProductionType;

public class ProductionTypeDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;

	private String getAllQuery = "select id, production_type from production_type order by production_type";

	public ProductionTypeDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ProductionType> getAll() throws SQLException {
		List<ProductionType> productionTypes = new ArrayList<ProductionType>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionType productionType = new ProductionType();
				productionType.setId(rs.getInt("id"));
				productionType.setProductionType(rs.getString("production_type"));

				productionTypes.add(productionType);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productionTypes;
	}
}
