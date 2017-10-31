package module.employeemanagement.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EmpPosition {
	int id;
	int empId;
	String empCode;
	String empName;
	int empStructureId;
	String position;
	String org_value;
	BigDecimal totalSalary;
	BigDecimal nettSalary;
	List<EmpTaxDetail> empTaxDetails;
	List<EmpPayrollDetail> empPayrollDetails;
	Map<Integer, EmpTaxDetail> deletedEmpTaxMaps;
	Map<Integer, EmpPayrollDetail> deletedEmpPayrollDetails;
	Date start;
	Date end;
	
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpStructureId() {
		return empStructureId;
	}
	public void setEmpStructureId(int empStructureId) {
		this.empStructureId = empStructureId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOrg_value() {
		return org_value;
	}
	public void setOrg_value(String org_value) {
		this.org_value = org_value;
	}
	public BigDecimal getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(BigDecimal totalSalary) {
		this.totalSalary = totalSalary;
	}
	public BigDecimal getNettSalary() {
		return nettSalary;
	}
	public void setNettSalary(BigDecimal nettSalary) {
		this.nettSalary = nettSalary;
	}
	public List<EmpTaxDetail> getEmpTaxDetails() {
		return empTaxDetails;
	}
	public void setEmpTaxDetails(List<EmpTaxDetail> empTaxDetails) {
		this.empTaxDetails = empTaxDetails;
	}
	public List<EmpPayrollDetail> getEmpPayrollDetails() {
		return empPayrollDetails;
	}
	public void setEmpPayrollDetails(List<EmpPayrollDetail> empPayrollDetails) {
		this.empPayrollDetails = empPayrollDetails;
	}
	public Map<Integer, EmpTaxDetail> getDeletedEmpTaxMaps() {
		return deletedEmpTaxMaps;
	}
	public void setDeletedEmpTaxMaps(Map<Integer, EmpTaxDetail> deletedEmpTaxMaps) {
		this.deletedEmpTaxMaps = deletedEmpTaxMaps;
	}
	public Map<Integer, EmpPayrollDetail> getDeletedEmpPayrollDetails() {
		return deletedEmpPayrollDetails;
	}
	public void setDeletedEmpPayrollDetails(Map<Integer, EmpPayrollDetail> deletedEmpPayrollDetails) {
		this.deletedEmpPayrollDetails = deletedEmpPayrollDetails;
	}
	
}
