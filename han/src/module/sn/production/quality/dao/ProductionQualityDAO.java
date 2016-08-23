package module.sn.production.quality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.production.quality.model.ProductionQuality;

public class ProductionQualityDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;

	private String getAllQuery = "select id, production_quality from production_quality order by production_quality";

	public ProductionQualityDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ProductionQuality> getAll() throws SQLException {
		List<ProductionQuality> productionQualitys = new ArrayList<ProductionQuality>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionQuality productionQuality = new ProductionQuality();
				productionQuality.setId(rs.getInt("id"));
				productionQuality.setProductionQuality(rs.getString("production_quality"));

				productionQualitys.add(productionQuality);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productionQualitys;
	}
}
