package module.receiveprodresult.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.product.model.Product;
import module.receiveprodresult.model.RPRProduct;
import module.util.DateUtil;

public class RPRProductDAO {
	private Connection connection;
	private PreparedStatement getAllByRPRCodeStatement;
	private PreparedStatement getAllByPPRCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByRPRCodeQuery = new StringBuilder()
			.append("select pp.id, pp.rpr_code, pp.product_code, pp.qty, ")
			.append("p.product_name, p.id as product_id, p.production_type_id, pt.production_type from rpr_product pp ")
			.append("inner join product p on pp.product_code = p.product_code ")
			.append("inner join production_type pt on pt.id = p.production_type_id ")
			.append("where pp.rpr_code = ? and pp.deleted_date is null and p.deleted_date is null and pt.deleted_date is null ")
			.toString();

	private String getAllByPPRCodeQuery = new StringBuilder()
			.append("select pp.id, pp.ppr_code, pp.product_code, pp.qty, ")
			.append("p.product_name, p.id as product_id, p.production_type_id, pt.production_type from ppr_product pp ")
			.append("inner join product p on pp.product_code = p.product_code ")
			.append("inner join production_type pt on pt.id = p.production_type_id ")
			.append("where pp.ppr_code = ? and pp.deleted_date is null and p.deleted_date is null and pt.deleted_date is null ")
			.toString();

	private String insertQuery = new StringBuilder()
			.append("insert into rpr_product (rpr_code, product_code, qty, ")
			.append("input_date, input_by) values (?,?,?,?,?)").toString();

	private String updateQuery = "update rpr_product set product_code=?, qty=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update rpr_product set deleted_date=?, deleted_by=? ";

	public RPRProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<RPRProduct> getAllByRPRCode(String rprCode) throws SQLException {
		List<RPRProduct> rprProducts = new ArrayList<RPRProduct>();

		try {
			getAllByRPRCodeStatement = connection
					.prepareStatement(getAllByRPRCodeQuery);
			getAllByRPRCodeStatement.setString(1, rprCode);

			ResultSet rs = getAllByRPRCodeStatement.executeQuery();
			while (rs.next()) {
				RPRProduct rprProduct = new RPRProduct();
				rprProduct.setId(rs.getInt("id"));
				rprProduct.setRprCode(rs.getString("rpr_code"));
				rprProduct.setProductCode(rs.getString("product_code"));
				rprProduct.setQty(rs.getBigDecimal("qty"));
				BigDecimal qty = rprProduct.getQty().setScale(2, BigDecimal.ROUND_DOWN);
				rprProduct.setQty(qty);
				Product product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));
				product.setProductionType(rs.getString("production_type"));
				product.setProductionTypeId(rs.getInt("production_type_id"));

				rprProduct.setProduct(product);
				rprProducts.add(rprProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rprProducts;
	}
	
	public List<RPRProduct> getAllByPPRCode(String pprCode) throws SQLException {
		List<RPRProduct> rprProducts = new ArrayList<RPRProduct>();

		try {
			getAllByPPRCodeStatement = connection
					.prepareStatement(getAllByPPRCodeQuery);
			getAllByPPRCodeStatement.setString(1, pprCode);

			ResultSet rs = getAllByPPRCodeStatement.executeQuery();
			while (rs.next()) {
				RPRProduct rprProduct = new RPRProduct();
				rprProduct.setId(rs.getInt("id"));
				rprProduct.setPprCode(rs.getString("ppr_code"));
				rprProduct.setProductCode(rs.getString("product_code"));
				rprProduct.setQty(rs.getBigDecimal("qty"));
				BigDecimal qty = rprProduct.getQty().setScale(2, BigDecimal.ROUND_DOWN);
				rprProduct.setQty(qty);
				Product product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));
				product.setProductionType(rs.getString("production_type"));
				product.setProductionTypeId(rs.getInt("production_type_id"));

				rprProduct.setProduct(product);
				rprProducts.add(rprProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rprProducts;
	}


	public void save(RPRProduct rprProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, rprProduct.getRprCode());
			insertStatement.setString(2, rprProduct.getProductCode());
			insertStatement.setBigDecimal(3, rprProduct.getQty());
			insertStatement.setDate(4, DateUtil.getCurrentDate());
			insertStatement.setString(5, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(RPRProduct rprProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, rprProduct.getProductCode());
			updateStatement.setBigDecimal(2, rprProduct.getQty());
			updateStatement.setDate(3, DateUtil.getCurrentDate());
			updateStatement.setString(4, "timotius");
			updateStatement.setInt(5, rprProduct.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(String rprCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery)
					.append("where rpr_code=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, rprCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery)
					.append("where id=? ").toString();

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
