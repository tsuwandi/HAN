package module.purchaseprodsupp.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.product.model.Product;
import module.purchaseprodsupp.model.PPSProduct;
import module.util.DateUtil;

public class PPSProductDAO {
	private Connection connection;
	private PreparedStatement getAllByPPSCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByPPSCodeQuery = new StringBuilder()
			.append("select pp.id, pp.pps_code, pp.product_code, pp.qty, ")
			.append("pp.unit_price, pp.sub_total, p.product_name, p.id as product_id, p.production_type_id, pt.production_type from pps_product pp ")
			.append("inner join product p on pp.product_code = p.product_code ")
			.append("inner join production_type pt on pt.id = p.production_type_id ")
			.append("where pp.pps_code = ? and pp.deleted_date is null and p.deleted_date is null and pt.deleted_date is null ").toString();

	private String insertQuery = new StringBuilder()
			.append("insert into pps_product (pps_code, product_code, qty, unit_price, sub_total, ")
			.append("input_date, input_by) values (?,?,?,?,?,?,?)").toString();

	private String updateQuery = "update pps_product set product_code=?, qty=?, unit_price=?, sub_total=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update pps_product set deleted_date=?, deleted_by=? ";
	
	public PPSProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	
	public List<PPSProduct> getAllByPPSCode(String ppsCode) throws SQLException {
		List<PPSProduct> ppsProducts = new ArrayList<PPSProduct>();

		try {
			getAllByPPSCodeStatement = connection.prepareStatement(getAllByPPSCodeQuery);
			getAllByPPSCodeStatement.setString(1, ppsCode);
			
			ResultSet rs = getAllByPPSCodeStatement.executeQuery();
			while (rs.next()) {
				PPSProduct ppsProduct = new PPSProduct();
				ppsProduct.setId(rs.getInt("id"));
				ppsProduct.setPpsCode(rs.getString("pps_code"));
				ppsProduct.setProductCode(rs.getString("product_code"));
				ppsProduct.setQty(rs.getBigDecimal("qty"));
				
				BigDecimal qty = ppsProduct.getQty().setScale(2, BigDecimal.ROUND_DOWN);
				
				ppsProduct.setUnitPrice(rs.getBigDecimal("unit_price"));
				BigDecimal unitPrice =  ppsProduct.getUnitPrice().setScale(2, BigDecimal.ROUND_DOWN);
				ppsProduct.setSubTotal(rs.getBigDecimal("sub_total"));
				BigDecimal subTotal =  ppsProduct.getSubTotal().setScale(2, BigDecimal.ROUND_DOWN);
				
				ppsProduct.setQty(qty);
				ppsProduct.setUnitPrice(unitPrice);
				ppsProduct.setSubTotal(subTotal);
				Product	product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));
				product.setProductionType(rs.getString("production_type"));
				product.setProductionTypeId(rs.getInt("production_type_id"));
				
				ppsProduct.setProduct(product);
				ppsProducts.add(ppsProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return ppsProducts;
	}
	
	public void save(PPSProduct ppsProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, ppsProduct.getPpsCode());
			insertStatement.setString(2, ppsProduct.getProductCode());
			insertStatement.setBigDecimal(3, ppsProduct.getQty());
			insertStatement.setBigDecimal(4, ppsProduct.getUnitPrice());
			insertStatement.setBigDecimal(5, ppsProduct.getSubTotal());
			insertStatement.setDate(6, DateUtil.getCurrentDate());
			insertStatement.setString(7, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(PPSProduct ppsProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, ppsProduct.getProductCode());
			updateStatement.setBigDecimal(2, ppsProduct.getQty());
			updateStatement.setBigDecimal(3, ppsProduct.getUnitPrice());
			updateStatement.setBigDecimal(4, ppsProduct.getSubTotal());
			updateStatement.setDate(5, DateUtil.getCurrentDate());
			updateStatement.setString(6, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setInt(7, ppsProduct.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteAll(String ppsCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where pps_code=? ").toString();

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
