package module.sn.woodtype.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.sn.woodgenus.dao.WoodGenusDAO;
import module.sn.woodgenus.model.WoodGenus;
import module.sn.woodtype.dao.WoodTypeDAO;
import module.sn.woodtype.model.WoodType;

public class WoodTypeBL {
	private DataSource dataSource;

	public WoodTypeBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<WoodType> getAllWoodType() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new WoodTypeDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<WoodType> getAllWoodTypeBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new WoodTypeDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}

	public void save(WoodType woodType) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new WoodTypeDAO(con).save(woodType);

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public void update(WoodType woodType) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new WoodTypeDAO(con).update(woodType);

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public void delete(WoodType woodType) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new WoodTypeDAO(con).delete(woodType.getId());
			
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public WoodType getWoodTypeById(Integer woodTypeId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new WoodTypeDAO(con).getById(woodTypeId);
		} finally {
			con.close();
		}
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
}
