package module.report.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.report.model.RekapKayuMasuk;

public class RekapKayuMasukDAO {
	private Connection connection;

	private PreparedStatement getAllPerincianPembelianBalkenStatement;
	private String getAllPerincianPembelianBalkenQuery = 
					"SELECT rcv.received_date,"
					+"  rcv.received_code,"
					+"  rcv.rit_no, "
					+"  MAX(confirm_date) AS 'TGL_EFEKTIV_LAPORAN',"
					+"  rcv.supplier_cp_id,cp.name AS 'supp_name',"
					+"  rcvdtl.id,"
					+"  pallet.length,"
					+"  pallet.width,"
					+"  pallet.thickness,"
					+"  pallet.product_code,"
					+"  SUM(pallet.total) AS 'TOTAL_BATANG',"
					+"  SUM(volume) AS 'TOTAL_VOLUME',"
					+"  prod.grade_id,"
					+"  grade.grade"
					+"  FROM received rcv"
					+"  LEFT JOIN received_detail rcvdtl ON rcvdtl.received_code = rcv.received_code"
					+"  LEFT JOIN pallet_Card pallet ON pallet.received_detail_id = rcvdtl.id"
					+"  LEFT JOIN product prod ON prod.product_code = pallet.product_code"
					+"  LEFT JOIN grade ON grade.id = prod.id"
					+"  LEFT JOIN supp_cp cp ON cp.id = rcv.supplier_cp_id"
					+"  WHERE rcv.deleted_date IS NULL"
					+"  AND rcv.confirm_date IS NOT NULL"
					+"  AND rcvdtl.deleted_date IS NULL"
					+"  AND pallet.deleted_date IS NULL"
					+"  AND prod.deleted_date IS NULL"
					+"  GROUP BY rcv.received_date,rcv.received_code,rcvdtl.id,"
					+"  pallet.length,pallet.width,pallet.thickness,pallet.product_code,prod.grade_id,"
					+"  grade.grade,cp.name,rcv.supplier_cp_id";
	
	private PreparedStatement getAllRekapitulasiPembelianBalkenStatement;
	private String getAllRekapitulasiPembelianBalkenQuery =
					"SELECT rcv.received_date,MAX(confirm_date) AS 'TGL_EFEKTIV_LAPORAN',"
					+" rcvdtl.id,"
					+" pallet.length,pallet.width,pallet.thickness,pallet.product_code,SUM(pallet.total) AS 'TOTAL_BATANG',SUM(volume) AS 'TOTAL_VOLUME',"
					+" prod.grade_id,"
					+" grade.grade"
					+" FROM received rcv"
					+" LEFT JOIN received_detail rcvdtl"
					+" ON rcvdtl.received_code = rcv.received_code"
					+" LEFT JOIN pallet_Card pallet"
					+" ON pallet.received_detail_id = rcvdtl.id"
					+" LEFT JOIN product prod"
					+" ON prod.product_code = pallet.product_code"
					+" LEFT JOIN grade"
					+" ON grade.id = prod.id"
					+" WHERE rcv.deleted_date IS NULL AND rcv.confirm_date IS NOT NULL"
					+" AND rcvdtl.deleted_date IS NULL"
					+" AND pallet.deleted_date IS NULL"
					+" AND prod.deleted_date IS NULL"
					+" GROUP BY rcv.received_date,rcvdtl.id,"
					+" pallet.length,pallet.width,pallet.thickness,pallet.product_code,prod.grade_id,"
					+" grade.grade";

