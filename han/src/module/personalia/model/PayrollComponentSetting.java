package module.personalia.model;

import java.math.BigDecimal;

public class PayrollComponentSetting {

	PayrollComponent payrollComponent;
	BigDecimal nominal;
	public PayrollComponent getPayrollComponent() {
		return payrollComponent;
	}
	public void setPayrollComponent(PayrollComponent payrollComponent) {
		this.payrollComponent = payrollComponent;
	}
	public BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
	}
}
