package module.productsupportinggood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import module.product.model.ProductCategory;
import module.product.model.Uom;
import module.productsupportinggood.model.ProductSupp;
import module.productsupportinggood.ui.ProductSupportingGoodListPanel;
import module.sn.bank.model.Bank;
import module.sn.currency.model.Currency;
import module.sn.supptype.model.SuppType;
import module.supplier.model.Supplier;
import module.util.DateUtil;

public class ProductSuppDAO {
	private static final Logger LOGGER = Logger.getLogger(ProductSuppDAO.class);
	
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement isProductCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = new StringBuilder()
			.append("SELECT ps.id, product_code, product_name, product_category_id, product_uom_id, is_maintain_stock, ")
			.append("image_path, brand, barcode, description, is_fixed_asset, warranty, weight_net, weight_gross, ")
			.append("weight_uom_id, is_purchase_item, minor, minor_uom_id, lead_time, buy_cost_center_id, expense_acc_id, ")
			.append("main_supp_code, manufacturer, is_sales_item, is_service_item, sell_cost_center_id, income_acc_id, ")
			.append("asset_id, max_disc, ps.input_date, ps.input_by, ps.edit_date, ps.edited_by, ps.deleted_date, ps.deleted_by, minqty, ")
			.append("thickness, length, width, volume_uom_id, tax_id, pc.product_category ")
			.append("FROM product_supp ps ")
			.append("LEFT JOIN uom uom_weight on ps.minor_uom_id = uom_weight.id ")
			.append("LEFT JOIN uom uom_minor on ps.volume_uom_id = uom_minor.id ")
			.append("LEFT JOIN uom uom_volume on ps.minor_uom_id = uom_volume.id ")
			.append("LEFT JOIN uom uom_product on ps.product_uom_id = uom_product.id ")
			.append("LEFT JOIN tax tax on ps.tax_id = tax.id ")
			.append("LEFT JOIN product_category pc on pc.product_category_id = pc.id ")
			.append("WHERE ps.deleted_date is null ")
			.toString();
			
	private String isProductCodeExistsQuery = "select count(*) as is_exists from product_supp where product_code = ? and deleted_date is null ";

	private String insertQuery = new StringBuilder()
			.append("INSERT INTO product_supp ")
			.append("( product_code, product_name, product_category_id, product_uom_id, is_maintain_stock, ")
			.append("image_path, brand, barcode, description, is_fixed_asset, warranty, weight_net, weight_gross, ")
			.append("weight_uom_id, is_purchase_item, minor, minor_uom_id, lead_time, buy_cost_center_id, expense_acc_id, ")
			.append("main_supp_code, manufacturer, is_sales_item, is_service_item, sell_cost_center_id, income_acc_id, ")
			.append("asset_id, max_disc, input_date, input_by, minqty, ")
			.append("thickness, length, width, volume_uom_id, tax_id ) ")
			.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ").toString();

	private String updateQuery = new StringBuilder()
			.append("UPDATE product_supp SET ")
			.append("product_name=?, product_category_id=?, product_uom_id=?, is_maintain_stock=?, ")
			.append("image_path=?, brand=?, barcode=?, description=?, is_fixed_asset=?, warranty=?, weight_net=?, weight_gross=?, ")
			.append("weight_uom_id=?, is_purchase_item=?, minor=?, minor_uom_id=?, lead_time=?, buy_cost_center_id=?, expense_acc_id=?, ")
			.append("main_supp_code=?, manufacturer=?, is_sales_item=?, is_service_item=?, sell_cost_center_id=?, income_acc_id=?, ")
			.append("asset_id=?, max_disc=?, edit_date=?, edited_by=?, minqty=?, ")
			.append("thickness=?, length=?, width=?, volume_uom_id=?, tax_id=? WHERE product_code=?").toString();
	
	private String deleteQuery = "UPDATE product_supp SET deleted_date=?, deleted_by=? WHERE id=?";

