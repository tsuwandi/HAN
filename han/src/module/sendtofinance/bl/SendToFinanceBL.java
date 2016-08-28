package module.sendtofinance.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.dao.ReceivedDAO;
import module.pembelian.model.Received;

public class SendToFinanceBL {

	private DataSource dataSource;

	public SendToFinanceBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Received> getAllBySendToFinanceDateIsNull() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceivedDAO(con).getAllBySendToFinanceDateIsNull();
		} finally {
			con.close();
		}
	}

	public void update(List<Received> listOfReceived) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			for(Received received : listOfReceived) {
				new ReceivedDAO(con).updateSendToFinanceDate(received);
			}
			
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}

	}
}
