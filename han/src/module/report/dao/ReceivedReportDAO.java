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
	private String queryGetAll = "SELECT A.RECEIVED_DATE, C.SUPP_NAME, F.PRODUCT_NAME, B.WOOD_TYPE, "
			+ "G.GRADE, E.LENGTH, E.WIDTH, E.THICKNESS, E.VOLUME FROM RECEIVED A "
			+ "INNER JOIN WOOD_TYPE B ON A.WOOD_TYPE_ID = B.ID "
			+ "INNER JOIN SUPPLIER C ON A.SUPPLIER_CODE = C.SUPP_CODE "
			+ "INNER JOIN RECEIVED_DETAIL D ON A.RECEIVED_CODE = D.RECEIVED_CODE "
			+ "INNER JOIN PALLET_CARD E ON D.ID = E.RECEIVED_DETAIL_ID "
			+ "INNER JOIN PRODUCT F ON E.PRODUCT_CODE = F.PRODUCT_CODE "
			+ "INNER JOIN GRADE G ON D.GRADE_ID = G.ID WHERE 1=1";
	
	public ReceivedReportDAO(Connection connection) throws SQLException{
		this.connection = connection;
	}
	
	public List<ReceivedReport> getAll(String sql, List<Object> objects) throws SQLException{
		List<ReceivedReport> receivedReports = new ArrayList<>();
		try {
			getAllStatement = connection.prepareStatement(queryGetAll + sql);
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
						getAllStatement.setDate(i, new Date(((java.util.Date) obj).getTime()));
						i++;
					}
				}
			}
			ResultSet rs = getAllStatement.executeQuery();
			while(rs.next()){
				ReceivedReport receivedReport = new ReceivedReport();
				receivedReport.setReceivedDate(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("RECEIVED_DATE")));
				receivedReport.setSupplierName(rs.getString("SUPP_NAME"));
				receivedReport.setProductName(rs.getString("PRODUCT_NAME"));
				receivedReport.setWoodType(rs.getString("WOOD_TYPE"));
				receivedReport.setGrade(rs.getString("GRADE"));
				receivedReport.setLength(rs.getDouble("LENGTH"));
				receivedReport.setWidth(rs.getDouble("WIDTH"));
				receivedReport.setThick(rs.getDouble("THICKNESS"));
				receivedReport.setVolume(rs.getDouble("VOLUME"));
				receivedReports.add(receivedReport);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
		return receivedReports;
	}
}
