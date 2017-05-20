package module.dailyclosing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.packingresult.model.PackingRM;
import module.packingresult.model.PackingResult;
import module.pembelian.model.Received;
import module.prodpk.model.ProdPKMaterial;
import module.prodpk.model.ProdPKResultProduct;
import module.production.model.ProdRM;
import module.production.model.ProductionResultProduct;
import module.productionwaste.model.ProductionResultProductWaste;
import module.purchaseprodresult.model.PPRProduct;
import module.util.DateUtil;

public class ProductionDailyClosingDAO {

	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement updateStatement;
	
	public ProductionDailyClosingDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	private PreparedStatement updateDailyClosingStatement;
	
	public void updateDailyClosingByCode(String code, String status, String query) throws SQLException {
		try {
			updateDailyClosingStatement = connection.prepareStatement(query);
			
			updateDailyClosingStatement.setDate(1, DateUtil.getCurrentDate());
			updateDailyClosingStatement.setString(2, status);
			updateDailyClosingStatement.setDate(3, DateUtil.getCurrentDate());
			updateDailyClosingStatement.setString(4, ServiceFactory.getSystemBL().getUsernameActive());
			updateDailyClosingStatement.setString(5, code);
			updateDailyClosingStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void updateDailyClosingById(int id, String status, String query) throws SQLException {
		try {
			updateDailyClosingStatement = connection.prepareStatement(query);
			
			updateDailyClosingStatement.setDate(1, DateUtil.getCurrentDate());
			updateDailyClosingStatement.setString(2, status);
			updateDailyClosingStatement.setDate(3, DateUtil.getCurrentDate());
			updateDailyClosingStatement.setString(4, ServiceFactory.getSystemBL().getUsernameActive());
			updateDailyClosingStatement.setInt(5, id);
			updateDailyClosingStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	/** START PROD_RM **/
	public static final String PROD_RM_QUERY = "update production set confirm_date=?, status=?, edited_date=?, edited_by=? where production_code=? ";
	
	public List<ProdRM> getAllProdRM() throws SQLException {
		List<ProdRM> prodRMs = new ArrayList<ProdRM>();
		
		String query = new StringBuilder()
			.append(" SELECT prodrm.id, prodrm.production_code, prodrm.pallet_card_code, palletcard.total, palletcard.product_code FROM prod_rm prodrm")
			.append(" INNER JOIN pallet_card palletcard on prodrm.pallet_card_code = palletcard.pallet_card_code")
			.append(" INNER JOIN production prod on prod.production_code = prodrm.production_code")
			.append(" WHERE prod.confirm_date IS NULL AND prod.deleted_date is NULL AND prodrm.deleted_date is NULL AND palletcard.deleted_date is NULL")
			.append(" AND prodrm.input_date <= CURDATE()").toString();
		
		getAllStatement = connection.prepareStatement(query);
		
		ResultSet rs = getAllStatement.executeQuery();
		while (rs.next()) {
			ProdRM prodRM = new ProdRM();
			prodRM.setId(rs.getInt("id"));
			prodRM.setProductionCode(rs.getString("production_code"));
			prodRM.setPalletCardCode(rs.getString("pallet_card_code"));
			prodRM.setTotal(rs.getBigDecimal("total"));
			prodRM.setProductCode(rs.getString("product_code"));
			prodRMs.add(prodRM);
		}
		
		return prodRMs;
	} 
	/** END PROD_RM **/
	
	/** START PROD_RESULT_PRODUCT **/
	public static final String PROD_RESULT_PRODUCT_QUERY = "update production set confirm_date=?, status=?, edited_date=?, edited_by=? where production_code=? ";
	
	public List<ProductionResultProduct> getAllProdResult() throws SQLException {
		
		List<ProductionResultProduct> productionResultProductResults = new ArrayList<ProductionResultProduct>();
		String query = new StringBuilder()
				.append(" SELECT prp.id, prp.prod_result_id, prp.product_code, prp.qty, pr.prod_code as production_code FROM prod_result_product prp")
				.append(" INNER JOIN prod_result pr on prp.prod_result_id = pr.id")
				.append(" INNER JOIN production prod on prod.production_code = pr.prod_code")
				.append(" WHERE prod.confirm_date is null and pr.deleted_date is null and prp.deleted_date is null")
				.append(" AND prp.input_date <= CURDATE()").toString();
		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionResultProduct productionResultProduct = new ProductionResultProduct();
				productionResultProduct.setId(rs.getInt("id"));
				productionResultProduct.setProdResultID(rs.getInt("prod_result_id"));
				productionResultProduct.setProductCode(rs.getString("product_code"));
				productionResultProduct.setQty(rs.getDouble("qty"));
				productionResultProduct.setProductionCode(rs.getString("production_code"));
				productionResultProductResults.add(productionResultProduct);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productionResultProductResults;
	}
	/** END PROD_RESULT_PRODUCT **/
	
	/** START PROD_WASTE_RESULT **/
	public static final String PROD_WASTE_RESULT_QUERY = "update prod_waste_result set confirm_date=?, status=?, edited_date=?, edited_by=? where pw_code=? ";
	
	public List<ProductionResultProductWaste> getAllProdWasteResult() throws SQLException {
		
		List<ProductionResultProductWaste> productionResultProductWastes = new ArrayList<ProductionResultProductWaste>();
		String query = new StringBuilder()
				.append(" SELECT prp.id, prp.prod_waste_result_id, prp.product_code, prp.qty, pr.pw_code FROM prod_waste_result_product prp")
				.append(" INNER JOIN prod_waste_result pr on prp.prod_waste_result_id = pr.id")
				.append(" WHERE pr.confirm_date is null and pr.deleted_date is null and prp.deleted_date is null")
				.append(" AND prp.input_date <= CURDATE()").toString();
		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionResultProductWaste productionResultProductWaste = new ProductionResultProductWaste();
				productionResultProductWaste.setId(rs.getInt("id"));
				productionResultProductWaste.setProdWasteResultId(rs.getInt("prod_waste_result_id"));
				productionResultProductWaste.setProductCode(rs.getString("product_code"));
				productionResultProductWaste.setQty(rs.getDouble("qty"));
				productionResultProductWaste.setPwCode(rs.getString("pw_code"));
				productionResultProductWastes.add(productionResultProductWaste);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productionResultProductWastes;
	}
	/** END PROD_WASTE_RESULT **/
	
	/** START PROD_PK_MATERIAL **/
	public static final String PROD_PK_MATERIAL_QUERY = "update prod_pk set confirm_date=?, status=?, edited_date=?, edited_by=? where prod_pk_code=? ";
	
	public List<ProdPKMaterial> getAllProdPKMaterial() throws SQLException {
		
		List<ProdPKMaterial> prodPKMaterials = new ArrayList<ProdPKMaterial>();
		String query = new StringBuilder()
				.append(" SELECT prp.id, prp.prod_pk_code, prp.product_code, prp.qty FROM prod_pk_material prp")
				.append(" INNER JOIN prod_pk pr on prp.prod_pk_code = pr.prod_pk_code")
				.append(" WHERE pr.confirm_date is null and pr.deleted_date is null and prp.deleted_date is null")
				.append(" AND prp.input_date <= CURDATE()").toString();
		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProdPKMaterial prodPKMaterial = new ProdPKMaterial();
				prodPKMaterial.setId(rs.getInt("id"));
				prodPKMaterial.setProdPKCode(rs.getString("prod_pk_code"));
				prodPKMaterial.setProductCode(rs.getString("product_code"));
				prodPKMaterial.setQty(rs.getBigDecimal("qty"));
				prodPKMaterials.add(prodPKMaterial);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodPKMaterials;
	}
	/** END PROD_PK_MATERIAL **/
	
	/** START PROD_PK_RESULT_PRODUCT **/
	public static final String PROD_PK_RESULT_PRODUCT_QUERY = "update prod_pk set confirm_date=?, status=?, edited_date=?, edited_by=? where prod_pk_code=? ";
	
	public List<ProdPKResultProduct> getAllProdPKResultProduct() throws SQLException {
		
		List<ProdPKResultProduct> prodPKResultProducts = new ArrayList<ProdPKResultProduct>();
		String query = new StringBuilder()
				.append(" SELECT prp.id, prp.prod_pk_code, prp.product_code, prp.qty FROM prod_pk_result_product prp")
				.append(" INNER JOIN prod_pk pr on prp.prod_pk_code = pr.prod_pk_code")
				.append(" WHERE pr.confirm_date is null and pr.deleted_date is null and prp.deleted_date is null")
				.append(" AND prp.input_date <= CURDATE()").toString();
		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProdPKResultProduct prodPKResultProduct = new ProdPKResultProduct();
				prodPKResultProduct.setId(rs.getInt("id"));
				prodPKResultProduct.setProdPKCode(rs.getString("prod_pk_code"));
				prodPKResultProduct.setProductCode(rs.getString("product_code"));
				prodPKResultProduct.setQty(rs.getBigDecimal("qty"));
				prodPKResultProducts.add(prodPKResultProduct);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodPKResultProducts;
	}
	/** END PROD_PK_RESULT_PRODUCT **/
	
	/** START PPR_PRODUCT **/
	public static final String PPR_PRODUCT_QUERY = "update purchase_prod_result set confirm_date=?, status=?, edited_date=?, edited_by=? where ppr_code=? ";
	
	public List<PPRProduct> getAllPPRProduct() throws SQLException {
		
		List<PPRProduct> pPRProducts = new ArrayList<PPRProduct>();
		String query = new StringBuilder()
				.append(" SELECT prp.id, prp.ppr_code, prp.product_code, prp.qty FROM ppr_product prp")
				.append(" INNER JOIN purchase_prod_result pr on prp.ppr_code = pr.ppr_code")
				.append(" WHERE pr.confirm_date is null and pr.deleted_date is null and prp.deleted_date is null")
				.append(" AND prp.input_date <= CURDATE()").toString();
		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PPRProduct pPRProduct = new PPRProduct();
				pPRProduct.setId(rs.getInt("id"));
				pPRProduct.setPprCode(rs.getString("ppr_code"));
				pPRProduct.setProductCode(rs.getString("product_code"));
				pPRProduct.setQty(rs.getBigDecimal("qty"));
				pPRProducts.add(pPRProduct);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return pPRProducts;
	}
	/** END PPR_PRODUCT **/
	
	/** START PACKING_RM **/
	public static final String PACKING_RM_QUERY = "update packing set confirm_date=?, status=?, edited_date=?, edited_by=? where id=? ";
	
	public List<PackingRM> getAllPackingRM() throws SQLException {
		
		List<PackingRM> packingRMs = new ArrayList<PackingRM>();
		String query = new StringBuilder()
				.append(" SELECT prp.id, prp.packing_id, prp.product_code, prp.qty FROM packing_rm prp")
				.append(" INNER JOIN packing pr on prp.packing_id = pr.id")
				.append(" WHERE pr.confirm_date is null and pr.deleted_date is null and prp.deleted_date is null")
				.append(" AND prp.input_date <= CURDATE()").toString();
		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PackingRM packingRM = new PackingRM();
				packingRM.setId(rs.getInt("id"));
				packingRM.setPackingID(rs.getInt("packing_id"));
				packingRM.setProductCode(rs.getString("product_code"));
				packingRM.setQty(rs.getDouble("qty"));
				packingRMs.add(packingRM);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return packingRMs;
	}
	/** END PPR_PRODUCT **/
	
	/** START PACKING_RM **/
	public static final String PACKING_RESULT_QUERY = "update packing set confirm_date=?, status=?, edited_date=?, edited_by=? where id=? ";
	
	public List<PackingResult> getAllPackingResult() throws SQLException {
		
		List<PackingResult> packingResults = new ArrayList<PackingResult>();
		String query = new StringBuilder()
				.append(" SELECT prp.id, prp.packing_id, prp.product_code, prp.qty FROM packing_result prp")
				.append(" INNER JOIN packing pr on prp.packing_id = pr.id")
				.append(" WHERE pr.confirm_date is null and pr.deleted_date is null and prp.deleted_date is null")
				.append(" AND prp.input_date <= CURDATE()").toString();
		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PackingResult packingResult = new PackingResult();
				packingResult.setId(rs.getInt("id"));
				packingResult.setPackingID(rs.getInt("packing_id"));
				packingResult.setProductCode(rs.getString("product_code"));
				packingResult.setQty(rs.getDouble("qty"));
				packingResults.add(packingResult);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return packingResults;
	}
	/** END PPR_PRODUCT **/
}
