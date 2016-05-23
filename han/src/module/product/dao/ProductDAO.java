package module.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.employee.model.Employee;
import module.product.model.Product;
import module.product.model.ProductCategory;
import module.sn.bank.model.Bank;
import module.sn.currency.model.Currency;
import module.sn.supptype.model.SuppType;
import module.supplier.model.Supplier;
import module.util.DateUtil;

public class ProductDAO {
	private DataSource dataSource;
	private Connection connection;
	private PreparedStatement getAllProductStatement;
	private PreparedStatement getProductIdStatement;
	private PreparedStatement getAllProductCategory;
	private PreparedStatement updateProductStatement;
	private PreparedStatement insertProductStatement;

	private String selectAllQuery = "select a.id, product_code, product_name, product_category_id, "
			+ "product_status, product_uom_id, is_maintain_stock, image_path, brand, barcode, description, "
			+ "wood_type_id, grade_id, thickness_id, condition_id, is_has_serial, is_fixed_asset, warranty, "
			+ "netto, netto_uom_id, is_purchase_item, minor, minor_uom_id, lead_time, buy_cost_center_id, "
			+ "expense_acc_id, main_supp_code, manufacturer, is_sales_item, is_service_item, sell_cost_center_id, "
			+ "income_acc_id, max_disc, a.input_date, a.input_by, a.edit_date, a.edited_by, "
			+ "b.wood_type, c.grade "
			+ "from product a join wood_type b on a.wood_type_id = b.id "
			+ "join grade c on a.grade_id = c.id "
			+ "where 1=1 order by a.id asc ";
	
	private String getAllNoOrder = "select a.id, product_code, product_name, product_category_id, "
			+ "product_status, product_uom_id, is_maintain_stock, image_path, brand, barcode, description, "
			+ "wood_type_id, grade_id, thickness_id, condition_id, is_has_serial, is_fixed_asset, warranty, "
			+ "netto, netto_uom_id, is_purchase_item, minor, minor_uom_id, lead_time, buy_cost_center_id, "
			+ "expense_acc_id, main_supp_code, manufacturer, is_sales_item, is_service_item, sell_cost_center_id, "
			+ "income_acc_id, max_disc, a.input_date, a.input_by, a.edit_date, a.edited_by, "
			+ "b.wood_type, c.grade, d.product_category "
			+ "from product a join wood_type b on a.wood_type_id = b.id "
			+ "join grade c on a.grade_id = c.id "
			+ "join product_category d on a.product_category_id = d.id "
			+ "where 1=1 ";
	
	private String searchQuery = "select a.id, product_code, product_name, product_category_id, "
			+ "product_status, product_uom_id, is_maintain_stock, image_path, brand, barcode, description, "
			+ "wood_type_id, grade_id, thickness_id, condition_id, is_has_serial, is_fixed_asset, warranty, "
			+ "netto, netto_uom_id, is_purchase_item, minor, minor_uom_id, lead_time, buy_cost_center_id, "
			+ "expense_acc_id, main_supp_code, manufacturer, is_sales_item, is_service_item, sell_cost_center_id, "
			+ "income_acc_id, max_disc, a.input_date, a.input_by, a.edit_date, a.edited_by, "
			+ "b.wood_type, c.grade "
			+ "from product a join wood_type b on a.wood_type_id = b.id "
			+ "join grade c on a.grade_id = c.id "
			+ "where 1=1 ";
	
	private String productCatQuery = "select * from product_category order by id asc";
	
	private String insertProductQuery = "insert into product(id, product_code, product_name, product_category_id, "
			+ "product_status, product_uom_id, is_maintain_stock, image_path, brand, barcode, description, "
			+ "wood_type_id, grade_id, thickness_id, condition_id, is_has_serial, is_fixed_asset, warranty, "
			+ "netto, netto_uom_id, is_purchase_item, minor, minor_uom_id, lead_time, buy_cost_center_id, "
			+ "expense_acc_id, main_supp_code, manufacturer, is_sales_item, is_service_item, sell_cost_center_id, "
			+ "income_acc_id, max_disc, input_date, input_by) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	
	private String updateQuery = "update product set product_name=?, product_status=?, brand=?, barcode=?, description=?, "
			+ "is_maintain_stock=?, is_has_serial=?, is_fixed_asset=?, is_purchase_item=?, is_sales_item=?, is_service_item=?, "			
			+ "wood_type_id=?, grade_id=?, thickness_id=?, condition_id=?, warranty=?, netto=?, netto_uom_id=?, minor=?, "
			+ "minor_uom_id=?, lead_time=?, buy_cost_center_id=?, expense_acc_id=?, main_supp_code=?, manufacturer=?, "
			+ "sell_cost_center_id=?, income_acc_id=?, max_disc=?, edit_date=?, edited_by=? where product_code=? ";
	
