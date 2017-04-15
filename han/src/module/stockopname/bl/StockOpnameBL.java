package module.stockopname.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import main.component.DialogBox;
import module.dailyclosing.model.Inventory;
import module.stockopname.dao.ProductSODAO;
import module.stockopname.dao.SetSoScheduledDAO;
import module.stockopname.dao.SetSoScheduledProductDAO;
import module.stockopname.dao.StockOpnameDAO;
import module.stockopname.dao.StockOpnameProductDAO;
import module.stockopname.model.SetSOScheduled;
import module.stockopname.model.SetSoScheduledProduct;
import module.stockopname.model.StockOpname;
import module.stockopname.model.StockOpnameProduct;

public class StockOpnameBL {
	private DataSource dataSource;
	private SetSoScheduledDAO setSOScheduleDAO;
	private SetSoScheduledProductDAO setSOScheduledProductDAO;
	private StockOpnameDAO stockOpnameDAO;
	private StockOpnameProductDAO stockOpnameProductDAO;
	private ProductSODAO productSODAO;
	private Map<String, Inventory> inventoryMap;
	
	public StockOpnameBL(DataSource dataSource){
		this.dataSource = dataSource;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			setSOScheduleDAO = new SetSoScheduledDAO(con);
			setSOScheduledProductDAO = new SetSoScheduledProductDAO(con);
			stockOpnameDAO = new StockOpnameDAO(con);
			stockOpnameProductDAO = new StockOpnameProductDAO(con);
			inventoryMap = getAllInventory();
		} catch (Exception e) {
			e.getMessage();
			DialogBox.showError("Koneksi Error Stock Opname BL");
		}
	}
	
	public List<StockOpname> getStockOpname() throws SQLException{
		List<StockOpname> stockOpnames = stockOpnameDAO.getAll();
		for (StockOpname stockOpname : stockOpnames) {
			if(getStockOpnameProduct(stockOpname.getId())!=null)stockOpname.setStockOpnameProduct(getStockOpnameProduct(stockOpname.getId()));
		}
		return stockOpnames;
	}
	
	public List<StockOpnameProduct> getStockOpnameProduct(int stockOpnameID) throws SQLException{
		return stockOpnameProductDAO.getAllStockOpnameProductByID(stockOpnameID);
	}
	
	public List<SetSOScheduled> getSetSoSchedule() throws SQLException{
		List<SetSOScheduled> setSoScheduled = setSOScheduleDAO.getAll();
		for(SetSOScheduled setSO : setSoScheduled){
			if(getSetSoScheduledProduct(setSO.getId())!=null)setSO.setSetSoScheduledProducts(getSetSoScheduledProduct(setSO.getId()));
		}
		return setSoScheduled;
	}
	
	public List<SetSoScheduledProduct> getSetSoScheduledProduct(int setSOID) throws SQLException{
		return setSOScheduledProductDAO.getAllSetScheduledSOProductByID(setSOID);
	}
	
	public List<StockOpnameProduct> getSOProduct() throws SQLException{
		List<StockOpnameProduct> soProducts = productSODAO.getAllProductSO();
		for(StockOpnameProduct sop : soProducts){
			if(inventoryMap.get(sop.getProductCode())!=null) sop.setQtySystem(inventoryMap.get(sop.getProductCode()).getQty().doubleValue());
		}
		return soProducts;
	}
	
	public List<SetSoScheduledProduct> getSOScheduledProduct() throws SQLException{
		return productSODAO.getAllProductSchedule();
	}
	
	public int getLastIDSO() throws SQLException{
		return stockOpnameDAO.getLastID();
	}
	
	public int getLastStockOpnameID() throws SQLException{
		return setSOScheduleDAO.getLastID();
	} 
	
	public Map<String, Inventory> getAllInventory() throws SQLException{
		return productSODAO.getInventory();
	}
	
	
}
