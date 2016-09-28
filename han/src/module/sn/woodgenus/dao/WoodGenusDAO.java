package module.sn.woodgenus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.woodgenus.model.WoodGenus;
import module.util.DateUtil;

public class WoodGenusDAO {
	private Connection connection;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;

	private String insertQuery = "insert into wood_genus (wood_genus, input_date, input_by) values (?,?,?)";
	private String updateQuery = "update wood_genus set wood_genus=?, edit_date=?, edited_by=? where id=?";
	private String deleteQuery = "update wood_genus set deleted_date=?, deleted_by=? where id=?";
	private String getAllQuery = "select id, wood_genus from wood_genus ";

	public WoodGenusDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public void save(WoodGenus woodGenus) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, woodGenus.getWoodGenus());
			insertStatement.setDate(2, DateUtil.getCurrentDate());
			insertStatement.setString(3, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(WoodGenus woodGenus) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, woodGenus.getWoodGenus());
			updateStatement.setDate(2, DateUtil.getCurrentDate());
			updateStatement.setString(3, "timotius");
			updateStatement.setInt(4, woodGenus.getId());

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

	public List<WoodGenus> getAll() throws SQLException {
		List<WoodGenus> woodGenuses = new ArrayList<WoodGenus>();

		try {
			String query = new StringBuilder().append(getAllQuery).append("where deleted_date is null order by id ")
					.toString();

			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				WoodGenus woodGenus = new WoodGenus();
				woodGenus.setId(rs.getInt("id"));
				woodGenus.setWoodGenus(rs.getString("wood_genus"));

				woodGenuses.add(woodGenus);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return woodGenuses;
	}

	public WoodGenus getById(Integer woodGenusId) throws SQLException {
		WoodGenus woodGenus = null;
		try {
			String query = new StringBuilder().append(getAllQuery).append("WHERE deleted_date is null and id = ? ")
					.toString();

			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, woodGenusId);
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				woodGenus = new WoodGenus();
				woodGenus.setId(rs.getInt("id"));
				woodGenus.setWoodGenus(rs.getString("wood_genus"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return woodGenus;
	}

	public List<WoodGenus> getAllBySimpleSearch(String value) throws SQLException {
		List<WoodGenus> woodGenuses = new ArrayList<WoodGenus>();

		try {
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value).append("%").toString();
				String query = new StringBuilder().append(getAllQuery).append(" where")
						.append(" (lower(wood_genus) like lower('%s')")
						.append(" or lower(id) like lower('%s')) and deleted_date is null order by id ").toString();
				getAllStatement = connection.prepareStatement(String.format(query, keyword, keyword));
			} else {
				String query = new StringBuilder().append(getAllQuery).append("where deleted_date is null order by id ")
						.toString();

				getAllStatement = connection.prepareStatement(query);
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				WoodGenus woodGenus = new WoodGenus();
				woodGenus.setId(rs.getInt("id"));
				woodGenus.setWoodGenus(rs.getString("wood_genus"));

				woodGenuses.add(woodGenus);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return woodGenuses;
	}

}
