package module.report.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.sn.woodtype.dao.WoodTypeDAO;
import module.pembelian.model.Thickness;
import module.sn.woodtype.model.WoodType;
import module.pembelian.dao.GradeDAO;
import module.pembelian.dao.ThicknessDAO;
import module.pembelian.model.Grade;

public class ReportBL {

	private DataSource dataSource;

	public ReportBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<WoodType> getAllWoodType() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new WoodTypeDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<Grade> getAllGrade() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new GradeDAO(dataSource).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<Thickness> getAllThickness() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ThicknessDAO(dataSource).getThickness();
		} finally {
			con.close();
		}
	}
}
