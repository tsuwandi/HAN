package module.production.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.ProductionType;

public class ProductionTypeDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	
	private String getAllQuery = "SELECT id, production_type, production_type_code, description FROM production_type WHERE deleted_date IS NULL ";
	
	public ProductionTypeDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<ProductionType> getAll() throws SQLException {
		List<ProductionType> productionTypes = new ArrayList<ProductionType>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionType pt = new ProductionType();
				pt.setId(rs.getInt("id"));
				pt.setProductionType(rs.getString("production_type"));
				pt.setProductionTypeCode(rs.getString("production_type_code"));
				pt.setDescription(rs.getString("description"));
				productionTypes.add(pt);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productionTypes;
	}
}
