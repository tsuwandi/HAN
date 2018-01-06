package module.sn.insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.bank.model.Bank;
import module.sn.insurance.model.Insurance;

public class InsuranceDAO {
	private Connection connection;
	// private PreparedStatement insertStatement;
	// private PreparedStatement updateStatement;
	// private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;

	// private String insertQuery = "insert into bank (bank_abbr, bank,
	// input_date, input_by) values (?,?,?,?)";
	// private String updateQuery = "update bank set bank_abbr=?, bank=?,
	// edit_date=?, edited_by=? where id=?";
	// private String deleteQuery = "update bank set deleted_date=?,
	// deleted_by=? where id=?";
	private String getAllQuery = "select insurance_id, insurance_company_name, insurance_type from insurance order by insurance_company_name";

	public InsuranceDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Insurance> getAll() throws SQLException {
		List<Insurance> insurances = new ArrayList<Insurance>();
		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Insurance insurance = new Insurance();
				insurance.setInsuranceId(rs.getInt("insurance_id"));
				insurance.setInsuranceCompanyName(rs.getString("insurance_company_name"));
				insurance.setInsuranceType(rs.getString("insurance_type"));

				insurances.add(insurance);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return insurances;
	}
}
