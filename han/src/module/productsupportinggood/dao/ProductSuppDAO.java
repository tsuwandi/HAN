package module.productsupportinggood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import module.personalia.model.Tax;
import module.productsupportinggood.model.ProductSupp;
import module.productsupportinggood.ui.ProductSupportingGoodListPanel;
import module.sn.bank.model.Bank;
import module.sn.currency.model.Currency;
import module.sn.productcategory.model.ProductCategory;
import module.sn.supptype.model.SuppType;
import module.sn.uom.model.Uom;
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
			.append("thickness, length, width, volume_uom_id, tax_id, pc.product_category, ")
			.append("uom_weight.uom uom_weight_uom, uom_minor.uom uom_minor_uom, uom_volume.uom uom_volume_uom, uom_product.uom uom_product_uom, tax.tax ")
			.append("FROM product_supp ps ")
			.append("LEFT JOIN uom uom_weight on ps.minor_uom_id = uom_weight.id ")
			.append("LEFT JOIN uom uom_minor on ps.volume_uom_id = uom_minor.id ")
			.append("LEFT JOIN uom uom_volume on ps.minor_uom_id = uom_volume.id ")
			.append("LEFT JOIN uom uom_product on ps.product_uom_id = uom_product.id ")
			.append("LEFT JOIN tax tax on ps.tax_id = tax.id ")
			.append("LEFT JOIN product_category pc on ps.product_category_id = pc.id ")
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
				productSupp.setWarranty(rs.getObject("warranty") != null ? rs.getInt("warranty") : null);
				productSupp.setWeightNet(rs.getBigDecimal("weight_net"));
				productSupp.setWeightGross(rs.getBigDecimal("weight_gross"));
				productSupp.setWeightUomId(rs.getInt("weight_uom_id"));
				productSupp.setIsPurchaseItem(rs.getInt("is_purchase_item"));
				productSupp.setMinor(rs.getObject("minor") != null ? rs.getInt("minor") : null);
				productSupp.setMinorUomId(rs.getInt("minor_uom_id"));
				productSupp.setLeadTime(rs.getObject("lead_time") != null ? rs.getInt("lead_time") : null);
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
				productSupp.setWarranty(rs.getObject("warranty") != null ? rs.getInt("warranty") : null);
				productSupp.setWeightNet(rs.getBigDecimal("weight_net"));
				productSupp.setWeightGross(rs.getBigDecimal("weight_gross"));
				productSupp.setWeightUomId(rs.getInt("weight_uom_id"));
				productSupp.setIsPurchaseItem(rs.getInt("is_purchase_item"));
				productSupp.setMinor(rs.getObject("minor") != null ? rs.getInt("minor") : null);
				productSupp.setMinorUomId(rs.getInt("minor_uom_id"));
				productSupp.setLeadTime(rs.getObject("lead_time") != null ? rs.getInt("lead_time") : null);
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
			insertStatement.setString(1, productSupp.getProductCode());
			insertStatement.setString(2, productSupp.getProductName());
			insertStatement.setInt(3, productSupp.getProductCategoryId());
			insertStatement.setInt(4, productSupp.getProductUomId());
			insertStatement.setInt(5, productSupp.getIsMaintainStock());
			// not use for now
			insertStatement.setString(6, productSupp.getImagePath());
			insertStatement.setString(7, productSupp.getBrand());
			// not use for now
			insertStatement.setString(8, productSupp.getBarcode());
			insertStatement.setString(9, productSupp.getDescription());
			insertStatement.setInt(10, productSupp.getIsFixedAsset());
			if (productSupp.getWarranty() == null) {
				insertStatement.setNull(11, java.sql.Types.INTEGER);
			} else {
				insertStatement.setInt(11, productSupp.getWarranty());
			}
			insertStatement.setBigDecimal(12, productSupp.getWeightNet());
			insertStatement.setBigDecimal(13, productSupp.getWeightGross());
			if (productSupp.getWeightUomId() == null) {
				insertStatement.setNull(14, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getWeightUomId() == 0) {
					insertStatement.setNull(14, java.sql.Types.INTEGER);
				} else {
					insertStatement.setInt(14, productSupp.getWeightUomId());
				}
				
			}
			insertStatement.setInt(15, productSupp.getIsPurchaseItem());
			if (productSupp.getMinor() == null) {
				insertStatement.setNull(16, java.sql.Types.INTEGER);
			} else {
				insertStatement.setInt(16, productSupp.getMinor());
			}
			if (productSupp.getMinorUomId() == null) {
				insertStatement.setNull(17, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getMinorUomId() == 0) {
					insertStatement.setNull(17, java.sql.Types.INTEGER);
				} else {
					insertStatement.setInt(17, productSupp.getMinorUomId());
				}
			}
			if (productSupp.getLeadTime() == null) {
				insertStatement.setNull(18, java.sql.Types.INTEGER);
			} else {
				insertStatement.setInt(18, productSupp.getLeadTime());
			}
			if (productSupp.getBuyCostCenterId() == null) {
				insertStatement.setNull(19, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getBuyCostCenterId() == 0) {
					insertStatement.setNull(19, java.sql.Types.INTEGER);
				} else {
					insertStatement.setInt(19, productSupp.getBuyCostCenterId());
				}
			}
			if (productSupp.getExpenseAccId() == null) {
				insertStatement.setNull(20, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getExpenseAccId() == 0) {
					insertStatement.setNull(20, java.sql.Types.INTEGER);
				} else {
					insertStatement.setInt(20, productSupp.getExpenseAccId());
				}
			}
			
			//not use for now
			insertStatement.setString(21, productSupp.getMainSuppCode());
			insertStatement.setString(22, productSupp.getManufacturer());
			//not use for now
			
			insertStatement.setInt(23, productSupp.getIsSalesItem());
			insertStatement.setInt(24, productSupp.getIsServiceItem());
			if (productSupp.getSellCostCenterId() == null) {
				insertStatement.setNull(25, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getSellCostCenterId() == 0) {
					insertStatement.setNull(25, java.sql.Types.INTEGER);
				} else {
					insertStatement.setInt(25, productSupp.getSellCostCenterId());
				}
			}
			
			if (productSupp.getIncomeAccId() == null) {
				insertStatement.setNull(26, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getIncomeAccId() == 0) {
					insertStatement.setNull(26, java.sql.Types.INTEGER);
				} else {
					insertStatement.setInt(26, productSupp.getIncomeAccId());
				}
			}
			
			if (productSupp.getAssetId() == null) {
				insertStatement.setNull(27, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getAssetId() == 0) {
					insertStatement.setNull(27, java.sql.Types.INTEGER);
				} else {
					insertStatement.setInt(27, productSupp.getAssetId());
				}
			}
			
			insertStatement.setBigDecimal(28, productSupp.getMaxDisc());
			insertStatement.setDate(29, DateUtil.getCurrentDate());
			insertStatement.setString(30, ServiceFactory.getSystemBL().getUsernameActive());
			if (productSupp.getMinqty() == null) {
				insertStatement.setNull(31, java.sql.Types.INTEGER);
			} else {
				insertStatement.setInt(31, productSupp.getMinqty());
			}
			insertStatement.setBigDecimal(32, productSupp.getThickness());
			insertStatement.setBigDecimal(33, productSupp.getLength());
			insertStatement.setBigDecimal(34, productSupp.getWidth());
			if (productSupp.getVolumeUomId() == null) {
				insertStatement.setNull(35, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getVolumeUomId() == 0) {
					insertStatement.setNull(35, java.sql.Types.INTEGER);
				} else {
					insertStatement.setInt(35, productSupp.getVolumeUomId());
				}
			}
			if (productSupp.getTaxId() == null) {
				insertStatement.setNull(36, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getTaxId() == 0) {
					insertStatement.setNull(36, java.sql.Types.INTEGER);
				} else {
					insertStatement.setInt(36, productSupp.getTaxId());
				}
			}
			
			insertStatement.executeUpdate();
			
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(ProductSupp productSupp) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, productSupp.getProductCode());
			updateStatement.setString(2, productSupp.getProductName());
			updateStatement.setInt(3, productSupp.getProductCategoryId());
			updateStatement.setInt(4, productSupp.getProductUomId());
			updateStatement.setInt(5, productSupp.getIsMaintainStock());
			// not use for now
			updateStatement.setString(6, productSupp.getImagePath());
			updateStatement.setString(7, productSupp.getBrand());
			// not use for now
			updateStatement.setString(8, productSupp.getBarcode());
			updateStatement.setString(9, productSupp.getDescription());
			updateStatement.setInt(10, productSupp.getIsFixedAsset());
			if (productSupp.getWarranty() == null) {
				updateStatement.setNull(11, java.sql.Types.INTEGER);
			} else {
				updateStatement.setInt(11, productSupp.getWarranty());
			}
			updateStatement.setBigDecimal(12, productSupp.getWeightNet());
			updateStatement.setBigDecimal(13, productSupp.getWeightGross());
			if (productSupp.getWeightUomId() == null) {
				updateStatement.setNull(14, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getWeightUomId() == 0) {
					updateStatement.setNull(14, java.sql.Types.INTEGER);
				} else {
					updateStatement.setInt(14, productSupp.getWeightUomId());
				}
				
			}
			updateStatement.setInt(15, productSupp.getIsPurchaseItem());
			if (productSupp.getMinor() == null) {
				updateStatement.setNull(16, java.sql.Types.INTEGER);
			} else {
				updateStatement.setInt(16, productSupp.getMinor());
			}
			if (productSupp.getMinorUomId() == null) {
				updateStatement.setNull(17, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getMinorUomId() == 0) {
					updateStatement.setNull(17, java.sql.Types.INTEGER);
				} else {
					updateStatement.setInt(17, productSupp.getMinorUomId());
				}
			}
			if (productSupp.getLeadTime() == null) {
				updateStatement.setNull(18, java.sql.Types.INTEGER);
			} else {
				updateStatement.setInt(18, productSupp.getLeadTime());
			}
			if (productSupp.getBuyCostCenterId() == null) {
				updateStatement.setNull(19, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getBuyCostCenterId() == 0) {
					updateStatement.setNull(19, java.sql.Types.INTEGER);
				} else {
					updateStatement.setInt(19, productSupp.getBuyCostCenterId());
				}
			}
			if (productSupp.getExpenseAccId() == null) {
				updateStatement.setNull(20, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getExpenseAccId() == 0) {
					updateStatement.setNull(20, java.sql.Types.INTEGER);
				} else {
					updateStatement.setInt(20, productSupp.getExpenseAccId());
				}
			}
			
			//not use for now
			updateStatement.setString(21, productSupp.getMainSuppCode());
			updateStatement.setString(22, productSupp.getManufacturer());
			//not use for now
			
			updateStatement.setInt(23, productSupp.getIsSalesItem());
			updateStatement.setInt(24, productSupp.getIsServiceItem());
			if (productSupp.getSellCostCenterId() == null) {
				updateStatement.setNull(25, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getSellCostCenterId() == 0) {
					updateStatement.setNull(25, java.sql.Types.INTEGER);
				} else {
					updateStatement.setInt(25, productSupp.getSellCostCenterId());
				}
			}
			
			if (productSupp.getIncomeAccId() == null) {
				updateStatement.setNull(26, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getIncomeAccId() == 0) {
					updateStatement.setNull(26, java.sql.Types.INTEGER);
				} else {
					updateStatement.setInt(26, productSupp.getIncomeAccId());
				}
			}
			
			if (productSupp.getAssetId() == null) {
				updateStatement.setNull(27, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getAssetId() == 0) {
					updateStatement.setNull(27, java.sql.Types.INTEGER);
				} else {
					updateStatement.setInt(27, productSupp.getAssetId());
				}
			}
			
			updateStatement.setBigDecimal(28, productSupp.getMaxDisc());
			updateStatement.setDate(29, DateUtil.getCurrentDate());
			updateStatement.setString(30, ServiceFactory.getSystemBL().getUsernameActive());
			if (productSupp.getMinqty() == null) {
				updateStatement.setNull(31, java.sql.Types.INTEGER);
			} else {
				updateStatement.setInt(31, productSupp.getMinqty());
			}
			updateStatement.setBigDecimal(32, productSupp.getThickness());
			updateStatement.setBigDecimal(33, productSupp.getLength());
			updateStatement.setBigDecimal(34, productSupp.getWidth());
			if (productSupp.getVolumeUomId() == null) {
				updateStatement.setNull(35, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getVolumeUomId() == 0) {
					updateStatement.setNull(35, java.sql.Types.INTEGER);
				} else {
					updateStatement.setInt(35, productSupp.getVolumeUomId());
				}
			}
			if (productSupp.getTaxId() == null) {
				updateStatement.setNull(36, java.sql.Types.INTEGER);
			} else {
				if(productSupp.getTaxId() == 0) {
					updateStatement.setNull(36, java.sql.Types.INTEGER);
				} else {
					updateStatement.setInt(36, productSupp.getTaxId());
				}
			}
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
				productSupp.setWarranty(rs.getObject("warranty") != null ? rs.getInt("warranty") : null);
				productSupp.setWeightNet(rs.getBigDecimal("weight_net"));
				productSupp.setWeightGross(rs.getBigDecimal("weight_gross"));
				productSupp.setWeightUomId(rs.getInt("weight_uom_id"));
				productSupp.setIsPurchaseItem(rs.getInt("is_purchase_item"));
				productSupp.setMinor(rs.getObject("minor") != null ? rs.getInt("minor") : null);
				productSupp.setMinorUomId(rs.getInt("minor_uom_id"));
				productSupp.setLeadTime(rs.getObject("lead_time") != null ? rs.getInt("lead_time") : null);
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
				
				Uom productUom = new Uom();
				productUom.setUom(rs.getString("uom_product_uom"));
				productSupp.setProductUom(productUom);
				
				Uom volumeUom = new Uom();
				volumeUom.setUom(rs.getString("uom_volume_uom"));
				productSupp.setVolumeUom(volumeUom);
				
				Uom weightUom = new Uom();
				weightUom.setUom(rs.getString("uom_weight_uom"));
				productSupp.setWeightUom(weightUom);
				
				Uom minorUom = new Uom();
				minorUom.setUom(rs.getString("uom_minor_uom"));
				productSupp.setMinorUom(minorUom);
				
				Tax tax = new Tax();
				tax.setTax(rs.getString("tax"));
				productSupp.setTax(tax);
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