	public ProductSuppDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ProductSupp> getAll() throws SQLException {
		List<ProductSupp> productSupps = new ArrayList<ProductSupp>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				
				ProductSupp productSupp = new ProductSupp();
				productSupp.setId(rs.getInt("id"));
				productSupp.setProductCode(rs.getString("product_code"));
				productSupp.setProductName(rs.getString("product_name"));
				productSupp.setProductCategoryId(rs.getInt("product_category_id"));
				productSupp.setProductUomId(rs.getInt("product_uom_id"));
				productSupp.setIsMaintainStock(rs.getInt("is_maintain_stock"));
				productSupp.setImagePath(rs.getString("image_path"));
				productSupp.setBrand(rs.getString("brand"));
				productSupp.setBarcode(rs.getString("barcode"));
				productSupp.setDescription(rs.getString("description"));
				productSupp.setIsFixedAsset(rs.getInt("is_fixed_asset"));
				productSupp.setWarranty(rs.getInt("warranty"));
				productSupp.setWeightNet(rs.getBigDecimal("weight_net"));
				productSupp.setWeightGross(rs.getBigDecimal("weight_gross"));
				productSupp.setWeightUomId(rs.getInt("weight_uom_id"));
				productSupp.setIsPurchaseItem(rs.getInt("is_purchase_item"));
				productSupp.setMinor(rs.getInt("minor"));
				productSupp.setMinorUomId(rs.getInt("minor_uom_id"));
				productSupp.setLeadTime(rs.getInt("lead_time"));
				productSupp.setBuyCostCenterId(rs.getInt("buy_cost_center_id"));
				productSupp.setExpenseAccId(rs.getInt("expense_acc_id"));
				productSupp.setMainSuppCode(rs.getString("main_supp_code"));
				productSupp.setManufacturer(rs.getString("manufacturer"));
				productSupp.setIsSalesItem(rs.getInt("is_sales_item"));
				productSupp.setIsServiceItem(rs.getInt("is_service_item"));
				productSupp.setSellCostCenterId(rs.getInt("sell_cost_center_id"));
				productSupp.setIncomeAccId(rs.getInt("income_acc_id"));
				productSupp.setAssetId(rs.getInt("asset_id"));
				productSupp.setMaxDisc(rs.getBigDecimal("max_disc"));
				productSupp.setMinqty(rs.getInt("minqty"));
				productSupp.setThickness(rs.getBigDecimal("thickness"));
				productSupp.setLength(rs.getBigDecimal("length"));
				productSupp.setWidth(rs.getBigDecimal("width"));
				productSupp.setVolumeUomId(rs.getInt("volume_uom_id"));
				productSupp.setTaxId(rs.getInt("tax_id"));
				
				ProductCategory productCategory = new ProductCategory();
				productCategory.setProductCategory(rs.getString("product_category"));
				productSupp.setProductCategory(productCategory);
				
				productSupps.add(productSupp);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productSupps;
	}

