package module.pembelian.bl;

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

	public void update() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			List<Received> listOfReceived = new ReceivedDAO(con).getAllBySendToFinanceDateIsNull();

			for(Received received : listOfReceived) {
				new ReceivedDAO(con).updateSendToFinanceDate(received);
			}
			
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}

	}
}