	private String getProductIdQuery = "select id from product";
	
	public ProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public void save(Product product) throws SQLException {
		try {
			insertProductStatement = connection.prepareStatement(insertProductQuery);
			java.sql.Date sqlInputDate = new java.sql.Date(product.getInputDate().getTime());
			
			insertProductStatement.setInt(1, product.getProductId());
			insertProductStatement.setString(2, product.getProductCode());
			insertProductStatement.setString(3, product.getProductName());
			insertProductStatement.setInt(4, product.getProductCat());
			insertProductStatement.setString(5, product.getProductStat());
			insertProductStatement.setInt(6, product.getProductUom());
			insertProductStatement.setInt(7, product.getIsMaintain());
			insertProductStatement.setString(8, product.getImagePath());
			insertProductStatement.setString(9, product.getBrand());
			insertProductStatement.setString(10, product.getBarcode());
			insertProductStatement.setString(11, product.getDescription());
			insertProductStatement.setInt(12, product.getWoodType());
			insertProductStatement.setInt(13, product.getGrade());
			insertProductStatement.setInt(14, product.getThickness());
			insertProductStatement.setInt(15, product.getCondition());
			insertProductStatement.setInt(16, product.getIsSerial());
			insertProductStatement.setInt(17, product.getIsAsset());
			insertProductStatement.setInt(18, product.getWarranty());
			insertProductStatement.setDouble(19, product.getNetto());
			insertProductStatement.setInt(20, product.getNettoUom());
			insertProductStatement.setInt(21, product.getIsPurchase());
			insertProductStatement.setInt(22, product.getMinor());
			insertProductStatement.setInt(23, product.getMinorUom());
			insertProductStatement.setInt(24, product.getLeadTime());
			insertProductStatement.setInt(25, product.getBuyCost());
			insertProductStatement.setInt(26, product.getExpense());
			insertProductStatement.setString(27, product.getMainSuppCode());
			insertProductStatement.setString(28, product.getManufacturer());
			insertProductStatement.setInt(29, product.getIsSales());
			insertProductStatement.setInt(30, product.getIsService());
			insertProductStatement.setInt(31, product.getSellCost());
			insertProductStatement.setInt(32, product.getIncome());
			insertProductStatement.setDouble(33, product.getMaxDisc());
			insertProductStatement.setDate(34, sqlInputDate);
			insertProductStatement.setString(35, product.getInputBy());
			insertProductStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	public List<Product> getAll() throws SQLException {
		List<Product> products = new ArrayList<Product>();
		try {
			getAllProductStatement = connection.prepareStatement(selectAllQuery);
			ResultSet rs = getAllProductStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("id"));
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));
				product.setProductCat(rs.getInt("product_category_id"));
				product.setProductStat(rs.getString("product_status"));
				product.setProductUom(rs.getInt("product_uom_id"));
				product.setIsMaintain(rs.getInt("is_maintain_stock"));
				product.setImagePath(rs.getString("image_path"));
				product.setBrand(rs.getString("brand"));
				product.setBarcode(rs.getString("barcode"));
				product.setDescription(rs.getString("description"));
				product.setWoodType(rs.getInt("wood_type_id"));
				product.setGrade(rs.getInt("grade_id"));
				product.setThickness(rs.getInt("thickness_id"));
				product.setCondition(rs.getInt("condition_id"));
				product.setIsSerial(rs.getInt("is_has_serial"));
				product.setIsAsset(rs.getInt("is_fixed_asset"));
				product.setWarranty(rs.getInt("warranty"));
				product.setNetto(rs.getDouble("netto"));
				product.setNettoUom(rs.getInt("netto_uom_id"));
				product.setIsPurchase(rs.getInt("is_purchase_item"));
				product.setMinor(rs.getInt("minor"));
				product.setMinorUom(rs.getInt("minor_uom_id"));
				product.setLeadTime(rs.getInt("lead_time"));
				product.setBuyCost(rs.getInt("buy_cost_center_id"));
				product.setExpense(rs.getInt("expense_acc_id"));
				product.setMainSuppCode(rs.getString("main_supp_code"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setIsSales(rs.getInt("is_sales_item"));
				product.setIsService(rs.getInt("is_service_item"));
				product.setSellCost(rs.getInt("sell_cost_center_id"));
				product.setIncome(rs.getInt("income_acc_id"));
				product.setMaxDisc(rs.getDouble("max_disc"));
				product.setInputDate(rs.getDate("a.input_date"));
				product.setInputBy(rs.getString("a.input_by"));
				product.setEditDate(rs.getDate("a.edit_date"));
				product.setEditBy(rs.getString("a.edited_by"));
				product.setWoodTypeName(rs.getString("b.wood_type"));
				product.setGradeName(rs.getString("c.grade"));

				products.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return products;
	}
	
	public List<Product> getProductId() throws SQLException {
		List<Product> products = new ArrayList<Product>();
		try {
			getProductIdStatement = connection.prepareStatement(getProductIdQuery);
			ResultSet rs = getProductIdStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("id"));
				
				products.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return products;
	}
	
	public List<Product> getAllBySimpleSearch(String keyword) throws SQLException {
		List<Product> products = new ArrayList<Product>();
		try {
			if (null != keyword && !"".equals(keyword)) {
				String query = new StringBuilder().append(searchQuery).append(" and")
						.append(" (lower(product_code)=lower('%s')").append(" or lower(product_name) like lower('%s')")
						.append(" or lower(product_category_id) like lower('%s')")
						.append(" or lower(product_status) like lower('%s')) order by a.id asc ").toString();
				getAllProductStatement = connection.prepareStatement(String.format(query, keyword, keyword, keyword, keyword));
			} else {
				getAllProductStatement = connection.prepareStatement(selectAllQuery);
			}

			ResultSet rs = getAllProductStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("id"));
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));
				product.setProductCat(rs.getInt("product_category_id"));
				product.setProductStat(rs.getString("product_status"));
				product.setProductUom(rs.getInt("product_uom_id"));
				product.setIsMaintain(rs.getInt("is_maintain_stock"));
				product.setImagePath(rs.getString("image_path"));
				product.setBrand(rs.getString("brand"));
				product.setBarcode(rs.getString("barcode"));
				product.setDescription(rs.getString("description"));
				product.setWoodType(rs.getInt("wood_type_id"));
				product.setGrade(rs.getInt("grade_id"));
				product.setThickness(rs.getInt("thickness_id"));
				product.setCondition(rs.getInt("condition_id"));
				product.setIsSerial(rs.getInt("is_has_serial"));
				product.setIsAsset(rs.getInt("is_fixed_asset"));
				product.setWarranty(rs.getInt("warranty"));
				product.setNetto(rs.getDouble("netto"));
				product.setNettoUom(rs.getInt("netto_uom_id"));
				product.setIsPurchase(rs.getInt("is_purchase_item"));
				product.setMinor(rs.getInt("minor"));
				product.setMinorUom(rs.getInt("minor_uom_id"));
				product.setLeadTime(rs.getInt("lead_time"));
				product.setBuyCost(rs.getInt("buy_cost_center_id"));
				product.setExpense(rs.getInt("expense_acc_id"));
				product.setMainSuppCode(rs.getString("main_supp_code"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setIsSales(rs.getInt("is_sales_item"));
				product.setIsService(rs.getInt("is_service_item"));
				product.setSellCost(rs.getInt("sell_cost_center_id"));
				product.setIncome(rs.getInt("income_acc_id"));
				product.setMaxDisc(rs.getDouble("max_disc"));
				product.setInputDate(rs.getDate("a.input_date"));
				product.setInputBy(rs.getString("a.input_by"));
				product.setEditDate(rs.getDate("a.edit_date"));
				product.setEditBy(rs.getString("a.edited_by"));
				product.setWoodTypeName(rs.getString("b.wood_type"));
				product.setGradeName(rs.getString("c.grade"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return products;
	}
	
	public List<ProductCategory> getAllProductCategory() throws SQLException {
		List<ProductCategory> categories = new ArrayList<ProductCategory>();

		try {
			getAllProductCategory = connection.prepareStatement(productCatQuery);
			ResultSet rs = getAllProductCategory.executeQuery();
			while (rs.next()) {
				ProductCategory productCat = new ProductCategory();
				productCat.setId(rs.getString("id"));
				productCat.setProductCategory(rs.getString("product_category"));
				categories.add(productCat);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return categories;
	}
	
	public void update(Product product) throws SQLException {
		try {
			updateProductStatement = connection.prepareStatement(updateQuery);
			java.sql.Date sqlEditDate = new java.sql.Date(product.getEditDate().getTime());

			updateProductStatement.setString(1, product.getProductName());
			updateProductStatement.setString(2, product.getProductStat());
			updateProductStatement.setString(3, product.getBrand());
			updateProductStatement.setString(4, product.getBarcode());
			updateProductStatement.setString(5, product.getDescription());
			updateProductStatement.setInt(6, product.getIsMaintain());
			updateProductStatement.setInt(7, product.getIsSerial());
			updateProductStatement.setInt(8, product.getIsAsset());
			updateProductStatement.setInt(9, product.getIsPurchase());
			updateProductStatement.setInt(10, product.getIsSales());
			updateProductStatement.setInt(11, product.getIsService());
			updateProductStatement.setInt(12, product.getWoodType());
			updateProductStatement.setInt(13, product.getGrade());
			updateProductStatement.setInt(14, product.getThickness());
			updateProductStatement.setInt(15, product.getCondition());
			updateProductStatement.setInt(16, product.getWarranty());
			updateProductStatement.setDouble(17, product.getNetto());
			updateProductStatement.setInt(18, product.getNettoUom());
			updateProductStatement.setInt(19, product.getMinor());
			updateProductStatement.setInt(20, product.getMinorUom());
			updateProductStatement.setInt(21, product.getLeadTime());
			updateProductStatement.setInt(22, product.getBuyCost());
			updateProductStatement.setInt(23, product.getExpense());
			updateProductStatement.setString(24, product.getMainSuppCode());
			updateProductStatement.setString(25, product.getManufacturer());
			updateProductStatement.setInt(26, product.getSellCost());
			updateProductStatement.setInt(27, product.getIncome());
			updateProductStatement.setDouble(28, product.getMaxDisc());
			updateProductStatement.setDate(29, sqlEditDate);
			updateProductStatement.setString(30, product.getEditBy());
			updateProductStatement.setString(31, product.getProductCode());
			updateProductStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	public Product getById(String product_code) throws SQLException {
		Product p = null;
		String query = new StringBuilder().append(getAllNoOrder).append(" and product_code=? order by a.id asc ").toString();
		try {
			getAllProductStatement = connection.prepareStatement(query);
			getAllProductStatement.setString(1, product_code);
			ResultSet rs = getAllProductStatement.executeQuery();

			while (rs.next()) {
				p = new Product();
				p.setProductId(rs.getInt("id"));
				p.setProductCode(rs.getString("product_code"));
				p.setProductName(rs.getString("product_name"));
				p.setProductCat(rs.getInt("product_category_id"));
				p.setProductStat(rs.getString("product_status"));
				p.setProductUom(rs.getInt("product_uom_id"));
				p.setIsMaintain(rs.getInt("is_maintain_stock"));
				p.setImagePath(rs.getString("image_path"));
				p.setBrand(rs.getString("brand"));
				p.setBarcode(rs.getString("barcode"));
				p.setDescription(rs.getString("description"));
				p.setWoodType(rs.getInt("wood_type_id"));
				p.setGrade(rs.getInt("grade_id"));
				p.setThickness(rs.getInt("thickness_id"));
				p.setCondition(rs.getInt("condition_id"));
				p.setIsSerial(rs.getInt("is_has_serial"));
				p.setIsAsset(rs.getInt("is_fixed_asset"));
				p.setWarranty(rs.getInt("warranty"));
				p.setNetto(rs.getDouble("netto"));
				p.setNettoUom(rs.getInt("netto_uom_id"));
				p.setIsPurchase(rs.getInt("is_purchase_item"));
				p.setMinor(rs.getInt("minor"));
				p.setMinorUom(rs.getInt("minor_uom_id"));
				p.setLeadTime(rs.getInt("lead_time"));
				p.setBuyCost(rs.getInt("buy_cost_center_id"));
				p.setExpense(rs.getInt("expense_acc_id"));
				p.setMainSuppCode(rs.getString("main_supp_code"));
				p.setManufacturer(rs.getString("manufacturer"));
				p.setIsSales(rs.getInt("is_sales_item"));
				p.setIsService(rs.getInt("is_service_item"));
				p.setSellCost(rs.getInt("sell_cost_center_id"));
				p.setIncome(rs.getInt("income_acc_id"));
				p.setMaxDisc(rs.getDouble("max_disc"));
				p.setInputDate(rs.getDate("a.input_date"));
				p.setInputBy(rs.getString("a.input_by"));
				p.setEditDate(rs.getDate("a.edit_date"));
				p.setEditBy(rs.getString("a.edited_by"));
				p.setWoodTypeName(rs.getString("b.wood_type"));
				p.setGradeName(rs.getString("c.grade"));
				p.setProductCatName(rs.getString("d.product_category"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return p;
	}
}
