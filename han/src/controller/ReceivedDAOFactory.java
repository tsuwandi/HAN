package controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import module.pembelian.dao.EmployeeDAO;
import module.pembelian.dao.GradeDAO;
import module.pembelian.dao.ReceivedDAO;
import module.pembelian.dao.SupplierVehicleDAO;

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
}
