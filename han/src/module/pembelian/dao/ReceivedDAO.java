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
import module.util.DateUtil;

public class ReceivedDAO {
	private DataSource dataSource;
	private Connection connection;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;
	private PreparedStatement lastIdStatement;
	private PreparedStatement advancedSearchStatement;
	private PreparedStatement updateStatusStatement;
	private PreparedStatement udpdateEmpCodeStatement;

	private String insertQuery = "INSERT INTO received (received_code, received_date,"
			+ " rit_no, license_plate, driver, delivery_note, wood_type_id, driver_id, received_status, supplier_code, supplier_cp_id, input_date, input_by) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "UPDATE received set received_date=?, rit_no=?,"
			+ " license_plate=?, driver=?, delivery_note=?, wood_type_id=?, supplier_code=?, supplier_cp_id=?, edit_date=?, edited_by=?"
			+ " WHERE id=? AND received_code=?";
	private String deleteQuery = "update received set deleted_date=? , " + "deleted_by=? where received_code=?";
	private String getAllQuery = "select a.id, received_code, received_date, rit_no, a.license_plate, a.supplier_code, a.supplier_cp_id, s.name, "
			+ "driver, a.delivery_note, a.wood_type_id, supp_name, driver_id, received_status, wood_type, wood_domicile, wood_resource, a.emp_code, a.total_volume "
			+ "FROM received a " + "INNER JOIN supplier c ON a.supplier_code = c.supp_code "
			+ "INNER JOIN supp_cp s ON a.supplier_code = s.supp_code "
			+ "INNER JOIN wood_type d ON a.wood_type_id = d.id "
			+ "INNER JOIN delivery f ON a.delivery_note = f.delivery_note "
			+ "INNER JOIN wood_resource e ON f.wood_resource_id = e.id WHERE 1=1 AND a.deleted_date IS NULL";

	private String lastID = "SELECT received_code FROM received ORDER BY ID DESC LIMIT 1";

	private String updateStatusQuery = "UPDATE received SET received_status = 'Diproses', total_volume=?, emp_code=? WHERE received_code = ?";

	private String updateEmpCodeQuery = "UPDATE received SET emp_code = ? WHERE received_code = ?";

