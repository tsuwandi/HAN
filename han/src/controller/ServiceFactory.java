package controller;

import module.supplier.bl.SupplierBL;

public class ServiceFactory {
	//private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

	private static SupplierBL supplierBL = null;

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
}
