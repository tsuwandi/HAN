package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.Product;

public class ProductDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "SELECT id, product_name, product_code, length, width, thickness FROM product WHERE 1 = 1 AND wood_type_id = ? AND condition_id = 1 AND grade_id = ? ";

	public ProductDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}

	public List<Product> getAllProduct(int woodTypeId, int gradeId) throws SQLException {
		Connection con = null;
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);
			getAllStatement.setInt(1, woodTypeId);
			getAllStatement.setInt(2, gradeId);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductCode(rs.getString("product_code"));
				product.setLength(rs.getDouble("length"));
				product.setWidth(rs.getDouble("width"));
				product.setThickness(rs.getDouble("thickness"));
				products.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return products;
	}
}