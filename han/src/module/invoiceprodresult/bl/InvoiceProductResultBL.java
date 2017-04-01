package module.invoiceprodresult.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.invoiceprodresult.dao.InvoiceProdResultDAO;
import module.invoiceprodresult.model.InvoiceProdResult;
import module.invoiceprodresult.dao.InvPrProductDAO;
import module.invoiceprodresult.model.InvPrProduct;

public class InvoiceProductResultBL {

	private DataSource dataSource;

	public InvoiceProductResultBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<InvoiceProdResult> getAllInvoiceProdResult() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvoiceProdResultDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<InvoiceProdResult> getAllInvoiceProdResultBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvoiceProdResultDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}
	
	public InvoiceProdResult getRPRById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvoiceProdResultDAO(con).getRPRById(id);
		} finally {
			con.close();
		}
	}
	
	public InvoiceProdResult getPayPrById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvoiceProdResultDAO(con).getPayPrById(id);
		} finally {
			con.close();
		}
	}
	
	public List<InvPrProduct> getInvPrProductByPPRCode(int idInvPr) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvPrProductDAO(con).getAllByPPRCode(idInvPr);
		} finally {
			con.close();
		}
	}
	
	public List<InvPrProduct> getInvPrProductByRPRCode(String rprCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvPrProductDAO(con).getAllByRPRCode(rprCode);
		} finally {
			con.close();
		}
	}
	
	public void save(InvoiceProdResult ppr, List<InvPrProduct> pprProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			ppr.setStatus(AppConstants.STATUS_COMPLETED);
			
			ppr = new InvoiceProdResultDAO(con).save(ppr);

			for (InvPrProduct s : pprProducts) {
				s.setIdInvPr(ppr.getId());
				new InvPrProductDAO(con).save(s);
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
	
	public void update(InvoiceProdResult ppr, List<InvPrProduct> pprProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new InvoiceProdResultDAO(con).update(ppr);

			for (InvPrProduct s : pprProducts) {
				if (s.getId() == 0) {
					s.setIdInvPr(ppr.getId());
					new InvPrProductDAO(con).save(s);
				} else {
					new InvPrProductDAO(con).update(s);
				}
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

}
