package module.prodpk.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.production.dao.GroupShiftDAO;
import module.production.dao.LineDAO;
import module.production.dao.ProductionTypeDAO;
import module.production.dao.ShiftDAO;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.prodpk.dao.ProdPKMaterialDAO;
import module.prodpk.dao.ProdPKResultProductDAO;
import module.prodpk.dao.ProdPKDAO;
import module.prodpk.model.ProdPKMaterial;
import module.prodpk.model.ProdPKResultProduct;
import module.prodpk.model.ProdPK;

public class ProdPKBL  {
	private DataSource dataSource;

	public ProdPKBL(DataSource dataSource) {
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
	
	public ProdPK getProdPKById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProdPKDAO(con).getById(id);
		} finally {
			con.close();
		}
	}
	
	public List<ProdPKMaterial> getProdPKMaterialByProdPKCode(String prodPKCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProdPKMaterialDAO(con).getAllByProdPKCode(prodPKCode);
		} finally {
			con.close();
		}
	}
	
	public List<ProdPKResultProduct> getProdPKResultProductByProdPKCode(String prodPKCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProdPKResultProductDAO(con).getAllByProdPKCode(prodPKCode);
		} finally {
			con.close();
		}
	}
	
	public List<ProdPK> getAllProdPK(String type) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProdPKDAO(con).getAll(type);
		} finally {
			con.close();
		}
	}

	public List<ProdPK> getAllProdPKBySimpleSearch(String value, String type) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProdPKDAO(con).getAllBySimpleSearch(value, type);
		} finally {
			con.close();
		}
	}
	
	public void save(ProdPK prodPK, List<ProdPKMaterial> prodPKMaterials, List<ProdPKResultProduct> prodPKResultProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			prodPK.setStatus(AppConstants.STATUS_COMPLETED);
			
			new ProdPKDAO(con).save(prodPK);

			for (ProdPKMaterial s : prodPKMaterials) {
				s.setProdPKCode(prodPK.getProdPKCode());
				new ProdPKMaterialDAO(con).save(s);
			}
			
			for (ProdPKResultProduct s : prodPKResultProducts) {
				s.setProdPKCode(prodPK.getProdPKCode());
				new ProdPKResultProductDAO(con).save(s);
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
	
	public void update(ProdPK prodPK, List<ProdPKMaterial> prodPKMaterials, List<ProdPKResultProduct> prodPKResultProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ProdPKDAO(con).update(prodPK);

			for (ProdPKMaterial s : prodPKMaterials) {
				new ProdPKMaterialDAO(con).update(s);
			}
			
			for (ProdPKResultProduct s : prodPKResultProducts) {
				new ProdPKResultProductDAO(con).update(s);
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
	
	public void deleteAll(ProdPK pw) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ProdPKDAO(con).delete(pw.getId());
			new ProdPKMaterialDAO(con).deleteAll(pw.getProdPKCode());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public int isProdPKCodeExists(String prodPKCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProdPKDAO(con).isProdPKCodeExists(prodPKCode);
		} finally {
			con.close();
		}
	}
	
	public String getOrdinalOfCodeNumber(int year, String type) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return String.format("%04d",
					new ProdPKDAO(con).getOrdinalOfCodeNumberByYearAndType(year,type) + 1);

		} finally {
			con.close();
		}
	}
	
}
