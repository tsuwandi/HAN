package module.pembelian.model;

import module.util.ComboBoxProperties;

public class SupplierCP implements ComboBoxProperties {
	int id;
	String suppCode;
	int suppAddressId;
	String suppAddress;
	String name;
	String department;
	String phone;
	String email;
	
	public SupplierCP(){
		
	}
	
	public SupplierCP(String name){
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSuppCode() {
		return suppCode;
	}
	public void setSuppCode(String suppCode) {
		this.suppCode = suppCode;
	}
	public int getSuppAddressId() {
		return suppAddressId;
	}
	public void setSuppAddressId(int suppAddressId) {
		this.suppAddressId = suppAddressId;
	}
	public String getSuppAddress() {
		return suppAddress;
	}
	public void setSuppAddress(String suppAddress) {
		this.suppAddress = suppAddress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Object getField() {	
		return name;
	}
	
	
}
