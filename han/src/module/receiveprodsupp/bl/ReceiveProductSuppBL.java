package module.receiveprodsupp.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.productsupportinggood.dao.ProductSuppDAO;
import module.productsupportinggood.model.ProductSupp;
import module.purchaseprodsupp.dao.PurchaseProdSuppDAO;
import module.purchaseprodsupp.model.PurchaseProdSupp;
import module.receiveprodsupp.dao.RPSProductDAO;
import module.receiveprodsupp.dao.ReceiveProdSuppDAO;
import module.receiveprodsupp.model.RPSProduct;
import module.receiveprodsupp.model.ReceiveProdSupp;
import module.sn.costcenter.dao.CostCenterDAO;
import module.sn.costcenter.model.CostCenter;
import module.sn.currency.dao.CurrencyDAO;
import module.sn.currency.model.Currency;
import module.sn.productcategory.dao.ProductCategoryDAO;
import module.sn.productcategory.model.ProductCategory;
import module.sn.production.type.dao.ProductionTypeDAO;
import module.sn.production.type.model.ProductionType;
import module.supplier.dao.SupplierDAO;
import module.supplier.model.Supplier;

public class ReceiveProductSuppBL  {
	private DataSource dataSource;

	public ReceiveProductSuppBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<ProductSupp> getAllByProductCategoryId(int productionCategoryId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			ProductSupp productSupp = new ProductSupp();
			productSupp.setProductCategoryId(productionCategoryId);
			return new ProductSuppDAO(con).getAllByProductCategoryId(productSupp);
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
	
	public ReceiveProdSupp getRPSById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceiveProdSuppDAO(con).getById(id);
		} finally {
			con.close();
		}
	}
	
	public List<RPSProduct> getRPSProductByRPSCode(String rpsCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new RPSProductDAO(con).getAllByRPSCode(rpsCode);
		} finally {
			con.close();
		}
	}
	
	public List<ReceiveProdSupp> getAllReceiveProdSupp(String status) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceiveProdSuppDAO(con).getAll(status);
		} finally {
			con.close();
		}
	}

	public List<ReceiveProdSupp> getAllReceiveProdSuppBySimpleSearch(String value, String status) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceiveProdSuppDAO(con).getAllBySimpleSearch(value, status);
		} finally {
			con.close();
		}
	}
	
	public void save(ReceiveProdSupp rps, List<RPSProduct> rpsProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			rps.setStatus(AppConstants.STATUS_COMPLETED);
			
			new ReceiveProdSuppDAO(con).save(rps);

			for (RPSProduct s : rpsProducts) {
				s.setRpsCode(rps.getRpsCode());
				new RPSProductDAO(con).save(s);
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
	
	public void update(ReceiveProdSupp rps, List<RPSProduct> rpsProducts, List<RPSProduct> rpsProductsDeleted)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ReceiveProdSuppDAO(con).update(rps);

			for (RPSProduct s : rpsProducts) {
				if (s.getId() == 0) {
					s.setRpsCode(rps.getPpsCode());
					new RPSProductDAO(con).save(s);
				} else {
					new RPSProductDAO(con).update(s);
				}
			}

			for (RPSProduct s : rpsProductsDeleted) {
				if (s.getId() != 0)
					new RPSProductDAO(con).deleteById(s.getId());
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
	
	public void deleteAll(ReceiveProdSupp rps) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ReceiveProdSuppDAO(con).delete(rps.getId());
			new RPSProductDAO(con).deleteAll(rps.getPpsCode());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public int isRPSCodeExists(String rpsCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceiveProdSuppDAO(con).isRPSCodeExists(rpsCode);
		} finally {
			con.close();
		}
	}
	
	public String getOrdinalOfCodeNumber(int year) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return String.format("%04d",
					new ReceiveProdSuppDAO(con).getOrdinalOfCodeNumberByYear(year) + 1);

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
	
	public List<PurchaseProdSupp> getAllPurchaseProdSuppByAdvancedSearch(PurchaseProdSupp purchaseProdSupp) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PurchaseProdSuppDAO(con).getAllPurchaseProdSuppByAdvancedSearch(purchaseProdSupp);
		} finally {
			con.close();
		}
	}
	
	public List<CostCenter> getAllCostCenterByAdvancedSearch(CostCenter costCenter) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new CostCenterDAO(con).getAllByCostCenter(costCenter);
		} finally {
			con.close();
		}
	}
	
	public List<ProductCategory> getAllProductCategory(int productCategoryTypeId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductCategoryDAO(con).getAllByProductCategoryTypeId(productCategoryTypeId);
		} finally {
			con.close();
		}
	}
	
	public List<ProductSupp> getAllProductByAdvancedSearchAndPpsCode(ProductSupp productSupp, String ppsCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductSuppDAO(con).getAllByPPSCode(productSupp, ppsCode);
		} finally {
			con.close();
		}
	}
}
