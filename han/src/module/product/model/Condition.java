package module.product.model;

import module.util.ComboBoxProperties;

public class Condition implements ComboBoxProperties {
	int id;
	String condition;

	public Condition() {
	}

	public Condition(String condition) {
		this.condition = condition;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public Object getField() {
		return condition;
	}

}
