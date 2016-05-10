package module.pembelian.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.Pallet;
import module.pembelian.model.PalletCardDetail;
import module.pembelian.model.Received;

public class PalletDAO {

	private Connection connection;
	private DataSource dataSource;

	private PreparedStatement getAllForDryInPalletStatement;
	private PreparedStatement getAllForDryOutPalletStatement;
	private PreparedStatement getAllPallets;
	private PreparedStatement insertStatement;
	
	private String getAllForDryInPalletQuery = "SELECT pc.id, r.received_date, r.rit_no, pc.pallet_card_code, pc.total_volume "
			+ "FROM pallet_card pc INNER JOIN received r ON r.received_code = pc.received_code ";
	private String getAllForDryOutPalletQuery = "SELECT pc.id, r.received_date, r.rit_no, pc.pallet_card_code, pc.total_volume, d.date_in "
			+ "FROM pallet_card pc INNER JOIN received r ON r.received_code = pc.received_code "
			+ "INNER JOIN dry_in_pallet dp  ON dp.pallet_card_code = pc.pallet_card_code "
			+ "INNER JOIN dry_in d ON d.dry_in_code = dp.dry_in_code ";

	private String getAllPalletsQuery = "SELECT a.id, pallet_card_code, received_code, emp_code, grade_id, total_volume, total_log, grade, employee_name "
			+ "FROM pallet_card a INNER JOIN grade b ON a.grade_id = b.id INNER JOIN employee c ON a.emp_code = c.employee_id WHERE received_code = ?";
	
	private String insertQuery = "INSERT INTO pallet_card (pallet_card_code, received_code,"
		 		+ " emp_code, grade_id, total_volume, total_log, input_date, input_by) "
		 		+ " VALUES (?,?,?,?,?,?,?,?)";
	 
