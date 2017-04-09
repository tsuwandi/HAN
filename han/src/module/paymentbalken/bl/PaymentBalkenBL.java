package module.paymentbalken.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.paymentbalken.dao.PayBalkenProductDAO;
import module.paymentbalken.dao.PaymentBalkenDAO;
import module.paymentbalken.model.PayBalkenProduct;
import module.paymentbalken.model.PaymentBalken;
import module.invoicebalken.dao.InvoiceBalkenDAO;
import module.invoicebalken.model.InvoiceBalken;

public class PaymentBalkenBL {

	private DataSource dataSource;

	public PaymentBalkenBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<PaymentBalken> getAllPaymentBalken() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PaymentBalkenDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<PaymentBalken> getAllPaymentBalkenBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PaymentBalkenDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}
	
	public PaymentBalken getRPRById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PaymentBalkenDAO(con).getRPRById(id);
		} finally {
			con.close();
		}
	}
	
	public PaymentBalken getPayPrById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PaymentBalkenDAO(con).getPayPrById(id);
		} finally {
			con.close();
		}
	}
	
	public List<PayBalkenProduct> getPayBalkenProductByPPRCode(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PayBalkenProductDAO(con).getAllByPPRCode(id);
		} finally {
			con.close();
		}
	}
	
	public List<PayBalkenProduct> getPayBalkenProductByIdInvPr(int idInvPr) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PayBalkenProductDAO(con).getAllByIdInvPr(idInvPr);
		} finally {
			con.close();
		}
	}
	
	public void save(PaymentBalken ppr, List<PayBalkenProduct> pprProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			ppr.setStatus(AppConstants.STATUS_COMPLETED);
			
			ppr = new PaymentBalkenDAO(con).save(ppr);

			for (PayBalkenProduct s : pprProducts) {
				s.setPaymentBalkenId(ppr.getId());
				new PayBalkenProductDAO(con).save(s);
			}
			
			if("Ya".equals(ppr.getPaymentStatus())) {
				InvoiceBalken ipr = new InvoiceBalken();
				ipr.setId(ppr.getInventoryBalkenId());
				ipr.setPaymentStatus(ppr.getPaymentStatus());
				ipr.setPaymentDate(ppr.getPaymentDate());
				new InvoiceBalkenDAO(con).updatePaymentStatusStatement(ipr);
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
	
	public void update(PaymentBalken ppr, List<PayBalkenProduct> pprProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new PaymentBalkenDAO(con).update(ppr);

			for (PayBalkenProduct s : pprProducts) {
				if (s.getId() == 0) {
					s.setPaymentBalkenId(ppr.getId());
					new PayBalkenProductDAO(con).save(s);
				} else {
					new PayBalkenProductDAO(con).update(s);
				}
			}
			
			if("Ya".equals(ppr.getPaymentStatus())) {
				InvoiceBalken ipr = new InvoiceBalken();
				ipr.setId(ppr.getInventoryBalkenId());
				ipr.setPaymentStatus(ppr.getPaymentStatus());
				ipr.setPaymentDate(ppr.getPaymentDate());
				new InvoiceBalkenDAO(con).updatePaymentStatusStatement(ipr);
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
