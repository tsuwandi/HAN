package module.dryout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.dryout.model.DryOutPallet;
import module.pembelian.model.PalletCard;
import module.pembelian.model.Received;
import module.util.DateUtil;

public class DryOutPalletDAO {

	private Connection connection;
	private PreparedStatement getAllByDryOutCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllByDryOutCodeQuery = "select d.id, d.dry_out_code, d.pallet_card_code, di.date_in, "
			+ "pc.id, r.received_date, r.rit_no, pc.volume, pc.length, pc.width, pc.thickness from dry_out_pallet d "
			+ "inner join pallet_card pc on pc.pallet_card_code = d.pallet_card_code "
			+ "inner join received_detail rd ON rd.id = pc.received_detail_id "
			+ "inner join received r on r.received_code = rd.received_code "
			+ "inner join dry_in_pallet dp on dp.pallet_card_code = pc.pallet_card_code "
			+ "inner join dry_in di on di.dry_in_code = dp.dry_in_code "
			+ "where d.dry_out_code = ? and d.deleted_date is null and pc.deleted_date is null and r.deleted_date is null "
			+ "group by d.pallet_card_code";
	private String insertQuery = "insert into dry_out_pallet (dry_out_code, pallet_card_code, "
			+ "input_date, input_by) values (?,?,?,?)";
	private String updateQuery = "update dry_out_pallet set pallet_card_code=?, "
			+ "edit_date=?, edited_by=? where id=?";
	private String deleteQuery = "update dry_out_pallet set deleted_date=?, deleted_by=? ";

	public DryOutPalletDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<DryOutPallet> getAllByDryOutCode(String dryOutCode) throws SQLException {
		List<DryOutPallet> dryOutPallets = new ArrayList<DryOutPallet>();

		try {
			getAllByDryOutCodeStatement = connection.prepareStatement(getAllByDryOutCodeQuery);
			getAllByDryOutCodeStatement.setString(1, dryOutCode);

			ResultSet rs = getAllByDryOutCodeStatement.executeQuery();
			while (rs.next()) {
				DryOutPallet dryOutPallet = new DryOutPallet();
				dryOutPallet.setId(rs.getInt("id"));
				dryOutPallet.setDryOutCode(rs.getString("dry_out_code"));
				dryOutPallet.setPalletCardCode(rs.getString("pallet_card_code"));
				
				PalletCard palletCard = new PalletCard();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setVolume(rs.getDouble("volume")/1000000);
				palletCard.setDateIn(rs.getTimestamp("date_in"));
				palletCard.setLength(rs.getDouble("length"));
				palletCard.setWidth(rs.getDouble("width"));
				palletCard.setThickness(rs.getDouble("thickness"));
				
				Received received = new Received();
				received.setRitNo(rs.getString("rit_no"));
				received.setReceivedDate(rs.getDate("received_date"));

				palletCard.setReceived(received);

				dryOutPallet.setPalletCard(palletCard);
				dryOutPallets.add(dryOutPallet);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return dryOutPallets;
	}

	public void save(DryOutPallet dryOutPallet) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, dryOutPallet.getDryOutCode());
			insertStatement.setString(2, dryOutPallet.getPalletCardCode());
			insertStatement.setDate(3, DateUtil.getCurrentDate());
			insertStatement.setString(4, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(DryOutPallet dryOutPallet) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, dryOutPallet.getPalletCardCode());
			updateStatement.setDate(2, DateUtil.getCurrentDate());
			updateStatement.setString(3, "timotius");
			updateStatement.setInt(4, dryOutPallet.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(String dryOutCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where dry_out_code = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, dryOutCode);
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
