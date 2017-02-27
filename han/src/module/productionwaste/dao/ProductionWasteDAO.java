package module.productionwaste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.productionwaste.model.ProductionWaste;
import module.util.DateUtil;

public class ProductionWasteDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement getOrdinalOfCodeNumberStatement;
	private PreparedStatement isPWCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = new StringBuilder()
			.append("select p.id, p.pw_code, p.production_date, p.group_shift_code, ")
			.append("p.shift_code, p.line_code, p.production_type_code, p.status, g.description as group_shift_desc,  ")
			.append("s.shift_name, l.description as line_desc, pt.production_type, p.type from production_waste p ")
			.append("inner join group_shift g on p.group_shift_code = g.group_shift_code ")
			.append("inner join shift s on p.shift_code = s.shift_code ")
			.append("inner join line l on p.line_code = l.line_code ")
			.append("inner join production_type pt on p.production_type_code = pt.production_type_code ")
			.append("where p.deleted_date is null and s.deleted_date is null ")
			.append("and l.deleted_date is null and pt.deleted_date is null ").toString();

	private String isPWCodeExistsQuery = "select count(*) as is_exists from production_waste where pw_code = ? and deleted_date is null ";

	private String insertQuery = new StringBuilder()
			.append("insert into production_waste (pw_code, production_date, group_shift_code, shift_code, line_code, ")
			.append("production_type_code, status, input_date, input_by, type) ").append(" values (?,?,?,?,?,?,?,?,?,?)")
			.toString();

	private String updateQuery = new StringBuilder()
			.append("update production_waste set production_date=?, group_shift_code=?, shift_code=?, line_code=?, ")
			.append("production_type_code=?, status=?, edit_date=?, edited_by=? where pw_code=?").toString();

	private String deleteQuery = "update production_waste set deleted_date=?, deleted_by=? where id=?";
	
	private String getOrdinalOfCodeNumberQuery = "SELECT SUBSTRING_INDEX(pw_code, '/', 1) AS ordinal FROM production_waste "
			+ "WHERE SUBSTRING_INDEX(pw_code, '/', -1) = ? "
			+ "ORDER BY ordinal DESC LIMIT 1 ";

	public ProductionWasteDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ProductionWaste> getAll(String sql) throws SQLException {
		List<ProductionWaste> pprs = new ArrayList<ProductionWaste>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+sql);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionWaste ppr = new ProductionWaste();
				ppr.setId(rs.getInt("id"));
				ppr.setPwCode(rs.getString("pw_code"));
				ppr.setProductionDate(rs.getDate("production_date"));
				ppr.setGroupShiftCode(rs.getString("group_shift_code"));
				ppr.setShiftCode(rs.getString("shift_code"));
				ppr.setLineCode(rs.getString("line_code"));
				ppr.setProductionTypeCode(rs.getString("production_type_code"));
				ppr.setStatus(rs.getString("status"));
				ppr.setType(rs.getString("type"));

				GroupShift groupShift = new GroupShift();
				groupShift.setGroupShiftCode(rs.getString("group_shift_code"));
				groupShift.setDescription(rs.getString("group_shift_desc"));

				Line line = new Line();
				line.setLineCode(rs.getString("line_code"));
				line.setDescription(rs.getString("line_desc"));

				Shift shift = new Shift();
				shift.setShiftCode(rs.getString("shift_code"));
				shift.setShiftName(rs.getString("shift_name"));

				ProductionType productionType = new ProductionType();
				productionType.setProductionTypeCode(rs.getString("production_type_code"));
				productionType.setProductionType(rs.getString("production_type"));

				ppr.setGroupShift(groupShift);
				ppr.setLine(line);
				ppr.setShift(shift);
				ppr.setProductionType(productionType);

				pprs.add(ppr);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return pprs;
	}

	public List<ProductionWaste> getAllBySimpleSearch(String type, String value) throws SQLException {
		List<ProductionWaste> pprs = new ArrayList<ProductionWaste>();
		try {
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value).append("%").toString();
				String query = new StringBuilder().append(getAllQuery).append(" AND type = '"+type+"'").append(" and")
						.append(" (lower(p.pw_code) like lower('%s')")
						.append(" or lower(p.group_shift_code) like lower('%s')")
						.append(" or lower(s.shift_code) like lower('%s')")
						.append(" or lower(l.line_code) like lower('%s')")
						.append(" or lower(p.production_type_code) like lower('%s')")
						.append(" or lower(p.status) like lower('%s'))").toString();
				getAllStatement = connection
						.prepareStatement(String.format(query, keyword, keyword, keyword, keyword, keyword,keyword));
			} else {
				getAllStatement = connection.prepareStatement(getAllQuery);
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionWaste ppr = new ProductionWaste();
				ppr.setId(rs.getInt("id"));
				ppr.setPwCode(rs.getString("pw_code"));
				ppr.setProductionDate(rs.getDate("production_date"));
				ppr.setGroupShiftCode(rs.getString("group_shift_code"));
				ppr.setShiftCode(rs.getString("shift_code"));
				ppr.setLineCode(rs.getString("line_code"));
				ppr.setProductionTypeCode(rs.getString("production_type_code"));
				ppr.setStatus(rs.getString("status"));
				ppr.setType(rs.getString("type"));

				GroupShift groupShift = new GroupShift();
				groupShift.setGroupShiftCode(rs.getString("group_shift_code"));
				groupShift.setDescription(rs.getString("group_shift_desc"));

				Line line = new Line();
				line.setLineCode(rs.getString("line_code"));
				line.setDescription(rs.getString("line_desc"));

				Shift shift = new Shift();
				shift.setShiftCode(rs.getString("shift_code"));
				shift.setShiftName(rs.getString("shift_name"));

				ProductionType productionType = new ProductionType();
				productionType.setProductionTypeCode(rs.getString("production_type_code"));
				productionType.setProductionType(rs.getString("production_type"));

				ppr.setGroupShift(groupShift);
				ppr.setLine(line);
				ppr.setShift(shift);
				ppr.setProductionType(productionType);

				pprs.add(ppr);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return pprs;
	}

	public int isPWCodeExists(String pprCode) throws SQLException {
		int count = 0;
		try {
			isPWCodeExistsStatement = connection.prepareStatement(isPWCodeExistsQuery);
			isPWCodeExistsStatement.setString(1, pprCode);

			ResultSet rs = isPWCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(ProductionWaste ppr) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, ppr.getPwCode());
			insertStatement.setDate(2, DateUtil.toDate(ppr.getProductionDate()));
			insertStatement.setString(3, ppr.getGroupShiftCode());
			insertStatement.setString(4, ppr.getShiftCode());
			insertStatement.setString(5, ppr.getLineCode());
			insertStatement.setString(6, ppr.getProductionTypeCode());
			insertStatement.setString(7, ppr.getStatus());
			insertStatement.setDate(8, DateUtil.getCurrentDate());
			insertStatement.setString(9, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setString(10, ppr.getType());
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.getCause();
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(ProductionWaste ppr) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setDate(1, DateUtil.toDate(ppr.getProductionDate()));
			updateStatement.setString(2, ppr.getGroupShiftCode());
			updateStatement.setString(3, ppr.getShiftCode());
			updateStatement.setString(4, ppr.getLineCode());
			updateStatement.setString(5, ppr.getProductionTypeCode());
			updateStatement.setString(6, ppr.getStatus());
			updateStatement.setDate(7, DateUtil.getCurrentDate());
			updateStatement.setString(8, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setString(9, ppr.getPwCode());
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

	public ProductionWaste getById(int id) throws SQLException {
		ProductionWaste ppr = null;
		String query = new StringBuilder().append(getAllQuery).append(" and p.id=?").toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, id);
			ResultSet rs = getAllStatement.executeQuery();

			while (rs.next()) {
				ppr = new ProductionWaste();
				ppr.setId(rs.getInt("id"));
				ppr.setPwCode(rs.getString("pw_code"));
				ppr.setProductionDate(rs.getDate("production_date"));
				ppr.setGroupShiftCode(rs.getString("group_shift_code"));
				ppr.setShiftCode(rs.getString("shift_code"));
				ppr.setLineCode(rs.getString("line_code"));
				ppr.setProductionTypeCode(rs.getString("production_type_code"));
				ppr.setStatus(rs.getString("status"));

				GroupShift groupShift = new GroupShift();
				groupShift.setGroupShiftCode(rs.getString("group_shift_code"));
				groupShift.setDescription(rs.getString("group_shift_desc"));

				Line line = new Line();
				line.setLineCode(rs.getString("line_code"));
				line.setDescription(rs.getString("line_desc"));

				Shift shift = new Shift();
				shift.setShiftCode(rs.getString("shift_code"));
				shift.setShiftName(rs.getString("shift_name"));

				ProductionType productionType = new ProductionType();
				productionType.setProductionTypeCode(rs.getString("production_type_code"));
				productionType.setProductionType(rs.getString("production_type"));

				ppr.setGroupShift(groupShift);
				ppr.setLine(line);
				ppr.setShift(shift);
				ppr.setProductionType(productionType);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return ppr;
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
