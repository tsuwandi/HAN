package module.sn.uom.model;

import module.util.ComboBoxProperties;

public class Uom implements ComboBoxProperties {
	int id;
	String uom;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Uom(String uom) {
		this.uom = uom;
	}

	@Override
	public Object getField() {
		return uom;
	}
	
	public Uom() {

	}
}