	public RekapKayuMasukDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<RekapKayuMasuk> getAllPerincianPembelianBalken(RekapKayuMasuk pRekapKayuMasuk) throws SQLException {
		List<RekapKayuMasuk> listOfRekapKayuMasuk = new ArrayList<RekapKayuMasuk>();

		try {
			
;
			
			getAllPerincianPembelianBalkenStatement = connection.prepareStatement(getAllRekapitulasiPembelianBalkenQuery);

			//getAllStatement.setDate(1, DateUtil.toDate(pRekapKayuMasuk.getDateIn()));
			//getAllStatement.setDate(2, DateUtil.toDate(pRekapKayuMasuk.getDateOut()));

			ResultSet rs = getAllPerincianPembelianBalkenStatement.executeQuery();
			while (rs.next()) {
				RekapKayuMasuk rekapKayuMasuk = new RekapKayuMasuk();
				rekapKayuMasuk.setDate(rs.getDate("received_date"));
				rekapKayuMasuk.setRitNo(rs.getString("received_code"));
				rekapKayuMasuk.setSupplierName(rs.getString("supplier_name"));
				rekapKayuMasuk.setLength(rs.getBigDecimal("length"));
				rekapKayuMasuk.setWidth(rs.getBigDecimal("width"));
				rekapKayuMasuk.setThickness(rs.getBigDecimal("thickness"));
				rekapKayuMasuk.setVolume(rs.getBigDecimal("volume"));
				rekapKayuMasuk.setTotal(rs.getBigDecimal("total"));
				listOfRekapKayuMasuk.add(rekapKayuMasuk);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return listOfRekapKayuMasuk;
	}
	
	
	public List<RekapKayuMasuk> getAllRekapitulasiPembelianBalken(RekapKayuMasuk pRekapKayuMasuk) throws SQLException {
		List<RekapKayuMasuk> listOfRekapKayuMasuk = new ArrayList<RekapKayuMasuk>();

		try {
			getAllRekapitulasiPembelianBalkenStatement = connection.prepareStatement(getAllRekapitulasiPembelianBalkenQuery);

			//getAllStatement.setDate(1, DateUtil.toDate(pRekapKayuMasuk.getDateIn()));
			//getAllStatement.setDate(2, DateUtil.toDate(pRekapKayuMasuk.getDateOut()));

			ResultSet rs = getAllRekapitulasiPembelianBalkenStatement.executeQuery();
			while (rs.next()) {
				RekapKayuMasuk rekapKayuMasuk = new RekapKayuMasuk();
				rekapKayuMasuk.setDate(rs.getDate("TGL_EFEKTIV_LAPORAN"));
				rekapKayuMasuk.setLength(rs.getBigDecimal("length"));
				rekapKayuMasuk.setWidth(rs.getBigDecimal("width"));
				rekapKayuMasuk.setThickness(rs.getBigDecimal("thickness"));
				rekapKayuMasuk.setVolume(rs.getBigDecimal("TOTAL_VOLUME"));
				rekapKayuMasuk.setTotal(rs.getBigDecimal("TOTAL_BATANG"));
				listOfRekapKayuMasuk.add(rekapKayuMasuk);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return listOfRekapKayuMasuk;
	}
	
	private PreparedStatement getAllPerincianPembelianBarecoreStatement;
	private String getAllPerincianPembelianBarecoreQuery = 
			"SELECT "
			+"rcv.received_date,rcv.received_code,rcv.supplier_code,rcv.supplier_cp_id, "
			+"rcvdtl.id, pallet.pallet_card_code,pallet.length,pallet.width,pallet.thickness,pallet.total,pallet.volume, "
			+"pallet.product_code,cp.name AS 'supplier_name',grade.grade "
			+"FROM received rcv "
			+"LEFT JOIN received_detail rcvdtl "
			+"ON rcvdtl.received_code= rcv.received_code "
			+"LEFT JOIN pallet_card pallet "
			+"ON pallet.received_detail_id = rcvdtl.id "
			+"LEFT JOIN supp_cp cp  "
			+"ON cp.id=rcv.supplier_cp_id "
			+"LEFT JOIN grade "
			+"ON grade.id = rcvdtl.grade_id  "
			+"WHERE rcv.deleted_date IS NULL  "
			+"AND received_status='FINAL' "
			+"AND rcvdtl.deleted_date IS NULL "
			+"AND pallet.deleted_date IS NULL "
			+"ORDER BY received_date ";
	
	public List<RekapKayuMasuk> getAllPerincianPembelianBarecore(RekapKayuMasuk pRekapKayuMasuk) throws SQLException {
		List<RekapKayuMasuk> listOfRekapKayuMasuk = new ArrayList<RekapKayuMasuk>();

		try {

			getAllPerincianPembelianBarecoreStatement = connection.prepareStatement(getAllPerincianPembelianBarecoreQuery);

			//getAllStatement.setDate(1, DateUtil.toDate(pRekapKayuMasuk.getDateIn()));
			//getAllStatement.setDate(2, DateUtil.toDate(pRekapKayuMasuk.getDateOut()));

			ResultSet rs = getAllPerincianPembelianBarecoreStatement.executeQuery();
			BigDecimal total = new BigDecimal("0.00");
			BigDecimal volume = new BigDecimal("0.00");
			while (rs.next()) {
				RekapKayuMasuk rekapKayuMasuk = new RekapKayuMasuk();
				rekapKayuMasuk.setDate(rs.getDate("received_date"));
				rekapKayuMasuk.setRitNo(rs.getString("received_code"));
				rekapKayuMasuk.setSupplierName(rs.getString("supplier_name"));
				rekapKayuMasuk.setLength(rs.getBigDecimal("length"));
				rekapKayuMasuk.setWidth(rs.getBigDecimal("width"));
				rekapKayuMasuk.setThickness(rs.getBigDecimal("thickness"));
				
				String grade = rs.getString("grade");
				if(grade.equalsIgnoreCase("A"))
				{
					BigDecimal totalGrade = rs.getBigDecimal("total") == null ? new BigDecimal("0.00") : rs.getBigDecimal("total");
					BigDecimal volumeGrade = rs.getBigDecimal("volume") == null ? new BigDecimal("0.00") : rs.getBigDecimal("volume");
					
					rekapKayuMasuk.setVolumeGradeA(volumeGrade);
					rekapKayuMasuk.setTotalGradeA(totalGrade);
					
					total = total.add(totalGrade);
					volume = volume.add(volumeGrade);
				}
				else if(grade.equalsIgnoreCase("B"))
				{
					BigDecimal totalGrade = rs.getBigDecimal("total") == null ? new BigDecimal("0.00") : rs.getBigDecimal("total");
					BigDecimal volumeGrade = rs.getBigDecimal("volume") == null ? new BigDecimal("0.00") : rs.getBigDecimal("volume");
					
					rekapKayuMasuk.setVolumeGradeB(volumeGrade);
					rekapKayuMasuk.setTotalGradeB(totalGrade);
					
					total = total.add(totalGrade);
					volume = volume.add(volumeGrade);
				}
				else if(grade.equalsIgnoreCase("All"))
				{
					BigDecimal totalGrade = rs.getBigDecimal("total") == null ? new BigDecimal("0.00") : rs.getBigDecimal("total");
					BigDecimal volumeGrade = rs.getBigDecimal("volume") == null ? new BigDecimal("0.00") : rs.getBigDecimal("volume");
					
					rekapKayuMasuk.setVolumeAllGrade(volumeGrade);
					rekapKayuMasuk.setTotalAllGrade(totalGrade);
					
					total = total.add(totalGrade);
					volume = volume.add(volumeGrade);
				}
				else if(grade.equalsIgnoreCase("Afkir"))
				{
					BigDecimal totalGrade = rs.getBigDecimal("total") == null ? new BigDecimal("0.00") : rs.getBigDecimal("total");
					BigDecimal volumeGrade = rs.getBigDecimal("volume") == null ? new BigDecimal("0.00") : rs.getBigDecimal("volume");
					
					rekapKayuMasuk.setVolumeAval(volumeGrade);
					rekapKayuMasuk.setTotalAval(totalGrade);
					
					total = total.add(totalGrade);
					volume = volume.add(volumeGrade);
				}
				
				
				rekapKayuMasuk.setVolume(total);
				rekapKayuMasuk.setTotal(volume);
				listOfRekapKayuMasuk.add(rekapKayuMasuk);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return listOfRekapKayuMasuk;
	}

}
