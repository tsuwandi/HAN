package module.productionwaste.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.production.dao.GroupShiftDAO;
import module.production.dao.LineDAO;
import module.production.dao.ProductionTypeDAO;
import module.production.dao.ShiftDAO;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.productionwaste.dao.PWProductDAO;
import module.productionwaste.dao.ProductionWasteDAO;
import module.productionwaste.model.PWProduct;
import module.productionwaste.model.ProductionWaste;

public class ProductionWasteBL  {
	private DataSource dataSource;

	public ProductionWasteBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<GroupShift> getAllGroupShift() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new GroupShiftDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<Shift> getAllShift() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ShiftDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<Line> getAllLine() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new LineDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<ProductionType> getAllProductionType() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionTypeDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public ProductionWaste getProductionWasteById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionWasteDAO(con).getById(id);
		} finally {
			con.close();
		}
	}
	
	public List<PWProduct> getPWProductByPwCode(String pwCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PWProductDAO(con).getAllByPwCode(pwCode);
		} finally {
			con.close();
		}
	}
	
	public List<ProductionWaste> getAllProductionWaste() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionWasteDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<ProductionWaste> getAllProductionWasteBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionWasteDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}
	
	private static final String STATUS = "COMPLETED";
	public void save(ProductionWaste pw, List<PWProduct> pwProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			pw.setStatus(STATUS);
			
			new ProductionWasteDAO(con).save(pw);

			for (PWProduct s : pwProducts) {
				s.setPwCode(pw.getPwCode());
				new PWProductDAO(con).save(s);
			}
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public void update(ProductionWaste pw, List<PWProduct> pwProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ProductionWasteDAO(con).update(pw);

			for (PWProduct s : pwProducts) {
				new PWProductDAO(con).update(s);
			}
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public void deleteAll(ProductionWaste pw) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ProductionWasteDAO(con).delete(pw.getId());
			new PWProductDAO(con).deleteAll(pw.getPwCode());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public int isPWCodeExists(String pwCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionWasteDAO(con).isPWCodeExists(pwCode);
		} finally {
			con.close();
		}
	}
	
}
