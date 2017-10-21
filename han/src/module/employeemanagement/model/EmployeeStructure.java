package module.employeemanagement.model;

public class EmployeeStructure  {
	private int id;
	private String code;
	private int orgValueId;
	private int positionId;
	private String orgValue;
	private String position;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getOrgValueId() {
		return orgValueId;
	}
	public void setOrgValueId(int orgValueId) {
		this.orgValueId = orgValueId;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getOrgValue() {
		return orgValue;
	}
	public void setOrgValue(String orgValue) {
		this.orgValue = orgValue;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
