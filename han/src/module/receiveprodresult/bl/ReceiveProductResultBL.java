package module.receiveprodresult.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.product.dao.ProductDAO;
import module.product.model.Product;
import module.receiveprodresult.dao.RPRNoteDAO;
import module.receiveprodresult.dao.RPRProductDAO;
import module.receiveprodresult.dao.ReceiveProdResultDAO;
import module.receiveprodresult.model.RPRNote;
import module.receiveprodresult.model.RPRProduct;
import module.receiveprodresult.model.ReceiveProdResult;
import module.sn.currency.dao.CurrencyDAO;
import module.sn.currency.model.Currency;
import module.sn.production.type.dao.ProductionTypeDAO;
import module.sn.production.type.model.ProductionType;
import module.supplier.dao.SupplierDAO;
import module.supplier.model.Supplier;

public class ReceiveProductResultBL  {
	private DataSource dataSource;

	public ReceiveProductResultBL(DataSource dataSource) {
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
	
	public ReceiveProdResult getRPRById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceiveProdResultDAO(con).getRPRById(id);
		} finally {
			con.close();
		}
	}
	
	public ReceiveProdResult getPPRById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceiveProdResultDAO(con).getPPRById(id);
		} finally {
			con.close();
		}
	}
	
	public List<RPRProduct> getRPRProductByRPRCode(String rprCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new RPRProductDAO(con).getAllByRPRCode(rprCode);
		} finally {
			con.close();
		}
	}
	
	public List<RPRProduct> getPPRProductByPPRCode(String pprCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new RPRProductDAO(con).getAllByPPRCode(pprCode);
		} finally {
			con.close();
		}
	}
	
	public List<RPRNote> getRPRNoteByRPRCode(String rprCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new RPRNoteDAO(con).getAllByRPRCode(rprCode);
		} finally {
			con.close();
		}
	}
	
	public List<ReceiveProdResult> getAllReceiveProdResult() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceiveProdResultDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<ReceiveProdResult> getAllReceiveProdResultBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceiveProdResultDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}
	
	public void save(ReceiveProdResult rpr, List<RPRProduct> rprProducts, List<RPRNote> rprNotes)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			rpr.setStatus(AppConstants.STATUS_COMPLETED);
			
			new ReceiveProdResultDAO(con).save(rpr);

			for (RPRProduct s : rprProducts) {
				s.setRprCode(rpr.getRprCode());
				new RPRProductDAO(con).save(s);
			}
			
			for (RPRNote s : rprNotes) {
				s.setRprCode(rpr.getRprCode());
				new RPRNoteDAO(con).save(s);
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
	
	public void update(ReceiveProdResult rpr, List<RPRProduct> rprProducts, List<RPRProduct> rprProductsDeleted,
			List<RPRNote> rprNotes, List<RPRNote> rprNotesDeleted)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ReceiveProdResultDAO(con).update(rpr);

			for (RPRProduct s : rprProducts) {
				if (s.getId() == 0) {
					s.setRprCode(rpr.getRprCode());
					new RPRProductDAO(con).save(s);
				} else {
					new RPRProductDAO(con).update(s);
				}
			}

			for (RPRProduct s : rprProductsDeleted) {
				if (s.getId() != 0)
					new RPRProductDAO(con).deleteById(s.getId());
			}
			
			for (RPRNote s : rprNotes) {
				if (s.getId() == 0) {
					s.setRprCode(rpr.getRprCode());
					new RPRNoteDAO(con).save(s);
				} else {
					new RPRNoteDAO(con).update(s);
				}
			}

			for (RPRNote s : rprNotesDeleted) {
				if (s.getId() != 0)
					new RPRNoteDAO(con).deleteById(s.getId());
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
	
	public void deleteAll(ReceiveProdResult rpr) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ReceiveProdResultDAO(con).delete(rpr.getId());
			new RPRProductDAO(con).deleteAll(rpr.getPprCode());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public int isRPRCodeExists(String rprCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceiveProdResultDAO(con).isRPRCodeExists(rprCode);
		} finally {
			con.close();
		}
	}
	
	public String getOrdinalOfCodeNumber(int year) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return String.format("%04d",
					new ReceiveProdResultDAO(con).getOrdinalOfCodeNumberByYear(year) + 1);

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
