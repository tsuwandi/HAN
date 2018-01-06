package module.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sales.model.SalesInsuranceDetail;
import module.sn.insurance.model.Insurance;
import module.util.DateUtil;

public class SalesInsuranceDetailDAO {
	private Connection connection;

	private PreparedStatement getAllBySalesIdStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllBySalesIdQuery = "select sid.id, sid.sales_id, sid.insurance_id, sid.cost, i.insurance_company_name, i.insurance_type "
			+ "from sales_insurance_detail sid " + "inner join insurance i on sid.insurance_id = i.insurance_id "
			+ "where sid.sales_id = ? ";

	private String insertQuery = "insert into sales_insurance_detail (sales_id, insurance_id, cost) values (?,?,?)";

	private String updateQuery = "update sales_insurance_detail set sales_id=?, insurance_id=?, cost=? where id=?";

	private String deleteQuery = "update sales_insurance_detail set deleted_date=?, deleted_by=? ";

	public SalesInsuranceDetailDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<SalesInsuranceDetail> getAllBySalesId(int salesId) throws SQLException {
		List<SalesInsuranceDetail> salesInsuranceDetails = new ArrayList<SalesInsuranceDetail>();

		try {

			getAllBySalesIdStatement = connection.prepareStatement(getAllBySalesIdQuery);

			getAllBySalesIdStatement.setInt(1, salesId);

			ResultSet rs = getAllBySalesIdStatement.executeQuery();
			while (rs.next()) {
				SalesInsuranceDetail salesInsuranceDetail = new SalesInsuranceDetail();
				salesInsuranceDetail.setId(rs.getInt("id"));
				salesInsuranceDetail.setSalesId(rs.getInt("sales_id"));
				salesInsuranceDetail.setInsuranceId(rs.getInt("insurance_id"));
				salesInsuranceDetail.setCost(rs.getDouble("cost"));

				Insurance insurance = new Insurance();
				insurance.setInsuranceCompanyName(rs.getString("insurance_company_name"));
				insurance.setInsuranceType(rs.getString("insurance_type"));

				salesInsuranceDetail.setInsurance(insurance);

				salesInsuranceDetails.add(salesInsuranceDetail);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return salesInsuranceDetails;
	}

	public SalesInsuranceDetail save(SalesInsuranceDetail salesInsuranceDetail) throws SQLException {
		ResultSet generatedKeys = null;

		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, salesInsuranceDetail.getSalesId());
			insertStatement.setInt(2, salesInsuranceDetail.getInsuranceId());
			insertStatement.setDouble(3, salesInsuranceDetail.getCost());
			insertStatement.executeUpdate();

			generatedKeys = insertStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				salesInsuranceDetail.setId(generatedKeys.getInt(1));
				generatedKeys.close();
			}
			return salesInsuranceDetail;

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(SalesInsuranceDetail salesInsuranceDetail) throws SQLException {
		try {

			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, salesInsuranceDetail.getSalesId());
			updateStatement.setInt(2, salesInsuranceDetail.getInsuranceId());
			updateStatement.setDouble(3, salesInsuranceDetail.getCost());
			updateStatement.setInt(4, salesInsuranceDetail.getId());
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
			deleteStatement.setString(2, "Sandy");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

}
