package module.production.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import module.production.dao.GroupShiftDAO;
import module.production.dao.LineDAO;
import module.production.dao.MachineDAO;
import module.production.dao.ProdRMDAO;
import module.production.dao.ProductionDAO;
import module.production.dao.ProductionResultDAO;
import module.production.dao.ProductionResultDetailDAO;
import module.production.dao.ProductionTypeDAO;
import module.production.dao.ShiftDAO;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.Machine;
import module.production.model.ProdRM;
import module.production.model.Production;
import module.production.model.ProductionResult;
import module.production.model.ProductionResultProduct;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.productionpk.dao.ProdPKResultDAO;
import module.productionpk.dao.ProdPKResultProductDAO;
import module.productionpk.model.ProdPKResult;
import module.productionpk.model.ProdPKResultProduct;

public class ProductionBL {
	private DataSource dataSource;
	private LineDAO lineDAO;
	private ShiftDAO shiftDAO;
	private GroupShiftDAO groupShiftDAO;
	private MachineDAO machineDAO;
	private ProdRMDAO prodRMDAO;
	private ProductionDAO productionDAO;
	private ProductionResultDAO productionResultDAO;
	private ProductionResultDetailDAO productionResultDetailDAO;
	private ProductionTypeDAO productionTypeDAO;
	
