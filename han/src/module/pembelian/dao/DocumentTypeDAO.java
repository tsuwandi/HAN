package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.DocumentType;

public class DocumentTypeDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "SELECT id, document_type FROM document_type WHERE 1 = 1 ";

	public DocumentTypeDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}

	public List<DocumentType> getDocumentType() throws SQLException {
		Connection con = null;
		ArrayList<DocumentType> documentTypes = new ArrayList<DocumentType>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				DocumentType documentType = new DocumentType();
				documentType.setId(rs.getInt("id"));
				documentType.setDocumentType(rs.getString("document_type"));
				documentTypes.add(documentType);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return documentTypes;
	}
}