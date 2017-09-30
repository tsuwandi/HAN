package module.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.product.model.Condition;
import module.product.model.Product;
import module.util.DateUtil;

public class ProductDAO {
	private Connection connection;
	private PreparedStatement getAllProductStatement;
	private PreparedStatement getProductIdStatement;
	
	private PreparedStatement getAllCondition;
	private PreparedStatement updateProductStatement;
	private PreparedStatement deleteProductStatement;
	private PreparedStatement insertProductStatement;

	private String selectAllQuery = "select a.id, product_code, product_name, a.product_category_id, "
			+ "product_status, product_uom_id, is_maintain_stock, image_path, brand, barcode, a.description, "
			+ "wood_type_id, grade_id, thickness, length, width, condition_id, minqty, is_has_serial, is_fixed_asset, warranty, "
			+ "netto, netto_uom_id, is_purchase_item, minor, minor_uom_id, lead_time, buy_cost_center_id, "
			+ "expense_acc_id, main_supp_code, manufacturer, is_sales_item, is_service_item, sell_cost_center_id, "
			+ "income_acc_id, max_disc, a.input_date, a.input_by, a.edit_date, a.edited_by, "
			+ "b.wood_type, c.grade, d.product_category, a.production_quality_id, a.production_type_id,"
			+ "q.production_quality, t.production_type " 
			+ "from product a left join wood_type b on a.wood_type_id = b.id "
			+ "left join grade c on a.grade_id = c.id " 
			+ "left join production_quality q on a.production_quality_id = q.id " 
			+ "left join production_type t on a.production_type_id = t.id " 
			+ "inner join product_category d on a.product_category_id = d.id "
			+ "inner join uom e on a.product_uom_id = e.id "
			+ "where 1=1 and a.deleted_date is null and a.deleted_by is null ";

	private String getAllNoOrder = "select a.id, product_code, product_name, a.product_category_id, "
			+ "product_status, product_uom_id, is_maintain_stock, image_path, brand, barcode, a.description, "
			+ "wood_type_id, grade_id, thickness, length, width, condition_id, minqty, is_has_serial, is_fixed_asset, warranty, "
			+ "netto, netto_uom_id, is_purchase_item, minor, minor_uom_id, lead_time, buy_cost_center_id, "
			+ "expense_acc_id, main_supp_code, manufacturer, is_sales_item, is_service_item, sell_cost_center_id, "
			+ "income_acc_id, max_disc, a.input_date, a.input_by, a.edit_date, a.edited_by, "
			+ "b.wood_type, c.grade, d.product_category, production_quality_id, production_type_id, e.uom, "
			+ "q.production_quality, t.production_type " 
			+ "from product a left join wood_type b on a.wood_type_id = b.id "
			+ "left join grade c on a.grade_id = c.id " 
			+ "left join production_quality q on a.production_quality_id = q.id " 
			+ "left join production_type t on a.production_type_id = t.id " 
			+ "inner join product_category d on a.product_category_id = d.id "
			+ "inner join uom e on a.product_uom_id = e.id "
			+ "where 1=1 ";

	private String conditionQuery = "select * from `condition` order by id asc";

	private String insertProductQuery = "insert into product(product_code, product_name, product_category_id, "
			+ "product_status, product_uom_id, is_maintain_stock, "
			+ "wood_type_id, grade_id, thickness, length, width, condition_id, minqty, " + "input_date, input_by, production_type_id, production_quality_id) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

	private String updateQuery = "update product set product_name=?, product_status=?, product_category_id=?, "
			+ "product_uom_id = ?, is_maintain_stock=?, "
			+ "wood_type_id=?, grade_id=?, thickness=?, length=?, width=?, condition_id=?, minqty=?, "
			+ "edit_date=?, edited_by=?, production_type_id=?, production_quality_id=? where id=? ";

	private String getProductIdQuery = "select id, product_name from product";