	public PalletDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public PalletDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}
	
	public List<Pallet> getAllPallets(String receivedCode) throws SQLException{
		List<Pallet> palletCards = new ArrayList<Pallet>();
		Connection con = null;
		try {
	
			con = dataSource.getConnection();
			
			getAllPallets = con.prepareStatement(getAllPalletsQuery);
			getAllPallets.setString(1, receivedCode);
	
			ResultSet rs = getAllPallets.executeQuery();
			while (rs.next()) {
				Pallet palletCard = new Pallet();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setTotalVolume(rs.getDouble("total_volume"));
				palletCard.setEmpCode(rs.getString("emp_code"));
				palletCard.setEmpName(rs.getString("employee_name"));
				palletCard.setGradeID(rs.getInt("grade_id"));
				palletCard.setGrade(rs.getString("grade"));
				palletCard.setTotalLog(rs.getInt("total_log"));
				palletCard.setTotalVolume(rs.getDouble("total_volume"));
				palletCards.add(palletCard);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		
		return palletCards;
	}
	
	public void save(Pallet pallet) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		insertStatement = con.prepareStatement(insertQuery);
    		insertStatement.setString(1, pallet.getPalletCardCode());
    		insertStatement.setString(2, pallet.getReceivedCode());
    		insertStatement.setString(3, pallet.getEmpCode());
    		insertStatement.setInt(4, pallet.getGradeID());
    		insertStatement.setInt(5, pallet.getTotalLog());
    		insertStatement.setDouble(6, pallet.getTotalVolume());
    		insertStatement.setDate(7, new Date(new java.util.Date().getTime()));
    		insertStatement.setString(8, "Michael");
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

	public List<Pallet> getAllForDryInPallet() throws SQLException {
		List<Pallet> palletCards = new ArrayList<Pallet>();

		try {
			String query = new StringBuilder().append(getAllForDryInPalletQuery)
					.append("WHERE pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_in_pallet WHERE deleted_date is null) ")
					.append("ORDER BY r.received_code, pc.pallet_card_code ").toString();

			getAllForDryInPalletStatement = connection.prepareStatement(query);

			ResultSet rs = getAllForDryInPalletStatement.executeQuery();
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

	public List<Pallet> getAllForDryInPalletBySearch(String value) throws SQLException {
		List<Pallet> palletCards = new ArrayList<Pallet>();

		try {
			String query = new StringBuilder().append(getAllForDryInPalletQuery)
					.append("WHERE pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_in_pallet) ")
					.append("AND ( STR_TO_DATE(r.received_date, '%Y-%m-%d') LIKE ('%").append(value).append("%') ")
					.append("OR r.rit_no LIKE ('%").append(value).append("%') ")
					.append("OR pc.pallet_card_code LIKE ('%").append(value).append("%') ")
					.append("OR pc.total_volume LIKE ('%").append(value).append("%')) ")
					.append("ORDER BY r.received_code, pc.pallet_card_code ").toString();

			getAllForDryInPalletStatement = connection.prepareStatement(query);

			ResultSet rs = getAllForDryInPalletStatement.executeQuery();
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

	public Pallet getPalletForDryInPalletByPalletCardCode(String palletCardCode) throws SQLException {
		Pallet palletCard = null;
		try {
			String query = new StringBuilder().append(getAllForDryInPalletQuery)
					.append("WHERE pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_in_pallet) ")
					.append("AND pc.pallet_card_code = '").append(palletCardCode).append("' ")
					.append("ORDER BY pc.pallet_card_code LIMIT 1").toString();

			getAllForDryInPalletStatement = connection.prepareStatement(query);

			ResultSet rs = getAllForDryInPalletStatement.executeQuery();
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

	public List<Pallet> getAllForDryOutPalletByChamberId(int chamberId) throws SQLException {
		List<Pallet> palletCards = new ArrayList<Pallet>();

		try {
			String query = new StringBuilder().append("SELECT a. * FROM ( ").append(getAllForDryOutPalletQuery)
					.append("WHERE EXISTS ( ").append("SELECT 1 FROM dry_in_pallet dip ")
					.append("INNER JOIN dry_in di ON di.dry_in_code = dip.dry_in_code ")
					.append("WHERE pc.pallet_card_code = dip.pallet_card_code ").append("AND di.chamber_id = ")
					.append(chamberId).append(" ").append("AND di.deleted_date is null AND dip.deleted_date is null) ")
					.append("AND pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_out_pallet WHERE deleted_date is null) ")
					.append("GROUP BY pallet_card_code )a ").append("ORDER BY a.pallet_card_code ").toString();

			getAllForDryOutPalletStatement = connection.prepareStatement(query);
			
			System.out.println(getAllForDryOutPalletStatement);

			ResultSet rs = getAllForDryOutPalletStatement.executeQuery();
			while (rs.next()) {
				Pallet palletCard = new Pallet();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setTotalVolume(rs.getDouble("total_volume"));
				palletCard.setDateIn(rs.getTimestamp("date_in"));

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

	public List<Pallet> getAllForDryOutPalletBySearchAndChamberId(String value, int chamberId) throws SQLException {
		List<Pallet> palletCards = new ArrayList<Pallet>();

		try {
			String query = new StringBuilder().append("SELECT a. * FROM ( ").append(getAllForDryOutPalletQuery)
					.append("WHERE EXISTS ( ").append("SELECT 1 FROM dry_in_pallet dip ")
					.append("INNER JOIN dry_in di ON di.dry_in_code = dip.dry_in_code ")
					.append("WHERE pc.pallet_card_code = dip.pallet_card_code ").append("AND di.chamber_id = ")
					.append(chamberId).append(" ").append("AND di.deleted_date is null AND dip.deleted_date is null) ")
					.append("AND pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_out_pallet) ")
					.append("GROUP BY pallet_card_code )a ")
					.append("WHERE STR_TO_DATE(a.received_date, '%Y-%m-%d') LIKE ('%").append(value).append("%') ")
					.append("OR a.rit_no LIKE ('%").append(value).append("%') ")
					.append("OR a.pallet_card_code LIKE ('%").append(value).append("%') ")
					.append("OR a.total_volume LIKE ('%").append(value).append("%') ")
					.append("ORDER BY a.pallet_card_code ").toString();

			getAllForDryOutPalletStatement = connection.prepareStatement(query);
			
			ResultSet rs = getAllForDryOutPalletStatement.executeQuery();
			while (rs.next()) {
				Pallet palletCard = new Pallet();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setTotalVolume(rs.getDouble("total_volume"));
				palletCard.setDateIn(rs.getTimestamp("date_in"));

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

	public Pallet getPalletForDryOutPalletByPalletCardCodeAndChamberId(String palletCardCode, int chamberId)
			throws SQLException {
		Pallet palletCard = null;
		try {
			String query = new StringBuilder().append("SELECT a. * FROM ( ").append(getAllForDryOutPalletQuery)
					.append("WHERE EXISTS ( ").append("SELECT 1 FROM dry_in_pallet dip ")
					.append("INNER JOIN dry_in di ON di.dry_in_code = dip.dry_in_code ")
					.append("WHERE pc.pallet_card_code = dip.pallet_card_code ").append("AND di.chamber_id = ")
					.append(chamberId).append(" ").append("AND di.deleted_date is null AND dip.deleted_date is null) ")
					.append("AND pc.pallet_card_code NOT IN (SELECT pallet_card_code FROM dry_out_pallet) ")
					.append("GROUP BY pallet_card_code )a ").append("WHERE a.pallet_card_code = '")
					.append(palletCardCode).append("' ").append("ORDER BY a.pallet_card_code LIMIT 1").toString();

			getAllForDryOutPalletStatement = connection.prepareStatement(query);

			ResultSet rs = getAllForDryOutPalletStatement.executeQuery();
			while (rs.next()) {
				palletCard = new Pallet();
				palletCard.setId(rs.getInt("id"));
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setTotalVolume(rs.getDouble("total_volume"));
				palletCard.setDateIn(rs.getTimestamp("date_in"));

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
