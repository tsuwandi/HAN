package module.dailyclosing.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.dailyclosing.dao.ConfirmDAO;
import module.dailyclosing.dao.InventoryLogTempDAO;
import module.dailyclosing.model.Confirm;
import module.dailyclosing.model.InventoryLogTemp;
import module.dryin.dao.DryInDAO;
import module.dryin.model.DryIn;
import module.dryout.dao.DryOutDAO;
import module.dryout.model.DryOut;
import module.pembelian.dao.ReceivedDAO;
import module.pembelian.model.Received;
import module.util.DateUtil;

public class DailyClosingBL {
	private DataSource dataSource;

	public DailyClosingBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Received> getAllReceivedForDailyClosing() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceivedDAO(con).getAllReceivedForDailyClosing();
		} finally {
			con.close();
		}
	}

	public List<DryIn> getAllDryInForDailyClosing() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryInDAO(con).getAllDryInForDailyClosing();
		} finally {
			con.close();
		}
	}

	public List<DryOut> getAllDryOutForDailyClosing() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryOutDAO(con).getAllDryOutForDailyClosing();
		} finally {
			con.close();
		}
	}

	static final String BUYING_MODULE = "PEMBELIAN";
	static final String RECEIVED = "RECEIVED";
	static final String DRY_IN = "DRY_IN";
	static final String DRY_OUT = "DRY_OUT";
	static final String DEBET = "D";
	static final String CREDIT = "C";

	public void save(List<Received> listOfReceived, List<DryIn> listOfDryIn, List<DryOut> listOfDryOut, String confirmCode)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			Confirm confirm = new Confirm();
			confirm.setConfirmCode(confirmCode);
			confirm.setModule(BUYING_MODULE);
			confirm.setDailyClosingDate(DateUtil.setTimeStamp());
			new ConfirmDAO(con).save(confirm);

			for (Received received : listOfReceived) {
				InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				inventoryLogTemp.setProductCode(received.getPalletCard().getProductCode());
				inventoryLogTemp.setWarehouse(0);
				inventoryLogTemp.setQty(received.getPalletCard().getTotal());
				inventoryLogTemp.setMutasi(DEBET);
				inventoryLogTemp.setSrcTable(RECEIVED);
				inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTemp);
			}

			for (DryIn dryIn : listOfDryIn) {
				InventoryLogTemp inventoryLogTempCredit = new InventoryLogTemp();
				inventoryLogTempCredit.setProductCode(dryIn.getPalletCard().getProductCode());
				inventoryLogTempCredit.setWarehouse(0);
				inventoryLogTempCredit.setQty(dryIn.getPalletCard().getTotal());
				inventoryLogTempCredit.setMutasi(CREDIT);
				inventoryLogTempCredit.setSrcTable(DRY_IN);
				inventoryLogTempCredit.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTempCredit);

				InventoryLogTemp inventoryLogTempDebet = new InventoryLogTemp();
				inventoryLogTempDebet.setProductCode(dryIn.getPalletCard().getProductCode());
				inventoryLogTempDebet.setWarehouse(dryIn.getChamberId());
				inventoryLogTempCredit.setQty(dryIn.getPalletCard().getTotal());
				inventoryLogTempDebet.setMutasi(DEBET);
				inventoryLogTempDebet.setSrcTable(DRY_IN);
				inventoryLogTempDebet.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTempDebet);
			}

			for (DryOut dryOut : listOfDryOut) {
				InventoryLogTemp inventoryLogTempCredit = new InventoryLogTemp();
				inventoryLogTempCredit.setProductCode(dryOut.getPalletCard().getProductCode());
				inventoryLogTempCredit.setWarehouse(dryOut.getChamberId());
				inventoryLogTempCredit.setQty(dryOut.getPalletCard().getTotal());
				inventoryLogTempCredit.setMutasi(CREDIT);
				inventoryLogTempCredit.setSrcTable(DRY_OUT);
				inventoryLogTempCredit.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTempCredit);

				InventoryLogTemp inventoryLogTempDebet = new InventoryLogTemp();
				inventoryLogTempDebet.setProductCode(dryOut.getPalletCard().getProductCode());
				inventoryLogTempDebet.setWarehouse(dryOut.getChamberId());
				inventoryLogTempCredit.setQty(dryOut.getPalletCard().getTotal());
				inventoryLogTempDebet.setMutasi(DEBET);
				inventoryLogTempDebet.setSrcTable(DRY_OUT);
				inventoryLogTempDebet.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTempDebet);
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

	public static String makeConfirmCode() {
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int count = 5;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
