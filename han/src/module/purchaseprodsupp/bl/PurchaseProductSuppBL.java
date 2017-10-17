package module.purchaseprodsupp.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.product.dao.ProductDAO;
import module.product.model.Product;
import module.purchaseprodsupp.dao.PPSProductDAO;
import module.purchaseprodsupp.dao.PurchaseProdSuppDAO;
import module.purchaseprodsupp.model.PPSProduct;
import module.purchaseprodsupp.model.PurchaseProdSupp;
import module.sn.costcenter.dao.CostCenterDAO;
import module.sn.costcenter.model.CostCenter;
import module.sn.currency.dao.CurrencyDAO;
import module.sn.currency.model.Currency;
import module.sn.production.type.dao.ProductionTypeDAO;
import module.sn.production.type.model.ProductionType;
import module.supplier.dao.SupplierDAO;
import module.supplier.model.Supplier;

public class PurchaseProductSuppBL  {
	private DataSource dataSource;

	public PurchaseProductSuppBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Product> getAllByProductCategoryId(int productionCategoryId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllByProductCategoryId(productionCategoryId);
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
	
	public List<CostCenter> getAllCostCenter() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new CostCenterDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public PurchaseProdSupp getPPSById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PurchaseProdSuppDAO(con).getById(id);
		} finally {
			con.close();
		}
	}
	
	public List<PPSProduct> getPPSProductByPPSCode(String ppsCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PPSProductDAO(con).getAllByPPSCode(ppsCode);
		} finally {
			con.close();
		}
	}
	
	public List<PurchaseProdSupp> getAllPurchaseProdSupp(String status) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PurchaseProdSuppDAO(con).getAll(status);
		} finally {
			con.close();
		}
	}

	public List<PurchaseProdSupp> getAllPurchaseProdSuppBySimpleSearch(String value, String status) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PurchaseProdSuppDAO(con).getAllBySimpleSearch(value, status);
		} finally {
			con.close();
		}
	}
	
	public void save(PurchaseProdSupp pps, List<PPSProduct> ppsProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			pps.setStatus(AppConstants.STATUS_COMPLETED);
			
			new PurchaseProdSuppDAO(con).save(pps);

			for (PPSProduct s : ppsProducts) {
				s.setPpsCode(pps.getPpsCode());
				new PPSProductDAO(con).save(s);
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
	
	public void update(PurchaseProdSupp pps, List<PPSProduct> ppsProducts, List<PPSProduct> ppsProductsDeleted)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new PurchaseProdSuppDAO(con).update(pps);

			for (PPSProduct s : ppsProducts) {
				if (s.getId() == 0) {
					s.setPpsCode(pps.getPpsCode());
					new PPSProductDAO(con).save(s);
				} else {
					new PPSProductDAO(con).update(s);
				}
			}

			for (PPSProduct s : ppsProductsDeleted) {
				if (s.getId() != 0)
					new PPSProductDAO(con).deleteById(s.getId());
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
	
	public void deleteAll(PurchaseProdSupp pps) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new PurchaseProdSuppDAO(con).delete(pps.getId());
			new PPSProductDAO(con).deleteAll(pps.getPpsCode());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public int isPPSCodeExists(String ppsCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PurchaseProdSuppDAO(con).isPPSCodeExists(ppsCode);
		} finally {
			con.close();
		}
	}
	
	public String getOrdinalOfCodeNumber(int year) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return String.format("%04d",
					new PurchaseProdSuppDAO(con).getOrdinalOfCodeNumberByYear(year) + 1);

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
