package module.report.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import module.report.model.ReceivedReport;

public class ReceivedReportDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private StringBuffer queryGetAll = new StringBuffer("SELECT RCV.received_code,RCV.received_date ,RCV.supplier_cp_id ,RCV.delivery_note, ")
.append("RCV.WOOD_TYPE_ID, DELIVERY.DOC_ISSUED_DATE ")
.append(",DELIVERY.document_type, DELIVERY.wood_resource_id ")
.append(",delivery.wood_type_id, DELIVERY.total_log , DELIVERY.total_volume , ")
.append("WOODRSC.wood_resource, suppcp.supp_address_id, suppcp.name, ")
.append("addr.city , supp.supp_name , woodt.wood_type, ")
.append("genus.wood_genus , (SELECT SUM(DTL.TOTAL_VOLUME)  FROM RECEIVED_DETAIL DTL WHERE DTL.RECEIVED_CODE = RCV.RECEIVED_CODE ")
.append("GROUP BY DTL.RECEIVED_CODE ) AS volume_grader, (SELECT SUM(DTL.TOTAL_LOG)  FROM RECEIVED_DETAIL DTL WHERE DTL.RECEIVED_CODE = RCV.RECEIVED_CODE ")
.append("GROUP BY DTL.RECEIVED_CODE ) AS log_grader FROM RECEIVED RCV LEFT JOIN DELIVERY ")
.append("ON DELIVERY.received_code = RCV.RECEIVED_CODE LEFT JOIN WOOD_RESOURCE WOODRSC ON WOODRSC.ID = DELIVERY.wood_resource_id LEFT JOIN SUPP_CP suppcp ")
.append("ON suppcp.id=rcv.supplier_cp_id LEFT JOIN supp_address addr ON addr.id = suppcp.supp_address_id ")
.append("LEFT JOIN supplier supp ON supp.supp_code = rcv.supplier_code LEFT JOIN  wood_type woodt ")
.append("ON woodt.ID = delivery.wood_type_id LEFT JOIN wood_genus genus ON genus.id = woodt.wood_genus_id WHERE RCV.deleted_date IS NULL ");

	
	public ReceivedReportDAO(Connection connection) throws SQLException{
		this.connection = connection;
	}
	
	public List<ReceivedReport> getAll(String sql, List<Object> objects) throws SQLException{
		List<ReceivedReport> receivedReports = new ArrayList<>();
		try {
			System.out.println(queryGetAll.toString()+sql);
			getAllStatement = connection.prepareStatement(queryGetAll.toString() + sql);
			int i = 1;
			for (int j = 0; j < objects.size(); j++) {
				Object obj = objects.get(j);
				if (obj instanceof String) {
					if (obj != null) {
						getAllStatement.setString(i, (String) "%" + obj + "%");
						i++;
					}
				} else {
					if (obj != null) {
						System.out.println(" DATE "+ new Date(((java.util.Date) obj).getTime()));
						getAllStatement.setDate(i, new Date(((java.util.Date) obj).getTime()));
						i++;
					}
				}
			}
			System.out.println(getAllStatement);
			ResultSet rs = getAllStatement.executeQuery();
			while(rs.next()){
				ReceivedReport receivedReport = new ReceivedReport();
				receivedReport.setReceivedCode(rs.getString("RECEIVED_CODE"));
				receivedReport.setReceivedDate(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("RECEIVED_DATE")));
				receivedReport.setSupplierCPID(rs.getInt("rcv.supplier_cp_id"));
				receivedReport.setNoDocLegalitas(rs.getString("rcv.delivery_note"));
				receivedReport.setWoodTypeID(rs.getInt("rcv.wood_type_id"));
				receivedReport.setCreatedDate(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("delivery.doc_issued_date")));
				receivedReport.setDocLegalitas(rs.getString("delivery.document_type"));
				receivedReport.setWoodResourceID(rs.getInt("delivery.wood_resource_id"));
				receivedReport.setTotalLogSupplier(rs.getInt("delivery.total_log"));
				receivedReport.setTotalVolumeSupplier(rs.getDouble("delivery.total_volume"));
				receivedReport.setWoodResource(rs.getString("woodrsc.wood_resource"));
				receivedReport.setSuppAddressID(rs.getInt("suppcp.supp_address_id"));
				receivedReport.setNameOwner(rs.getString("suppcp.name"));
				receivedReport.setWoodDomicile(rs.getString("addr.city"));
				receivedReport.setSupplierName(rs.getString("supp.supp_name"));
				receivedReport.setWoodType(rs.getString("woodt.wood_type"));
				receivedReport.setWoodGenus(rs.getString("genus.wood_genus"));
				receivedReport.setTotalLogGrader(rs.getInt("log_grader"));
				receivedReport.setTotalVolumeGrader(rs.getDouble("volume_grader"));
				receivedReports.add(receivedReport);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
		return receivedReports;
	}
}
