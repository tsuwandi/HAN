package module.report.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.dailyclosing.dao.InventoryLogDAO;
import module.dailyclosing.model.InventoryLog;
import module.pembelian.dao.GradeDAO;
import module.pembelian.dao.ReceivedDAO;
import module.pembelian.dao.ThicknessDAO;
import module.pembelian.model.Grade;
import module.pembelian.model.Received;
import module.pembelian.model.Thickness;
import module.report.dao.ReceivedReportDAO;
import module.report.dao.RekapKayuMasukDAO;
import module.report.model.DryStockFlow;
import module.report.model.ReceivedReport;
import module.report.model.RekapKayuMasuk;
import module.sn.woodtype.dao.WoodTypeDAO;
import module.sn.woodtype.model.WoodType;

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

	public List<InventoryLog> getAllDryStock(DryStockFlow dryStockFlow) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InventoryLogDAO(con).getAllDryStock(dryStockFlow);
		} finally {
			con.close();
		}
	}
	
	public List<ReceivedReport> getReceivedReport(String sql, List<Object> objects) throws SQLException{
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ReceivedReportDAO(con).getAll(sql, objects);
		} finally {
			con.close();
		}
	} 
	
	public List<RekapKayuMasuk> getAllPerincianPembelianBalken(RekapKayuMasuk rekapKayuMasuk) throws SQLException{
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new RekapKayuMasukDAO(con).getAllPerincianPembelianBalken(rekapKayuMasuk);
		} finally {
			con.close();
		}
	} 
	
	public List<RekapKayuMasuk> getAllRekapitulasiPembelianBalken(RekapKayuMasuk rekapKayuMasuk) throws SQLException{
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new RekapKayuMasukDAO(con).getAllRekapitulasiPembelianBalken(rekapKayuMasuk);
		} finally {
			con.close();
		}
	} 
}
