package module.personalia.model;

import java.util.Date;

public class EmployeeR {
	private int id;
	private String empCode;
	private String name;
	private String npwp;
	private String currentAddress;
	private String currentZipCode;
	private String currentCity;
	private String ktp;
	private String ktpAddress;
	private String ktpZipCode;
	private int totalChild;
	private String bankCode;
	private String bankAcctNo;
	private String groupShiftID;
	private Date birthDate;
	private String emergencyContact;
	private String emergencyPhone;
	private String email;
	private String phone;
	private String genderID;
	private String positionID;
	private String maritalID;
	private String employeeStatusID;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getCurrentZipCode() {
		return currentZipCode;
	}
	public void setCurrentZipCode(String currentZipCode) {
		this.currentZipCode = currentZipCode;
	}
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public String getKtp() {
		return ktp;
	}
	public void setKtp(String ktp) {
		this.ktp = ktp;
	}
	public String getKtpAddress() {
		return ktpAddress;
	}
	public void setKtpAddress(String ktpAddress) {
		this.ktpAddress = ktpAddress;
	}
	public String getKtpZipCode() {
		return ktpZipCode;
	}
	public void setKtpZipCode(String ktpZipCode) {
		this.ktpZipCode = ktpZipCode;
	}
	public int getTotalChild() {
		return totalChild;
	}
	public void setTotalChild(int totalChild) {
		this.totalChild = totalChild;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankAcctNo() {
		return bankAcctNo;
	}
	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}
	
	public String getGroupShiftID() {
		return groupShiftID;
	}
	public void setGroupShiftID(String groupShiftID) {
		this.groupShiftID = groupShiftID;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public String getEmergencyPhone() {
		return emergencyPhone;
	}
	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGenderID() {
		return genderID;
	}
	public void setGenderID(String genderID) {
		this.genderID = genderID;
	}
	public String getPositionID() {
		return positionID;
	}
	public void setPositionID(String positionID) {
		this.positionID = positionID;
	}
	public String getMaritalID() {
		return maritalID;
	}
	public void setMaritalID(String maritalID) {
		this.maritalID = maritalID;
	}
	public String getEmployeeStatusID() {
		return employeeStatusID;
	}
	public void setEmployeeStatusID(String employeeStatusID) {
		this.employeeStatusID = employeeStatusID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankAcctNo == null) ? 0 : bankAcctNo.hashCode());
		result = prime * result + ((bankCode == null) ? 0 : bankCode.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((currentAddress == null) ? 0 : currentAddress.hashCode());
		result = prime * result + ((currentCity == null) ? 0 : currentCity.hashCode());
		result = prime * result + ((currentZipCode == null) ? 0 : currentZipCode.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emergencyContact == null) ? 0 : emergencyContact.hashCode());
		result = prime * result + ((emergencyPhone == null) ? 0 : emergencyPhone.hashCode());
		result = prime * result + ((empCode == null) ? 0 : empCode.hashCode());
		result = prime * result + ((employeeStatusID == null) ? 0 : employeeStatusID.hashCode());
		result = prime * result + ((genderID == null) ? 0 : genderID.hashCode());
		result = prime * result + ((groupShiftID == null) ? 0 : groupShiftID.hashCode());
		result = prime * result + id;
		result = prime * result + ((ktp == null) ? 0 : ktp.hashCode());
		result = prime * result + ((ktpAddress == null) ? 0 : ktpAddress.hashCode());
		result = prime * result + ((ktpZipCode == null) ? 0 : ktpZipCode.hashCode());
		result = prime * result + ((maritalID == null) ? 0 : maritalID.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((npwp == null) ? 0 : npwp.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((positionID == null) ? 0 : positionID.hashCode());
		result = prime * result + totalChild;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeR other = (EmployeeR) obj;
		if (bankAcctNo == null) {
			if (other.bankAcctNo != null)
				return false;
		} else if (!bankAcctNo.equals(other.bankAcctNo))
			return false;
		if (bankCode == null) {
			if (other.bankCode != null)
				return false;
		} else if (!bankCode.equals(other.bankCode))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (currentAddress == null) {
			if (other.currentAddress != null)
				return false;
		} else if (!currentAddress.equals(other.currentAddress))
			return false;
		if (currentCity == null) {
			if (other.currentCity != null)
				return false;
		} else if (!currentCity.equals(other.currentCity))
			return false;
		if (currentZipCode == null) {
			if (other.currentZipCode != null)
				return false;
		} else if (!currentZipCode.equals(other.currentZipCode))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emergencyContact == null) {
			if (other.emergencyContact != null)
				return false;
		} else if (!emergencyContact.equals(other.emergencyContact))
			return false;
		if (emergencyPhone == null) {
			if (other.emergencyPhone != null)
				return false;
		} else if (!emergencyPhone.equals(other.emergencyPhone))
			return false;
		if (empCode == null) {
			if (other.empCode != null)
				return false;
		} else if (!empCode.equals(other.empCode))
			return false;
		if (employeeStatusID == null) {
			if (other.employeeStatusID != null)
				return false;
		} else if (!employeeStatusID.equals(other.employeeStatusID))
			return false;
		if (genderID == null) {
			if (other.genderID != null)
				return false;
		} else if (!genderID.equals(other.genderID))
			return false;
		if (groupShiftID == null) {
			if (other.groupShiftID != null)
				return false;
		} else if (!groupShiftID.equals(other.groupShiftID))
			return false;
		if (id != other.id)
			return false;
		if (ktp == null) {
			if (other.ktp != null)
				return false;
		} else if (!ktp.equals(other.ktp))
			return false;
		if (ktpAddress == null) {
			if (other.ktpAddress != null)
				return false;
		} else if (!ktpAddress.equals(other.ktpAddress))
			return false;
		if (ktpZipCode == null) {
			if (other.ktpZipCode != null)
				return false;
		} else if (!ktpZipCode.equals(other.ktpZipCode))
			return false;
		if (maritalID == null) {
			if (other.maritalID != null)
				return false;
		} else if (!maritalID.equals(other.maritalID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (npwp == null) {
			if (other.npwp != null)
				return false;
		} else if (!npwp.equals(other.npwp))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (positionID == null) {
			if (other.positionID != null)
				return false;
		} else if (!positionID.equals(other.positionID))
			return false;
		if (totalChild != other.totalChild)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmployeeR [id=" + id + ", empCode=" + empCode + ", name=" + name + ", npwp=" + npwp
				+ ", currentAddress=" + currentAddress + ", currentZipCode=" + currentZipCode + ", currentCity="
				+ currentCity + ", ktp=" + ktp + ", ktpAddress=" + ktpAddress + ", ktpZipCode=" + ktpZipCode
				+ ", totalChild=" + totalChild + ", bankCode=" + bankCode + ", bankAcctNo=" + bankAcctNo
				+ ", groupShiftID=" + groupShiftID + ", birthDate=" + birthDate + ", emergencyContact="
				+ emergencyContact + ", emergencyPhone=" + emergencyPhone + ", email=" + email + ", phone=" + phone
				+ ", genderID=" + genderID + ", positionID=" + positionID + ", maritalID=" + maritalID
				+ ", employeeStatusID=" + employeeStatusID + "]";
	}
	
}
