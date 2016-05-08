package module.pembelian.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.PicDocking;
import module.pembelian.model.Received;

public class PICDockingDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;

	private String getAllQuery = "SELECT a.id , a.received_code, a.emp_code, b.employee_name FROM pic_docking a INNER JOIN employee b  ON a.emp_code = b.employee_id WHERE a.received_code = ?";
	private String insertQuery = "INSERT INTO pic_docking (received_code, emp_code, input_date, input_by) "
		 		+ " VALUES (?,?,?,?)";

	
	public PICDockingDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}

	public List<PicDocking> getPICDocking(String receivedCode) throws SQLException {
		Connection con = null;
		ArrayList<PicDocking> picDockings = new ArrayList<PicDocking>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);
			getAllStatement.setString(0, receivedCode);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PicDocking picDocking = new PicDocking();
				picDocking.setId(rs.getInt("id"));
				picDocking.setEmpCode(rs.getString("emp_code"));
				picDocking.setEmpName(rs.getString("employee_name"));;
				picDocking.setReceivedCode(rs.getString("received_code"));
				picDockings.add(picDocking);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return picDockings;
	}
	
	public void save(PicDocking picDocking) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		insertStatement = con.prepareStatement(insertQuery);
    		insertStatement.setString(1, picDocking.getReceivedCode());
    		insertStatement.setString(2, picDocking.getEmpCode());
    		insertStatement.setDate(3, new Date(new java.util.Date().getTime()));
    		insertStatement.setString(4, "Michael");
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
}
