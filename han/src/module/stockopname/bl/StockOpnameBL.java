package module.stockopname.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import main.component.DialogBox;
import module.dailyclosing.model.Inventory;
import module.stockopname.dao.CompletedSOScheduleDAO;
import module.stockopname.dao.ProductSODAO;
import module.stockopname.dao.SetSoScheduledDAO;
import module.stockopname.dao.SetSoScheduledProductDAO;
import module.stockopname.dao.StockOpnameDAO;
import module.stockopname.dao.StockOpnameProductDAO;
import module.stockopname.model.CompletedSOSchedule;
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
	private CompletedSOScheduleDAO completedSOScheduleDAO;
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
			productSODAO = new ProductSODAO(con);
			inventoryMap = getAllInventory();
			completedSOScheduleDAO = new CompletedSOScheduleDAO(con);
		} catch (Exception e) {
			e.getMessage();
			DialogBox.showError("Koneksi Error Stock Opname BL");
		}
	}
	
	public List<StockOpname> getStockOpname() throws SQLException{
		List<StockOpname> allSO = new ArrayList<>();
		List<StockOpname> stockOpnames = stockOpnameDAO.getAll();
		for (StockOpname stockOpname : stockOpnames) {
			if(getStockOpnameProduct(stockOpname.getId())!=null)stockOpname.setStockOpnameProduct(getStockOpnameProduct(stockOpname.getId()));
		}
		allSO.addAll(getStockOpnameSchedule());
		allSO.addAll(stockOpnames);
		return allSO;
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
	
	public List<SetSoScheduledProduct> getSOSearchScheduledProduct(String sql, List<Object> objs) throws SQLException{
		return productSODAO.getAllSearchProductSchedule(sql, objs);
	}
	
	public List<StockOpnameProduct> getSearchSOManualProduct(String sql, List<Object> objs) throws SQLException{
		return productSODAO.getAllSearchProductSO(sql, objs);
	}
	
	public int getLastIDSO() throws SQLException{
		return stockOpnameDAO.getLastID()==0?1:stockOpnameDAO.getLastID()+1;
	}
	
	public int getLastSetSOID() throws SQLException{
		return setSOScheduleDAO.getLastID()==0? 1 : setSOScheduleDAO.getLastID()+1;
	} 
	
	public Map<String, Inventory> getAllInventory() throws SQLException{
		return productSODAO.getInventory();
	}
	
	public List<StockOpname> getStockOpnameSchedule() throws SQLException{
		List<SetSOScheduled> listScheduledSO = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
		for(SetSOScheduled so : setSOScheduleDAO.getAllNotCompletedSOToday()){
			if(so.getReccurence().equals("Harian")){
				if(getSetSoScheduledProduct(so.getId())!=null)so.setSetSoScheduledProducts(getSetSoScheduledProduct(so.getId()));
				listScheduledSO.add(so);
			}else if(so.getReccurence().equals("Mingguan")){
		        String day="";
		        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
				case Calendar.SUNDAY:
					day="Minggu";
					break;
				case Calendar.MONDAY:
					day="Senin";
					break;
				case Calendar.TUESDAY:
					day="Selasa";
					break;
				case Calendar.WEDNESDAY:
					day="Rabu";
					break;
				case Calendar.THURSDAY:
					day="Kamis";
					break;
				case Calendar.FRIDAY:
					day="Jumat";
					break;
				case Calendar.SATURDAY:
					day="Sabtu";
					break;
				default:
					break;
				}
		        if(so.getDay().equals(day)){
		        	if(getSetSoScheduledProduct(so.getId())!=null)so.setSetSoScheduledProducts(getSetSoScheduledProduct(so.getId()));
		        	listScheduledSO.add(so);
		        }
			}else if(so.getReccurence().equals("Bulanan")){
				if(so.getDate()==calendar.get(Calendar.DAY_OF_MONTH)){
					if(getSetSoScheduledProduct(so.getId())!=null)so.setSetSoScheduledProducts(getSetSoScheduledProduct(so.getId()));
					listScheduledSO.add(so);
				}
			}
		}
		List<StockOpname> soManuals = new ArrayList<>();
		for(SetSOScheduled so : listScheduledSO){
			StockOpname soManual = new StockOpname();
			soManual.setSoName(so.getSoName());
			soManual.setSoType(so.getSoType());
			soManual.setSoDate(new Date());
			soManual.setStatus("New");
			soManual.setSetSoScheduleID(so.getId());
			List<StockOpnameProduct> soProds = new ArrayList<>();
			for(SetSoScheduledProduct soProd : so.getSetSoScheduledProducts()){
				StockOpnameProduct prod = new StockOpnameProduct();
				prod.setProductID(soProd.getProductID());
				prod.setProductCode(soProd.getProductCode());
				prod.setProductCategoryID(soProd.getProductCategoryID());
				prod.setProductCategory(soProd.getProductCategory());
				prod.setProductName(soProd.getProductName());
				if(inventoryMap.get(prod.getProductCode())!=null) prod.setQtySystem(inventoryMap.get(prod.getProductCode()).getQty().doubleValue());
				soProds.add(prod);
			}
			soManual.setStockOpnameProduct(soProds);
			soManuals.add(soManual);
		}

		return soManuals;
	}
	
	public void saveSetSoSchedule(SetSOScheduled setSoSchedule) throws SQLException{
		int lastID = getLastSetSOID();
		setSoSchedule.setId(lastID);
		for(SetSoScheduledProduct setSOScheduledProduct : setSoSchedule.getSetSoScheduledProducts()){
			setSOScheduledProduct.setSetSOScheduledID(lastID);
			setSOScheduledProductDAO.save(setSOScheduledProduct);
		}
		setSOScheduleDAO.save(setSoSchedule);
	}
	
	public void updateSetSoSchedule(SetSOScheduled setSoSchedule) throws SQLException{
		for(SetSoScheduledProduct setSOScheduledProduct : setSoSchedule.getSetSoScheduledProducts()){
			setSOScheduledProduct.setSetSOScheduledID(setSoSchedule.getId());
			if(setSOScheduledProduct.getId()!=0)setSOScheduledProductDAO.update(setSOScheduledProduct);
			else setSOScheduledProductDAO.save(setSOScheduledProduct);
			
			if(setSoSchedule.getDeletedProducts()!=null){
				for (SetSoScheduledProduct setSOScheduledDeletedProduct : setSoSchedule.getDeletedProducts()) {
					setSOScheduledProductDAO.updateDelete(setSOScheduledDeletedProduct);
				}
			}
		}
		setSOScheduleDAO.update(setSoSchedule);
	}
	
	public void saveSOScheduled(StockOpname so) throws SQLException{
		int lastID = getLastIDSO();
		so.setId(lastID);
		for(StockOpnameProduct soProduct : so.getStockOpnameProduct()){
			soProduct.setStockOpnameID(lastID);
			stockOpnameProductDAO.save(soProduct);
		}
		CompletedSOSchedule cso = new CompletedSOSchedule();
		cso.setSetSOScheduleID(so.getSetSoScheduleID());
		cso.setDate(so.getSoDate());
		completedSOScheduleDAO.save(cso);
		stockOpnameDAO.save(so);
	}
	
	public void saveSO(StockOpname so) throws SQLException{
		int lastID = getLastIDSO();
		so.setId(lastID);
		for(StockOpnameProduct soProduct : so.getStockOpnameProduct()){
			soProduct.setStockOpnameID(lastID);
			stockOpnameProductDAO.save(soProduct);
		}
		stockOpnameDAO.save(so);
	}
	
	public void updateSO(StockOpname so) throws SQLException{
		for(StockOpnameProduct soProduct : so.getStockOpnameProduct()){
			soProduct.setStockOpnameID(so.getId());
			if(soProduct.getId()!=0)stockOpnameProductDAO.update(soProduct);
			else stockOpnameProductDAO.save(soProduct);
		}
		for(StockOpnameProduct soProduct : so.getDeletedProducts()){
			stockOpnameProductDAO.updateDelete(soProduct);
		}
		stockOpnameDAO.update(so);
	}
	
	public void deleteStockOpname(StockOpname stockOpname){
		try {
			for (StockOpnameProduct soProd : stockOpname.getStockOpnameProduct()) {
				stockOpnameProductDAO.updateDelete(soProd);
			}
			stockOpnameDAO.updateDelete(stockOpname);
		} catch (SQLException e) {
			DialogBox.showError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void deleteScheduleSO(SetSOScheduled setSoScheduled){
		try {
			for (SetSoScheduledProduct soProd : setSoScheduled.getSetSoScheduledProducts()) {
				setSOScheduledProductDAO.updateDelete(soProd);
			}
			setSOScheduleDAO.updateDelete(setSoScheduled);
		} catch (SQLException e) {
			DialogBox.showError(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
