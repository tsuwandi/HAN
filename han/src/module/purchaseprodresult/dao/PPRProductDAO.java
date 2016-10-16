package module.purchaseprodresult.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.product.model.Product;
import module.purchaseprodresult.model.PPRProduct;
import module.util.DateUtil;

public class PPRProductDAO {
	private Connection connection;
	private PreparedStatement getAllByPPRCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByPPRCodeQuery = new StringBuilder()
			.append("select pp.id, pp.ppr_code, pp.product_code, pp.qty, ")
			.append("pp.unit_price, pp.sub_total, p.product_name, p.id as product_id from ppr_product pp ")
			.append("inner join product p on pp.product_code = p.product_code ")
			.append("where pp.ppr_code = ? and pp.deleted_date is null and p.deleted_date is null ").toString();

	private String insertQuery = new StringBuilder()
			.append("insert into ppr_product (ppr_code, product_code, qty, unit_price, sub_total, ")
			.append("input_date, input_by) values (?,?,?,?,?,?,?)").toString();

	private String updateQuery = "update ppr_product set product_code=?, qty=?, unit_price=?, sub_total=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update ppr_product set deleted_date=?, deleted_by=? ";
	
	public PPRProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	
	public List<PPRProduct> getAllByPPRCode(String pprCode) throws SQLException {
		List<PPRProduct> pprProducts = new ArrayList<PPRProduct>();

		try {
			getAllByPPRCodeStatement = connection.prepareStatement(getAllByPPRCodeQuery);
			getAllByPPRCodeStatement.setString(1, pprCode);
			
			ResultSet rs = getAllByPPRCodeStatement.executeQuery();
			while (rs.next()) {
				PPRProduct pprProduct = new PPRProduct();
				pprProduct.setId(rs.getInt("id"));
				pprProduct.setPprCode(rs.getString("ppr_code"));
				pprProduct.setProductCode(rs.getString("product_code"));
				pprProduct.setQty(rs.getInt("qty"));
				pprProduct.setUnitPrice(rs.getDouble("unit_price"));
				pprProduct.setSubTotal(rs.getDouble("sub_total"));
				
				Product	product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));
				
				pprProduct.setProduct(product);
				pprProducts.add(pprProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return pprProducts;
	}
	
	public void save(PPRProduct pprProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, pprProduct.getPprCode());
			insertStatement.setString(2, pprProduct.getProductCode());
			insertStatement.setInt(3, pprProduct.getQty());
			insertStatement.setDouble(4, pprProduct.getUnitPrice());
			insertStatement.setDouble(5, pprProduct.getSubTotal());
			insertStatement.setDate(6, DateUtil.getCurrentDate());
			insertStatement.setString(7, "timotius");
			System.out.println(insertStatement);
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(PPRProduct pprProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, pprProduct.getProductCode());
			updateStatement.setInt(2, pprProduct.getQty());
			updateStatement.setDouble(3, pprProduct.getUnitPrice());
			updateStatement.setDouble(4, pprProduct.getSubTotal());
			updateStatement.setDate(5, DateUtil.getCurrentDate());
			updateStatement.setString(6, "timotius");
			updateStatement.setInt(7, pprProduct.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteAll(String pprCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where ppr_code=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, pprCode);
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
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
}
