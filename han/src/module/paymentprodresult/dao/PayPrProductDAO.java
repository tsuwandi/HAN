package module.paymentprodresult.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.product.model.Product;
import module.util.DateUtil;
import module.paymentprodresult.model.PayPrProduct;

public class PayPrProductDAO {
	private Connection connection;
	private PreparedStatement getAllByPayPrCodeStatement;
	private PreparedStatement getAllByIdInvPrStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByPayPrCodeQuery = new StringBuilder()
			.append("select pp.id, pp.pay_pr_code, pp.product_code, pp.qty, pp.price, pp.idr_price, pp.subtotal, ")
			.append("p.product_name, p.id as product_id, rpr_p.qty as qty_receive, ppr_p.qty as qty_purchase ")
			.append("from pay_pr_product pp ")
			.append("inner join product p on pp.product_code = p.product_code ")
			.append("inner join payment_prod_result pay_pr on pp.pay_pr_code = pay_pr.pay_pr_code ")
			.append("inner join invoice_prod_result ipr on ipr.id = pay_pr.id_inv_pr ")
			.append("inner join inv_pr_product iprd on iprd.id_inv_pr = ipr.id and p.product_code = iprd.product_code ")
			.append("inner join receive_prod_result rpr on rpr.rpr_code = ipr.rpr_code ")
			.append("inner join rpr_product rpr_p on rpr_p.rpr_code = rpr.rpr_code and p.product_code = rpr_p.product_code ")
			.append("inner join purchase_prod_result ppr on ppr.ppr_code = rpr.ppr_code ")
			.append("inner join ppr_product ppr_p on ppr_p.ppr_code = ppr.ppr_code and p.product_code = ppr_p.product_code ")
			.append("where pp.pay_pr_code = ? and pp.deleted_date is null and p.deleted_date is null ")
			.toString();

	private String getAllByIdInvPrQuery = new StringBuilder()
			.append("select iprd.id, null as pay_pr_code, iprd.id_inv_pr, iprd.product_code, iprd.qty, iprd.price as price, iprd.idr_price as idr_price, iprd.subtotal as subtotal, ")
			.append("p.product_name, p.id as product_id, rpr_p.qty as qty_receive, ppr_p.qty as qty_purchase ")
			.append("from invoice_prod_result ipr ")
			.append("inner join inv_pr_product iprd on ipr.id = iprd.id_inv_pr ")
			.append("inner join product p on iprd.product_code = p.product_code ")
			.append("inner join receive_prod_result rpr on rpr.rpr_code = ipr.rpr_code ")
			.append("inner join rpr_product rpr_p on rpr_p.rpr_code = rpr.rpr_code and p.product_code = rpr_p.product_code ")
			.append("inner join purchase_prod_result ppr on ppr.ppr_code = rpr.ppr_code ")
			.append("inner join ppr_product ppr_p on ppr_p.ppr_code = ppr.ppr_code and p.product_code = ppr_p.product_code ")
			.append("where iprd.id_inv_pr = ? and ipr.deleted_date is null and p.deleted_date is null ")
			.toString();

	private String insertQuery = new StringBuilder()
			.append("insert into pay_pr_product (pay_pr_code, product_code, qty, price, idr_price, subtotal, ")
			.append("input_date, input_by) values (?,?,?,?,?,?,?,?)")
			.toString();

	private String updateQuery = "update pay_pr_product set product_code=?, qty=?, price=?, idr_price=?, subtotal=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update pay_pr_product set deleted_date=?, deleted_by=? ";

