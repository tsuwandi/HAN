package module.invoicebalken.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.invoicebalken.dao.InvoiceBalkenDAO;
import module.invoicebalken.model.InvoiceBalken;
import module.invoicebalken.dao.InvBalkenProductDAO;
import module.invoicebalken.model.InvBalkenProduct;

public class InvoiceBalkenBL {

	private DataSource dataSource;

	public InvoiceBalkenBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<InvoiceBalken> getAllInvoiceBalken() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvoiceBalkenDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<InvoiceBalken> getAllInvoiceBalkenBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvoiceBalkenDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}
	
	public InvoiceBalken getRPRById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvoiceBalkenDAO(con).getRPRById(id);
		} finally {
			con.close();
		}
	}
	
	public InvoiceBalken getPayPrById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvoiceBalkenDAO(con).getPayPrById(id);
		} finally {
			con.close();
		}
	}
	
	public List<InvBalkenProduct> getInvBalkenProductByPPRCode(int idInvPr) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvBalkenProductDAO(con).getAllByPPRCode(idInvPr);
		} finally {
			con.close();
		}
	}
	
	public List<InvBalkenProduct> getInvBalkenProductByRPRCode(String rprCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InvBalkenProductDAO(con).getAllByRPRCode(rprCode);
		} finally {
			con.close();
		}
	}
	
	public void save(InvoiceBalken ppr, List<InvBalkenProduct> pprProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			ppr.setStatus(AppConstants.STATUS_COMPLETED);
			
			ppr = new InvoiceBalkenDAO(con).save(ppr);

			for (InvBalkenProduct s : pprProducts) {
				s.setInventoryBalkenId(ppr.getId());
				new InvBalkenProductDAO(con).save(s);
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
	
	public void update(InvoiceBalken ppr, List<InvBalkenProduct> pprProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new InvoiceBalkenDAO(con).update(ppr);

			for (InvBalkenProduct s : pprProducts) {
				if (s.getId() == 0) {
					s.setInventoryBalkenId(ppr.getId());
					new InvBalkenProductDAO(con).save(s);
				} else {
					new InvBalkenProductDAO(con).update(s);
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
