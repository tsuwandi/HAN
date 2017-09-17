package module.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.customer.model.CustAddress;
import module.pembelian.model.Product;
import module.product.model.Uom;
import module.sales.model.SalesDetail;
import module.util.DateUtil;

public class SalesDetailDAO {
	private Connection connection;

	private PreparedStatement getAllBySalesIdStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllBySalesIdQuery = "select sd.id, sd.product_id, sd.sales_id, sd.quantity, sd.nett_price, "
			+ "p.id as product_id, p.product_name as product_name, p.product_code as product_code, p.product_uom_id as product_uom_id, "
			+ "p.length as product_length, p.width as product_width, p.thickness as product_thickness, "
			+ "u.id as uom_id, u.uom as uom_name " + "from sales_detail sd "
			+ "inner join product p on sd.product_id = p.id " + "inner join uom u on p.product_uom_id = u.id "
			+ "where sales_id = ? " + "and sd.deleted_date is null";

	private String insertQuery = "insert into sales_detail (product_id, sales_id, quantity, nett_price, "
			+ "input_date, input_by) values (?,?,?,?,?,?)";

	private String updateQuery = "update sales_detail set product_id=?, sales_id=?, quantity=?, "
			+ "nett_price=?, edited_date=?, edited_by=? where id=?";

	private String deleteQuery = "update sales_detail set deleted_date=?, deleted_by=? ";

	public SalesDetailDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<SalesDetail> getAllBySalesId(int salesId) throws SQLException {
		List<SalesDetail> salesDetails = new ArrayList<SalesDetail>();

		try {

			getAllBySalesIdStatement = connection.prepareStatement(getAllBySalesIdQuery);

			getAllBySalesIdStatement.setInt(1, salesId);

			ResultSet rs = getAllBySalesIdStatement.executeQuery();
			while (rs.next()) {
				SalesDetail salesDetail = new SalesDetail();
				salesDetail.setId(rs.getInt("id"));
				salesDetail.setProductId(rs.getInt("product_id"));
				salesDetail.setSalesId(rs.getInt("sales_id"));
				salesDetail.setQuantity(rs.getInt("quantity"));
				salesDetail.setNettPrice(rs.getDouble("nett_price"));
				salesDetail.setTotalPrice(salesDetail.getQuantity() * salesDetail.getNettPrice());
				
				Product product = new Product();
				product.setId(rs.getInt("product_id"));
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));
				product.setProductUomId(rs.getInt("product_uom_id"));
				product.setLength(rs.getInt("product_length"));
				product.setWidth(rs.getInt("product_width"));
				product.setThickness(rs.getInt("product_thickness"));

				Uom uom = new Uom();
				uom.setId(rs.getInt("uom_id"));
				uom.setUom(rs.getString("uom_name"));

				salesDetail.setUom(uom);
				salesDetail.setProduct(product);
				salesDetails.add(salesDetail);

				salesDetail.setTotalVolume(salesDetail.getQuantity() * salesDetail.getProduct().getLength()
						* salesDetail.getProduct().getWidth() * salesDetail.getProduct().getThickness());
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return salesDetails;
	}

	public SalesDetail save(SalesDetail salesDetail) throws SQLException {
		ResultSet generatedKeys = null;

		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, salesDetail.getProductId());
			insertStatement.setInt(2, salesDetail.getSalesId());
			insertStatement.setInt(3, salesDetail.getQuantity());
			insertStatement.setDouble(4, salesDetail.getNettPrice());
			insertStatement.setDate(5, DateUtil.getCurrentDate());
			insertStatement.setString(6, "Sandy");
			insertStatement.executeUpdate();

			generatedKeys = insertStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				salesDetail.setId(generatedKeys.getInt(1));
				generatedKeys.close();
			}
			return salesDetail;

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(SalesDetail salesDetail) throws SQLException {
		try {

			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, salesDetail.getProductId());
			updateStatement.setInt(2, salesDetail.getSalesId());
			updateStatement.setInt(3, salesDetail.getQuantity());
			updateStatement.setDouble(4, salesDetail.getNettPrice());
			updateStatement.setDate(5, DateUtil.getCurrentDate());
			updateStatement.setString(6, "Sandy");
			updateStatement.setInt(7, salesDetail.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(int salesId) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where sales_id = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "Sandy");
			deleteStatement.setInt(3, salesId);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where id = ? ").toString();

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
