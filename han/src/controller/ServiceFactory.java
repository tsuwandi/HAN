package controller;

import module.dryin.bl.DryInBL;
import module.dryout.bl.DryOutBL;
import module.employee.bl.EmployeeBL;
import module.product.bl.ProductBL;
import module.report.bl.ReportBL;
import module.supplier.bl.SupplierBL;

public class ServiceFactory {
	// private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

	private static SupplierBL supplierBL = null;
	private static EmployeeBL employeeBL = null;
	private static DryInBL dryInBL = null;
	private static DryOutBL dryOutBL = null;
	private static ProductBL productBL = null;
	private static ReportBL reportBL = null;

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

	/**
	 * Method to init or return {@link DryInBL}
	 * 
	 * @return {@link DryInBL}
	 */
	public static DryInBL getDryInBL() {
		if (dryInBL == null) {
			dryInBL = new DryInBL(DataSourceFactory.getDataSource());
		}
		return dryInBL;
	}

	/**
	 * Method to init or return {@link DryOutBL}
	 * 
	 * @return {@link DryOutBL}
	 */
	public static DryOutBL getDryOutBL() {
		if (dryOutBL == null) {
			dryOutBL = new DryOutBL(DataSourceFactory.getDataSource());
		}
		return dryOutBL;
	}
	
	public static ProductBL getProductBL() {
		if(productBL == null){
			productBL = new ProductBL(DataSourceFactory.getDataSource());
		}
		return productBL;
	}
	
	public static ReportBL getReportBL() {
		if(reportBL == null){
			reportBL = new ReportBL(DataSourceFactory.getDataSource());
		}
		return reportBL;
	}
}
