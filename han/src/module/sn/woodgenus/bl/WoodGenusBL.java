package module.sn.woodgenus.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.sn.woodgenus.dao.WoodGenusDAO;
import module.sn.woodgenus.model.WoodGenus;

public class WoodGenusBL {
	private DataSource dataSource;

	public WoodGenusBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<WoodGenus> getAllWoodGenus() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new WoodGenusDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<WoodGenus> getAllWoodGenusBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new WoodGenusDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}

	public void save(WoodGenus woodGenus) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new WoodGenusDAO(con).save(woodGenus);

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public void update(WoodGenus woodGenus) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new WoodGenusDAO(con).update(woodGenus);

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public void delete(WoodGenus woodGenus) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new WoodGenusDAO(con).delete(woodGenus.getId());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public WoodGenus getWoodGenusById(Integer woodGenusId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new WoodGenusDAO(con).getById(woodGenusId);
		} finally {
			con.close();
		}
	}
}