	public PayPrProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<PayPrProduct> getAllByPPRCode(String pprCode)
			throws SQLException {
		List<PayPrProduct> rprProducts = new ArrayList<PayPrProduct>();

		try {
			getAllByPayPrCodeStatement = connection
					.prepareStatement(getAllByPayPrCodeQuery);
			getAllByPayPrCodeStatement.setString(1, pprCode);

			ResultSet rs = getAllByPayPrCodeStatement.executeQuery();
			while (rs.next()) {
				PayPrProduct rprProduct = new PayPrProduct();
				rprProduct.setId(rs.getInt("id"));
				rprProduct.setPayPrCode(rs.getString("pay_pr_code"));
				rprProduct.setProductCode(rs.getString("product_code"));
				rprProduct.setQty(rs.getBigDecimal("qty"));
				rprProduct.setPrice(rs.getBigDecimal("price"));
				rprProduct.setIdrPrice(rs.getBigDecimal("idr_price"));

				if (rprProduct.getQty() != null) {
					BigDecimal qty = rprProduct.getQty().setScale(2,
							BigDecimal.ROUND_DOWN);
					rprProduct.setQty(qty);
				}
				if (rprProduct.getPrice() != null) {
					BigDecimal price = rprProduct.getPrice().setScale(2,
							BigDecimal.ROUND_DOWN);
					rprProduct.setPrice(price);
				}
				if (rprProduct.getIdrPrice() != null) {
					BigDecimal idrPrice = rprProduct.getIdrPrice().setScale(2,
							BigDecimal.ROUND_DOWN);
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
				BigDecimal qtyReceive = rprProduct.getQtyReceive().setScale(2,
						BigDecimal.ROUND_DOWN);
				rprProduct.setQtyReceive(qtyReceive);
				rprProduct.setQtyPurchase(rs.getBigDecimal("qty_purchase"));
				BigDecimal qtyPurchase = rprProduct.getQtyPurchase().setScale(
						2, BigDecimal.ROUND_DOWN);
				rprProduct.setQtyPurchase(qtyPurchase);

				rprProducts.add(rprProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rprProducts;
	}

	public List<PayPrProduct> getAllByIdInvPr(int idInvPr) throws SQLException {
		List<PayPrProduct> rprProducts = new ArrayList<PayPrProduct>();

		try {
			getAllByIdInvPrStatement = connection
					.prepareStatement(getAllByIdInvPrQuery);
			getAllByIdInvPrStatement.setInt(1, idInvPr);
			
			ResultSet rs = getAllByIdInvPrStatement.executeQuery();
			while (rs.next()) {
				PayPrProduct rprProduct = new PayPrProduct();
				rprProduct.setId(rs.getInt("id"));
				rprProduct.setPayPrCode(rs.getString("pay_pr_code"));
				rprProduct.setProductCode(rs.getString("product_code"));
				rprProduct.setQty(rs.getBigDecimal("qty"));
				rprProduct.setPrice(rs.getBigDecimal("price"));
				rprProduct.setIdrPrice(rs.getBigDecimal("idr_price"));

				if (rprProduct.getQty() != null) {
					BigDecimal qty = rprProduct.getQty().setScale(2,
							BigDecimal.ROUND_DOWN);
					rprProduct.setQty(qty);
				}
				if (rprProduct.getPrice() != null) {
					BigDecimal price = rprProduct.getPrice().setScale(2,
							BigDecimal.ROUND_DOWN);
					rprProduct.setPrice(price);
				} else {
					rprProduct.setPrice(new BigDecimal("0.00"));
				}
				if (rprProduct.getIdrPrice() != null) {
					BigDecimal idrPrice = rprProduct.getIdrPrice().setScale(2,
							BigDecimal.ROUND_DOWN);
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
				if (rprProduct.getQtyReceive() != null) {
					BigDecimal qtyReceive = rprProduct.getQtyReceive()
							.setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setQtyReceive(qtyReceive);
				}
				rprProduct.setQtyPurchase(rs.getBigDecimal("qty_purchase"));
				if (rprProduct.getQtyPurchase() != null) {
					BigDecimal qtyPurchase = rprProduct.getQtyPurchase()
							.setScale(2, BigDecimal.ROUND_DOWN);
					rprProduct.setQtyPurchase(qtyPurchase);
				}

				rprProducts.add(rprProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rprProducts;
	}

	public void save(PayPrProduct pprProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, pprProduct.getPayPrCode());
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

	public void update(PayPrProduct pprProduct) throws SQLException {
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

	public void deleteAll(String pprCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery)
					.append("where pay_pr_code=? ").toString();

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
