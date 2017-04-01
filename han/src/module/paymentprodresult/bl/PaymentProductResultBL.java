package module.paymentprodresult.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.paymentprodresult.dao.PayPrProductDAO;
import module.paymentprodresult.dao.PaymentProdResultDAO;
import module.paymentprodresult.model.PayPrProduct;
import module.paymentprodresult.model.PaymentProdResult;
import module.receiveprodresult.dao.RPRNoteDAO;
import module.receiveprodresult.dao.RPRProductDAO;
import module.receiveprodresult.dao.ReceiveProdResultDAO;
import module.receiveprodresult.model.RPRNote;
import module.receiveprodresult.model.RPRProduct;
import module.receiveprodresult.model.ReceiveProdResult;

public class PaymentProductResultBL {

	private DataSource dataSource;

	public PaymentProductResultBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<PaymentProdResult> getAllPaymentProdResult() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PaymentProdResultDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<PaymentProdResult> getAllPaymentProdResultBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PaymentProdResultDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}
	
	public PaymentProdResult getRPRById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PaymentProdResultDAO(con).getRPRById(id);
		} finally {
			con.close();
		}
	}
	
	public PaymentProdResult getPayPrById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PaymentProdResultDAO(con).getPayPrById(id);
		} finally {
			con.close();
		}
	}
	
	public List<PayPrProduct> getPayPrProductByPPRCode(String pprCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PayPrProductDAO(con).getAllByPPRCode(pprCode);
		} finally {
			con.close();
		}
	}
	
	public List<PayPrProduct> getPayPrProductByIdInvPr(int idInvPr) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PayPrProductDAO(con).getAllByIdInvPr(idInvPr);
		} finally {
			con.close();
		}
	}
	
	public int isPPRCodeExists(String rprCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PaymentProdResultDAO(con).isPPRCodeExists(rprCode);
		} finally {
			con.close();
		}
	}
	
	public String getOrdinalOfCodeNumber(int year) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return String.format("%04d",
					new PaymentProdResultDAO(con).getOrdinalOfCodeNumberByYear(year) + 1);

		} finally {
			con.close();
		}
	}
	
	public void save(PaymentProdResult ppr, List<PayPrProduct> pprProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			ppr.setStatus(AppConstants.STATUS_COMPLETED);
			
			new PaymentProdResultDAO(con).save(ppr);

			for (PayPrProduct s : pprProducts) {
				s.setPayPrCode(ppr.getPayPrCode());
				new PayPrProductDAO(con).save(s);
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
	
	public void update(PaymentProdResult ppr, List<PayPrProduct> pprProducts)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new PaymentProdResultDAO(con).update(ppr);

			for (PayPrProduct s : pprProducts) {
				if (s.getId() == 0) {
					s.setPayPrCode(ppr.getPayPrCode());
					new PayPrProductDAO(con).save(s);
				} else {
					new PayPrProductDAO(con).update(s);
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
