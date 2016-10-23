package module.productionpk.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.productionpk.model.ProdPK;

public class ProdPKDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getLastCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	
	private String getAllQuery = "SELECT a.id, prod_pk_code, a.group_shift_code, d.description AS group_shift_description, "
			+ "a.line_code, b.description AS line_description, a.shift_code, c.shift_name, "
			+ "production_date, information, total_material_protol, total_material_klem, status, confirm_code, confirm_date "
			+ "FROM prod_pk a INNER JOIN line b ON a.line_code = b.line_code "
			+ "INNER JOIN shift c ON a.shift_code = c.shift_code INNER JOIN group_shift d ON a.group_shift_code = d.group_shift_code "
			+ "WHERE a.deleted_date IS NULL";
	
	private String getLastCodeQuery = "SELECT prod_pk_code FROM prod_pk WHERE deleted_date IS NULL ORDER BY id DESC LIMIT 1";
	
	private String insertQuery = "INSERT INTO prod_pk (prod_pk_code, group_shift_code, line_code, shift_code, "
			+ "production_date, information, total_material_protol, total_material_klem, status , input_by, input_date) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "UPDATE prod_pk SET group_shift_code =?, "
			+ "line_code=?, shift_code=?, production_date=?, information=?, total_material_protol=?, total_material_klem=?, status=?, edited_by=?, edited_date=? "
			+ "WHERE prod_pk_code =?";
	
	public ProdPKDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public String getLastCode() throws SQLException{
		String lastCode = null;
		try {
			getLastCodeStatement = connection.prepareStatement(getLastCodeQuery);
			ResultSet rs = getLastCodeStatement.executeQuery();
			if(rs.next()) lastCode =  rs.getString("prod_pk_code");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return lastCode;
	}
	
	public List<ProdPK> getAll() throws SQLException {
		List<ProdPK> productions = new ArrayList<ProdPK>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProdPK production = new ProdPK();
				production.setId(rs.getInt("id"));
				production.setProdPKCode(rs.getString("prod_pk_code"));
				production.setGroupShiftCode(rs.getString("group_shift_code"));
				production.setGroupShiftDescription(rs.getString("group_shift_description"));
				production.setLineCode(rs.getString("line_code"));
				production.setLineDescription(rs.getString("line_description"));
				production.setShiftCode(rs.getString("shift_code"));
				production.setShiftName(rs.getString("shift_name"));
				production.setProductionDate(rs.getDate("production_date"));
				production.setInformation(rs.getString("information"));
				production.setTotalMaterialProtol(rs.getDouble("total_material_protol"));
				production.setTotalMaterialKlem(rs.getDouble("total_material_klem"));
				production.setStatus(rs.getString("status"));
				production.setConfirmCode(rs.getString("confirm_code"));
				production.setConfirmDate(rs.getDate("confirm_date"));
				productions.add(production);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productions;
	}
	
	public List<ProdPK> getSearchAll(String sql) throws SQLException {
		List<ProdPK> productions = new ArrayList<ProdPK>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+sql);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProdPK production = new ProdPK();
				production.setId(rs.getInt("id"));
				production.setProdPKCode(rs.getString("prod_pk_code"));
				production.setGroupShiftCode(rs.getString("group_shift_code"));
				production.setGroupShiftDescription(rs.getString("group_shift_description"));
				production.setLineCode(rs.getString("line_code"));
				production.setLineDescription(rs.getString("line_description"));
				production.setShiftCode(rs.getString("shift_code"));
				production.setShiftName(rs.getString("shift_name"));
				production.setProductionDate(rs.getDate("production_date"));
				production.setInformation(rs.getString("information"));
				production.setTotalMaterialProtol(rs.getDouble("total_material_protol"));
				production.setTotalMaterialKlem(rs.getDouble("total_material_klem"));
				production.setStatus(rs.getString("status"));
				production.setConfirmCode(rs.getString("confirm_code"));
				production.setConfirmDate(rs.getDate("confirm_date"));
				productions.add(production);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productions;
	}
	
	public void save(ProdPK production) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, production.getProdPKCode());
			insertStatement.setString(2, production.getGroupShiftCode());
			insertStatement.setString(3, production.getLineCode());
			insertStatement.setString(4, production.getShiftCode());
			insertStatement.setDate(5, new Date(production.getProductionDate().getTime()));
			insertStatement.setString(6, production.getInformation());
			insertStatement.setDouble(7, production.getTotalMaterialProtol());
			insertStatement.setDouble(8, production.getTotalMaterialKlem());
			insertStatement.setString(9, production.getStatus());
			insertStatement.setString(10, "Michael");
			insertStatement.setDate(11, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void update(ProdPK production) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, production.getGroupShiftCode());
			updateStatement.setString(2, production.getLineCode());
			updateStatement.setString(3, production.getShiftCode());
			updateStatement.setDate(4, new Date(production.getProductionDate().getTime()));
			updateStatement.setString(5, production.getInformation());
			updateStatement.setDouble(6, production.getTotalMaterialProtol());
			updateStatement.setDouble(7, production.getTotalMaterialKlem());
			updateStatement.setString(8, production.getStatus());
			updateStatement.setString(9, "Michael");
			updateStatement.setDate(10, new Date(new java.util.Date().getTime()));
			updateStatement.setString(11, production.getProdPKCode());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public List<ProdPK> advancedSearchProdPK(String sql, List<Object> objs) throws SQLException{
		List<ProdPK> productions = new ArrayList<ProdPK>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery + sql);

			int i = 1;
			for (int j = 0; j < objs.size(); j++) {
				Object obj = objs.get(j);
				if (obj instanceof String) {
					if (obj != null) {
						getAllStatement.setString(i, (String) "%" + obj + "%");
						i++;
					}
				} else {
					if (obj != null) {
						getAllStatement.setDate(i, new Date(((java.util.Date) obj).getTime()));
						i++;
					}
				}
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProdPK production = new ProdPK();
				production.setId(rs.getInt("id"));
				production.setProdPKCode(rs.getString("prod_pk_code"));
				production.setGroupShiftCode(rs.getString("group_shift_code"));
				production.setGroupShiftDescription(rs.getString("group_shift_description"));
				production.setLineCode(rs.getString("line_code"));
				production.setLineDescription(rs.getString("line_description"));
				production.setShiftCode(rs.getString("shift_code"));
				production.setShiftName(rs.getString("shift_name"));
				production.setProductionDate(rs.getDate("production_date"));
				production.setInformation(rs.getString("information"));
				production.setTotalMaterialProtol(rs.getDouble("total_material_protol"));
				production.setTotalMaterialKlem(rs.getDouble("total_material_klem"));
				production.setStatus(rs.getString("status"));
				production.setConfirmCode(rs.getString("confirm_code"));
				production.setConfirmDate(rs.getDate("confirm_date"));
				productions.add(production);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return productions;
	}
}
