package module.dailyclosing.bl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import main.component.AppConstants;
import module.dailyclosing.dao.ConfirmDAO;
import module.dailyclosing.dao.InventoryDAO;
import module.dailyclosing.dao.InventoryLogDAO;
import module.dailyclosing.dao.InventoryLogTempDAO;
import module.dailyclosing.dao.ProductionDailyClosingDAO;
import module.dailyclosing.model.Confirm;
import module.dailyclosing.model.Inventory;
import module.dailyclosing.model.InventoryLog;
import module.dailyclosing.model.InventoryLogTemp;
import module.dryin.dao.DryInDAO;
import module.dryin.model.DryIn;
import module.dryout.dao.DryOutDAO;
import module.dryout.model.DryOut;
import module.packingresult.model.PackingRM;
import module.packingresult.model.PackingResult;
import module.pembelian.dao.ReceivedDAO;
import module.pembelian.model.Received;
import module.prodpk.model.ProdPKMaterial;
import module.prodpk.model.ProdPKResultProduct;
import module.production.model.ProdRM;
import module.production.model.ProductionResultProduct;
import module.productionwaste.model.ProductionResultProductWaste;
import module.purchaseprodresult.model.PPRProduct;
import module.util.DateUtil;

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
			confirm.setModule(AppConstants.RECEIVE_MODULE);
			confirm.setDailyClosingDate(DateUtil.setTimeStamp());
			new ConfirmDAO(con).save(confirm);

			for (Received received : listOfReceived) {
				InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				inventoryLogTemp.setProductCode(received.getPalletCard().getProductCode());
				inventoryLogTemp.setWarehouse(0);
				inventoryLogTemp.setQty(new BigDecimal(received.getPalletCard().getTotal()));
				inventoryLogTemp.setMutasi(AppConstants.DEBET);
				inventoryLogTemp.setSrcTable(AppConstants.RECEIVED);
				inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTemp);

				received.setReceivedStatus(AppConstants.STATUS_FINAL);

				new ReceivedDAO(con).updateDailyClosing(received);
			}

			for (DryIn dryIn : listOfDryIn) {
				InventoryLogTemp inventoryLogTempCredit = new InventoryLogTemp();
				inventoryLogTempCredit.setProductCode(dryIn.getPalletCard().getProductCode());
				inventoryLogTempCredit.setWarehouse(0);
				inventoryLogTempCredit.setQty(new BigDecimal(dryIn.getPalletCard().getTotal()));
				inventoryLogTempCredit.setMutasi(AppConstants.CREDIT);
				inventoryLogTempCredit.setSrcTable(AppConstants.DRY_IN);
				inventoryLogTempCredit.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTempCredit);

				InventoryLogTemp inventoryLogTempDebet = new InventoryLogTemp();
				inventoryLogTempDebet.setProductCode(dryIn.getPalletCard().getProductCode());
				inventoryLogTempDebet.setWarehouse(dryIn.getChamberId());
				inventoryLogTempDebet.setQty(new BigDecimal (dryIn.getPalletCard().getTotal()));
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
				inventoryLogTempCredit.setQty(new BigDecimal (dryOut.getPalletCard().getTotal()));
				inventoryLogTempCredit.setMutasi(AppConstants.CREDIT);
				inventoryLogTempCredit.setSrcTable(AppConstants.DRY_OUT);
				inventoryLogTempCredit.setConfirmCode(confirm.getConfirmCode());

				new InventoryLogTempDAO(con).save(inventoryLogTempCredit);

				InventoryLogTemp inventoryLogTempDebet = new InventoryLogTemp();
				String productCodeBalkenKering = new StringBuilder().append("K")
							.append(dryOut.getPalletCard().getProductCode().substring(1)).toString();

				inventoryLogTempDebet.setProductCode(productCodeBalkenKering);
				inventoryLogTempDebet.setWarehouse(dryOut.getChamberId());
				inventoryLogTempDebet.setQty(new BigDecimal(dryOut.getPalletCard().getTotal()));
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
	public List<ProdRM> getAllProdRM() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionDailyClosingDAO(con).getAllProdRM();
		} finally {
			con.close();
		}
	}
	
	public List<ProductionResultProduct> getAllProdResult() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionDailyClosingDAO(con).getAllProdResult();
		} finally {
			con.close();
		}
	}
	
	public List<ProductionResultProductWaste> getAllProdWasteResult() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionDailyClosingDAO(con).getAllProdWasteResult();
		} finally {
			con.close();
		}
	}
	
	public List<ProdPKMaterial> getAllProdPKMaterial() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionDailyClosingDAO(con).getAllProdPKMaterial();
		} finally {
			con.close();
		}
	}
	
	public List<ProdPKResultProduct> getAllProdPKResultProduct() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionDailyClosingDAO(con).getAllProdPKResultProduct();
		} finally {
			con.close();
		}
	}
	
	public List<PPRProduct> getAllPPRProduct() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionDailyClosingDAO(con).getAllPPRProduct();
		} finally {
			con.close();
		}
	}
	
	public List<PackingRM> getAllPackingRM() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionDailyClosingDAO(con).getAllPackingRM();
		} finally {
			con.close();
		}
	}
	
	public List<PackingResult> getAllPackingResult() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionDailyClosingDAO(con).getAllPackingResult();
		} finally {
			con.close();
		}
	}
	


	public void save(List<ProdRM> listOfProdRM, List<ProductionResultProduct> listOfProductionResultProduct,
			List<ProductionResultProductWaste> listOfProductionResultProductWaste,
			List<ProdPKMaterial> listOfProdPKMaterial, List<ProdPKResultProduct> listOfProdPKResultProduct,
			List<PPRProduct> listOfPPRProduct,
			List<PackingRM> listOfPackingRM, List<PackingResult> listOfPackingResult, String confirmCode) throws SQLException {
		List<String> productionCodes = new ArrayList<String>();
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			Confirm confirm = new Confirm();
			confirm.setConfirmCode(confirmCode);
			confirm.setModule(AppConstants.PRODUCTION_MODULE);
			confirm.setDailyClosingDate(DateUtil.setTimeStamp());
			new ConfirmDAO(con).save(confirm);

			/** START PRODUKSI **/
			LOGGER.debug("START TUTUP HARIAN PRODUKSI - PRODUKSI");
			for (ProdRM prodRM : listOfProdRM) {
				 InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				
				 inventoryLogTemp.setProductCode(prodRM.getProductCode());
				 inventoryLogTemp.setWarehouse(AppConstants.WAREHOUSE_0);
				 inventoryLogTemp.setQty(prodRM.getTotal());
				 inventoryLogTemp.setMutasi(AppConstants.DEBET);
				 inventoryLogTemp.setSrcTable(AppConstants.PROD_RM);
				 inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());
				
				 new InventoryLogTempDAO(con).save(inventoryLogTemp);
				
				 new ProductionDailyClosingDAO(con).updateDailyClosingByCode(prodRM.getProductionCode(), AppConstants.STATUS_FINAL, ProductionDailyClosingDAO.PROD_RM_QUERY);
			}
			
			for (ProductionResultProduct productionResultProduct : listOfProductionResultProduct) {
				 InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				 inventoryLogTemp.setProductCode(productionResultProduct.getProductCode());
				 inventoryLogTemp.setWarehouse(AppConstants.WAREHOUSE_0);
				 inventoryLogTemp.setQty(new BigDecimal(productionResultProduct.getQty()));
				 inventoryLogTemp.setMutasi(AppConstants.CREDIT);
				 inventoryLogTemp.setSrcTable(AppConstants.PROD_RESULT_PRODUCT);
				 inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());
				
				 new InventoryLogTempDAO(con).save(inventoryLogTemp);
				
				 new ProductionDailyClosingDAO(con).updateDailyClosingByCode(productionResultProduct.getProductionCode(), AppConstants.STATUS_FINAL, ProductionDailyClosingDAO.PROD_RESULT_PRODUCT_QUERY);
			}
			LOGGER.debug("END TUTUP HARIAN PRODUKSI - PRODUKSI");
			/** END PRODUKSI **/
			
			/** START DOWN SIZE **/
			LOGGER.debug("START TUTUP HARIAN PRODUKSI - DOWN SIZE");
			for (ProductionResultProductWaste productionResultProductWaste : listOfProductionResultProductWaste) {
				 InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				 inventoryLogTemp.setProductCode(productionResultProductWaste.getProductCode());
				 inventoryLogTemp.setWarehouse(AppConstants.WAREHOUSE_0);
				 inventoryLogTemp.setQty(new BigDecimal(productionResultProductWaste.getQty()));
				 inventoryLogTemp.setMutasi(AppConstants.CREDIT);
				 inventoryLogTemp.setSrcTable(AppConstants.PROD_WASTE_RESULT_PRODUCT);
				 inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());
				
				 new InventoryLogTempDAO(con).save(inventoryLogTemp);
				
				 new ProductionDailyClosingDAO(con).updateDailyClosingByCode(productionResultProductWaste.getPwCode(), AppConstants.STATUS_FINAL, ProductionDailyClosingDAO.PROD_WASTE_RESULT_QUERY);
			}
			LOGGER.debug("END TUTUP HARIAN PRODUKSI - DOWN SIZE");
			/** END DOWN SIZE **/
			
			/** START HASIL KLEM **/
			LOGGER.debug("START TUTUP HARIAN PRODUKSI - HASIL KLEM");
			for (ProdPKMaterial prodPKMaterial : listOfProdPKMaterial) {
				 InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				 inventoryLogTemp.setProductCode(prodPKMaterial.getProductCode());
				 inventoryLogTemp.setWarehouse(AppConstants.WAREHOUSE_0);
				 inventoryLogTemp.setQty(prodPKMaterial.getQty());
				 inventoryLogTemp.setMutasi(AppConstants.DEBET);
				 inventoryLogTemp.setSrcTable(AppConstants.PROD_PK_MATERIAL);
				 inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());
				
				 new InventoryLogTempDAO(con).save(inventoryLogTemp);
				
				 new ProductionDailyClosingDAO(con).updateDailyClosingByCode(prodPKMaterial.getProdPKCode(), AppConstants.STATUS_FINAL, ProductionDailyClosingDAO.PROD_PK_MATERIAL_QUERY);
			}
			
			for (ProdPKResultProduct prodPKResultProduct : listOfProdPKResultProduct) {
				 InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				 inventoryLogTemp.setProductCode(prodPKResultProduct.getProductCode());
				 inventoryLogTemp.setWarehouse(AppConstants.WAREHOUSE_0);
				 inventoryLogTemp.setQty(prodPKResultProduct.getQty());
				 inventoryLogTemp.setMutasi(AppConstants.CREDIT);
				 inventoryLogTemp.setSrcTable(AppConstants.PROD_PK_RESULT_PRODUCT);
				 inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());
				
				 new InventoryLogTempDAO(con).save(inventoryLogTemp);
				
				 new ProductionDailyClosingDAO(con).updateDailyClosingByCode(prodPKResultProduct.getProdPKCode(), AppConstants.STATUS_FINAL, ProductionDailyClosingDAO.PROD_PK_RESULT_PRODUCT_QUERY);
			}
			LOGGER.debug("END TUTUP HARIAN PRODUKSI - HASIL KLEM");
			/** END HASIL KLEM **/
			
			/** START PENERIMAAN **/
			LOGGER.debug("START TUTUP HARIAN PRODUKSI - PENERIMAAN");
			for (PPRProduct pPRProduct : listOfPPRProduct) {
				 InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				 inventoryLogTemp.setProductCode(pPRProduct.getProductCode());
				 inventoryLogTemp.setWarehouse(AppConstants.WAREHOUSE_0);
				 inventoryLogTemp.setQty(pPRProduct.getQty());
				 inventoryLogTemp.setMutasi(AppConstants.CREDIT);
				 inventoryLogTemp.setSrcTable(AppConstants.PPR_PRODUCT);
				 inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());
				
				 new InventoryLogTempDAO(con).save(inventoryLogTemp);
				
				 new ProductionDailyClosingDAO(con).updateDailyClosingByCode(pPRProduct.getPprCode(), AppConstants.STATUS_FINAL, ProductionDailyClosingDAO.PPR_PRODUCT_QUERY);
			}
			LOGGER.debug("END TUTUP HARIAN PRODUKSI - PENERIMAAN");
			/** END HASIL KLEM **/
			
			/** START HASIL PACKING **/
			LOGGER.debug("START TUTUP HARIAN PRODUKSI - HASIL PACKING");
			for (PackingRM packingRM : listOfPackingRM) {
				 InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				 inventoryLogTemp.setProductCode(packingRM.getProductCode());
				 inventoryLogTemp.setWarehouse(AppConstants.WAREHOUSE_0);
				 inventoryLogTemp.setQty(new BigDecimal(packingRM.getQty()));
				 inventoryLogTemp.setMutasi(AppConstants.CREDIT);
				 inventoryLogTemp.setSrcTable(AppConstants.PACKING_RM);
				 inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());
				
				 new InventoryLogTempDAO(con).save(inventoryLogTemp);
				
				 new ProductionDailyClosingDAO(con).updateDailyClosingById(packingRM.getPackingID(), AppConstants.STATUS_FINAL, ProductionDailyClosingDAO.PACKING_RM_QUERY);
			}
			
			for (PackingResult packingResult : listOfPackingResult) {
				 InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				 inventoryLogTemp.setProductCode(packingResult.getProductCode());
				 inventoryLogTemp.setWarehouse(AppConstants.WAREHOUSE_0);
				 inventoryLogTemp.setQty(new BigDecimal(packingResult.getQty()));
				 inventoryLogTemp.setMutasi(AppConstants.CREDIT);
				 inventoryLogTemp.setSrcTable(AppConstants.PACKING_RESULT);
				 inventoryLogTemp.setConfirmCode(confirm.getConfirmCode());
				
				 new InventoryLogTempDAO(con).save(inventoryLogTemp);
				
				 new ProductionDailyClosingDAO(con).updateDailyClosingById(packingResult.getPackingID(), AppConstants.STATUS_FINAL, ProductionDailyClosingDAO.PACKING_RESULT_QUERY);
			}
			LOGGER.debug("END TUTUP HARIAN PRODUKSI - HASIL PACKING");
			/** END HASIL PACKING **/

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

			for (InventoryLogTemp inventoryLogTemp : inventoryLogTemps) {
				BigDecimal currStock = new BigDecimal(0);
				Inventory inventory = null;
				inventory = new InventoryDAO(con).getInventoryByProductCodeAndWarehouse(
						inventoryLogTemp.getProductCode(), inventoryLogTemp.getWarehouse());

				InventoryLog inventoryLog = new InventoryLog();
				inventoryLog.setProductCode(inventoryLogTemp.getProductCode());
				inventoryLog.setWarehouse(inventoryLogTemp.getWarehouse());
				inventoryLog.setConfirmCode(makeConfirmCode());
				if (inventoryLogTemp.getMutasi().equals(AppConstants.DEBET)) {
					inventoryLog.setPrevStock(BigDecimal.ZERO);
					inventoryLog.setPlusStock(inventoryLogTemp.getQty());
					inventoryLog.setMinStock(BigDecimal.ZERO);
				} else if (inventoryLogTemp.getMutasi().equals(AppConstants.CREDIT)) {
					inventoryLog.setPrevStock(BigDecimal.ZERO);
					inventoryLog.setMinStock(inventoryLogTemp.getQty());
					inventoryLog.setPlusStock(BigDecimal.ZERO);
					// currStock = inventoryLog.getPrevStock() -
					// inventoryLog.getMinStock();
					// inventoryLog.setCurrStock(currStock);
				} else {
					LOGGER.error("NO DATA MUTASI.");
					throw new Exception("Failed to Process Daily Closing.");
				}

				if (inventory == null) {
					currStock = inventoryLog.getPrevStock().add(inventoryLog.getPlusStock()).subtract(inventoryLog.getMinStock());
					inventoryLog.setCurrStock(currStock);
					inventoryLog.setPrevStockDate(new Date());
					inventoryLog.setCurrStockDate(new Date());

					inventoryLog = new InventoryLogDAO(con).save(inventoryLog);
					currStock = inventoryLog.getPrevStock().add(inventoryLog.getPlusStock()).subtract(inventoryLog.getMinStock());
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
					currStock = inventoryLog.getPrevStock().add(inventoryLog.getPlusStock()).subtract(inventoryLog.getMinStock());
					inventoryLog.setCurrStock(currStock);
					inventoryLog.setPrevStock(inventory.getQty());

					// override curr_stock
					inventoryLog.setCurrStock(inventory.getQty().add(currStock));

					inventoryLog.setPrevStockDate(inventory.getStockDate());
					inventoryLog.setCurrStockDate(new Date());

					inventoryLog = new InventoryLogDAO(con).save(inventoryLog);
					currStock = inventoryLog.getPrevStock().add(inventoryLog.getPlusStock()).subtract(inventoryLog.getMinStock());
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