	public List<ProductSupp> getAllBySimpleSearch(String value) throws SQLException {
		List<ProductSupp> productSupps = new ArrayList<ProductSupp>();
		try {
			StringBuilder query = new StringBuilder().append(getAllQuery);
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append('%').append(value).append('%').toString();
				query.append(" and (lower(product_code) like lower('%s')");
				query.append(" or lower(product_name) like lower('%s')");
				query.append(" or lower(product_category) like lower('%s')");
				query.append(" ) order by ps.id asc ");
				getAllStatement = connection.prepareStatement(String.format(query.toString(), keyword, keyword, keyword));
			} else {
				query.append(" order by ps.id asc");
				getAllStatement = connection.prepareStatement(query.toString());
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductSupp productSupp = new ProductSupp();
				productSupp.setId(rs.getInt("id"));
				productSupp.setProductCode(rs.getString("product_code"));
				productSupp.setProductName(rs.getString("product_name"));
				productSupp.setProductCategoryId(rs.getInt("product_category_id"));
				productSupp.setProductUomId(rs.getInt("product_uom_id"));
				productSupp.setIsMaintainStock(rs.getInt("is_maintain_stock"));
				productSupp.setImagePath(rs.getString("image_path"));
				productSupp.setBrand(rs.getString("brand"));
				productSupp.setBarcode(rs.getString("barcode"));
				productSupp.setDescription(rs.getString("description"));
				productSupp.setIsFixedAsset(rs.getInt("is_fixed_asset"));
				productSupp.setWarranty(rs.getInt("warranty"));
				productSupp.setWeightNet(rs.getBigDecimal("weight_net"));
				productSupp.setWeightGross(rs.getBigDecimal("weight_gross"));
				productSupp.setWeightUomId(rs.getInt("weight_uom_id"));
				productSupp.setIsPurchaseItem(rs.getInt("is_purchase_item"));
				productSupp.setMinor(rs.getInt("minor"));
				productSupp.setMinorUomId(rs.getInt("minor_uom_id"));
				productSupp.setLeadTime(rs.getInt("lead_time"));
				productSupp.setBuyCostCenterId(rs.getInt("buy_cost_center_id"));
				productSupp.setExpenseAccId(rs.getInt("expense_acc_id"));
				productSupp.setMainSuppCode(rs.getString("main_supp_code"));
				productSupp.setManufacturer(rs.getString("manufacturer"));
				productSupp.setIsSalesItem(rs.getInt("is_sales_item"));
				productSupp.setIsServiceItem(rs.getInt("is_service_item"));
				productSupp.setSellCostCenterId(rs.getInt("sell_cost_center_id"));
				productSupp.setIncomeAccId(rs.getInt("income_acc_id"));
				productSupp.setAssetId(rs.getInt("asset_id"));
				productSupp.setMaxDisc(rs.getBigDecimal("max_disc"));
				productSupp.setMinqty(rs.getInt("minqty"));
				productSupp.setThickness(rs.getBigDecimal("thickness"));
				productSupp.setLength(rs.getBigDecimal("length"));
				productSupp.setWidth(rs.getBigDecimal("width"));
				productSupp.setVolumeUomId(rs.getInt("volume_uom_id"));
				productSupp.setTaxId(rs.getInt("tax_id"));
				
				ProductCategory productCategory = new ProductCategory();
				productCategory.setProductCategory(rs.getString("product_category"));
				productSupp.setProductCategory(productCategory);
				
				productSupps.add(productSupp);
				
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return productSupps;
	}

	public int isProductCodeExists(String productCode) throws SQLException {
		int count = 0;
		try {
			isProductCodeExistsStatement = connection.prepareStatement(isProductCodeExistsQuery);
			isProductCodeExistsStatement.setString(1, productCode);

			ResultSet rs = isProductCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(ProductSupp productSupp) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.executeUpdate();
			
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(ProductSupp productSupp) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void delete(int id) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public ProductSupp getById(int id) throws SQLException {
		ProductSupp productSupp = null;
		String query = new StringBuilder().append(getAllQuery).append(" and ps.id=?").toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, id);
			ResultSet rs = getAllStatement.executeQuery();
			
			while (rs.next()) {	
				productSupp = new ProductSupp();
				productSupp.setId(rs.getInt("id"));
				productSupp.setProductCode(rs.getString("product_code"));
				productSupp.setProductName(rs.getString("product_name"));
				productSupp.setProductCategoryId(rs.getInt("product_category_id"));
				productSupp.setProductUomId(rs.getInt("product_uom_id"));
				productSupp.setIsMaintainStock(rs.getInt("is_maintain_stock"));
				productSupp.setImagePath(rs.getString("image_path"));
				productSupp.setBrand(rs.getString("brand"));
				productSupp.setBarcode(rs.getString("barcode"));
				productSupp.setDescription(rs.getString("description"));
				productSupp.setIsFixedAsset(rs.getInt("is_fixed_asset"));
				productSupp.setWarranty(rs.getInt("warranty"));
				productSupp.setWeightNet(rs.getBigDecimal("weight_net"));
				productSupp.setWeightGross(rs.getBigDecimal("weight_gross"));
				productSupp.setWeightUomId(rs.getInt("weight_uom_id"));
				productSupp.setIsPurchaseItem(rs.getInt("is_purchase_item"));
				productSupp.setMinor(rs.getInt("minor"));
				productSupp.setMinorUomId(rs.getInt("minor_uom_id"));
				productSupp.setLeadTime(rs.getInt("lead_time"));
				productSupp.setBuyCostCenterId(rs.getInt("buy_cost_center_id"));
				productSupp.setExpenseAccId(rs.getInt("expense_acc_id"));
				productSupp.setMainSuppCode(rs.getString("main_supp_code"));
				productSupp.setManufacturer(rs.getString("manufacturer"));
				productSupp.setIsSalesItem(rs.getInt("is_sales_item"));
				productSupp.setIsServiceItem(rs.getInt("is_service_item"));
				productSupp.setSellCostCenterId(rs.getInt("sell_cost_center_id"));
				productSupp.setIncomeAccId(rs.getInt("income_acc_id"));
				productSupp.setAssetId(rs.getInt("asset_id"));
				productSupp.setMaxDisc(rs.getBigDecimal("max_disc"));
				productSupp.setMinqty(rs.getInt("minqty"));
				productSupp.setThickness(rs.getBigDecimal("thickness"));
				productSupp.setLength(rs.getBigDecimal("length"));
				productSupp.setWidth(rs.getBigDecimal("width"));
				productSupp.setVolumeUomId(rs.getInt("volume_uom_id"));
				productSupp.setTaxId(rs.getInt("tax_id"));
				
				ProductCategory productCategory = new ProductCategory();
				productCategory.setProductCategory(rs.getString("product_category"));
				productSupp.setProductCategory(productCategory);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return productSupp;
	}

	private PreparedStatement getOrdinalOfCodeNumberStatement;
	
	private String getOrdinalOfCodeNumberQuery = "SELECT CONVERT(SUBSTRING_INDEX(p.product_code, '-', -1),UNSIGNED INTEGER) AS ordinal FROM product_supp p "
			+ "INNER JOIN product_category pc ON pc.id = p.product_category_id "
			+ "WHERE pc.product_category = ? "
			+ "ORDER BY ordinal DESC LIMIT 1 ";
	
	public int getOrdinalOfCodeNumber(String productCategory) throws SQLException {
		int ordinal = 0;
		try {
			getOrdinalOfCodeNumberStatement = connection.prepareStatement(getOrdinalOfCodeNumberQuery);
			getOrdinalOfCodeNumberStatement.setString(1, productCategory);
			ResultSet rs = getOrdinalOfCodeNumberStatement.executeQuery();
			while (rs.next()) {
				ordinal = rs.getInt("ordinal");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return ordinal;
	}
	
	//timotius@20170903_start
	private PreparedStatement getAllProductCategory;
	private PreparedStatement getAllUom;
	
	private String productCatQuery = "select id, product_category from product_category where delete_date is null order by id asc";
	private String uomQuery = "select id, uom from uom where delete_date is null order by id asc";
	
	public List<ProductCategory> getAllProductCategory() throws SQLException {
		List<ProductCategory> categories = new ArrayList<ProductCategory>();

		try {
			getAllProductCategory = connection.prepareStatement(productCatQuery);
			ResultSet rs = getAllProductCategory.executeQuery();
			while (rs.next()) {
				ProductCategory productCat = new ProductCategory();
				productCat.setId(rs.getInt("id"));
				productCat.setProductCategory(rs.getString("product_category"));
				categories.add(productCat);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return categories;
	}
	
	public List<Uom> getAllUom() throws SQLException {
		List<Uom> units = new ArrayList<Uom>();

		try {
			getAllUom = connection.prepareStatement(uomQuery);
			ResultSet rs = getAllUom.executeQuery();
			while (rs.next()) {
				Uom uom = new Uom();
				uom.setId(rs.getInt("id"));
				uom.setUom(rs.getString("uom"));
				units.add(uom);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return units;
	}
	
	private PreparedStatement isProductNameExistsStatement;
	private String isProductNameExistsQuery = "select count(*) as is_exists from product_supp where product_name = ? ";

	public int isProductNameExists(String productName) throws SQLException {
		int count = 0;
		try {
			isProductNameExistsStatement = connection.prepareStatement(isProductNameExistsQuery);
			isProductNameExistsStatement.setString(1, productName);

			ResultSet rs = isProductNameExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return count;
	}
	//timotius@20170903_end
}
