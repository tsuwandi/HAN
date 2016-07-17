package module.dryin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.dryin.model.DryIn;
import module.pembelian.model.PalletCard;
import module.pembelian.model.Received;
import module.sn.chamber.model.Chamber;
import module.util.DateUtil;

public class DryInDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement getOrdinalOfCodeNumberStatement;
	private PreparedStatement isDryInCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement updateConfirmDateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = "SELECT d.id, d.dry_in_code, d.date_in, d.chamber_id, d.total_volume, c.chamber "
			+ "FROM dry_in d INNER JOIN chamber c ON d.chamber_id = c.id ";
	private String getOrdinalOfCodeNumberQuery = "SELECT SUBSTRING_INDEX(dry_in_code, '/', -1) AS ordinal FROM dry_in "
			+ "WHERE SUBSTRING_INDEX(SUBSTRING_INDEX(dry_in_code, '/', 2), '/', -1) = ? "
			+ "ORDER BY ordinal DESC LIMIT 1 ";
	private String isDryInCodeExistsQuery = "select count(*) as is_exists from dry_in where dry_in_code = ? ";
	private String insertQuery = "insert into dry_in (dry_in_code, date_in, chamber_id, total_volume, "
			+ "input_date, input_by) values (?,?,?,?,?,?)";
	private String updateQuery = "update dry_in set date_in=?, chamber_id=?, total_volume=?, "
			+ "edit_date=?, edited_by=? where dry_in_code=? ";
	private String updateConfirmDateQuery = "update dry_in set confirm_date=? "
			+ "edit_date=?, edited_by=? where dry_in_code=? ";
	private String deleteQuery = "update dry_in set deleted_date=?, deleted_by=? where id=?";

	public DryInDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<DryIn> getAll() throws SQLException {
		List<DryIn> dryIns = new ArrayList<DryIn>();

		try {
			String query = new StringBuilder().append(getAllQuery)
					.append("WHERE d.deleted_date is null AND c.deleted_date is null ")
					.append("ORDER BY dry_in_code DESC ").toString();

			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				DryIn dryIn = new DryIn();
				dryIn.setId(rs.getInt("id"));
				dryIn.setDryInCode(rs.getString("dry_in_code"));
				dryIn.setDateIn(rs.getTimestamp("date_in"));
				dryIn.setChamberId(rs.getInt("chamber_id"));
				dryIn.setTotalVolume(rs.getDouble("total_volume"));

				Chamber chamber = new Chamber();
				chamber.setId(rs.getInt("chamber_id"));
				chamber.setChamber(rs.getString("chamber"));

				dryIn.setChamber(chamber);
				dryIns.add(dryIn);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return dryIns;
	}

	public List<DryIn> getAllDryInBySimpleSearch(String value) throws SQLException {
		List<DryIn> dryIns = new ArrayList<DryIn>();

		try {
			String query = new StringBuilder().append(getAllQuery)
					.append("WHERE d.deleted_date is null AND c.deleted_date is null ")
					.append("AND (dry_in_code like ('%").append(value).append("%') ")
					.append("OR STR_TO_DATE(date_in, '%Y-%m-%d') LIKE ('%").append(value).append("%') ")
					.append("OR total_volume like ('%").append(value).append("%') ").append("OR chamber like ('%")
					.append(value).append("%')) ").append("ORDER BY dry_in_code DESC ").toString();

			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				DryIn dryIn = new DryIn();
				dryIn.setId(rs.getInt("id"));
				dryIn.setDryInCode(rs.getString("dry_in_code"));
				dryIn.setDateIn(rs.getTimestamp("date_in"));
				dryIn.setChamberId(rs.getInt("chamber_id"));
				dryIn.setTotalVolume(rs.getDouble("total_volume"));

				Chamber chamber = new Chamber();
				chamber.setId(rs.getInt("chamber_id"));
				chamber.setChamber(rs.getString("chamber"));

				dryIn.setChamber(chamber);
				dryIns.add(dryIn);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return dryIns;
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

	public int isDryInCodeExists(String dryInCode) throws SQLException {
		int count = 0;
		try {
			isDryInCodeExistsStatement = connection.prepareStatement(isDryInCodeExistsQuery);
			isDryInCodeExistsStatement.setString(1, dryInCode);

			ResultSet rs = isDryInCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(DryIn dryIn) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, dryIn.getDryInCode());
			insertStatement.setTimestamp(2, dryIn.getDateIn());
			insertStatement.setInt(3, dryIn.getChamberId());
			insertStatement.setDouble(4, dryIn.getTotalVolume());
			insertStatement.setDate(5, DateUtil.getCurrentDate());
			insertStatement.setString(6, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}

	public void update(DryIn dryIn) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setTimestamp(1, dryIn.getDateIn());
			updateStatement.setInt(2, dryIn.getChamberId());
			updateStatement.setDouble(3, dryIn.getTotalVolume());
			updateStatement.setDate(4, DateUtil.getCurrentDate());
			updateStatement.setString(5, "timotius");
			updateStatement.setString(6, dryIn.getDryInCode());
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

	public DryIn getById(int id) throws SQLException {
		DryIn dryIn = null;

		try {
			String query = new StringBuilder().append(getAllQuery).append("WHERE d.id = ? ").toString();

			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, id);
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				dryIn = new DryIn();
				dryIn.setId(rs.getInt("id"));
				dryIn.setDryInCode(rs.getString("dry_in_code"));
				dryIn.setDateIn(rs.getTimestamp("date_in"));
				dryIn.setChamberId(rs.getInt("chamber_id"));
				dryIn.setTotalVolume(rs.getDouble("total_volume"));

				Chamber chamber = new Chamber();
				chamber.setId(rs.getInt("chamber_id"));
				chamber.setChamber(rs.getString("chamber"));

				dryIn.setChamber(chamber);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return dryIn;
	}

	/**
	 * @createBy TSI
	 * 
	 * @return DryIn
	 * @throws SQLException
	 */
	public List<DryIn> getAllDryInForDailyClosing() throws SQLException {
		List<DryIn> listOfDryIn = new ArrayList<DryIn>();

		try {

			String allDryInForDailyClosingQuery = "SELECT d.id, d.dry_in_code, d.date_in, d.chamber_id, d.total_volume, d.confirm_date, "
					+ "pc.total, pc.volume, pc.product_code, pc.pallet_card_code FROM dry_in d "
					+ "INNER JOIN dry_in_pallet dp ON d.dry_in_code = dp.dry_in_code "
					+ "INNER JOIN pallet_card pc ON pc.pallet_card_code = dp.pallet_card_code "
					+ "WHERE d.confirm_date IS NULL "
					+ "AND d.deleted_date IS NULL AND dp.deleted_date IS NULL AND pc.deleted_date IS NULL";
			
			getAllStatement = connection.prepareStatement(allDryInForDailyClosingQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				DryIn dryIn = new DryIn();
				dryIn.setId(rs.getInt("id"));
				dryIn.setDryInCode(rs.getString("dry_in_code"));
				dryIn.setDateIn(rs.getTimestamp("date_in"));
				dryIn.setChamberId(rs.getInt("chamber_id"));
				dryIn.setTotalVolume(rs.getDouble("total_volume"));
				dryIn.setConfirmDate(rs.getDate("confirm_date"));

				PalletCard palletCard = new PalletCard();
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setVolume(rs.getDouble("volume"));
				palletCard.setTotal(rs.getInt("total"));
				palletCard.setProductCode(rs.getString("product_code"));

				dryIn.setPalletCard(palletCard);

				listOfDryIn.add(dryIn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}

		return listOfDryIn;
	}
	
	public void updateConfirmDate(DryIn dryIn) throws SQLException {
		try {
			updateConfirmDateStatement = connection.prepareStatement(updateConfirmDateQuery);
			updateConfirmDateStatement.setDate(1, DateUtil.getCurrentDate());
			updateConfirmDateStatement.setDate(2, DateUtil.getCurrentDate());
			updateConfirmDateStatement.setString(3, "timotius");
			updateConfirmDateStatement.setString(4, dryIn.getDryInCode());
			updateConfirmDateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
