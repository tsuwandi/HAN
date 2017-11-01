package module.purchaseprodsupp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.purchaseprodsupp.model.PurchaseProdSupp;
import module.sn.costcenter.model.CostCenter;
import module.supplier.model.Supplier;
import module.util.DateUtil;

public class PurchaseProdSuppDAO {
	private Connection connection;
	
	private PreparedStatement getAllStatement;
	private PreparedStatement getOrdinalOfCodeNumberStatement;
	private PreparedStatement isPPSCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllQuery = new StringBuilder()
			.append("select p.id, p.pps_code, p.supp_code, p.cost_center_id, ")
			.append("p.purchase_date, p.delivery_date, p.status, p.total, p.note, p.tax, p.total, p.grand_total, ")
			.append("s.id as supp_id, s.supp_name, c.cost_center from purchase_prod_supp p ")
			.append("inner join supplier s on p.supp_code = s.supp_code ")
			.append("inner join cost_center c on p.cost_center_id = c.id ")
			.append("where s.deleted_date is null and p.deleted_date is null and c.deleted_date is null ").toString();
	
	private String isPPSCodeExistsQuery = "select count(*) as is_exists from purchase_prod_supp where pps_code = ? and deleted_date is null ";

	private String insertQuery = new StringBuilder()
			.append("insert into purchase_prod_supp (pps_code, supp_code, cost_center_id, purchase_date, delivery_date, note, total, tax, grand_total, ")
			.append("status, input_date, input_by) ")
			.append("values (?,?,?,?,?,?,?,?,?,?,?,?)").toString();

	private String updateQuery = new StringBuilder()
			.append("update purchase_prod_supp set supp_code=?, cost_center_id=?, purchase_date=?, delivery_date=?, note=?, ")
			.append("status=?, total=?, tax=?, grand_total=?, ")
			.append("edit_date=?, edited_by=? where pps_code=?").toString();

	private String deleteQuery = "update purchase_prod_supp set deleted_date=?, deleted_by=? where id=?";
	
	private String getOrdinalOfCodeNumberQuery = "SELECT CONVERT(SUBSTRING_INDEX(pps_code, '/', 1),UNSIGNED INTEGER) AS ordinal FROM purchase_prod_supp "
			+ "WHERE SUBSTRING_INDEX(pps_code, '/', -1) = ? "
			+ "ORDER BY ordinal DESC LIMIT 1 ";
	
