package module.invoicebalken.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.product.model.Product;
import module.util.DateUtil;
import module.invoicebalken.model.InvBalkenProduct;

public class InvBalkenProductDAO {
	private Connection connection;
	private PreparedStatement getAllByPayPrCodeStatement;
	private PreparedStatement getAllByRPRCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByPayPrCodeQuery = new StringBuilder()
			.append("select pp.id, pp.inventory_balken_id, pp.product_code, pp.qty, pp.price, pp.idr_price, pp.subtotal, ")
			.append("p.product_name, p.id as product_id, pc.total as qty_receive from inv_balken_product pp ")
			.append("inner join product p on pp.product_code = p.product_code ")
			.append("inner join invoice_balken pay_pr on pp.inventory_balken_id = pay_pr.id ")
			.append("inner join received rpr on rpr.received_code = pay_pr.received_code ")
			.append("inner join received_detail rpr_p on rpr_p.received_code = rpr.received_code ")
			.append("inner join pallet_card pc on rpr_p.id = pc.received_detail_id and p.product_code = pc.product_code ")
			.append("where pp.inventory_balken_id = ? and pp.deleted_date is null and p.deleted_date is null ")
			.toString();
	
	private String getAllByRprCodeQuery = new StringBuilder()
			.append("select rpr_p.id, null as inventory_balken_id, pc.product_code, pc.total as qty, null as price, null as idr_price, null as subtotal, ")
			.append("p.product_name, p.id as product_id, pc.total as qty_receive ")
			.append("from received rpr ")
			.append("inner join received_detail rpr_p on rpr_p.received_code = rpr.received_code ")
			.append("inner join pallet_card pc on rpr_p.id = pc.received_detail_id ")
			.append("inner join product p on pc.product_code = p.product_code ")
			.append("where rpr.received_code = ? and rpr.deleted_date is null and p.deleted_date is null ")
			.toString();

	private String insertQuery = new StringBuilder()
			.append("insert into inv_balken_product (inventory_balken_id, product_code, qty, price, idr_price, subtotal, ")
			.append("input_date, input_by) values (?,?,?,?,?,?,?,?)").toString();

	private String updateQuery = "update inv_balken_product set product_code=?, qty=?, price=?, idr_price=?, subtotal=?, edited_date=?, edited_by=? where id=?";

	private String deleteQuery = "update inv_balken_product set deleted_date=?, deleted_by=? ";

