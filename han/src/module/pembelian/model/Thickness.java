package module.pembelian.model;

import module.util.ComboBoxProperties;

public class Thickness implements ComboBoxProperties{
	int id;
	double thickness;
	String thick;
	
	public Thickness(){
	}
	public Thickness(String thick){
		this.thick = thick;
	}
	
	public String getThick() {
		return thick;
	}
	public void setThick(String thick) {
		this.thick = thick;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getThickness() {
		return thickness;
	}
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}
	@Override
	public Object getField() {
		return thick;
	}
	
	
}
