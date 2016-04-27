package controller;

import module.employee.bl.EmployeeBL;
import module.supplier.bl.SupplierBL;

public class ServiceFactory {
	//private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

	private static SupplierBL supplierBL = null;
	private static EmployeeBL employeeBL = null;

	/**
	 * Method to init or return {@link SupplierBL}
	 * 
	 * @return {@link SupplierBL}
	 */
	public static SupplierBL getSupplierBL() {
		if (supplierBL == null) {
			supplierBL = new SupplierBL(DataSourceFactory.getDataSource());
		}
		return supplierBL;
	}
	
	/**
	 * Method to init or return {@link EmployeeBL}
	 * 
	 * @return {@link EmployeeBL}
	 */
	public static EmployeeBL getEmployeeBL() {
		if (employeeBL == null) {
			employeeBL = new EmployeeBL(DataSourceFactory.getDataSource());
		}
		return employeeBL;
	}
}