package module.dryout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.dryin.model.DryIn;
import module.dryout.model.DryOut;
import module.pembelian.model.PalletCard;
import module.sn.chamber.model.Chamber;
import module.util.DateUtil;

public class DryOutDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement getOrdinalOfCodeNumberStatement;
	private PreparedStatement isDryOutCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateConfirmDateStatement;

	private String getAllQuery = "SELECT d.id, d.dry_out_code, d.date_out, d.chamber_id, d.total_volume, d.status, c.chamber "
			+ "FROM dry_out d INNER JOIN chamber c ON d.chamber_id = c.id ";
	private String getOrdinalOfCodeNumberQuery = "SELECT SUBSTRING_INDEX(dry_out_code, '/', -1) AS ordinal FROM dry_out "
			+ "WHERE SUBSTRING_INDEX(SUBSTRING_INDEX(dry_out_code, '/', 2), '/', -1) = ? "
			+ "ORDER BY ordinal DESC LIMIT 1 ";
	private String isDryOutCodeExistsQuery = "select count(*) as is_exists from dry_out where dry_out_code = ? ";
	private String insertQuery = "insert into dry_out (dry_out_code, date_out, chamber_id, total_volume, status, "
			+ "input_date, input_by) values (?,?,?,?,?,?,?)";
	private String updateQuery = "update dry_out set date_out=?, chamber_id=?, total_volume=?, "
			+ "edit_date=?, edited_by=? where dry_out_code=? ";
	private String deleteQuery = "update dry_out set deleted_date=?, deleted_by=? where id=?";
	private String updateConfirmDateQuery = "update dry_out set confirm_date=?, "
			+ "edit_date=?, edited_by=? where dry_out_code=? ";

	public DryOutDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<DryOut> getAll() throws SQLException {
		List<DryOut> dryOuts = new ArrayList<DryOut>();

		try {
			String query = new StringBuilder().append(getAllQuery)
					.append("WHERE d.deleted_date is null AND c.deleted_date is null ")
					.append("ORDER BY dry_out_code DESC ").toString();

			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				DryOut dryOut = new DryOut();
				dryOut.setId(rs.getInt("id"));
				dryOut.setDryOutCode(rs.getString("dry_out_code"));
				dryOut.setDateOut(rs.getTimestamp("date_out"));
				dryOut.setChamberId(rs.getInt("chamber_id"));
				dryOut.setTotalVolume(rs.getDouble("total_volume"));

				Chamber chamber = new Chamber();
				chamber.setId(rs.getInt("chamber_id"));
				chamber.setChamber(rs.getString("chamber"));

				dryOut.setChamber(chamber);
				dryOuts.add(dryOut);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return dryOuts;
	}

	public List<DryOut> getAllDryOutBySimpleSearch(String value) throws SQLException {
		List<DryOut> dryOuts = new ArrayList<DryOut>();

		try {
			String query = new StringBuilder().append(getAllQuery)
					.append("WHERE d.deleted_date is null AND c.deleted_date is null ")
					.append("AND (dry_out_code like ('%").append(value).append("%') ")
					.append("OR STR_TO_DATE(date_out, '%Y-%m-%d') LIKE ('%").append(value).append("%') ")
					.append("OR total_volume like ('%").append(value).append("%') ").append("OR chamber like ('%")
					.append(value).append("%')) ").append("ORDER BY dry_out_code DESC ").toString();

			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				DryOut dryOut = new DryOut();
				dryOut.setId(rs.getInt("id"));
				dryOut.setDryOutCode(rs.getString("dry_out_code"));
				dryOut.setDateOut(rs.getTimestamp("date_out"));
				dryOut.setChamberId(rs.getInt("chamber_id"));
				dryOut.setTotalVolume(rs.getDouble("total_volume"));

				Chamber chamber = new Chamber();
				chamber.setId(rs.getInt("chamber_id"));
				chamber.setChamber(rs.getString("chamber"));

				dryOut.setChamber(chamber);
				dryOuts.add(dryOut);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return dryOuts;
	}

	public int getOrdinalOfCodeNumberByYear(int year) throws SQLException {
		int ordinal = 0;
		try {
			getOrdinalOfCodeNumberStatement = connection.prepareStatement(getOrdinalOfCodeNumberQuery);
			getOrdinalOfCodeNumberStatement.setInt(1, year);
			
			ResultSet rs = getOrdinalOfCodeNumberStatement.executeQuery();
			while (rs.next()) {
				ordinal = rs.getInt("ordinal");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return ordinal;
	}

	public int isDryOutCodeExists(String dryOutCode) throws SQLException {
		int count = 0;
		try {
			isDryOutCodeExistsStatement = connection.prepareStatement(isDryOutCodeExistsQuery);
			isDryOutCodeExistsStatement.setString(1, dryOutCode);

			ResultSet rs = isDryOutCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(DryOut dryOut) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, dryOut.getDryOutCode());
			insertStatement.setTimestamp(2, dryOut.getDateOut());
			insertStatement.setInt(3, dryOut.getChamberId());
			insertStatement.setDouble(4, dryOut.getTotalVolume());
			insertStatement.setString(5, dryOut.getStatus());
			insertStatement.setDate(6, DateUtil.getCurrentDate());
			insertStatement.setString(7, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(DryOut dryOut) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setTimestamp(1, dryOut.getDateOut());
			updateStatement.setInt(2, dryOut.getChamberId());
			updateStatement.setDouble(3, dryOut.getTotalVolume());
			updateStatement.setDate(4, DateUtil.getCurrentDate());
			updateStatement.setString(5, "timotius");
			updateStatement.setString(6, dryOut.getDryOutCode());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		
	}

	public void delete(int id) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}

	public DryOut getById(int id) throws SQLException {
		DryOut dryOut = null;

		try {
			String query = new StringBuilder().append(getAllQuery)
					.append("WHERE d.id = ? ").toString();
			
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, id);
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				dryOut = new DryOut();
				dryOut.setId(rs.getInt("id"));
				dryOut.setDryOutCode(rs.getString("dry_out_code"));
				dryOut.setDateOut(rs.getTimestamp("date_out"));
				dryOut.setChamberId(rs.getInt("chamber_id"));
				dryOut.setTotalVolume(rs.getDouble("total_volume"));
				dryOut.setStatus(rs.getString("status"));
				
				Chamber chamber = new Chamber();
				chamber.setId(rs.getInt("chamber_id"));
				chamber.setChamber(rs.getString("chamber"));

				dryOut.setChamber(chamber);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return dryOut;
	}

	/**
	 * @createBy TSI
	 * 
	 * @return DryOut
	 * @throws SQLException
	 */
	public List<DryOut> getAllDryOutForDailyClosing() throws SQLException {
		List<DryOut> listOfDryOut = new ArrayList<DryOut>();

		try {

			String allDryOutForDailyClosingQuery = "SELECT d.id, d.dry_out_code, d.date_out, d.chamber_id, d.total_volume, d.confirm_date, "
					+ "pc.total, pc.volume, pc.product_code, pc.pallet_card_code, c.chamber, pd.product_name FROM dry_out d "
					+ "INNER JOIN chamber c ON c.id = d.chamber_id "
					+ "INNER JOIN dry_out_pallet dp ON d.dry_out_code = dp.dry_out_code "
					+ "INNER JOIN pallet_card pc ON pc.pallet_card_code = dp.pallet_card_code "
					+ "INNER JOIN product pd ON pd.product_code = pc.product_code "
					+ "WHERE d.confirm_date IS NULL "
					+ "AND d.deleted_date IS NULL AND dp.deleted_date IS NULL AND pc.deleted_date IS NULL";
			
			getAllStatement = connection.prepareStatement(allDryOutForDailyClosingQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				DryOut dryOut = new DryOut();
				dryOut.setId(rs.getInt("id"));
				dryOut.setDryOutCode(rs.getString("dry_out_code"));
				dryOut.setDateOut(rs.getTimestamp("date_out"));
				dryOut.setChamberId(rs.getInt("chamber_id"));
				dryOut.setTotalVolume(rs.getDouble("total_volume"));
				dryOut.setConfirmDate(rs.getDate("confirm_date"));
				
				Chamber chamber = new Chamber();
				chamber.setChamber(rs.getString("chamber"));
				dryOut.setChamber(chamber);

				PalletCard palletCard = new PalletCard();
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setVolume(rs.getDouble("volume"));
				palletCard.setTotal(rs.getInt("total"));
				palletCard.setProductCode(rs.getString("product_code"));
				palletCard.setProductName(rs.getString("product_name"));
				
				dryOut.setPalletCard(palletCard);

				listOfDryOut.add(dryOut);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}

		return listOfDryOut;
	}
	
	public void updateConfirmDate(DryOut dryOut) throws SQLException {
		try {
			updateConfirmDateStatement = connection.prepareStatement(updateConfirmDateQuery);
			updateConfirmDateStatement.setDate(1, DateUtil.getCurrentDate());
			updateConfirmDateStatement.setDate(2, DateUtil.getCurrentDate());
			updateConfirmDateStatement.setString(3, "timotius");
			updateConfirmDateStatement.setString(4, dryOut.getDryOutCode());
			updateConfirmDateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
