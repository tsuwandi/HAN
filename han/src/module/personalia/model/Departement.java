package module.personalia.model;

import java.util.Date;

public class Departement{

	String id;
	String name;
	String divisionId;
	Date input_date;
	String input_by;
	Date edit_date;
	String edit_by;
	Date delete_date;
	String delete_by;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public Date getInput_date() {
		return input_date;
	}
	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}
	public String getInput_by() {
		return input_by;
	}
	public void setInput_by(String input_by) {
		this.input_by = input_by;
	}
	public Date getEdit_date() {
		return edit_date;
	}
	public void setEdit_date(Date edit_date) {
		this.edit_date = edit_date;
	}
	public String getEdit_by() {
		return edit_by;
	}
	public void setEdit_by(String edit_by) {
		this.edit_by = edit_by;
	}
	public Date getDelete_date() {
		return delete_date;
	}
	public void setDelete_date(Date delete_date) {
		this.delete_date = delete_date;
	}
	public String getDelete_by() {
		return delete_by;
	}
	public void setDelete_by(String delete_by) {
		this.delete_by = delete_by;
	}	
}