	public ProductionBL(DataSource dataSource) {
		Connection con = null;
		this.dataSource = dataSource;
		try {
			con = dataSource.getConnection();
			shiftDAO = new ShiftDAO(con);
			lineDAO = new LineDAO(con);
			groupShiftDAO = new GroupShiftDAO(con);
			machineDAO = new MachineDAO(con);
			prodRMDAO = new ProdRMDAO(con);
			productionDAO = new ProductionDAO(con);
			productionResultDAO = new ProductionResultDAO(con);
			productionResultDetailDAO = new ProductionResultDetailDAO(con);
			productionTypeDAO = new ProductionTypeDAO(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Line> getLine() throws SQLException {
		return lineDAO.getAll();
	}
	
	public List<Machine> getMachine() throws SQLException {
		return machineDAO.getAll();
	}
	
	public List<Shift> getShift() throws SQLException {
		return shiftDAO.getAll();
	}
	public List<GroupShift> getGroupShift() throws SQLException {
		return groupShiftDAO.getAll();
	}
	public List<ProductionType> getProductionType() throws SQLException {
		return productionTypeDAO.getAll();
	}
	
	public String getProductionLastCode() throws SQLException{
		String lastCode = productionDAO.getLastCode();
		if(lastCode==null){
			lastCode = ("0001");
		}else{
			String [] splittedCode = lastCode.split("/");
			int tempIntCode = Integer.valueOf(splittedCode[0])+1;
			String textTemp = String.valueOf(tempIntCode);
			if(textTemp.length()==1){
				lastCode ="000"+textTemp;
			}else if(textTemp.length()==2){
				lastCode="00"+textTemp;
			}else if(textTemp.length()==3){
				lastCode="0"+textTemp;
			}else{
				lastCode=textTemp;
			}
		}
		return lastCode;
	}
	
	public List<ProductionResult> getProductResult(String prodCode) throws SQLException{
		List<ProductionResult> prodPKResults = productionResultDAO.getAllByCode(prodCode);
		if(prodPKResults!=null){
			if(prodPKResults.size()>0){
				for (ProductionResult prodPKResult : prodPKResults) {
					prodPKResult.setListProductionResultProduct(getProductPKResultProduct(prodPKResult.getId()));
				}
				return prodPKResults;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public List<ProductionResultProduct> getProductPKResultProduct(int resultID) throws SQLException{
		return productionResultDetailDAO.getAllByProdPKResultID(resultID);
	}
	
	public List<Production> getProduction() throws SQLException {
		List<Production> productions = productionDAO.getAll();
		for (Production production : productions) {
			if(getProductResult(production.getProductionCode())!=null)production.setProductionResults(getProductResult(production.getProductionCode()));
			if(getProductRMByCode(production.getProductionCode())!=null)production.setListOfProdRM(getProductRMByCode(production.getProductionCode()));
		}
		return productions;
	}
	
	public int getLastProductPKResultID() throws SQLException {
		return productionResultDAO.getLastID()+1;
	}

	private List<ProdRM> getProductRMByCode(String productionCode)throws SQLException{
		return prodRMDAO.getAllByProductionCode(productionCode);
	}
	
	public List<ProdRM> getSearchProdRM(List<ProdRM> prodRMs,Map<String, ProdRM> deletedProdRms,String productionCode)throws SQLException{
		StringBuffer sqlQuery = new StringBuffer();
		if(prodRMs.size()!=0){
			sqlQuery.append(" AND b.pallet_card_code NOT IN (");
			int i = 0;
			for (ProdRM pr:prodRMs) {
				if(deletedProdRms.get(pr.getPalletCardCode())==null){
					if(i==0)sqlQuery.append("'"+pr.getPalletCardCode()+"'");
					else sqlQuery.append(",'"+pr.getPalletCardCode()+"'");
					i++;
				}
			}
			sqlQuery.append(") ");
		}
		
		return prodRMDAO.getAllSearch(sqlQuery.toString(),productionCode);				
	}
	
	public ProdRM getSearchProdRMByPalletCard(String palletCardCode,List<ProdRM> prodRMs)throws SQLException{
		StringBuffer sqlQuery = new StringBuffer();
		if(prodRMs.size()!=0){
			sqlQuery.append(" AND b.pallet_card_code NOT IN (");
			for (int i=0;i<prodRMs.size();i++) {
				ProdRM pr = prodRMs.get(i);
				if(i==0)sqlQuery.append("'"+pr.getPalletCardCode()+"'");
				else sqlQuery.append(",'"+pr.getPalletCardCode()+"'");
			}
			sqlQuery.append(") ");
		}
		return prodRMDAO.getProdRMByPalletCard(palletCardCode,sqlQuery.toString());				
	}
	
	public void saveAll(Production production)throws SQLException {
		Connection cone = null;
		boolean flagProductionResult=false;
		boolean flagProductionRawMaterial=false;
		try {
			cone = dataSource.getConnection();
			cone.setAutoCommit(false);
			if(production.getProductionResults()!=null){
				if(production.getProductionResults().size()!=0){
					int id = getLastProductPKResultID();
					for (ProductionResult prodPK : production.getProductionResults()) {
						prodPK.setId(id);
						prodPK.setProdCode(production.getProductionCode());
						new ProductionResultDAO(cone).save(prodPK);
						for (ProductionResultProduct prodResultProduct : prodPK.getListProductionResultProduct()) {
							prodResultProduct.setProdResultID(id);
							new ProductionResultDetailDAO(cone).save(prodResultProduct);
						}
						id++;
					}
					flagProductionResult=true;
				}
			}
			if(production.getListOfProdRM()!=null){
				if(production.getListOfProdRM().size()!=0){
					for(ProdRM prodRM :production.getListOfProdRM()){
						new ProdRMDAO(cone).save(prodRM);
					}
					flagProductionRawMaterial=true;
				}
				
			}
			if(flagProductionRawMaterial&&flagProductionResult)production.setStatus("Complete");	
			else production.setStatus("InComplete");
			new ProductionDAO(cone).save(production);
			cone.commit();
		} catch (Exception e) {
			cone.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}finally {
			cone.close();
		}
	}
	
	public void updateAll(Production production)throws SQLException {
		Connection cone = null;
		boolean flagProductionResult=false;
		boolean flagProductionRawMaterial=false;
		try {
			cone = dataSource.getConnection();
			cone.setAutoCommit(false);
			if(production.getProductionResults()!=null){
				ProductionResultDAO prd = new ProductionResultDAO(cone);
				ProductionResultDetailDAO prdd = new ProductionResultDetailDAO(cone);
				if(production.getProductionResults().size()!=0){
					int id = getLastProductPKResultID();
					for (ProductionResult prodPK : production.getProductionResults()) {
						if(prodPK.getId()==0){
							prodPK.setId(id);
							prodPK.setProdCode(production.getProductionCode());
							prd.save(prodPK);
							for (ProductionResultProduct prodResultProduct : prodPK.getListProductionResultProduct()) {
								prodResultProduct.setProdResultID(id);
								prdd.save(prodResultProduct);
							}
							id++;
						}else{
							prd.update(prodPK);
							for (ProductionResultProduct prodResultProduct : prodPK.getListProductionResultProduct()) {
								prdd.update(prodResultProduct);
							}
						}
						
					}
					flagProductionResult=true;
				}
				if(production.getDeletedProductionResult().size()!=0){
					for(ProductionResult pr : production.getDeletedProductionResult().values()){
						prd.delete(pr);
						System.out.println("Size PR :"+pr.getListProductionResultProduct().size());
						for (ProductionResultProduct prp : pr.getListProductionResultProduct()) {
							prdd.updateDelete(prp);
						}
					}
				}
			}
			if(production.getListOfProdRM()!=null){
				if(production.getListOfProdRM().size()!=0){
					List<ProdRM> prodRMs = prodRMDAO.getAllByProductionCode(production.getProductionCode());
					Map<String, ProdRM> tempMap = new HashMap<>();
					for (ProdRM prodRM : prodRMs) {
						tempMap.put(prodRM.getPalletCardCode(), prodRM);
					}
					for(ProdRM prodRM :production.getListOfProdRM()){
						if(tempMap.get(prodRM.getPalletCardCode())==null)new ProdRMDAO(cone).save(prodRM);
						
					}
					flagProductionRawMaterial=true;
				}
				if(production.getDeletedProdRMs().size()!=0){
					for (ProdRM prodRM : production.getDeletedProdRMs().values()) {
						new ProdRMDAO(cone).update(prodRM);
					}
				}
			}
			if(flagProductionRawMaterial&&flagProductionResult)production.setStatus("Complete");
			else production.setStatus("InComplete");
			new ProductionDAO(cone).update(production);
			cone.commit();
		} catch (Exception e) {
			cone.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}finally {
			cone.close();
		}
	}
	
	public List<Production> advancedSearchProduction(String sql, List<Object> objs) throws SQLException{
		return productionDAO.advancedSearchProduction(sql, objs);
	}
	
	public List<Production> searchProduction(String sql) throws SQLException{
		return productionDAO.getSearchAll(sql);
	}
	
	public void delete(Production production) throws SQLException{
		Connection cone = null;
		try {
			cone = dataSource.getConnection();
			cone.setAutoCommit(false);
			if(production.getProductionResults()!=null){
				ProductionResultDAO prd = new ProductionResultDAO(cone);
				ProductionResultDetailDAO prdd = new ProductionResultDetailDAO(cone);
				if(production.getProductionResults().size()!=0){
					for (ProductionResult prodPK : production.getProductionResults()) {
						prd.delete(prodPK);
						for (ProductionResultProduct prodResultProduct : prodPK.getListProductionResultProduct()) {
							prdd.updateDelete(prodResultProduct);
						}
					}
				}
			}
			if(production.getListOfProdRM()!=null){
				if(production.getListOfProdRM().size()!=0){
					ProdRMDAO prodRMDAO = new ProdRMDAO(cone);
					for(ProdRM prodRM :production.getListOfProdRM()){
						prodRMDAO.update(prodRM);
					}
				}
			}
			new ProductionDAO(cone).delete(production);
			cone.commit();
		} catch (Exception e) {
			cone.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}finally {
			cone.close();
		}
	}
}
