package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.pembelian.model.Pallet;
import module.pembelian.model.Received;

public class PalletDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "SELECT pc.id, r.received_date, r.rit_no, pc.pallet_card_code, pc.total_volume "
			+ "FROM pallet_card pc INNER JOIN received r ON r.received_code = pc.received_code ";

	public PalletDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Pallet> getAll() throws SQLException {
		List<Pallet> palletCards = new ArrayList<Pallet>();

		try {
			String query = new StringBuilder().append(getAllQuery)
					.append("WHERE pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_in_pallet WHERE deleted_date is null) ")
					.append("ORDER BY r.received_code, pc.pallet_card_code ").toString();

			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Pallet palletCard = new Pallet();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setTotalVolume(rs.getDouble("total_volume"));

				Received received = new Received();
				received.setRitNo(rs.getString("rit_no"));
				received.setReceivedDate(rs.getDate("received_date"));

				palletCard.setReceived(received);

				palletCards.add(palletCard);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return palletCards;
	}

	public List<Pallet> getAllBySearch(String value) throws SQLException {
		List<Pallet> palletCards = new ArrayList<Pallet>();

		try {
			String query = new StringBuilder().append(getAllQuery)
					.append("WHERE pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_in_pallet) ")
					.append("AND ( r.received_date = '").append(value).append("' ").append("OR r.rit_no LIKE ('%")
					.append(value).append("%') ").append("OR pc.pallet_card_code LIKE ('%").append(value).append("%') ")
					.append("OR pc.total_volume LIKE ('%").append(value).append("%')) ")
					.append("ORDER BY r.received_code, pc.pallet_card_code ").toString();

			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Pallet palletCard = new Pallet();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setTotalVolume(rs.getDouble("total_volume"));

				Received received = new Received();
				received.setRitNo(rs.getString("rit_no"));
				received.setReceivedDate(rs.getDate("received_date"));

				palletCard.setReceived(received);

				palletCards.add(palletCard);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return palletCards;
	}

	public Pallet getPalletByPalletCardCode(String palletCardCode) throws SQLException {
		Pallet palletCard = null;
		try {
			String query = new StringBuilder().append(getAllQuery)
					.append("WHERE pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_in_pallet) ")
					.append("AND pc.pallet_card_code = '").append(palletCardCode).append("' ")
					.append("ORDER BY pc.pallet_card_code LIMIT 1").toString();

			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				palletCard = new Pallet();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setTotalVolume(rs.getDouble("total_volume"));

				Received received = new Received();
				received.setRitNo(rs.getString("rit_no"));
				received.setReceivedDate(rs.getDate("received_date"));

				palletCard.setReceived(received);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return palletCard;
	}
}
