package module.dailyclosing.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.dailyclosing.dao.ConfirmDAO;
import module.dailyclosing.dao.InventoryDAO;
import module.dailyclosing.dao.InventoryLogDAO;
import module.dailyclosing.dao.InventoryLogTempDAO;
import module.dailyclosing.model.Confirm;
import module.dailyclosing.model.Inventory;
import module.dailyclosing.model.InventoryLog;
import module.dailyclosing.model.InventoryLogTemp;
import module.dryin.dao.DryInDAO;
import module.dryin.model.DryIn;
import module.dryout.dao.DryOutDAO;
import module.dryout.model.DryOut;
import module.pembelian.ReceivedType;
import module.pembelian.dao.ReceivedDAO;
import module.pembelian.model.Received;
import module.production.dao.ProdRMDAO;
import module.production.dao.ProductionDAO;
import module.production.dao.ProductionResultDAO;
import module.production.dao.ProductionResultProductDAO;
import module.production.model.ProdRM;
import module.production.model.Production;
import module.production.model.ProductionResult;
import module.production.model.ProductionResultProduct;
import module.purchaseprodresult.dao.PurchaseProdResultDAO;
import module.purchaseprodresult.model.PurchaseProdResult;
import module.util.DateUtil;

import org.apache.log4j.Logger;