	public InvBalkenProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<InvBalkenProduct> getAllByPPRCode(int idInvPr) throws SQLException {
		List<InvBalkenProduct> rprProducts = new ArrayList<InvBalkenProduct>();

		try {
			getAllByPayPrCodeStatement = connection
					.prepareStatement(getAllByPayPrCodeQuery);
			getAllByPayPrCodeStatement.setInt(1, idInvPr);

			ResultSet rs = getAllByPayPrCodeStatement.executeQuery();
			while (rs.next()) {
				InvBalkenProduct rprProduct = new InvBalkenProduct();
				rprProduct.setId(rs.getInt("id"));
				rprProduct.setInventoryBalkenId(rs.getInt("inventory_balken_id"));
				rprProduct.setProductCode(rs.getString("product_code"));
				rprProduct.setQty(rs.getBigDecimal("qty"));
				rprProduct.setPrice(rs.getBigDecimal("price"));
				rprProduct.setIdrPrice(rs.getBigDecimal("idr_price"));
				
				if(rprProduct.getQty() != null) {
					BigDecimal qty = rprProduct.getQty().setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setQty(qty);
				}
				if(rprProduct.getPrice() != null) {
					BigDecimal price = rprProduct.getPrice().setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setPrice(price);
				}
				if(rprProduct.getIdrPrice() != null) {
					BigDecimal idrPrice = rprProduct.getIdrPrice().setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setIdrPrice(idrPrice);
				}
			
				rprProduct.setSubtotal(rs.getBigDecimal("subtotal"));
				if(rprProduct.getSubtotal() != null) {
					BigDecimal subtotal = rprProduct.getSubtotal().setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setSubtotal(subtotal);
				} else {
					rprProduct.setSubtotal(new BigDecimal("0.00"));
				}
				
				Product product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));

				rprProduct.setProduct(product);
				
				rprProduct.setQtyReceive(rs.getBigDecimal("qty_receive"));
				BigDecimal qtyReceive = rprProduct.getQtyReceive().setScale(2, BigDecimal.ROUND_DOWN);
				rprProduct.setQtyReceive(qtyReceive);
				
				rprProducts.add(rprProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rprProducts;
	}

	public List<InvBalkenProduct> getAllByRPRCode(String rprCode) throws SQLException {
		List<InvBalkenProduct> rprProducts = new ArrayList<InvBalkenProduct>();

		try {
			getAllByRPRCodeStatement = connection
					.prepareStatement(getAllByRprCodeQuery);
			getAllByRPRCodeStatement.setString(1, rprCode);

			ResultSet rs = getAllByRPRCodeStatement.executeQuery();
			while (rs.next()) {
				InvBalkenProduct rprProduct = new InvBalkenProduct();
				rprProduct.setId(rs.getInt("id"));
				rprProduct.setInventoryBalkenId(rs.getInt("inventory_balken_id"));
				rprProduct.setProductCode(rs.getString("product_code"));
				rprProduct.setQty(rs.getBigDecimal("qty"));
				rprProduct.setPrice(rs.getBigDecimal("price"));
				rprProduct.setIdrPrice(rs.getBigDecimal("idr_price"));
				
				if(rprProduct.getQty() != null) {
					BigDecimal qty = rprProduct.getQty().setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setQty(qty);
				}
				if(rprProduct.getPrice() != null) {
					BigDecimal price = rprProduct.getPrice().setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setPrice(price);
				} else {
					rprProduct.setPrice(new BigDecimal("0.00"));
				}
				if(rprProduct.getIdrPrice() != null) {
					BigDecimal idrPrice = rprProduct.getIdrPrice().setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setIdrPrice(idrPrice);
				} else {
					rprProduct.setIdrPrice(new BigDecimal("0.00"));
				}
				
				rprProduct.setSubtotal(rs.getBigDecimal("subtotal"));
				if(rprProduct.getSubtotal() != null) {
					BigDecimal subtotal = rprProduct.getSubtotal().setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setSubtotal(subtotal);
				} else {
					rprProduct.setSubtotal(new BigDecimal("0.00"));
				}
				
				Product product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));

				rprProduct.setProduct(product);
				rprProduct.setQtyReceive(rs.getBigDecimal("qty_receive"));
				if(rprProduct.getQtyReceive() != null) {
					BigDecimal qtyReceive = rprProduct.getQtyReceive().setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setQtyReceive(qtyReceive);
				}
				
				rprProducts.add(rprProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rprProducts;
	}

	public void save(InvBalkenProduct pprProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, pprProduct.getInventoryBalkenId());
			insertStatement.setString(2, pprProduct.getProductCode());
			insertStatement.setBigDecimal(3, pprProduct.getQty());
			insertStatement.setBigDecimal(4, pprProduct.getPrice());
			insertStatement.setBigDecimal(5, pprProduct.getIdrPrice());
			insertStatement.setBigDecimal(6, pprProduct.getSubtotal());
			insertStatement.setDate(7, DateUtil.getCurrentDate());
			insertStatement.setString(8, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(InvBalkenProduct pprProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, pprProduct.getProductCode());
			updateStatement.setBigDecimal(2, pprProduct.getQty());
			updateStatement.setBigDecimal(3, pprProduct.getPrice());
			updateStatement.setBigDecimal(4, pprProduct.getIdrPrice());
			updateStatement.setBigDecimal(5, pprProduct.getSubtotal());
			updateStatement.setDate(6, DateUtil.getCurrentDate());
			updateStatement.setString(7, "timotius");
			updateStatement.setInt(8, pprProduct.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(int idInvPr) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery)
					.append("where inventory_balken_id=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, idInvPr);
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
