package module.dryin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.dryin.model.DryInPallet;
import module.pembelian.model.PalletCard;
import module.pembelian.model.Received;
import module.util.DateUtil;

public class DryInPalletDAO {

	private Connection connection;

	private PreparedStatement getAllByDryInCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByDryInCodeQuery = "select d.id, d.dry_in_code, d.pallet_card_code, "
			+ "pc.id, r.received_date, r.rit_no, pc.pallet_card_code, pc.volume, pc.length, pc.width, pc.thickness from dry_in_pallet d "
			+ "inner join pallet_card pc on pc.pallet_card_code = d.pallet_card_code "
			+ "inner join received_detail rd ON rd.id = pc.received_detail_id "
			+ "inner join received r on r.received_code = rd.received_code "
			+ "where d.dry_in_code = ? and d.deleted_date is null and pc.deleted_date is null and r.deleted_date is null ";
	private String insertQuery = "insert into dry_in_pallet (dry_in_code, pallet_card_code, "
			+ "input_date, input_by) values (?,?,?,?)";
	private String updateQuery = "update dry_in_pallet set pallet_card_code=?, "
			+ "edit_date=?, edited_by=? where id=?";
	private String deleteQuery = "update dry_in_pallet set deleted_date=?, deleted_by=? ";

	public DryInPalletDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<DryInPallet> getAllByDryInCode(String dryInCode) throws SQLException {
		List<DryInPallet> dryInPallets = new ArrayList<DryInPallet>();

		try {
			getAllByDryInCodeStatement = connection.prepareStatement(getAllByDryInCodeQuery);
			getAllByDryInCodeStatement.setString(1, dryInCode);

			ResultSet rs = getAllByDryInCodeStatement.executeQuery();
			while (rs.next()) {
				DryInPallet dryInPallet = new DryInPallet();
				dryInPallet.setId(rs.getInt("id"));
				dryInPallet.setDryInCode(rs.getString("dry_in_code"));
				dryInPallet.setPalletCardCode(rs.getString("pallet_card_code"));
				
				PalletCard palletCard = new PalletCard();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setVolume(rs.getDouble("volume"));
				palletCard.setLength(rs.getDouble("length"));
				palletCard.setWidth(rs.getDouble("width"));
				palletCard.setThickness(rs.getDouble("thickness"));

				Received received = new Received();
				received.setRitNo(rs.getString("rit_no"));
				received.setReceivedDate(rs.getDate("received_date"));

				palletCard.setReceived(received);

				dryInPallet.setPalletCard(palletCard);
				dryInPallets.add(dryInPallet);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return dryInPallets;
	}

	public void save(DryInPallet dryInPallet) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, dryInPallet.getDryInCode());
			insertStatement.setString(2, dryInPallet.getPalletCardCode());
			insertStatement.setDate(3, DateUtil.getCurrentDate());
			insertStatement.setString(4, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(DryInPallet dryInPallet) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, dryInPallet.getPalletCardCode());
			updateStatement.setDate(2, DateUtil.getCurrentDate());
			updateStatement.setString(3, "timotius");
			updateStatement.setInt(4, dryInPallet.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(String dryInCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where dry_in_code = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, dryInCode);
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
