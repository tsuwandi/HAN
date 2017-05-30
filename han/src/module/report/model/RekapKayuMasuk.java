package module.report.model;

import java.math.BigDecimal;
import java.util.Date;

public class RekapKayuMasuk {

	private String id;
	private Date date;
	private String ritNo;
	private String supplierName;
	private BigDecimal length;
	private BigDecimal width;
	private BigDecimal thickness;
	private BigDecimal totalGradeA;
	private BigDecimal volumeGradeA;
	private BigDecimal totalGradeB;
	private BigDecimal volumeGradeB;
	private BigDecimal totalAllGrade;
	private BigDecimal volumeAllGrade;
	private BigDecimal totalAval;
	private BigDecimal volumeAval;
	private BigDecimal total;
	private BigDecimal volume;
	private Date dateIn;
	private Date dateOut;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRitNo() {
		return ritNo;
	}

	public void setRitNo(String ritNo) {
		this.ritNo = ritNo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getThickness() {
		return thickness;
	}

	public void setThickness(BigDecimal thickness) {
		this.thickness = thickness;
	}

	public BigDecimal getTotalGradeA() {
		return totalGradeA;
	}

	public void setTotalGradeA(BigDecimal totalGradeA) {
		this.totalGradeA = totalGradeA;
	}

	public BigDecimal getVolumeGradeA() {
		return volumeGradeA;
	}

	public void setVolumeGradeA(BigDecimal volumeGradeA) {
		this.volumeGradeA = volumeGradeA;
	}

	public BigDecimal getTotalGradeB() {
		return totalGradeB;
	}

	public void setTotalGradeB(BigDecimal totalGradeB) {
		this.totalGradeB = totalGradeB;
	}

	public BigDecimal getVolumeGradeB() {
		return volumeGradeB;
	}

	public void setVolumeGradeB(BigDecimal volumeGradeB) {
		this.volumeGradeB = volumeGradeB;
	}

	public BigDecimal getTotalAllGrade() {
		return totalAllGrade;
	}

	public void setTotalAllGrade(BigDecimal totalAllGrade) {
		this.totalAllGrade = totalAllGrade;
	}

	public BigDecimal getVolumeAllGrade() {
		return volumeAllGrade;
	}

	public void setVolumeAllGrade(BigDecimal volumeAllGrade) {
		this.volumeAllGrade = volumeAllGrade;
	}

	public BigDecimal getTotalAval() {
		return totalAval;
	}

	public void setTotalAval(BigDecimal totalAval) {
		this.totalAval = totalAval;
	}

	public BigDecimal getVolumeAval() {
		return volumeAval;
	}

	public void setVolumeAval(BigDecimal volumeAval) {
		this.volumeAval = volumeAval;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}


}
