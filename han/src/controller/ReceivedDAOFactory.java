package controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import module.pembelian.dao.DeliveryDAO;
import module.pembelian.dao.DocumentTypeDAO;
import module.pembelian.dao.EmployeeDAO;
import module.pembelian.dao.GradeDAO;
import module.pembelian.dao.PICDockingDAO;
import module.pembelian.dao.PalletCardDAO;
import module.pembelian.dao.PalletCardDetailDAO;
import module.pembelian.dao.PalletDAO;
import module.pembelian.dao.ProductDAO;
import module.pembelian.dao.ReceivedDAO;
import module.pembelian.dao.ReceivedDetailDAO;
import module.pembelian.dao.SuppCPDAO;
import module.pembelian.dao.SupplierReceivedDAO;
import module.pembelian.dao.SupplierVehicleDAO;
import module.pembelian.dao.ThicknessDAO;
import module.pembelian.dao.WoodResourceDAO;
import module.pembelian.dao.WoodTypeDAO;
import module.supplier.dao.SupplierDAO;

public class ReceivedDAOFactory {
	private static final Logger LOGGER = Logger.getLogger(ReceivedDAOFactory.class);

	public static GradeDAO getGradeDAO() {
		try {
			return new GradeDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static ReceivedDAO getReceivedDAO() {
		try {
			return new ReceivedDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static EmployeeDAO getPICDockingDAO() {
		try {
			return new EmployeeDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	public static SupplierVehicleDAO getSupplierVehicleDAO() {
		try {
			return new SupplierVehicleDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static WoodTypeDAO getWoodTypeDAO() {
		try {
			return new WoodTypeDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static DeliveryDAO getDeliveryDAO() {
		try {
			return new DeliveryDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static ThicknessDAO getThicknessDAO() {
		try {
			return new ThicknessDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static ProductDAO getProductDAO() {
		try {
			return new ProductDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static PalletDAO getPalletDAO() {
		try {
			return new PalletDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static PICDockingDAO getPicDockingReceivedDAO() {
		try {
			return new PICDockingDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static PalletCardDetailDAO getPalletCardDetailDAO() {
		try {
			return new PalletCardDetailDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static WoodResourceDAO getWoodResourceDAO() {
		try {
			return new WoodResourceDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static DocumentTypeDAO getDocumentTypeDAO() {
		try {
			return new DocumentTypeDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	public static SuppCPDAO getSupplierCPDAO(){
		try {
			return new SuppCPDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static SupplierReceivedDAO getSupplierDAO(){
		try {
			return new SupplierReceivedDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	public static ReceivedDetailDAO getReceivedDetailDAO(){
		try {
			return new ReceivedDetailDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
	public static PalletCardDAO getPalletCardDAO(){
		try {
			return new PalletCardDAO(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			LOGGER.error("SQL Exception " + e);
			return null;
		}
	}
	
}
