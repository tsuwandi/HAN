package module.sn.costcenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.costcenter.model.CostCenter;

public class CostCenterDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "select id, cost_center from cost_center where deleted_date is null ";

	public CostCenterDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<CostCenter> getAll() throws SQLException {

		List<CostCenter> costcenters = new ArrayList<CostCenter>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				CostCenter costcenter = new CostCenter();
				costcenter.setId(rs.getInt("id"));
				costcenter.setCostCenter(rs.getString("cost_center"));
				costcenters.add(costcenter);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return costcenters;
	}

	public List<CostCenter> getAllByCostCenter(CostCenter costCenter) throws SQLException {

		List<CostCenter> costcenters = new ArrayList<CostCenter>();

		try {
			StringBuilder query = new StringBuilder().append(getAllQuery);
			query.append("and lower(cost_center) like lower('%");
			query.append(costCenter.getCostCenter());
			query.append("%') ");
			
			getAllStatement = connection.prepareStatement(query.toString());
			
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				CostCenter costcenter = new CostCenter();
				costcenter.setId(rs.getInt("id"));
				costcenter.setCostCenter(rs.getString("cost_center"));
				costcenters.add(costcenter);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return costcenters;
	}
}