public class DailyClosingBL {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DailyClosingBL.class);

	private DataSource dataSource;

	public DailyClosingBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/** START TUTUP HARIAN PENERIMAAN **/
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

	public void save(List<Received> listOfReceived, List<DryIn> listOfDryIn, List<DryOut> listOfDryOut,
			String confirmCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			Confirm confirm = new Confirm();
			confirm.setConfirmCode(confirmCode);
			confirm.setModule(AppConstants.BUYING_MODULE);
			confirm.setDailyClosingDate(DateUtil.setTimeStamp());
			new ConfirmDAO(con).save(confirm);

			for (Received received : listOfReceived) {
				InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				inventoryLogTemp.setProductCode(received.getPalletCard().getProductCode());
				inventoryLogTemp.setWarehouse(0);
				inventoryLogTemp.setQty(received.getPalletCard().getTotal());
				inventoryLogTemp.setMutasi(AppConstants.DEBET);
				inventoryLogTemp.setSrcTable(AppConstants.RECEIVED);
				inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTemp);

				received.setReceivedStatus(ReceivedType.FINAL.toString());

				new ReceivedDAO(con).updateDailyClosing(received);
			}

			for (DryIn dryIn : listOfDryIn) {
				InventoryLogTemp inventoryLogTempCredit = new InventoryLogTemp();
				inventoryLogTempCredit.setProductCode(dryIn.getPalletCard().getProductCode());
				inventoryLogTempCredit.setWarehouse(0);
				inventoryLogTempCredit.setQty(dryIn.getPalletCard().getTotal());
				inventoryLogTempCredit.setMutasi(AppConstants.CREDIT);
				inventoryLogTempCredit.setSrcTable(AppConstants.DRY_IN);
				inventoryLogTempCredit.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTempCredit);

				InventoryLogTemp inventoryLogTempDebet = new InventoryLogTemp();
				inventoryLogTempDebet.setProductCode(dryIn.getPalletCard().getProductCode());
				inventoryLogTempDebet.setWarehouse(dryIn.getChamberId());
				inventoryLogTempDebet.setQty(dryIn.getPalletCard().getTotal());
				inventoryLogTempDebet.setMutasi(AppConstants.DEBET);
				inventoryLogTempDebet.setSrcTable(AppConstants.DRY_IN);
				inventoryLogTempDebet.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTempDebet);

				dryIn.setStatus(AppConstants.STATUS_FINAL);

				new DryInDAO(con).updateDailyClosing(dryIn);
			}

			for (DryOut dryOut : listOfDryOut) {
				InventoryLogTemp inventoryLogTempCredit = new InventoryLogTemp();
				inventoryLogTempCredit.setProductCode(dryOut.getPalletCard().getProductCode());
				inventoryLogTempCredit.setWarehouse(dryOut.getChamberId());
				inventoryLogTempCredit.setQty(dryOut.getPalletCard().getTotal());
				inventoryLogTempCredit.setMutasi(AppConstants.CREDIT);
				inventoryLogTempCredit.setSrcTable(AppConstants.DRY_OUT);
				inventoryLogTempCredit.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTempCredit);

				InventoryLogTemp inventoryLogTempDebet = new InventoryLogTemp();
				inventoryLogTempDebet.setProductCode(dryOut.getPalletCard().getProductCode());
				inventoryLogTempDebet.setWarehouse(dryOut.getChamberId());
				inventoryLogTempDebet.setQty(dryOut.getPalletCard().getTotal());
				inventoryLogTempDebet.setMutasi(AppConstants.DEBET);
				inventoryLogTempDebet.setSrcTable(AppConstants.DRY_OUT);
				inventoryLogTempDebet.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTempDebet);

				dryOut.setStatus(AppConstants.STATUS_FINAL);

				new DryOutDAO(con).updateDailyClosing(dryOut);
			}

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	/** END TUTUP HARIAN PENERIMAAN **/
	
	/** START TUTUP HARIAN PRODUKSI **/
	public List<Production> getAllProductionForDailyClosing() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionDAO(con).getAllProductionForDailyClosing();
		} finally {
			con.close();
		}
	}
	
	public List<ProdRM> getAllProdRMForDailyClosing() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProdRMDAO(con).getAllProdRMForDailyClosing();
		} finally {
			con.close();
		}
	}
	
	public List<ProductionResult> getAllProductionResultForDailyClosing() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionResultDAO(con).getAllProductionResultForDailyClosing();
		} finally {
			con.close();
		}
	}
	
	public List<ProductionResultProduct> getAllProductionResultProductForDailyClosing() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionResultProductDAO(con).getAllProductionResultProductForDailyClosing();
		} finally {
			con.close();
		}
	}
	
	
	public void save(List<Production> listOfProduction,
			List<ProdRM> listOfProdRM,
			List<ProductionResult> listOfProductionResult,
			List<ProductionResultProduct> listOfProductionResultProduct,
			String confirmCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			Confirm confirm = new Confirm();
			confirm.setConfirmCode(confirmCode);
			confirm.setModule(AppConstants.BUYING_MODULE);
			confirm.setDailyClosingDate(DateUtil.setTimeStamp());
			new ConfirmDAO(con).save(confirm);
			
			for (Production production : listOfProduction) {
//				InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
//				inventoryLogTemp.setProductCode(production.getProduct);
//				inventoryLogTemp.setWarehouse(0);
//				inventoryLogTemp.setQty(received.getPalletCard().getTotal());
//				inventoryLogTemp.setMutasi(AppConstants.DEBET);
//				inventoryLogTemp.setSrcTable(AppConstants.RECEIVED);
//				inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());
//
//				new InventoryLogTempDAO(con).save(inventoryLogTemp);
//
//				received.setReceivedStatus(ReceivedType.FINAL.toString());
//
//				new ReceivedDAO(con).updateDailyClosing(received);
			}
			con.rollback();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	/** END TUTUP HARIAN PRODUKSI **/

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

	public void doProcessDailyClosing() throws Exception {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			List<InventoryLogTemp> inventoryLogTemps = new InventoryLogTempDAO(con).getAll();
			
			for(InventoryLogTemp inventoryLogTemp : inventoryLogTemps) {
				double currStock = 0;
				Inventory inventory = null;
				inventory = new InventoryDAO(con).getInventoryByProductCodeAndWarehouse(inventoryLogTemp.getProductCode(), inventoryLogTemp.getWarehouse());
				
				InventoryLog inventoryLog = new InventoryLog();
				inventoryLog.setProductCode(inventoryLogTemp.getProductCode());
				inventoryLog.setWarehouse(inventoryLogTemp.getWarehouse());
				inventoryLog.setConfirmCode(makeConfirmCode());
				if(inventoryLogTemp.getMutasi().equals(AppConstants.DEBET)) {
					inventoryLog.setPrevStock((double) 0);
					inventoryLog.setPlusStock(inventoryLogTemp.getQty());
					inventoryLog.setMinStock((double) 0);
				} else if (inventoryLogTemp.getMutasi().equals(AppConstants.CREDIT)) {
					inventoryLog.setPrevStock((double) 0);
					inventoryLog.setMinStock(inventoryLogTemp.getQty());
					inventoryLog.setPlusStock((double) 0);
					//currStock = inventoryLog.getPrevStock() - inventoryLog.getMinStock();
					//inventoryLog.setCurrStock(currStock);
				} else {
					LOGGER.error("NO DATA MUTASI.");
					throw new Exception("Failed to Process Daily Closing.");
				}
				
				if(inventory == null) {
					currStock = inventoryLog.getPrevStock() + inventoryLog.getPlusStock() - inventoryLog.getMinStock();
					inventoryLog.setCurrStock(currStock);
					inventoryLog.setPrevStockDate(new Date());
					inventoryLog.setCurrStockDate(new Date());
					
					inventoryLog = new InventoryLogDAO(con).save(inventoryLog);
					currStock = inventoryLog.getPrevStock() + inventoryLog.getPlusStock()  - inventoryLog.getMinStock();
					inventoryLog.setCurrStock(currStock);
							
					inventory = new Inventory();
					inventory.setId(0);
					inventory.setProductCode(inventoryLogTemp.getProductCode());
					inventory.setQty(currStock);
					inventory.setWarehouse(inventoryLogTemp.getWarehouse());
					inventory.setStockDate(new Date());
					inventory.setInventoryLogId(inventoryLog.getId());
					
					new InventoryDAO(con).save(inventory);
				} else {
					currStock = inventoryLog.getPrevStock() + inventoryLog.getPlusStock() - inventoryLog.getMinStock();
					inventoryLog.setCurrStock(currStock);
					inventoryLog.setPrevStock(inventory.getQty());
					
					//override curr_stock
					inventoryLog.setCurrStock(inventory.getQty() + currStock);
					
					inventoryLog.setPrevStockDate(inventory.getStockDate());
					inventoryLog.setCurrStockDate(new Date());
					
					inventoryLog = new InventoryLogDAO(con).save(inventoryLog);
					currStock = inventoryLog.getPrevStock() + inventoryLog.getPlusStock() - inventoryLog.getMinStock();
					inventoryLog.setCurrStock(currStock);
					
					inventory.setProductCode(inventoryLogTemp.getProductCode());
					inventory.setQty(currStock);
					inventory.setWarehouse(inventoryLogTemp.getWarehouse());
					inventory.setStockDate(new Date());
					inventory.setInventoryLogId(inventoryLog.getId());
					
					new InventoryDAO(con).update(inventory);
				}
				
				new InventoryLogTempDAO(con).updateStatusConfirmDate(inventoryLogTemp);
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
