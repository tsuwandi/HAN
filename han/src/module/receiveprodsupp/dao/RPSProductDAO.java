package module.receiveprodsupp.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.productsupportinggood.model.ProductSupp;
import module.receiveprodsupp.model.RPSProduct;
import module.sn.productcategory.model.ProductCategory;
import module.util.DateUtil;

public class RPSProductDAO {
	private Connection connection;
	private PreparedStatement getAllByPPSCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByRPSCodeQuery = new StringBuilder()
			.append("select pp.id, pp.rps_code, pp.product_code, pp.qty, ")
			.append("pp.unit_price, pp.sub_total, p.product_name, p.id as product_id, p.product_category_id, pt.product_category from rps_product pp ")
			.append("inner join product_supp p on pp.product_code = p.product_code ")
			.append("inner join product_category pt on pt.id = p.product_category_id ")
			.append("where pp.rps_code = ? and pp.deleted_date is null and p.deleted_date is null and pt.delete_date is null ").toString();

	private String insertQuery = new StringBuilder()
			.append("insert into rps_product (rps_code, product_code, qty, unit_price, sub_total, ")
			.append("input_date, input_by) values (?,?,?,?,?,?,?)").toString();

	private String updateQuery = "update rps_product set product_code=?, qty=?, unit_price=?, sub_total=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update rps_product set deleted_date=?, deleted_by=? ";
	
	public RPSProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	
	public List<RPSProduct> getAllByRPSCode(String rpsCode) throws SQLException {
		List<RPSProduct> rpsProducts = new ArrayList<RPSProduct>();

		try {
			getAllByPPSCodeStatement = connection.prepareStatement(getAllByRPSCodeQuery);
			getAllByPPSCodeStatement.setString(1, rpsCode);
			ResultSet rs = getAllByPPSCodeStatement.executeQuery();
			while (rs.next()) {
				RPSProduct rpsProduct = new RPSProduct();
				rpsProduct.setId(rs.getInt("id"));
				rpsProduct.setRpsCode(rs.getString("rps_code"));
				rpsProduct.setProductCode(rs.getString("product_code"));
				rpsProduct.setQty(rs.getBigDecimal("qty"));
				
				BigDecimal qty = rpsProduct.getQty().setScale(2, BigDecimal.ROUND_DOWN);
				
				rpsProduct.setUnitPrice(rs.getBigDecimal("unit_price"));
				BigDecimal unitPrice =  rpsProduct.getUnitPrice().setScale(2, BigDecimal.ROUND_DOWN);
				rpsProduct.setSubTotal(rs.getBigDecimal("sub_total"));
				BigDecimal subTotal =  rpsProduct.getSubTotal().setScale(2, BigDecimal.ROUND_DOWN);
				
				rpsProduct.setQty(qty);
				rpsProduct.setUnitPrice(unitPrice);
				rpsProduct.setSubTotal(subTotal);
				ProductSupp	product = new ProductSupp();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));
				ProductCategory productCategory = new ProductCategory();
				productCategory.setProductCategory(rs.getString("product_category"));
				product.setProductCategory(productCategory);
				product.setProductCategoryId(rs.getInt("product_category_id"));
				
				rpsProduct.setProductSupp(product);
				rpsProducts.add(rpsProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rpsProducts;
	}
	
	public void save(RPSProduct rpsProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, rpsProduct.getRpsCode());
			insertStatement.setString(2, rpsProduct.getProductSupp().getProductCode());
			insertStatement.setBigDecimal(3, rpsProduct.getQty());
			insertStatement.setBigDecimal(4, rpsProduct.getUnitPrice());
			insertStatement.setBigDecimal(5, rpsProduct.getSubTotal());
			insertStatement.setDate(6, DateUtil.getCurrentDate());
			insertStatement.setString(7, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(RPSProduct rpsProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, rpsProduct.getProductCode());
			updateStatement.setBigDecimal(2, rpsProduct.getQty());
			updateStatement.setBigDecimal(3, rpsProduct.getUnitPrice());
			updateStatement.setBigDecimal(4, rpsProduct.getSubTotal());
			updateStatement.setDate(5, DateUtil.getCurrentDate());
			updateStatement.setString(6, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setInt(7, rpsProduct.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteAll(String ppsCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where rps_code=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setString(3, ppsCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where id=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
}
