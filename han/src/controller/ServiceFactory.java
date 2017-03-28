package controller;

import module.dailyclosing.bl.DailyClosingBL;
import module.dryin.bl.DryInBL;
import module.dryout.bl.DryOutBL;
import module.employee.bl.EmployeeBL;
import module.packingresult.bl.PackingBL;
import module.personalia.bl.PersonaliaBL;
import module.product.bl.ProductBL;
import module.production.bl.ProductionBL;
import module.productionpk.bl.ProdPKBL;
import module.productionwaste.bl.ProductionWasteBL;
import module.purchaseprodresult.bl.PurchaseProductResultBL;
import module.receiveprodresult.bl.ReceiveProductResultBL;
import module.report.bl.ReportBL;
import module.sendtofinance.bl.SendToFinanceBL;
import module.sn.woodgenus.bl.WoodGenusBL;
import module.sn.woodtype.bl.WoodTypeBL;
import module.supplier.bl.SupplierBL;
import module.system.bl.SystemBL;
import module.paymentprodresult.bl.PaymentProductResultBL;

public class ServiceFactory {
	// private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

	private static SupplierBL supplierBL = null;
	private static EmployeeBL employeeBL = null;
	private static DryInBL dryInBL = null;
	private static DryOutBL dryOutBL = null;
	private static ProductBL productBL = null;
	private static ReportBL reportBL = null;
	private static ProductionBL productionBL = null;
	private static WoodGenusBL woodGenusBL = null;
	private static WoodTypeBL woodTypeBL = null;
	private static SendToFinanceBL sendToFinanceBL = null;
	private static DailyClosingBL dailyClosingBL = null;
	private static PurchaseProductResultBL purchaseProductResultBL = null;
	private static ProductionWasteBL productionWasteBL = null;
	private static ProdPKBL prodPKBL = null;
	private static SystemBL systemBL = null;
	private static PersonaliaBL personaliaBL = null;
	//private static PurchaseProductResultPaymentBL purchaseProductResultPaymentBL = null;
	private static module.prodpk.bl.ProdPKBL productionPKBL = null;
	private static PackingBL packingBL = null;
	private static ReceiveProductResultBL receiveProductResultBL = null;
	private static PaymentProductResultBL paymentProductResultBL = null;
	
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
		if (productBL == null) {
			productBL = new ProductBL(DataSourceFactory.getDataSource());
		}
		return productBL;
	}

	public static ReportBL getReportBL() {
		if (reportBL == null) {
			reportBL = new ReportBL(DataSourceFactory.getDataSource());
		}
		return reportBL;
	}

	public static SendToFinanceBL getSendToFinanceBL() {
		if (sendToFinanceBL == null) {
			sendToFinanceBL = new SendToFinanceBL(DataSourceFactory.getDataSource());
		}
		return sendToFinanceBL;
	}

	public static DailyClosingBL getDailyClosingBL() {
		if (dailyClosingBL == null) {
			dailyClosingBL = new DailyClosingBL(DataSourceFactory.getDataSource());
		}
		return dailyClosingBL;
	}

	public static ProductionBL getProductionBL() {
		if (productionBL == null) {
			productionBL = new ProductionBL(DataSourceFactory.getDataSource());
		}
		return productionBL;
	}

	public static WoodGenusBL getWoodGenusBL() {
		if (woodGenusBL == null) {
			woodGenusBL = new WoodGenusBL(DataSourceFactory.getDataSource());
		}
		return woodGenusBL;
	}
	
	public static WoodTypeBL getWoodTypeBL() {
		if (woodTypeBL == null) {
			woodTypeBL = new WoodTypeBL(DataSourceFactory.getDataSource());
		}
		return woodTypeBL;
	}
	
	public static PurchaseProductResultBL getPurchaseProductResultBL() {
		if (purchaseProductResultBL == null) {
			purchaseProductResultBL = new PurchaseProductResultBL(DataSourceFactory.getDataSource());
		}
		return purchaseProductResultBL;
	}
	
	public static ProductionWasteBL getProductionWasteBL() {
		if (productionWasteBL == null) {
			productionWasteBL = new ProductionWasteBL(DataSourceFactory.getDataSource());
		}
		return productionWasteBL;
	}
	
	public static ProdPKBL getProdPKBL() {
		if (prodPKBL == null) {
			prodPKBL = new ProdPKBL(DataSourceFactory.getDataSource());
		}
		return prodPKBL;
	}

	public static SystemBL getSystemBL() {
		if (systemBL == null) {
			systemBL = new SystemBL(DataSourceFactory.getDataSource());
		}
		return systemBL;
	}
	
	public static PersonaliaBL getPersonaliaBL() {
		if (personaliaBL == null) {
			personaliaBL = new PersonaliaBL(DataSourceFactory.getDataSource());
		}
		return personaliaBL;
	}
//	
//	public static PurchaseProductResultPaymentBL getPurchaseProductResultPaymentBL() {
//		if (purchaseProductResultPaymentBL == null) {
//			purchaseProductResultPaymentBL = new PurchaseProductResultPaymentBL(DataSourceFactory.getDataSource());
//		}
//		return purchaseProductResultPaymentBL;
//	}
	
	public static module.prodpk.bl.ProdPKBL getProductionPKBL() {
		if (productionPKBL == null) {
			productionPKBL = new module.prodpk.bl.ProdPKBL(DataSourceFactory.getDataSource());
		}
		return productionPKBL;
	}
	
	public static PackingBL getPackingBL() {
		if (packingBL == null) {
			packingBL = new PackingBL(DataSourceFactory.getDataSource());
		}
		return packingBL;
	}
	
	public static ReceiveProductResultBL getReceiveProductResultBL() {
		if (receiveProductResultBL == null) {
			receiveProductResultBL = new ReceiveProductResultBL(DataSourceFactory.getDataSource());
		}
		return receiveProductResultBL;
	}
	
	
	public static PaymentProductResultBL getPaymentProductResultBL() {
		if (paymentProductResultBL == null) {
			paymentProductResultBL = new PaymentProductResultBL(DataSourceFactory.getDataSource());
		}
		return paymentProductResultBL;
	}
}