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

public class ProductDAO {
	private DataSource dataSource;
	private Connection connection;
	private PreparedStatement insertEmployeeStatement;
	private PreparedStatement updateEmployeeStatement;
	private PreparedStatement deleteEmployeeStatement;
	private PreparedStatement getAllEmployeeStatement;
	private PreparedStatement searchEmployee;
	private PreparedStatement getAllGender;
	private PreparedStatement getAllPosition;
	private PreparedStatement getAllDivision;
	private PreparedStatement getAllDepartment;
	private PreparedStatement getAllEmployeeStatus;
	private PreparedStatement getAllEmployeeType;
	private PreparedStatement getAllMarital;

	private String selectAllQuery = "select a.id, product_code, product_name, product_category_id, "
			+ "product_status, product_uom_id, is_maintain_stock, image_path, brand, barcode, description, "
			+ "wood_type_id, grade_id, thickness_id, condition_id, is_has_serial, is_fixed_asset, warranty, "
			+ "netto, netto_uom_id, is_purchase_item, minor, minor_uom_id, lead_time, buy_cost_center_id, "
			+ "expense_acc_id, main_supp_code, manufacturer, is_sales_item, is_service_item, sell_cost_center_id, "
			+ "income_acc_id, max_disc, a.input_date, a.input_by, a.edit_date, a.edited_by, a.deleted_date, a.deleted_by, "
			+ "b.wood_type, c.grade "
			+ "from product a join wood_type b on a.wood_type_id = b.id "
			+ "join grade c on a.grade_id = c.id "
			+ "where 1=1 order by a.id asc";
	
	public ProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<Product> getAll() throws SQLException {
		List<Product> products = new ArrayList<Product>();
		try {
			getAllEmployeeStatement = connection.prepareStatement(selectAllQuery);
			ResultSet rs = getAllEmployeeStatement.executeQuery();
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
				product.setDeletedDate(rs.getDate("a.deleted_date"));
				product.setDeletedBy(rs.getString("a.deleted_by"));
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
}