	public PurchaseProdSuppDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<PurchaseProdSupp> getAll(String status) throws SQLException {
		List<PurchaseProdSupp> ppss = new ArrayList<PurchaseProdSupp>();

		String query = new StringBuilder().append(getAllQuery).toString();
				//.append("and status = ? ").toString();
		
		try {
			getAllStatement = connection.prepareStatement(query);
			//getAllStatement.setString(1, status);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PurchaseProdSupp pps = new PurchaseProdSupp();
				pps.setId(rs.getInt("id"));
				pps.setPpsCode(rs.getString("pps_code"));
				pps.setSuppCode(rs.getString("supp_code"));
				pps.setCostCenterId(rs.getInt("cost_center_id") == 0 ? null : rs.getInt("cost_center_id"));
				pps.setPurchaseDate(rs.getDate("purchase_date"));
				pps.setDeliveryDate(rs.getDate("delivery_date"));
				pps.setNote(rs.getString("note"));
				pps.setStatus(rs.getString("status"));
				pps.setTotal(rs.getBigDecimal("total"));
				pps.setTax(rs.getBigDecimal("tax"));
				pps.setGrandTotal(rs.getBigDecimal("grand_total"));
				
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("supp_id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				
				CostCenter costCenter = new CostCenter();
				costCenter.setId(rs.getInt("cost_center_id"));
				costCenter.setCostCenter(rs.getString("cost_center"));
				
				pps.setSupplier(supplier);
				pps.setCostCenter(costCenter);

				ppss.add(pps);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return ppss;
	}
	
	public List<PurchaseProdSupp> getAllBySimpleSearch(String value, String status) throws SQLException {
		List<PurchaseProdSupp> ppss = new ArrayList<PurchaseProdSupp>();
		try {
			
			
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value).append("%").toString();
				String query = new StringBuilder().append("and status = '%s'").append(" and")
						.append(" (lower(p.pps_code) like lower('%s')")
						.append(" or lower(p.supp_code) like lower('%s')")
						.append(" or lower(s.supp_name) like lower('%s')")
						.append(" or lower(p.status) like lower('%s'))").toString();
				getAllStatement = connection.prepareStatement(String.format(query, status,keyword, keyword, keyword, keyword));
			} else {
				String query = new StringBuilder().append(getAllQuery).toString();
						//.append("and status = ? ").toString();
				getAllStatement = connection.prepareStatement(query);
				//getAllStatement.setString(1, status);
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PurchaseProdSupp pps = new PurchaseProdSupp();
				pps.setId(rs.getInt("id"));
				pps.setPpsCode(rs.getString("pps_code"));
				pps.setSuppCode(rs.getString("supp_code"));
				pps.setCostCenterId(rs.getInt("cost_center_id") == 0 ? null : rs.getInt("cost_center_id"));
				pps.setPurchaseDate(rs.getDate("purchase_date"));
				pps.setDeliveryDate(rs.getDate("delivery_date"));
				pps.setNote(rs.getString("note"));
				pps.setStatus(rs.getString("status"));
				pps.setTotal(rs.getBigDecimal("total"));
				pps.setTax(rs.getBigDecimal("tax"));
				pps.setGrandTotal(rs.getBigDecimal("grand_total"));
				
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("supp_id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				
				CostCenter costCenter = new CostCenter();
				costCenter.setId(rs.getInt("cost_center_id"));
				costCenter.setCostCenter(rs.getString("cost_center"));
				
				pps.setSupplier(supplier);
				pps.setCostCenter(costCenter);

				ppss.add(pps);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return ppss;
	}
	
	public int isPPSCodeExists(String ppsCode) throws SQLException {
		int count = 0;
		try {
			isPPSCodeExistsStatement = connection.prepareStatement(isPPSCodeExistsQuery);
			isPPSCodeExistsStatement.setString(1, ppsCode);

			ResultSet rs = isPPSCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(PurchaseProdSupp pps) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, pps.getPpsCode());
			insertStatement.setString(2, pps.getSuppCode());
			if (pps.getCostCenterId() == 0) {
				insertStatement.setNull(3, java.sql.Types.INTEGER);
			} else {
				insertStatement.setInt(3, pps.getCostCenterId());
			}
			insertStatement.setDate(4, DateUtil.toDate(pps.getPurchaseDate()));
			insertStatement.setDate(5, DateUtil.toDate(pps.getDeliveryDate()));
			insertStatement.setString(6, pps.getNote());
			insertStatement.setBigDecimal(7, pps.getTotal());
			insertStatement.setBigDecimal(8, pps.getTax());
			insertStatement.setBigDecimal(9, pps.getGrandTotal());
			insertStatement.setString(10, pps.getStatus());
			insertStatement.setDate(11, DateUtil.getCurrentDate());
			insertStatement.setString(12, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.executeUpdate();
			
		} catch (SQLException ex) {
			ex.getCause();
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(PurchaseProdSupp pps) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, pps.getSuppCode());
			if (pps.getCostCenterId() == 0) {
				updateStatement.setNull(2, java.sql.Types.INTEGER);
			} else {
				updateStatement.setInt(2, pps.getCostCenterId());
			}
			updateStatement.setDate(3, DateUtil.toDate(pps.getPurchaseDate()));
			updateStatement.setDate(4, DateUtil.toDate(pps.getDeliveryDate()));
			updateStatement.setString(5, pps.getNote());
			updateStatement.setString(6, pps.getStatus());
			updateStatement.setBigDecimal(7, pps.getTotal());
			updateStatement.setBigDecimal(8, pps.getTax());
			updateStatement.setBigDecimal(9, pps.getGrandTotal());
			updateStatement.setDate(10, DateUtil.getCurrentDate());
			updateStatement.setString(11, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setString(12, pps.getPpsCode());
			
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
	
	public PurchaseProdSupp getById(int id) throws SQLException {
		PurchaseProdSupp pps = null;
		String query = new StringBuilder().append(getAllQuery).append(" and p.id=?").toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, id);
			ResultSet rs = getAllStatement.executeQuery();

			while (rs.next()) {
				pps = new PurchaseProdSupp();
				pps.setId(rs.getInt("id"));
				pps.setPpsCode(rs.getString("pps_code"));
				pps.setSuppCode(rs.getString("supp_code"));
				pps.setCostCenterId(rs.getInt("cost_center_id") == 0 ? null : rs.getInt("cost_center_id"));
				pps.setPurchaseDate(rs.getDate("purchase_date"));
				pps.setDeliveryDate(rs.getDate("delivery_date"));
				pps.setNote(rs.getString("note"));
				pps.setStatus(rs.getString("status"));
				pps.setTotal(rs.getBigDecimal("total"));
				pps.setTax(rs.getBigDecimal("tax"));
				pps.setGrandTotal(rs.getBigDecimal("grand_total"));
				
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("supp_id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				
				CostCenter costCenter = new CostCenter();
				costCenter.setId(rs.getInt("cost_center_id"));
				costCenter.setCostCenter(rs.getString("cost_center"));
				
				pps.setSupplier(supplier);
				pps.setCostCenter(costCenter);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return pps;
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
