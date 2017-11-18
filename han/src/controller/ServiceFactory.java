package controller;

import module.customer.bl.CustomerBL;
import module.dailyclosing.bl.DailyClosingBL;
import module.dryin.bl.DryInBL;
import module.dryout.bl.DryOutBL;
import module.employee.bl.EmployeeBL;
import module.employeemanagement.bl.EmployeeManagementBL;
import module.invoicebalken.bl.InvoiceBalkenBL;
import module.invoiceprodresult.bl.InvoiceProductResultBL;
import module.mastershift.bl.MasterShiftBL;
import module.packingresult.bl.PackingBL;
import module.paymentbalken.bl.PaymentBalkenBL;
import module.paymentprodresult.bl.PaymentProductResultBL;
import module.personalia.bl.PersonaliaBL;
import module.product.bl.ProductBL;
import module.production.bl.ProductionBL;
import module.productionpk.bl.ProdPKBL;
import module.productionwaste.bl.ProductionWasteBL;
import module.productsupportinggood.bl.ProductSupportingGoodBL;
import module.purchaseprodresult.bl.PurchaseProductResultBL;
import module.purchaseprodsupp.bl.PurchaseProductSuppBL;
import module.receiveprodresult.bl.ReceiveProductResultBL;
import module.receiveprodsupp.bl.ReceiveProductSuppBL;
import module.report.bl.ReportBL;
import module.salary.bl.CalculateSalaryBL;
import module.sales.bl.SalesBL;
import module.sendtofinance.bl.SendToFinanceBL;
import module.sn.woodgenus.bl.WoodGenusBL;
import module.sn.woodtype.bl.WoodTypeBL;
import module.stockopname.bl.StockOpnameBL;
import module.supplier.bl.SupplierBL;
import module.system.bl.SystemBL;

public class ServiceFactory {
	// private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

	private static SupplierBL supplierBL = null;
	private static CustomerBL customerBL = null;
	private static SalesBL salesBL = null;
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
	private static InvoiceProductResultBL invoiceProductResultBL = null;
	private static PaymentProductResultBL paymentProductResultBL = null;
	private static InvoiceBalkenBL invoiceBalkenBL = null;
	private static PaymentBalkenBL paymentBalkenBL = null;
	private static StockOpnameBL stockOpnameBL = null;
	private static MasterShiftBL masterShiftBL = null;
	private static CalculateSalaryBL calculateSalaryBL = null;
	private static ProductSupportingGoodBL productSupportingGoodBL = null;
	private static EmployeeManagementBL employeeManagementBL = null;
	private static PurchaseProductSuppBL purchaseProductSuppBL = null;
	private static ReceiveProductSuppBL receiveProductSuppBL = null;
	
	
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
	 * Method to init or return {@link CustomerBL}
	 * 
	 * @return {@link CustomerBL}
	 */
	public static CustomerBL getCustomerBL() {
		if (customerBL == null) {
			customerBL = new CustomerBL(DataSourceFactory.getDataSource());
		}
		return customerBL;
	}
	
	/**
	 * Method to init or return {@link SalesBL}
	 * 
	 * @return {@link SalesBL}
	 */
	public static SalesBL getSalesBL() {
		if (salesBL == null) {
			salesBL = new SalesBL(DataSourceFactory.getDataSource());
		}
		return salesBL;
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
	
	public static InvoiceProductResultBL getInvoiceProductResultBL() {
		if (invoiceProductResultBL == null) {
			invoiceProductResultBL = new InvoiceProductResultBL(DataSourceFactory.getDataSource());
		}
		return invoiceProductResultBL;
	}
	
	public static PaymentProductResultBL getPaymentProductResultBL() {
		if (paymentProductResultBL == null) {
			paymentProductResultBL = new PaymentProductResultBL(DataSourceFactory.getDataSource());
		}
		return paymentProductResultBL;
	}
	
	public static InvoiceBalkenBL getInvoiceBalkenBL() {
		if (invoiceBalkenBL == null) {
			invoiceBalkenBL = new InvoiceBalkenBL(DataSourceFactory.getDataSource());
		}
		return invoiceBalkenBL;
	}
	
	public static PaymentBalkenBL getPaymentBalkenBL() {
		if (paymentBalkenBL == null) {
			paymentBalkenBL = new PaymentBalkenBL(DataSourceFactory.getDataSource());
		}
		return paymentBalkenBL;
	}
	
	public static StockOpnameBL getStockOpnameBL() {
		if (stockOpnameBL == null) {
			stockOpnameBL = new StockOpnameBL(DataSourceFactory.getDataSource());
		}
		return stockOpnameBL;
	}
	
	public static MasterShiftBL getMasterShiftBL() {
		if (masterShiftBL == null) {
			masterShiftBL = new MasterShiftBL(DataSourceFactory.getDataSource());
		}
		return masterShiftBL;
	}
	
	public static CalculateSalaryBL getCalculateSalaryBL() {
		if (calculateSalaryBL == null) {
			calculateSalaryBL = new CalculateSalaryBL(DataSourceFactory.getDataSource());
		}
		return calculateSalaryBL;
	}

	public static ProductSupportingGoodBL getProductSupportingGoodBL() {
		if (productSupportingGoodBL == null) {
			productSupportingGoodBL = new ProductSupportingGoodBL(DataSourceFactory.getDataSource());
		}
		return productSupportingGoodBL;
	}
	
	public static EmployeeManagementBL getEmployeeManagementBL() {
		if (employeeManagementBL == null) {
			employeeManagementBL = new EmployeeManagementBL(DataSourceFactory.getDataSource());
		}
		return employeeManagementBL;
	}
	
	public static PurchaseProductSuppBL getPurchaseProductSuppBL() {
		if (purchaseProductSuppBL == null) {
			purchaseProductSuppBL = new PurchaseProductSuppBL(DataSourceFactory.getDataSource());
		}
		return purchaseProductSuppBL;
	}
	
	public static ReceiveProductSuppBL getReceiveProductSuppBL() {
		if (receiveProductSuppBL == null) {
			receiveProductSuppBL = new ReceiveProductSuppBL(DataSourceFactory.getDataSource());
		}
		return receiveProductSuppBL;
	}
	
	
}