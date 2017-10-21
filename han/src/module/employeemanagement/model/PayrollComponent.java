package module.employeemanagement.model;

public class PayrollComponent {
	private int id;
	private String code;
	private String description;
	private int isDaily;
	private int isSalary;
	private int isThr;
	private int isBonus;
	private String referenceDocument;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsDaily() {
		return isDaily;
	}
	public void setIsDaily(int isDaily) {
		this.isDaily = isDaily;
	}
	public int getIsSalary() {
		return isSalary;
	}
	public void setIsSalary(int isSalary) {
		this.isSalary = isSalary;
	}
	public int getIsThr() {
		return isThr;
	}
	public void setIsThr(int isThr) {
		this.isThr = isThr;
	}
	public int getIsBonus() {
		return isBonus;
	}
	public void setIsBonus(int isBonus) {
		this.isBonus = isBonus;
	}
	public String getReferenceDocument() {
		return referenceDocument;
	}
	public void setReferenceDocument(String referenceDocument) {
		this.referenceDocument = referenceDocument;
	}
	
	
}
