package module.packingresult.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import main.panel.MainPanel;
import module.dailyclosing.dao.InventoryDAO;
import module.dailyclosing.model.Inventory;
import module.packingresult.dao.PackingConversionDAO;
import module.packingresult.dao.PackingDAO;
import module.packingresult.dao.PackingRMDAO;
import module.packingresult.dao.PackingResultDAO;
import module.packingresult.model.Packing;
import module.packingresult.model.PackingConversion;
import module.packingresult.model.PackingRM;
import module.packingresult.model.PackingResult;

public class PackingBL {
	private PackingDAO packingDAO;
	private PackingResultDAO packingResultDAO;
	private PackingConversionDAO packingConversionDAO;
	private InventoryDAO inventoryDAO;
	private PackingRMDAO packingRMDAO;
	
	private DataSource dataSource;
	
	public PackingBL(DataSource dataSource){
		Connection con = null;
		this.dataSource = dataSource;
		try {
			con = dataSource.getConnection();
			packingDAO = new PackingDAO(con);
			packingResultDAO = new PackingResultDAO(con);
			packingConversionDAO = new PackingConversionDAO(con);
			inventoryDAO = new InventoryDAO(con);
			packingRMDAO = new PackingRMDAO(con);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<Packing> getPackings(String sql) throws SQLException{
		List<Packing> packings = new ArrayList<>();
		for (Packing packing : packingDAO.getAll(sql)) {
			packing.setPackingResults(getPackingResults(packing.getId()));
			packing.setPackingRMs(getPackingRms(packing.getId()));
			packings.add(packing);
		}
		return packings;
	}
	
	public List<PackingConversion> getPackingConversion() throws SQLException{
		return packingConversionDAO.getAll();
	}
	
	public List<PackingResult> getPackingResults(int packingID) throws SQLException{
		return packingResultDAO.getAllPackingByID(packingID);
	}
	
	public List<PackingRM> getPackingRms(int packingID) throws SQLException{
		return packingRMDAO.getAllPackingByID(packingID);
	}
	
	public int getLastIDPacking() throws SQLException{
		return packingDAO.getLastID()==0? 1:packingDAO.getLastID() +1;
	}
	
	public List<Inventory> getLastInventoryData(String sql) throws SQLException{
		return inventoryDAO.getInventoryBySelectedCode(sql);
	}
	
	public void save(Packing packing) throws SQLException{
		Connection connection = null;
		PackingResultDAO packingResultDAOTemp;
		PackingRMDAO packingRMDAOTemp;
		int lastID = getLastIDPacking();
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			packingResultDAOTemp = new PackingResultDAO(connection);
			packingRMDAOTemp = new PackingRMDAO(connection);
			
			packing.setId(lastID);
			for (PackingResult packingResult : packing.getPackingResults()) {
				packingResult.setPackingID(lastID);
				packingResultDAOTemp.save(packingResult);
			}
			for(PackingRM packingRM : packing.getPackingRMs()){
				packingRM.setPackingID(lastID);
				packingRMDAOTemp.save(packingRM);
			}
			packing.setStatus("BARU");
			new PackingDAO(connection).save(packing);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}
	public void update(Packing packing) throws SQLException{
		Connection connection = null;
		PackingResultDAO packingResultDAOTemp;
		PackingRMDAO packingRMDAOTemp;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			packingResultDAOTemp = new PackingResultDAO(connection);
			packingRMDAOTemp = new PackingRMDAO(connection);
			
			for (PackingResult packingResult : packing.getPackingResults()) {
				packingResultDAOTemp.update(packingResult);
			}
			for(PackingRM packingRM : packing.getPackingRMs()){
				packingRMDAOTemp.update(packingRM);
			}
			new PackingDAO(connection).update(packing);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}
	public void delete(Packing packing) throws SQLException{
		Connection connection = null;
		PackingResultDAO packingResultDAOTemp;
		PackingRMDAO packingRMDAOTemp;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			packingResultDAOTemp = new PackingResultDAO(connection);
			packingRMDAOTemp = new PackingRMDAO(connection);
			
			for (PackingResult packingResult : packing.getPackingResults()) {
				packingResultDAOTemp.updateDelete(packingResult);
			}
			for(PackingRM packingRM : packing.getPackingRMs()){
				packingRMDAOTemp.updateDelete(packingRM);
			}
			new PackingDAO(connection).delete(packing);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}
	
}
