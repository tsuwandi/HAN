package module.purchaseprodresult.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.product.dao.ProductDAO;
import module.product.model.Product;
import module.purchaseprodresult.dao.PPRProductDAO;
import module.purchaseprodresult.dao.PurchaseProdResultDAO;
import module.purchaseprodresult.model.PPRProduct;
import module.purchaseprodresult.model.PurchaseProdResult;
import module.sn.currency.dao.CurrencyDAO;
import module.sn.currency.model.Currency;
import module.sn.production.type.dao.ProductionTypeDAO;
import module.sn.production.type.model.ProductionType;
import module.supplier.dao.SupplierDAO;
import module.supplier.model.Supplier;

public class PurchaseProductResultBL  {
	private DataSource dataSource;

	public PurchaseProductResultBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Product> getAllByProductionTypeId(int productionTypeId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllByProductionTypeId(productionTypeId);
		} finally {
			con.close();
		}
	}
	
	public List<Currency> getAllCurrency() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new CurrencyDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<Supplier> getAllSupplierBySuppTypeId(int suppTypeId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SupplierDAO(con).getAllSupplierBySuppTypeId(suppTypeId);
		} finally {
			con.close();
		}
	}
	
	public PurchaseProdResult getPPRById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PurchaseProdResultDAO(con).getById(id);
		} finally {
			con.close();
		}
	}
	
	public List<PPRProduct> getPPRProductByPPRCode(String pprCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PPRProductDAO(con).getAllByPPRCode(pprCode);
		} finally {
			con.close();
		}
	}
	
	public List<PurchaseProdResult> getAllPurchaseProdResult(String status) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PurchaseProdResultDAO(con).getAll(status);
		} finally {
			con.close();
		}
	}

	public List<PurchaseProdResult> getAllPurchaseProdResultBySimpleSearch(String value, String status) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PurchaseProdResultDAO(con).getAllBySimpleSearch(value, status);
		} finally {
			con.close();
		}
	}
	
	public void save(PurchaseProdResult ppr, List<PPRProduct> pprProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			ppr.setStatus(AppConstants.STATUS_COMPLETED);
			
			new PurchaseProdResultDAO(con).save(ppr);

			for (PPRProduct s : pprProducts) {
				s.setPprCode(ppr.getPprCode());
				new PPRProductDAO(con).save(s);
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
	
	public void update(PurchaseProdResult ppr, List<PPRProduct> pprProducts, List<PPRProduct> pprProductsDeleted)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new PurchaseProdResultDAO(con).update(ppr);

			for (PPRProduct s : pprProducts) {
				if (s.getId() == 0) {
					s.setPprCode(ppr.getPprCode());
					new PPRProductDAO(con).save(s);
				} else {
					new PPRProductDAO(con).update(s);
				}
			}

			for (PPRProduct s : pprProductsDeleted) {
				if (s.getId() != 0)
					new PPRProductDAO(con).deleteById(s.getId());
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
	
	public void deleteAll(PurchaseProdResult ppr) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new PurchaseProdResultDAO(con).delete(ppr.getId());
			new PPRProductDAO(con).deleteAll(ppr.getPprCode());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public int isPPRCodeExists(String pprCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PurchaseProdResultDAO(con).isPPRCodeExists(pprCode);
		} finally {
			con.close();
		}
	}
	
	public String getOrdinalOfCodeNumber(int year) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return String.format("%04d",
					new PurchaseProdResultDAO(con).getOrdinalOfCodeNumberByYear(year) + 1);

		} finally {
			con.close();
		}
	}
	
	public List<ProductionType> getAllByProductionType() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionTypeDAO(con).getAll();
		} finally {
			con.close();
		}
	}
}
