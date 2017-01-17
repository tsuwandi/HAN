package module.personalia.model;

import java.math.BigDecimal;

public class TaxComponentSetting {

	Tax tax;
	BigDecimal nominal;
	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}
	public BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
	}
}