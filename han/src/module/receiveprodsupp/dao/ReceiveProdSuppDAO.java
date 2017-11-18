package module.receiveprodsupp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.purchaseprodsupp.model.PurchaseProdSupp;
import module.receiveprodsupp.model.ReceiveProdSupp;
import module.sn.costcenter.model.CostCenter;
import module.supplier.model.Supplier;
import module.util.DateUtil;

public class ReceiveProdSuppDAO {
private Connection connection;
	
	private PreparedStatement getAllStatement;
	private PreparedStatement getOrdinalOfCodeNumberStatement;
	private PreparedStatement isPPSCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllQuery = new StringBuilder()
			.append("select r.id, r.rps_code, r.receive_date, r.note, r.status, r.tax, r.total, r.grand_total, ")
			.append("p.pps_code, p.supp_code, p.cost_center_id, ")
			.append("p.purchase_date, p.delivery_date, ")
			.append("s.id as supp_id, s.supp_name, c.cost_center ")
			.append("from receive_prod_supp r ")
			.append("inner join purchase_prod_supp p on p.pps_code = r.pps_code ")
			.append("inner join supplier s on p.supp_code = s.supp_code ")
			.append("inner join cost_center c on p.cost_center_id = c.id ")
			.append("where r.deleted_date is null and s.deleted_date is null and p.deleted_date is null and c.deleted_date is null ").toString();
	
	private String isPPSCodeExistsQuery = "select count(*) as is_exists from receive_prod_supp where rps_code = ? and deleted_date is null ";

	private String insertQuery = new StringBuilder()
			.append("insert into receive_prod_supp (rps_code, pps_code, receive_date, note, total, tax, grand_total, ")
			.append("status, input_date, input_by) ")
			.append("values (?,?,?,?,?,?,?,?,?,?)").toString();

	private String updateQuery = new StringBuilder()
			.append("update receive_prod_supp set rps_code=?, receive_date=?, note=?, ")
			.append("status=?, total=?, tax=?, grand_total=?, ")
			.append("edit_date=?, edited_by=? where rps_code=?").toString();

	private String deleteQuery = "update receive_prod_supp set deleted_date=?, deleted_by=? where id=?";
	
	private String getOrdinalOfCodeNumberQuery = "SELECT CONVERT(SUBSTRING_INDEX(rps_code, '/', 1),UNSIGNED INTEGER) AS ordinal FROM receive_prod_supp "
			+ "WHERE SUBSTRING_INDEX(rps_code, '/', -1) = ? "
			+ "ORDER BY ordinal DESC LIMIT 1 ";
	
