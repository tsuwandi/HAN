package module.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.ServiceFactory;
import module.employeemanagement.model.EmpPosition;
import module.employeemanagement.model.Employee;
import module.util.DateUtil;

public class EmpPositionDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = "select * from employee where deleted_date is null";
	private String insertQuery = "insert into employee (emp_code, fname, lname, current_address, current_city, npwp, ktp, ktp_address, total_child, birth_date, email, phone,  gender_id, marital_id, emergency_contact, emergency_phone, emergency_relation,bank_name,bank_account,rfid,image, input_date, input_by,status_id)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update employee set emp_code = ?, fname = ?, lname=?, current_address = ?, current_city = ?, npwp = ?, ktp = ?, "
			+ "ktp_address = ?, total_child = ?, birth_date = ?, email = ?, phone = ?, gender_id = ?, marital_id = ?,"
			+ " emergency_contact=?, emergency_phone=?, emergency_relation=?,bank_name=?, bank_account=?,rfid=?,image=?, edit_date = ?, edited_by = ? , status_id = ? where id = ?";
	private String deleteQuery = "update employee set delete_date = ?, deleted_by = ? where id = ?";
	
	public EmpPositionDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<EmpPosition> getAllData(String query){
		List<EmpPosition> empPositions = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				EmpPosition empPosition = new EmpPosition();
				empPosition.setId(resultSet.getInt("id"));
				empPosition.setEmpId(resultSet.getInt("emp_id"));
				empPosition.setEmpCode(resultSet.getString("emp_code"));
				empPosition.setEmpCode(resultSet.getString("emp_name"));
				empPosition.setEmpStructureId(resultSet.getInt("emp_structure_id"));
				empPosition.setEmpCode(resultSet.getString("position"));
				empPosition.setEmpCode(resultSet.getString("org_value"));
				empPosition.setTotalSalary(resultSet.getBigDecimal("total_salary"));
				empPosition.setNettSalary(resultSet.getBigDecimal("nett_salary"));
				empPosition.setStart(resultSet.getDate("start"));
				empPosition.setEnd(resultSet.getDate("end"));
				empPositions.add(empPosition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empPositions;
	}

	public void insert(EmpPosition empPosition) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, empPosition.getEmpId());
			insertStatement.setInt(2, empPosition.getEmpStructureId());
			insertStatement.setDate(3, DateUtil.toDate(empPosition.getStart()));
			insertStatement.setDate(4, DateUtil.toDate(empPosition.getEnd()));
			insertStatement.setBigDecimal(5, empPosition.getTotalSalary());
			insertStatement.setBigDecimal(6, empPosition.getNettSalary());
			insertStatement.setString(7, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(8,  DateUtil.toDate(new Date()));
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(EmpPosition empPosition) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, empPosition.getEmpId());
			updateStatement.setInt(2, empPosition.getEmpStructureId());
			updateStatement.setDate(3, DateUtil.toDate(empPosition.getStart()));
			updateStatement.setDate(4, DateUtil.toDate(empPosition.getEnd()));
			updateStatement.setBigDecimal(5, empPosition.getTotalSalary());
			updateStatement.setBigDecimal(6, empPosition.getNettSalary());
			updateStatement.setString(7, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(8,  DateUtil.toDate(new Date()));
			updateStatement.setInt(9, empPosition.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(EmpPosition empPosition) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(new Date()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, empPosition.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
