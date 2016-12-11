package module.pembelian.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.PalletCard;
import module.pembelian.model.Received;

public class PalletCardDAO {
	
	private Connection connection;
	private DataSource dataSource;
	
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getAllForDryInPalletStatement;
	private PreparedStatement getAllForDryOutPalletStatement;
	
	private String insertQuery = "INSERT INTO pallet_card (received_detail_id, pallet_card_code, length, width, thickness,"
			+ " total, volume, product_code, description, input_date, input_by) "
	 		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private String updateQuery = "INSERT INTO pallet_card (received_detail_id, pallet_card_code, length, width, thickness,"
			+ " total, volume, product_code, description, edit_date, edited_by) "
	 		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private String deleteQuery = "DELETE FROM pallet_card WHERE received_detail_id = ?";
	
	private String getAllForDryInPalletQuery = "SELECT pc.id, r.received_date, r.rit_no, pc.pallet_card_code, pc.total, pc.volume, "
			+ "pc.length, pc.width, pc.thickness FROM pallet_card pc "
			+ "INNER JOIN received_detail rd ON rd.id = pc.received_detail_id "
			+ "INNER JOIN received r ON r.received_code = rd.received_code ";
	
	private String getAllForDryOutPalletQuery = "SELECT pc.id, r.received_date, r.rit_no, pc.pallet_card_code, pc.total, pc.volume, d.date_in, "
			+ "pc.length, pc.width, pc.thickness FROM pallet_card pc "
			+ "INNER JOIN received_detail rd ON rd.id = pc.received_detail_id "
			+ "INNER JOIN received r ON r.received_code = rd.received_code "
			+ "INNER JOIN dry_in_pallet dp  ON dp.pallet_card_code = pc.pallet_card_code "
			+ "INNER JOIN dry_in d ON d.dry_in_code = dp.dry_in_code ";

	
	public PalletCardDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}
	
	public PalletCardDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public void save(PalletCard palletCard) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		insertStatement = con.prepareStatement(insertQuery);
    		insertStatement.setInt(1, palletCard.getReceivedDetailID());
    		insertStatement.setString(2, palletCard.getPalletCardCode());
    		insertStatement.setDouble(3, palletCard.getLength());
    		insertStatement.setDouble(4, palletCard.getWidth());
    		insertStatement.setDouble(5, palletCard.getThickness());
    		insertStatement.setDouble(6, palletCard.getTotal());
    		insertStatement.setDouble(7, palletCard.getVolume());
    		insertStatement.setString(8, palletCard.getProductCode());
    		insertStatement.setString(9, palletCard.getDescription());
    		insertStatement.setDate(10, new Date(new java.util.Date().getTime()));
    		insertStatement.setString(11, "Michael");
    		insertStatement.executeUpdate();
            
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException(ex.getMessage());
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
			}
        }
	}
	
	public void update(PalletCard palletCard) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		updateStatement = con.prepareStatement(updateQuery);
    		updateStatement.setInt(1, palletCard.getReceivedDetailID());
    		updateStatement.setString(2, palletCard.getPalletCardCode());
    		updateStatement.setDouble(3, palletCard.getLength());
    		updateStatement.setDouble(4, palletCard.getWidth());
    		updateStatement.setDouble(5, palletCard.getThickness());
    		updateStatement.setDouble(6, palletCard.getTotal());
    		updateStatement.setDouble(7, palletCard.getVolume());
    		updateStatement.setString(8, palletCard.getProductCode());
    		updateStatement.setString(9, palletCard.getDescription());
    		updateStatement.setDate(10, new Date(new java.util.Date().getTime()));
    		updateStatement.setString(11, "Michael");
    		updateStatement.executeUpdate();
            
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException(ex.getMessage());
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
			}
        }
	}
	
	public void delete(int receivedDetailID) throws SQLException{
		  Connection con = null;
	    	try {
	    		con = dataSource.getConnection();
	    		
	    		deleteStatement = con.prepareStatement(deleteQuery);
	    		deleteStatement.setInt(1, receivedDetailID);
	    		deleteStatement.executeUpdate();
	            
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        	
	        } finally {
	        	try {
					con.close();
				} catch (SQLException e) {
				}
	        }
	}
	
	public List<PalletCard> getAllForDryInPallet() throws SQLException {
		List<PalletCard> palletCards = new ArrayList<PalletCard>();

		try {
			String query = new StringBuilder().append(getAllForDryInPalletQuery)
					.append("INNER JOIN product p ON p.product_code = pc.product_code ")
					.append("WHERE pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_in_pallet WHERE deleted_date is null) ")
					.append("AND p.grade_id != 100 ")
					.append("ORDER BY r.received_code, pc.pallet_card_code ").toString();

			getAllForDryInPalletStatement = connection.prepareStatement(query);

			ResultSet rs = getAllForDryInPalletStatement.executeQuery();
			while (rs.next()) {
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

				palletCards.add(palletCard);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return palletCards;
	}

	public List<PalletCard> getAllForDryInPalletBySearch(String value) throws SQLException {
		List<PalletCard> palletCards = new ArrayList<PalletCard>();

		try {
			String query = new StringBuilder().append(getAllForDryInPalletQuery)
					.append("INNER JOIN product p ON p.product_code = pc.product_code ")
					.append("WHERE pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_in_pallet where deleted_date is null) ")
					.append("AND p.grade_id != 100 ")
					.append("AND ( STR_TO_DATE(r.received_date, '%Y-%m-%d') LIKE ('%").append(value).append("%') ")
					.append("OR r.rit_no LIKE ('%").append(value).append("%') ")
					.append("OR pc.pallet_card_code LIKE ('%").append(value).append("%') ")
					.append("OR pc.volume LIKE ('%").append(value).append("%')) ")
					.append("ORDER BY r.received_code, pc.pallet_card_code ").toString();
			
			getAllForDryInPalletStatement = connection.prepareStatement(query);

			ResultSet rs = getAllForDryInPalletStatement.executeQuery();
			while (rs.next()) {
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

				palletCards.add(palletCard);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return palletCards;
	}

	public PalletCard getPalletForDryInPalletByPalletCardCode(String palletCardCode) throws SQLException {
		PalletCard palletCard = null;
		try {
			String query = new StringBuilder().append(getAllForDryInPalletQuery)
					.append("WHERE pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_in_pallet where deleted_date is null) ")
					.append("AND pc.pallet_card_code = '").append(palletCardCode).append("' ")
					.append("ORDER BY pc.pallet_card_code LIMIT 1").toString();

			getAllForDryInPalletStatement = connection.prepareStatement(query);

			ResultSet rs = getAllForDryInPalletStatement.executeQuery();
			while (rs.next()) {
				palletCard = new PalletCard();
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
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return palletCard;
	}

	public List<PalletCard> getAllForDryOutPalletByChamberId(int chamberId) throws SQLException {
		List<PalletCard> palletCards = new ArrayList<PalletCard>();

		try {
			String query = new StringBuilder().append("SELECT a. * FROM ( ").append(getAllForDryOutPalletQuery)
					.append("WHERE EXISTS ( ").append("SELECT 1 FROM dry_in_pallet dip ")
					.append("INNER JOIN dry_in di ON di.dry_in_code = dip.dry_in_code ")
					.append("WHERE pc.pallet_card_code = dip.pallet_card_code ").append("AND di.chamber_id = ")
					.append(chamberId).append(" ").append("AND di.deleted_date is null AND dip.deleted_date is null) ")
					.append("AND pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_out_pallet WHERE deleted_date is null) ")
					.append("GROUP BY pallet_card_code )a ").append("ORDER BY a.pallet_card_code ").toString();

			getAllForDryOutPalletStatement = connection.prepareStatement(query);
			
			ResultSet rs = getAllForDryOutPalletStatement.executeQuery();
			while (rs.next()) {
				PalletCard palletCard = new PalletCard();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setVolume(rs.getDouble("volume"));
				palletCard.setDateIn(rs.getTimestamp("date_in"));
				palletCard.setLength(rs.getDouble("length"));
				palletCard.setWidth(rs.getDouble("width"));
				palletCard.setThickness(rs.getDouble("thickness"));

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

	public List<PalletCard> getAllForDryOutPalletBySearchAndChamberId(String value, int chamberId) throws SQLException {
		List<PalletCard> palletCards = new ArrayList<PalletCard>();

		try {
			String query = new StringBuilder().append("SELECT a. * FROM ( ").append(getAllForDryOutPalletQuery)
					.append("WHERE EXISTS ( ").append("SELECT 1 FROM dry_in_pallet dip ")
					.append("INNER JOIN dry_in di ON di.dry_in_code = dip.dry_in_code ")
					.append("WHERE pc.pallet_card_code = dip.pallet_card_code ").append("AND di.chamber_id = ")
					.append(chamberId).append(" ").append("AND di.deleted_date is null AND dip.deleted_date is null) ")
					.append("AND pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_out_pallet where deleted_date is null) ")
					.append("GROUP BY pallet_card_code )a ")
					.append("WHERE STR_TO_DATE(a.received_date, '%Y-%m-%d') LIKE ('%").append(value).append("%') ")
					.append("OR a.rit_no LIKE ('%").append(value).append("%') ")
					.append("OR a.pallet_card_code LIKE ('%").append(value).append("%') ")
					.append("OR a.volume LIKE ('%").append(value).append("%') ")
					.append("ORDER BY a.pallet_card_code ").toString();

			getAllForDryOutPalletStatement = connection.prepareStatement(query);
			
			ResultSet rs = getAllForDryOutPalletStatement.executeQuery();
			while (rs.next()) {
				PalletCard palletCard = new PalletCard();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setVolume(rs.getDouble("volume"));
				palletCard.setDateIn(rs.getTimestamp("date_in"));
				palletCard.setLength(rs.getDouble("length"));
				palletCard.setWidth(rs.getDouble("width"));
				palletCard.setThickness(rs.getDouble("thickness"));

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

	public PalletCard getPalletForDryOutPalletByPalletCardCodeAndChamberId(String palletCardCode, int chamberId)
			throws SQLException {
		PalletCard palletCard = null;
		try {
			String query = new StringBuilder().append("SELECT a. * FROM ( ").append(getAllForDryOutPalletQuery)
					.append("WHERE EXISTS ( ").append("SELECT 1 FROM dry_in_pallet dip ")
					.append("INNER JOIN dry_in di ON di.dry_in_code = dip.dry_in_code ")
					.append("WHERE pc.pallet_card_code = dip.pallet_card_code ").append("AND di.chamber_id = ")
					.append(chamberId).append(" ").append("AND di.deleted_date is null AND dip.deleted_date is null) ")
					.append("AND pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_out_pallet where deleted_date is null) ")
					.append("GROUP BY pallet_card_code )a ").append("WHERE a.pallet_card_code = '")
					.append(palletCardCode).append("' ").append("ORDER BY a.pallet_card_code LIMIT 1").toString();

			getAllForDryOutPalletStatement = connection.prepareStatement(query);

			ResultSet rs = getAllForDryOutPalletStatement.executeQuery();
			while (rs.next()) {
				palletCard = new PalletCard();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setVolume(rs.getDouble("volume"));
				palletCard.setDateIn(rs.getTimestamp("date_in"));
				palletCard.setLength(rs.getDouble("length"));
				palletCard.setWidth(rs.getDouble("width"));
				palletCard.setThickness(rs.getDouble("thickness"));

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