	public ReceiveProdSuppDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ReceiveProdSupp> getAll(String status) throws SQLException {
		List<ReceiveProdSupp> rpss = new ArrayList<ReceiveProdSupp>();

		String query = new StringBuilder().append(getAllQuery).toString();
				//.append("and status = ? ").toString();
		
		try {
			getAllStatement = connection.prepareStatement(query);
			//getAllStatement.setString(1, status);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ReceiveProdSupp rps = new ReceiveProdSupp();
				rps.setId(rs.getInt("id"));
				rps.setRpsCode(rs.getString("rps_code"));
				rps.setPpsCode(rs.getString("pps_code"));
				rps.setReceiveDate(rs.getDate("receive_date"));
				rps.setNote(rs.getString("note"));
				rps.setStatus(rs.getString("status"));
				rps.setTotal(rs.getBigDecimal("total"));
				rps.setTax(rs.getBigDecimal("tax"));
				rps.setGrandTotal(rs.getBigDecimal("grand_total"));
				
				PurchaseProdSupp pps = new PurchaseProdSupp();
				pps.setPpsCode(rs.getString("pps_code"));
				pps.setSuppCode(rs.getString("supp_code"));
				pps.setCostCenterId(rs.getInt("cost_center_id") == 0 ? null : rs.getInt("cost_center_id"));
				pps.setPurchaseDate(rs.getDate("purchase_date"));
				pps.setDeliveryDate(rs.getDate("delivery_date"));
				
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("supp_id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				
				CostCenter costCenter = new CostCenter();
				costCenter.setId(rs.getInt("cost_center_id"));
				costCenter.setCostCenter(rs.getString("cost_center"));
				
				pps.setSupplier(supplier);
				pps.setCostCenter(costCenter);
				
				rps.setPurchaseProdSupp(pps);

				rpss.add(rps);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return rpss;
	}
	
	public List<ReceiveProdSupp> getAllBySimpleSearch(String value, String status) throws SQLException {
		List<ReceiveProdSupp> rpss = new ArrayList<ReceiveProdSupp>();
		try {
			
			
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value).append("%").toString();
				String query = new StringBuilder().append("and status = '%s'").append(" and")
						.append(" (lower(r.rps_code) like lower('%s')")
						.append(" (lower(p.pps_code) like lower('%s')")
						.append(" or lower(p.supp_code) like lower('%s')")
						.append(" or lower(s.supp_name) like lower('%s')")
						.append(" or lower(r.status) like lower('%s'))").toString();
				getAllStatement = connection.prepareStatement(String.format(query, status,keyword, keyword, keyword, keyword, keyword));
			} else {
				String query = new StringBuilder().append(getAllQuery).toString();
						//.append("and status = ? ").toString();
				getAllStatement = connection.prepareStatement(query);
				//getAllStatement.setString(1, status);
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ReceiveProdSupp rps = new ReceiveProdSupp();
				rps.setId(rs.getInt("id"));
				rps.setRpsCode(rs.getString("rps_code"));
				rps.setPpsCode(rs.getString("pps_code"));
				rps.setReceiveDate(rs.getDate("receive_date"));
				rps.setNote(rs.getString("note"));
				rps.setStatus(rs.getString("status"));
				rps.setTotal(rs.getBigDecimal("total"));
				rps.setTax(rs.getBigDecimal("tax"));
				rps.setGrandTotal(rs.getBigDecimal("grand_total"));
				
				PurchaseProdSupp pps = new PurchaseProdSupp();
				pps.setPpsCode(rs.getString("pps_code"));
				pps.setSuppCode(rs.getString("supp_code"));
				pps.setCostCenterId(rs.getInt("cost_center_id") == 0 ? null : rs.getInt("cost_center_id"));
				pps.setPurchaseDate(rs.getDate("purchase_date"));
				pps.setDeliveryDate(rs.getDate("delivery_date"));
				
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("supp_id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				
				CostCenter costCenter = new CostCenter();
				costCenter.setId(rs.getInt("cost_center_id"));
				costCenter.setCostCenter(rs.getString("cost_center"));
				
				pps.setSupplier(supplier);
				pps.setCostCenter(costCenter);
				
				rps.setPurchaseProdSupp(pps);

				rpss.add(rps);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rpss;
	}
	
	public int isRPSCodeExists(String rpsCode) throws SQLException {
		int count = 0;
		try {
			isPPSCodeExistsStatement = connection.prepareStatement(isPPSCodeExistsQuery);
			isPPSCodeExistsStatement.setString(1, rpsCode);

			ResultSet rs = isPPSCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(ReceiveProdSupp rps) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, rps.getRpsCode());
			insertStatement.setString(2, rps.getPpsCode());
			insertStatement.setDate(3, DateUtil.toDate(rps.getReceiveDate()));
			insertStatement.setString(4, rps.getNote());
			insertStatement.setBigDecimal(5, rps.getTotal());
			insertStatement.setBigDecimal(6, rps.getTax());
			insertStatement.setBigDecimal(7, rps.getGrandTotal());
			insertStatement.setString(8, rps.getStatus());
			insertStatement.setDate(9, DateUtil.getCurrentDate());
			insertStatement.setString(10, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.executeUpdate();
			
		} catch (SQLException ex) {
			ex.getCause();
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(ReceiveProdSupp rps) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, rps.getPpsCode());
			updateStatement.setString(2, rps.getRpsCode());
			updateStatement.setDate(3, DateUtil.toDate(rps.getReceiveDate()));
			updateStatement.setString(4, rps.getNote());
			updateStatement.setString(5, rps.getStatus());
			updateStatement.setBigDecimal(6, rps.getTotal());
			updateStatement.setBigDecimal(7, rps.getTax());
			updateStatement.setBigDecimal(8, rps.getGrandTotal());
			updateStatement.setDate(9, DateUtil.getCurrentDate());
			updateStatement.setString(10, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setString(11, rps.getRpsCode());
			
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void delete(int id) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public ReceiveProdSupp getById(int id) throws SQLException {
		ReceiveProdSupp rps = null;
		String query = new StringBuilder().append(getAllQuery).append(" and r.id=?").toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, id);
			ResultSet rs = getAllStatement.executeQuery();

			while (rs.next()) {
				rps = new ReceiveProdSupp();
				rps.setId(rs.getInt("id"));
				rps.setRpsCode(rs.getString("rps_code"));
				rps.setPpsCode(rs.getString("pps_code"));
				rps.setReceiveDate(rs.getDate("receive_date"));
				rps.setNote(rs.getString("note"));
				rps.setStatus(rs.getString("status"));
				rps.setTotal(rs.getBigDecimal("total"));
				rps.setTax(rs.getBigDecimal("tax"));
				rps.setGrandTotal(rs.getBigDecimal("grand_total"));
				
				PurchaseProdSupp pps = new PurchaseProdSupp();
				pps.setPpsCode(rs.getString("pps_code"));
				pps.setSuppCode(rs.getString("supp_code"));
				pps.setCostCenterId(rs.getInt("cost_center_id") == 0 ? null : rs.getInt("cost_center_id"));
				pps.setPurchaseDate(rs.getDate("purchase_date"));
				pps.setDeliveryDate(rs.getDate("delivery_date"));
				
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("supp_id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				
				CostCenter costCenter = new CostCenter();
				costCenter.setId(rs.getInt("cost_center_id"));
				costCenter.setCostCenter(rs.getString("cost_center"));
				
				pps.setSupplier(supplier);
				pps.setCostCenter(costCenter);
				
				rps.setPurchaseProdSupp(pps);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rps;
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
			throw new SQLException(ex.getMessage());
		}

		return ordinal;
	}

}