	public ReceivedDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}

	public void delete(Received received) throws SQLException {
		Connection con = null;

		try {
			con = dataSource.getConnection();
			deleteStatement = con.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			deleteStatement.setString(2, "Michael");
			deleteStatement.setString(3, received.getReceivedCode());
			deleteStatement.executeUpdate();
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

	public String getLastCode() throws SQLException {
		Connection con = null;
		String code;
		try {
			con = dataSource.getConnection();
			lastIdStatement = con.prepareStatement(lastID);

			ResultSet rs = lastIdStatement.executeQuery();
			rs.next();
			code = rs.getString("received_code");

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return code;
	}

	public void updateStatus(Double totalVolume, String empCode, String receivedCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			updateStatusStatement = con.prepareStatement(updateStatusQuery);
			updateStatusStatement.setDouble(1, totalVolume);
			updateStatusStatement.setString(2, empCode);
			updateStatusStatement.setString(3, receivedCode);
			updateStatusStatement.executeUpdate();

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

	public void updateEmpCode(String empCode, String receivedCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			udpdateEmpCodeStatement = con.prepareStatement(updateEmpCodeQuery);
			udpdateEmpCodeStatement.setString(1, empCode);
			udpdateEmpCodeStatement.setString(2, receivedCode);
			udpdateEmpCodeStatement.executeUpdate();

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

	public List<Received> getAllBySearch(String receivedCode) throws SQLException {
		Connection con = null;
		List<Received> receiveds = new ArrayList<Received>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery + " AND received_code LIKE ?");
			getAllStatement.setString(1, "%" + receivedCode + "%");

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Received received = new Received();
				received.setId(rs.getInt("id"));
				received.setReceivedCode(rs.getString("received_code"));
				received.setReceivedDate(rs.getDate("received_date"));
				received.setRitNo(rs.getString("rit_no"));
				received.setLicensePlate(rs.getString("license_plate"));
				received.setDriver(rs.getString("driver"));
				received.setDeliveryNote(rs.getString("delivery_note"));
				received.setWoodTypeID(rs.getInt("wood_type_id"));
				received.setSupplier(rs.getString("supp_name"));
				received.setReceivedStatus(rs.getString("received_status"));
				received.setDriverID(rs.getString("driver_id"));
				received.setWoodTypeName(rs.getString("wood_type"));
				received.setWoodDomicile(rs.getString("wood_domicile"));
				received.setWoodResource(rs.getString("wood_resource"));
				received.setSupplierCode(rs.getString("supplier_code"));
				received.setSupplierCpID(rs.getInt("supplier_cp_id"));
				received.setSubSupplierName(rs.getString("name"));
				received.setEmpCode(rs.getString("emp_code"));
				received.setTotalVolume(rs.getDouble("total_volume"));
				receiveds.add(received);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return receiveds;
	}

	public List<Received> getAll() throws SQLException {
		Connection con = null;
		List<Received> receiveds = new ArrayList<Received>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Received received = new Received();
				received.setId(rs.getInt("id"));
				received.setReceivedCode(rs.getString("received_code"));
				received.setReceivedDate(rs.getDate("received_date"));
				received.setRitNo(rs.getString("rit_no"));
				received.setLicensePlate(rs.getString("license_plate"));
				received.setDriver(rs.getString("driver"));
				received.setDeliveryNote(rs.getString("delivery_note"));
				received.setWoodTypeID(rs.getInt("wood_type_id"));
				received.setSupplier(rs.getString("supp_name"));
				received.setReceivedStatus(rs.getString("received_status"));
				received.setDriverID(rs.getString("driver_id"));
				received.setWoodTypeName(rs.getString("wood_type"));
				received.setWoodDomicile(rs.getString("wood_domicile"));
				received.setWoodResource(rs.getString("wood_resource"));
				received.setSupplierCode(rs.getString("supplier_code"));
				received.setSupplierCpID(rs.getInt("supplier_cp_id"));
				received.setSubSupplierName(rs.getString("name"));
				received.setEmpCode(rs.getString("emp_code"));
				received.setTotalVolume(rs.getDouble("total_volume"));
				receiveds.add(received);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return receiveds;
	}

	public List<Received> getAdvancedSearchData(String sql, List<Object> objs) throws SQLException {
		Connection con = null;
		List<Received> receiveds = new ArrayList<Received>();

		try {
			con = dataSource.getConnection();
			advancedSearchStatement = con.prepareStatement(getAllQuery + sql);

			int i = 1;
			for (int j = 0; j < objs.size(); j++) {
				Object obj = objs.get(j);
				if (obj instanceof String) {
					if (obj != null) {
						advancedSearchStatement.setString(i, (String) "%" + obj + "%");
						i++;
					}
				} else {
					if (obj != null) {
						advancedSearchStatement.setDate(i, new Date(((java.util.Date) obj).getTime()));
						i++;
					}
				}
			}

			ResultSet rs = advancedSearchStatement.executeQuery();
			while (rs.next()) {
				Received received = new Received();
				received.setId(rs.getInt("id"));
				received.setReceivedCode(rs.getString("received_code"));
				received.setReceivedDate(rs.getDate("received_date"));
				received.setRitNo(rs.getString("rit_no"));
				received.setLicensePlate(rs.getString("license_plate"));
				received.setDriver(rs.getString("driver"));
				received.setDeliveryNote(rs.getString("delivery_note"));
				received.setWoodTypeID(rs.getInt("wood_type_id"));
				received.setSupplier(rs.getString("supp_name"));
				received.setReceivedStatus(rs.getString("received_status"));
				received.setDriverID(rs.getString("driver_id"));
				received.setWoodTypeName(rs.getString("wood_type"));
				received.setWoodDomicile(rs.getString("wood_domicile"));
				received.setWoodResource(rs.getString("wood_resource"));
				received.setSupplierCode(rs.getString("supplier_code"));
				received.setSupplierCpID(rs.getInt("supplier_cp_id"));
				received.setSubSupplierName(rs.getString("name"));
				received.setEmpCode(rs.getString("emp_code"));
				received.setTotalVolume(rs.getDouble("total_volume"));
				receiveds.add(received);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return receiveds;
	}

	public void save(Received received) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();

			insertStatement = con.prepareStatement(insertQuery);
			insertStatement.setString(1, received.getReceivedCode());
			insertStatement.setDate(2, new Date(received.getReceivedDate().getTime()));
			insertStatement.setString(3, received.getRitNo());
			insertStatement.setString(4, received.getLicensePlate());
			insertStatement.setString(5, received.getDriver());
			insertStatement.setString(6, received.getDeliveryNote());
			insertStatement.setInt(7, received.getWoodTypeID());
			insertStatement.setString(8, received.getDriverID());
			insertStatement.setString(9, "Baru");
			insertStatement.setString(10, received.getSupplierCode());
			insertStatement.setInt(11, received.getSupplierCpID());
			insertStatement.setDate(12, new Date(new java.util.Date().getTime()));
			insertStatement.setString(13, "Michael");
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

	public void update(Received received) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			updateStatement = con.prepareStatement(updateQuery);
			updateStatement.setDate(1, new Date(received.getReceivedDate().getTime()));
			updateStatement.setString(2, received.getRitNo());
			updateStatement.setString(3, received.getLicensePlate());
			updateStatement.setString(4, received.getDriver());
			updateStatement.setString(5, received.getDeliveryNote());
			updateStatement.setInt(6, received.getWoodTypeID());
			updateStatement.setString(7, received.getSupplierCode());
			updateStatement.setInt(8, received.getSupplierCpID());
			updateStatement.setDate(9, new Date(new java.util.Date().getTime()));
			updateStatement.setString(10, "Michael");
			updateStatement.setInt(11, received.getId());
			updateStatement.setString(12, received.getReceivedCode());
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
	
	public ReceivedDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Received> getAllBySendToFinanceDateIsNull() throws SQLException {
		List<Received> listOfReceived = new ArrayList<Received>();

		try {
			String allBySendToFinanceDateIsNullQuery = "select id, received_code, received_date, supp_code, supplier_cp_id, rit_no, license_plate, "
					+ "driver, delivery_note, wood_type_id, driver_id, received_status, emp_code, total_volume "
					+ "FROM received WHERE send_to_finance_date IS NULL AND deleted_date IS NULL";

			getAllStatement = connection.prepareStatement(allBySendToFinanceDateIsNullQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Received received = new Received();
				received.setId(rs.getInt("id"));
				received.setReceivedCode(rs.getString("received_code"));
				received.setReceivedDate(rs.getDate("received_date"));
				received.setRitNo(rs.getString("rit_no"));
				received.setLicensePlate(rs.getString("license_plate"));
				received.setDriver(rs.getString("driver"));
				received.setDeliveryNote(rs.getString("delivery_note"));
				received.setWoodTypeID(rs.getInt("wood_type_id"));
				received.setReceivedStatus(rs.getString("received_status"));
				received.setDriverID(rs.getString("driver_id"));
				received.setSupplierCode(rs.getString("supplier_code"));
				received.setSupplierCpID(rs.getInt("supplier_cp_id"));
				received.setEmpCode(rs.getString("emp_code"));
				received.setTotalVolume(rs.getDouble("total_volume"));
				listOfReceived.add(received);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}

		return listOfReceived;
	}

	/**
	 * @createBy TSI
	 * 
	 * @param received
	 * @throws SQLException
	 */
	public void updateSendToFinanceDate(Received received) throws SQLException {
		try {
			String updateSendToFinanceDateQuery = "UPDATE received set send_to_finance=?, edit_date=?, edited_by=? "
					+ "WHERE id=?";
			updateStatement = connection.prepareStatement(updateSendToFinanceDateQuery);
			updateStatement.setDate(1, DateUtil.getCurrentDate());
			updateStatement.setDate(2, DateUtil.getCurrentDate());
			updateStatement.setString(3, "Finance");
			updateStatement.setInt(4, received.getId());
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
	}

	/**
	 * @createBy TSI
	 * 
	 * @return Received
	 * @throws SQLException
	 */
	public List<Received> getAllReceivedForDailyClosing() throws SQLException {
		List<Received> listOfReceived = new ArrayList<Received>();

		try {
			String allReceivedForDailyClosingQuery = "select r.id, r.received_code, r.received_date, r.supp_code, r.supplier_cp_id, r.rit_no, r.license_plate, "
					+ "r.driver, r.delivery_note, r.wood_type_id, r.driver_id, r.received_status, r.emp_code, r.total_volume, r.confirm_date, r.send_to_finance_date "
					+ "pc.total, pc.volume, pc.product_code, pc.pallet_card_code "
					+ "FROM received r "
					+ "INNER JOIN received_detail rd ON r.received_code = rd.received_code "
					+ "INNER JOIN pallet_card pc ON rd.id = pc.received_detail_id "
					+ "WHERE r.send_to_finance_date IS NOT NULL AND r.confirm_date IS NULL "
					+ "AND r.deleted_date IS NULL AND rd.deleted_date IS NULL AND pc.deleted_date IS NULL";

			getAllStatement = connection.prepareStatement(allReceivedForDailyClosingQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Received received = new Received();
				received.setId(rs.getInt("id"));
				received.setReceivedCode(rs.getString("received_code"));
				received.setReceivedDate(rs.getDate("received_date"));
				received.setRitNo(rs.getString("rit_no"));
				received.setLicensePlate(rs.getString("license_plate"));
				received.setDriver(rs.getString("driver"));
				received.setDeliveryNote(rs.getString("delivery_note"));
				received.setWoodTypeID(rs.getInt("wood_type_id"));
				received.setReceivedStatus(rs.getString("received_status"));
				received.setDriverID(rs.getString("driver_id"));
				received.setSupplierCode(rs.getString("supplier_code"));
				received.setSupplierCpID(rs.getInt("supplier_cp_id"));
				received.setEmpCode(rs.getString("emp_code"));
				received.setTotalVolume(rs.getDouble("total_volume"));
				received.setConfirmDate(rs.getDate("confirm_date"));
				received.setSendToFinanceDate(rs.getDate("send_to_finance_date"));
				
				PalletCard palletCard = new PalletCard();
				palletCard.setPalletCardCode(rs.getString("pallet_card_code"));
				palletCard.setVolume(rs.getDouble("volume"));
				palletCard.setTotal(rs.getInt("total"));
				palletCard.setProductCode(rs.getString("product_code"));
				
				received.setPalletCard(palletCard);
				
				listOfReceived.add(received);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}

		return listOfReceived;
	}

	/*
	 * public void delete(String employeeId) throws SQLException { Connection
	 * con = null; try { con = dataSource.getConnection(); deleteStatement =
	 * con.prepareStatement(deleteQuery);
	 * 
	 * deleteStatement.setString(1, employeeId);
	 * deleteStatement.executeUpdate();
	 * 
	 * } catch (SQLException ex) { ex.printStackTrace(); throw new
	 * SQLException(ex.getMessage()); } finally { try { con.close(); } catch
	 * (SQLException e) { } } }
	 */

}
