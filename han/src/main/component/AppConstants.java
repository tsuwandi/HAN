package main.component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.sun.javafx.collections.MappingChange.Map;

public class AppConstants {

	public static final int SUPP_TYPE_ID_BARECORE = 2;
	public static final int SUPP_TYPE_ID_HASIL_PRODUKSI = 3;
	public static final String PRODUCTION_TYPE_BARECORE = "Barecore";
	public static final String PRODUCT_CODE_KLEM_A_TYPE_9 = "PDC009-3";
	public static final String PRODUCT_CODE_KLEM_B_TYPE_9 = "PDC009-4";
	public static final String PRODUCT_CODE_PROD_RESULT_A_TYPE_9 = "PDC009-7";
	public static final String PRODUCT_CODE_PROD_RESULT_B_TYPE_9 = "PDC009-8";
	public static final String PRODUCT_CODE_NORMAL_A_TYPE_9 = "PDC009-1";
	public static final String PRODUCT_CODE_NORMAL_B_TYPE_9 = "PDC009-2";
	
	public static final String PRODUCT_CODE_KLEM_A_TYPE_12 = "PDC009-19";
	public static final String PRODUCT_CODE_KLEM_B_TYPE_12 = "PDC009-20";
	public static final String PRODUCT_CODE_PROD_RESULT_A_TYPE_12 = "PDC009-23"; // masih bingung
	public static final String PRODUCT_CODE_PROD_RESULT_B_TYPE_12 = "PDC009-24"; // masih bingung
	public static final String PRODUCT_CODE_NORMAL_A_TYPE_12 = "PDC009-17";
	public static final String PRODUCT_CODE_NORMAL_B_TYPE_12 = "PDC009-18";
	
	public static final String BC_TYPE_12 = "BC12";
	public static final String BC_TYPE_9 = "BC9";
	
	public static final String STATUS_COMPLETED = "COMPLETED";
	public static final String STATUS_FINAL = "FINAL";
	
	public static final String RECEIVE_MODULE = "PENERIMAAN";
	public static final String PRODUCTION_MODULE = "PRODUKSI";
	public static final String RECEIVED = "RECEIVED";
	public static final String DRY_IN = "DRY_IN";
	public static final String DRY_OUT = "DRY_OUT";
	public static final String DEBET = "D";
	public static final String CREDIT = "C";
	
	public static final String BALKEN_BASAH_CD = "B";
	public static final String BALKEN_KERING_CD = "K";
	public static final String HASIL_PRODUKSI_CD = "PDC009";
	public static final String BARANG_PENDUKUNG_CD = "P";
	
	public static final String BALKEN_BASAH = "Balken Basah";
	public static final String BALKEN_KERING = "Balken Kering";
	public static final String HASIL_PRODUKSI = "Hasil Produksi";
	public static final String BARANG_PENDUKUNG = "Barang Pendukung";
	
	public static final int BALKEN_BASAH_ID = 1;
	public static final int BALKEN_KERING_ID = 2;
	public static final int HASIL_PRODUKSI_ID = 3;
	public static final int BARANG_PENDUKUNG_ID = 4;
	
	public static final String CURRENCY_IDR = "IDR";
	
	public static final String SUPP_TYPE_BALKEN_CD = "SBL";
	public static final String SUPP_TYPE_BARECORE_CD = "SBR";
	public static final String SUPP_TYPE_VENEER_CD = "SVN";
	public static final String SUPP_TYPE_FACEBACK_CD = "SFB";
	public static final String SUPP_TYPE_PROD_SUPP_CD = "SPP";
	
	public static final int SUPP_TYPE_BALKEN_ID = 1;
	public static final int SUPP_TYPE_BARECORE_ID = 2;
	public static final int SUPP_TYPE_VENEER_ID = 3;
	public static final int SUPP_TYPE_FACEBACK_ID = 4;
	public static final int SUPP_TYPE_PROD_SUPP_ID = 5;
	
	public static final int WAREHOUSE_0 = 0;
	
	public static final String NEW = "NEW";
	public static final String COMPLETED = "COMPLETED";
	public static final String FINAL = "FINAL";
	
	public static final int DIVIDER_VOLUME=1000000;
	
	public static DecimalFormat FOUR_DIGIT_DECIMAL_FORMAT= null;
	public static java.util.Map<String, String> commonMap;
	public static final String TYPE_12 = "12";
	public static final String TYPE_9 = "9";

	
	
	public static final String SOURCE_INVOICE = "INVOICE";
	public static final String SOURCE_PAYMENT = "PAYMENT";
	public static final String SOURCE_RECEIVE = "RECEIVE";
	public static final String SOURCE_INVOICE_BALKEN = "INVOICE_BALKEN";
	public static final String SOURCE_RECEIVED = "RECEIVED";
	public static final String SOURCE_PAYMENT_BALKEN = "PAYMENT_BALKEN";
	
	public static final String PROD_RM = "PROD_RM";
	public static final String PROD_RESULT_PRODUCT = "PROD_RESULT_PRODUCT";
	public static final String PROD_WASTE_RESULT_PRODUCT = "PROD_WASTE_RESULT_PRODUCT";
	public static final String PROD_PK_MATERIAL = "PROD_PK_MATERIAL";
	public static final String PROD_PK_RESULT_PRODUCT = "PROD_PK_RESULT_PRODUCT";
	public static final String PPR_PRODUCT = "PPR_PRODUCT";
	public static final String PACKING_RM = "PACKING_RM";
	public static final String PACKING_RESULT = "PACKING_RESULT";
}
