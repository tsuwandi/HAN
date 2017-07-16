package module.sn.country.model;

import java.io.Serializable;

import module.util.ComboBoxProperties;

public class Country implements Serializable, ComboBoxProperties {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int idx;
	private String countryName;
	private String dateFormat;
	private String code;
	private String timeZones;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTimeZones() {
		return timeZones;
	}

	public void setTimeZones(String timeZones) {
		this.timeZones = timeZones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((dateFormat == null) ? 0 : dateFormat.hashCode());
		result = prime * result + id;
		result = prime * result + idx;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((timeZones == null) ? 0 : timeZones.hashCode());
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
		Country other = (Country) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (dateFormat == null) {
			if (other.dateFormat != null)
				return false;
		} else if (!dateFormat.equals(other.dateFormat))
			return false;
		if (id != other.id)
			return false;
		if (idx != other.idx)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (timeZones == null) {
			if (other.timeZones != null)
				return false;
		} else if (!timeZones.equals(other.timeZones))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", idx=" + idx + ", countryName=" + countryName
				+ ", dateFormat=" + dateFormat + ", code=" + code + ", timeZones=" + timeZones + "]";
	}

	@Override
	public Object getField() {
		return countryName;
	}
	
	public Country() {
	}
	
	public Country(String countryName) {
		this.countryName = countryName;
	}

}
