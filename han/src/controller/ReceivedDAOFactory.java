package controller;

import java.sql.SQLException;

import module.pembelian.dao.DeliveryDAO;
import module.pembelian.dao.EmployeeDAO;
import module.pembelian.dao.GradeDAO;
import module.pembelian.dao.ProductDAO;
import module.pembelian.dao.ReceivedDAO;
import module.pembelian.dao.SupplierVehicleDAO;
import module.pembelian.dao.ThicknessDAO;
import module.pembelian.dao.WoodTypeDAO;
import module.pembelian.model.Thickness;

import org.apache.log4j.Logger;

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
}
