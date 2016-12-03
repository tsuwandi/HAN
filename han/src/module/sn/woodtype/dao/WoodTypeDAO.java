package module.sn.woodtype.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.sn.woodgenus.model.WoodGenus;
import module.sn.woodtype.model.WoodType;
import module.util.DateUtil;

public class WoodTypeDAO {
	private Connection connection;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;

	private String insertQuery = "insert into wood_type (wood_type, wood_genus_id, input_date, input_by) values (?,?,?,?)";
	private String updateQuery = "update wood_type set wood_type=?, wood_genus_id=?, edit_date=?, edited_by=? where id=?";
	private String deleteQuery = "update wood_type set deleted_date=?, deleted_by=? where id=?";
	private String getAllQuery = "select wt.id, wt.wood_type, wt.wood_genus_id, wg.wood_genus from wood_type wt "
			+ "inner join wood_genus wg on wg.id = wt.wood_genus_id ";

	public WoodTypeDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public void save(WoodType woodType) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, woodType.getWoodType());
			insertStatement.setInt(2, woodType.getWoodGenusId());
			insertStatement.setDate(3, DateUtil.getCurrentDate());
			insertStatement.setString(4, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(WoodType woodType) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, woodType.getWoodType());
			updateStatement.setInt(2, woodType.getWoodGenusId());
			updateStatement.setDate(3, DateUtil.getCurrentDate());
			updateStatement.setString(4, "timotius");
			updateStatement.setInt(5, woodType.getId());

			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void delete(int id) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public List<WoodType> getAll() throws SQLException {
		List<WoodType> woodTypes = new ArrayList<WoodType>();

		try {
			String query = new StringBuilder().append(getAllQuery).append("where wt.deleted_date is null order by wt.id ").toString();
			
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				WoodType woodType = new WoodType();
				woodType.setId(rs.getInt("id"));
				woodType.setWoodType(rs.getString("wood_type"));
				woodType.setWoodGenusId(rs.getInt("wood_genus_id"));

				WoodGenus woodGenus = new WoodGenus();
				woodGenus.setId(rs.getInt("wood_genus_id"));
				woodGenus.setWoodGenus(rs.getString("wood_genus"));

				woodType.setWoodGenus(woodGenus);

				woodTypes.add(woodType);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return woodTypes;
	}
	
	public WoodType getById(Integer woodTypeId) throws SQLException {
		WoodType woodType = null;
		try {
			String query = new StringBuilder().append(getAllQuery).append("WHERE wt.deleted_date is null and wt.id = ? ").toString();
			
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, woodTypeId);
			
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				woodType = new WoodType();
				woodType.setId(rs.getInt("id"));
				woodType.setWoodType(rs.getString("wood_type"));
				
				WoodGenus woodGenus = new WoodGenus();
				woodGenus.setId(rs.getInt("wood_genus_id"));
				woodGenus.setWoodGenus(rs.getString("wood_genus"));

				woodType.setWoodGenus(woodGenus);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return woodType;
	}
	
	public List<WoodType> getAllBySimpleSearch(String value) throws SQLException {
		List<WoodType> woodTypes = new ArrayList<WoodType>();

		try {
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value).append("%").toString();
				String query = new StringBuilder().append(getAllQuery).append(" where")
						.append(" (lower(wg.wood_genus) like lower('%s')").append("or lower(wt.wood_type) like lower('%s')")
						.append(" or lower(wt.id) like lower('%s')) and wt.deleted_date is null order by wt.id ").toString();
				getAllStatement = connection.prepareStatement(String.format(query, keyword, keyword));
			} else {
				String query = new StringBuilder().append(getAllQuery).append("where wt.deleted_date is null order by wt.id ")
						.toString();

				getAllStatement = connection.prepareStatement(query);
			}
			
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				WoodType woodType = new WoodType();
				woodType.setId(rs.getInt("id"));
				woodType.setWoodType(rs.getString("wood_type"));
				woodType.setWoodGenusId(rs.getInt("wood_genus_id"));

				WoodGenus woodGenus = new WoodGenus();
				woodGenus.setId(rs.getInt("wood_genus_id"));
				woodGenus.setWoodGenus(rs.getString("wood_genus"));

				woodType.setWoodGenus(woodGenus);

				woodTypes.add(woodType);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return woodTypes;
	}

	// Vinci Soon To Be Deleted

	private DataSource dataSource;

	public WoodTypeDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}

	private PreparedStatement getWoodTypeStatement;

	private String getWoodTypeQuery = "SELECT id, wood_type FROM wood_type WHERE 1 = 1 ";

	public List<WoodType> getWoodType() throws SQLException {
		Connection con = dataSource.getConnection();
		ArrayList<WoodType> woodTypes = new ArrayList<WoodType>();

		try {
			getWoodTypeStatement = con.prepareStatement(getWoodTypeQuery);

			ResultSet rs = getWoodTypeStatement.executeQuery();
			while (rs.next()) {
				WoodType woodType = new WoodType();
				woodType.setId(rs.getInt("id"));
				woodType.setWoodType(rs.getString("wood_type"));
				woodTypes.add(woodType);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return woodTypes;
	}
}
