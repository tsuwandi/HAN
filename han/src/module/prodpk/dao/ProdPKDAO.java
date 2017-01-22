package module.prodpk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.prodpk.model.ProdPK;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.util.DateUtil;

public class ProdPKDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement getOrdinalOfCodeNumberStatement;
	private PreparedStatement isProdPKCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = new StringBuilder()
			.append("select p.id, p.prod_pk_code, p.production_date, p.group_shift_code, ")
			.append("p.shift_code, p.line_code, p.status, g.description as group_shift_desc,  ")
			.append("s.shift_name, l.description as line_desc from prod_pk p ")
			.append("inner join group_shift g on p.group_shift_code = g.group_shift_code ")
			.append("inner join shift s on p.shift_code = s.shift_code ")
			.append("inner join line l on p.line_code = l.line_code ")
			.append("where p.deleted_date is null and s.deleted_date is null ")
			.append("and l.deleted_date is null ").toString();

	private String isProdPKCodeExistsQuery = "select count(*) as is_exists from prod_pk where prod_pk_code = ? and deleted_date is null ";

	private String insertQuery = new StringBuilder()
			.append("insert into prod_pk (prod_pk_code, production_date, group_shift_code, shift_code, line_code, ")
			.append("status, input_date, input_by) ").append(" values (?,?,?,?,?,?,?,?)")
			.toString();

	private String updateQuery = new StringBuilder()
			.append("update prod_pk set production_date=?, group_shift_code=?, shift_code=?, line_code=?, ")
			.append("status=?, edited_date=?, edited_by=? where id=?").toString();

	private String deleteQuery = "update prod_pk set deleted_date=?, deleted_by=? where id=?";
	
	private String getOrdinalOfCodeNumberQuery = "SELECT CONVERT(SUBSTRING_INDEX(prod_pk_code, '/', 1),UNSIGNED INTEGER) AS ordinal FROM prod_pk "
			+ "WHERE SUBSTRING_INDEX(prod_pk_code, '/', -1) = ? "
			+ "ORDER BY ordinal DESC LIMIT 1 ";

	public ProdPKDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ProdPK> getAll() throws SQLException {
		List<ProdPK> pprs = new ArrayList<ProdPK>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProdPK ppr = new ProdPK();
				ppr.setId(rs.getInt("id"));
				ppr.setProdPKCode(rs.getString("prod_pk_code"));
				ppr.setProductionDate(rs.getDate("production_date"));
				ppr.setGroupShiftCode(rs.getString("group_shift_code"));
				ppr.setShiftCode(rs.getString("shift_code"));
				ppr.setLineCode(rs.getString("line_code"));
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

				ppr.setGroupShift(groupShift);
				ppr.setLine(line);
				ppr.setShift(shift);

				pprs.add(ppr);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return pprs;
	}

	public List<ProdPK> getAllBySimpleSearch(String value) throws SQLException {
		List<ProdPK> pprs = new ArrayList<ProdPK>();
		try {
			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value).append("%").toString();
				String query = new StringBuilder().append(getAllQuery).append(" and")
						.append(" (lower(p.prod_pk_code) like lower('%s')")
						.append(" or lower(p.group_shift_code) like lower('%s')")
						.append(" or lower(s.shift_code) like lower('%s')")
						.append(" or lower(s.line_code) like lower('%s')")
						.append(" or lower(p.status) like lower('%s'))").toString();
				getAllStatement = connection
						.prepareStatement(String.format(query, keyword, keyword, keyword, keyword, keyword));
			} else {
				getAllStatement = connection.prepareStatement(getAllQuery);
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProdPK ppr = new ProdPK();
				ppr.setId(rs.getInt("id"));
				ppr.setProdPKCode(rs.getString("prod_pk_code"));
				ppr.setProductionDate(rs.getDate("production_date"));
				ppr.setGroupShiftCode(rs.getString("group_shift_code"));
				ppr.setShiftCode(rs.getString("shift_code"));
				ppr.setLineCode(rs.getString("line_code"));
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

				ppr.setGroupShift(groupShift);
				ppr.setLine(line);
				ppr.setShift(shift);

				pprs.add(ppr);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return pprs;
	}

	public int isProdPKCodeExists(String pprCode) throws SQLException {
		int count = 0;
		try {
			isProdPKCodeExistsStatement = connection.prepareStatement(isProdPKCodeExistsQuery);
			isProdPKCodeExistsStatement.setString(1, pprCode);

			ResultSet rs = isProdPKCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(ProdPK ppr) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, ppr.getProdPKCode());
			insertStatement.setDate(2, DateUtil.toDate(ppr.getProductionDate()));
			insertStatement.setString(3, ppr.getGroupShiftCode());
			insertStatement.setString(4, ppr.getShiftCode());
			insertStatement.setString(5, ppr.getLineCode());
			insertStatement.setString(6, ppr.getStatus());
			insertStatement.setDate(7, DateUtil.getCurrentDate());
			insertStatement.setString(8, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.getCause();
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(ProdPK ppr) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setDate(1, DateUtil.toDate(ppr.getProductionDate()));
			updateStatement.setString(2, ppr.getGroupShiftCode());
			updateStatement.setString(3, ppr.getShiftCode());
			updateStatement.setString(4, ppr.getLineCode());
			updateStatement.setString(5, ppr.getStatus());
			updateStatement.setDate(6, DateUtil.getCurrentDate());
			updateStatement.setString(7, "timotius");
			updateStatement.setInt(8, ppr.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
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
			throw new SQLException(ex.getMessage());
		}
	}

	public ProdPK getById(int id) throws SQLException {
		ProdPK ppr = null;
		String query = new StringBuilder().append(getAllQuery).append(" and p.id=?").toString();
		try {
			getAllStatement = connection.prepareStatement(query);
			getAllStatement.setInt(1, id);
			ResultSet rs = getAllStatement.executeQuery();

			while (rs.next()) {
				ppr = new ProdPK();
				ppr.setId(rs.getInt("id"));
				ppr.setProdPKCode(rs.getString("prod_pk_code"));
				ppr.setProductionDate(rs.getDate("production_date"));
				ppr.setGroupShiftCode(rs.getString("group_shift_code"));
				ppr.setShiftCode(rs.getString("shift_code"));
				ppr.setLineCode(rs.getString("line_code"));
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

				ppr.setGroupShift(groupShift);
				ppr.setLine(line);
				ppr.setShift(shift);
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