	private String deleteQuery = "update product set deleted_date=?, deleted_by=? where product_code=?";

	public ProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public void delete(String productCode) throws SQLException {
		try {
			deleteProductStatement = connection.prepareStatement(deleteQuery);
			deleteProductStatement.setDate(1, DateUtil.getCurrentDate());
			deleteProductStatement.setString(2, "Timotius");
			deleteProductStatement.setString(3, productCode);
			deleteProductStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void save(Product product) throws SQLException {
		try {
			insertProductStatement = connection.prepareStatement(insertProductQuery);
			insertProductStatement.setString(1, product.getProductCode());
			insertProductStatement.setString(2, product.getProductName());
			insertProductStatement.setInt(3, product.getProductCat());
			insertProductStatement.setString(4, product.getProductStat());
			insertProductStatement.setInt(5, product.getProductUom());
			insertProductStatement.setInt(6, product.getIsMaintain());
			
			if(product.getWoodType() == 0)
				insertProductStatement.setNull(7, java.sql.Types.INTEGER);
			else
				insertProductStatement.setInt(7, product.getWoodType());
			
			if(product.getGrade() == 0)
				insertProductStatement.setNull(8, java.sql.Types.INTEGER);
			else
				insertProductStatement.setInt(8, product.getGrade());
			
			if(product.getThickness() == null)
				insertProductStatement.setNull(9, java.sql.Types.DOUBLE);
			else
				insertProductStatement.setDouble(9, product.getThickness());
			
			if(product.getLength() == null)
				insertProductStatement.setNull(10, java.sql.Types.DOUBLE);
			else
				insertProductStatement.setDouble(10, product.getLength());
			
			if(product.getWidth() == null)
				insertProductStatement.setNull(11, java.sql.Types.DOUBLE);
			else
				insertProductStatement.setDouble(11, product.getWidth());
			
			if(product.getCondition() == 0)
				insertProductStatement.setNull(12, java.sql.Types.INTEGER);
			else
				insertProductStatement.setInt(12, product.getCondition());
			
			insertProductStatement.setInt(13, product.getMinQty());
			insertProductStatement.setDate(14, DateUtil.getCurrentDate());
			insertProductStatement.setString(15, "Timotius");
			
			if(product.getProductionTypeId() == 0)
				insertProductStatement.setNull(16, java.sql.Types.INTEGER);
			else
				insertProductStatement.setInt(16, product.getProductionTypeId());
			
			if(product.getProductionQualityId() == 0)
				insertProductStatement.setNull(17, java.sql.Types.INTEGER);
			else
				insertProductStatement.setInt(17, product.getProductionQualityId());
			
			insertProductStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public List<Product> getAll() throws SQLException {
		List<Product> products = new ArrayList<Product>();
		try {
			String query = new StringBuilder().append(selectAllQuery).append(" order by a.id asc").toString();
			getAllProductStatement = connection.prepareStatement(query);
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
				product.setThickness(rs.getDouble("thickness"));
				product.setLength(rs.getDouble("length"));
				product.setWidth(rs.getDouble("width"));
				product.setCondition(rs.getInt("condition_id"));
				product.setMinQty(rs.getInt("minqty"));
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
				product.setProductCatName(rs.getString("d.product_category"));
				product.setProductionTypeId(rs.getInt("production_type_id"));
				product.setProductionQualityId(rs.getInt("production_quality_id"));
				product.setProductionType(rs.getString("production_type"));
				product.setProductionQuality(rs.getString("production_quality"));
				products.add(product);
			}

		} catch (SQLException ex) {
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
				product.setProductName(rs.getString("product_name"));
				products.add(product);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return products;
	}

	public List<Product> getAllBySimpleSearch(String value) throws SQLException {
		List<Product> products = new ArrayList<Product>();
		try {

			StringBuilder query = new StringBuilder().append(selectAllQuery);
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append('%').append(value).append('%').toString();
				query.append(" and (lower(product_code) like lower('%s')");
				query.append(" or lower(product_name) like lower('%s')");
				query.append(" or lower(product_category) like lower('%s')");
				query.append(" or lower(product_status) like lower('%s')) order by a.id asc ");
				getAllProductStatement = connection
						.prepareStatement(String.format(query.toString(), keyword, keyword, keyword, keyword));
			} else {
				query.append(" order by a.id asc");
				getAllProductStatement = connection.prepareStatement(query.toString());
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
				product.setThickness(rs.getDouble("thickness"));
				product.setLength(rs.getDouble("length"));
				product.setWidth(rs.getDouble("width"));
				product.setCondition(rs.getInt("condition_id"));
				product.setMinQty(rs.getInt("minqty"));
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
				product.setProductCatName(rs.getString("d.product_category"));
				product.setProductionTypeId(rs.getInt("production_type_id"));
				product.setProductionQualityId(rs.getInt("production_quality_id"));
				product.setProductionType(rs.getString("production_type"));
				product.setProductionQuality(rs.getString("production_quality"));
				products.add(product);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return products;
	}

	public List<Condition> getAllCondition() throws SQLException {
		List<Condition> conditions = new ArrayList<Condition>();

		try {
			getAllCondition = connection.prepareStatement(conditionQuery);
			ResultSet rs = getAllCondition.executeQuery();
			while (rs.next()) {
				Condition condition = new Condition();
				condition.setId(rs.getInt("id"));
				condition.setCondition(rs.getString("condition"));
				conditions.add(condition);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return conditions;
	}

	public void update(Product product) throws SQLException {
		try {
			updateProductStatement = connection.prepareStatement(updateQuery);
			
			updateProductStatement.setString(1, product.getProductName());
			updateProductStatement.setString(2, product.getProductStat());
			updateProductStatement.setInt(3, product.getProductCat());
			updateProductStatement.setInt(4, product.getProductUom());
			updateProductStatement.setInt(5, product.getIsMaintain());
			
			if(product.getWoodType() == 0)
				updateProductStatement.setNull(6, java.sql.Types.INTEGER);
			else
				updateProductStatement.setInt(6, product.getWoodType());
			
			if(product.getGrade() == 0)
				updateProductStatement.setNull(7, java.sql.Types.INTEGER);
			else
				updateProductStatement.setInt(7, product.getGrade());
			
			if(product.getThickness() == null)
				updateProductStatement.setNull(8, java.sql.Types.DOUBLE);
			else
				updateProductStatement.setDouble(8, product.getThickness());
			
			if(product.getLength() == null)
				updateProductStatement.setNull(9, java.sql.Types.DOUBLE);
			else
				updateProductStatement.setDouble(9, product.getLength());
			
			if(product.getWidth() == null)
				updateProductStatement.setNull(10, java.sql.Types.DOUBLE);
			else
				updateProductStatement.setDouble(10, product.getWidth());
			
			if(product.getCondition() == 0)
				updateProductStatement.setNull(11, java.sql.Types.INTEGER);
			else
				updateProductStatement.setInt(11, product.getCondition());
			
			updateProductStatement.setInt(12, product.getMinQty());
			updateProductStatement.setDate(13, DateUtil.getCurrentDate());
			updateProductStatement.setString(14, "Timotius");
			
			if(product.getProductionTypeId() == 0)
				updateProductStatement.setNull(15, java.sql.Types.INTEGER);
			else
				updateProductStatement.setInt(15, product.getProductionTypeId());
			
			if(product.getProductionQualityId() == 0)
				updateProductStatement.setNull(16, java.sql.Types.INTEGER);
			else
				updateProductStatement.setInt(16, product.getProductionQualityId());
			
			updateProductStatement.setInt(17, product.getProductId());
			updateProductStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public Product getById(String product_code) throws SQLException {
		Product p = null;
		String query = new StringBuilder().append(getAllNoOrder).append(" and product_code=? order by a.id asc ")
				.toString();
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
				p.setThickness(rs.getDouble("thickness"));
				p.setLength(rs.getDouble("length"));
				p.setWidth(rs.getDouble("width"));
				p.setCondition(rs.getInt("condition_id"));
				p.setMinQty(rs.getInt("minqty"));
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
				p.setProductionTypeId(rs.getInt("production_type_id"));
				p.setProductionQualityId(rs.getInt("production_quality_id"));
				p.setProductionType(rs.getString("production_type"));
				p.setProductionQuality(rs.getString("production_quality"));
				p.setUnitName(rs.getString("uom"));
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return p;
	}
	
	private PreparedStatement isProductCodeExistsStatement;
	private String isProductCodeExistsQuery = "select count(*) as is_exists from product where product_code = ? ";

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
	
	private PreparedStatement isProductNameExistsStatement;
	private String isProductNameExistsQuery = "select count(*) as is_exists from product where product_name = ? ";

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
	
	private PreparedStatement isProductExistsStatement;
	private String isProductExistsQuery = "select count(*) as is_exists, product_code from product "
			+ "where product_category_id = ? "
			+ "and grade_id = ? and wood_type_id = ? and deleted_date is null ";

	public Product isProductExists(Boolean isEdit, Product pProduct) throws SQLException {
		Product product = null;
		try {
			StringBuilder sb = new StringBuilder(isProductExistsQuery);
			
			if(pProduct.getThickness() == null)
				sb.append(" and thickness is null ");
			else
				sb.append(" and thickness = " + pProduct.getThickness());
			
			if(pProduct.getLength() == null)
				sb.append(" and length is null ");
			else
				sb.append(" and length = " + pProduct.getLength());
			
			if(pProduct.getWidth() == null)
				sb.append(" and width is null ");
			else
				sb.append(" and width = " + pProduct.getWidth());
			
			if(isEdit == Boolean.TRUE)
				sb.append(" and id != " + pProduct.getProductId());
			
			isProductExistsStatement = connection.prepareStatement(sb.toString());
			isProductExistsStatement.setInt(1, pProduct.getProductCat());
			isProductExistsStatement.setInt(2, pProduct.getGrade());
			isProductExistsStatement.setInt(3, pProduct.getWoodType());
			
//			if(pProduct.getThickness() != null)
//				isProductExistsStatement.setDouble(4, pProduct.getThickness());
//			
//			if(pProduct.getLength() != null)
//				isProductExistsStatement.setDouble(5, pProduct.getLength());
//			
//			if(pProduct.getWidth() != null)
//				isProductExistsStatement.setDouble(6, pProduct.getWidth());
			
//			if(isEdit == Boolean.TRUE)
//				isProductExistsStatement.setInt(7, pProduct.getProductId());
			
			ResultSet rs = isProductExistsStatement.executeQuery();

			while (rs.next()) {
				product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setIsExists(rs.getInt("is_exists"));
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return product;
	}
	
	private PreparedStatement getAllByProductionTypeIdStatement;
	private String getAllByProductionTypeIdQuery = "select product_code, product_name from product where production_type_id = ? and deleted_date is null";

	public List<Product> getAllByProductionTypeId(int productionTypeId) throws SQLException {
		List<Product> products = new ArrayList<Product>();

		try {
			getAllByProductionTypeIdStatement = connection.prepareStatement(getAllByProductionTypeIdQuery);
			getAllByProductionTypeIdStatement.setInt(1, productionTypeId);
			
			ResultSet rs = getAllByProductionTypeIdStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));

				products.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return products;
	}
	
	private PreparedStatement getOrdinalOfCodeNumberStatement;
	
	private String getOrdinalOfCodeNumberQuery = "SELECT CONVERT(SUBSTRING_INDEX(p.product_code, '-', -1),UNSIGNED INTEGER) AS ordinal FROM product p "
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
}
