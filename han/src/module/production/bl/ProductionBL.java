package module.production.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.production.dao.GroupShiftDAO;
import module.production.dao.LineDAO;
import module.production.dao.MachineDAO;
import module.production.dao.ProdRMDAO;
import module.production.dao.ProductionDAO;
import module.production.dao.ProductionResultDAO;
import module.production.dao.ProductionResultDetailDAO;
import module.production.dao.ShiftDAO;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.Machine;
import module.production.model.ProdRM;
import module.production.model.Production;
import module.production.model.ProductionResult;
import module.production.model.ProductionResultDetail;
import module.production.model.Shift;

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
	
	public String getProductionResultLastCode() throws SQLException{
		String lastCode = productionResultDAO.getLastCode();
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
	
	public List<Production> getProduction() throws SQLException {
		List<Production> productions = productionDAO.getAll();
		for (Production production : productions) {
			if(getProductionResultByCode(production.getProductionCode())!=null)production.setProductionResult(getProductionResultByCode(production.getProductionCode()));
			if(getProductRMByCode(production.getProductionCode())!=null)production.setListOfProdRM(getProductRMByCode(production.getProductionCode()));
		}
		return productions;
	}
	
	private ProductionResult getProductionResultByCode(String productionCode) throws SQLException {
		ProductionResult productionResult = productionResultDAO.getAllByProductionCode(productionCode);
		if(productionResult!=null)
			productionResult.setListOfProductionResultDetail(getProductionResultDetailByCode(productionResult.getProdResultCode()));
		return productionResult;
	}
	private List<ProductionResultDetail> getProductionResultDetailByCode(String prodResultCode) throws SQLException {
		return productionResultDetailDAO.getAllByProdResultCode(prodResultCode);
	}
	
	private List<ProdRM> getProductRMByCode(String productionCode)throws SQLException{
		return prodRMDAO.getAllByProductionCode(productionCode);
	}
	
	public List<ProdRM> getSearchProdRM(List<ProdRM> prodRMs)throws SQLException{
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
		return prodRMDAO.getAllSearch(sqlQuery.toString());				
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
			if(production.getProductionResult()!=null){
				new ProductionResultDAO(cone).save(production.getProductionResult());
				for(ProductionResultDetail prd : production.getProductionResult().getListOfProductionResultDetail()){
					new ProductionResultDetailDAO(cone).save(prd);
				}
				flagProductionResult=true;
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
			if(production.getProductionResult()!=null){
				if(getProductionResultByCode(production.getProductionCode())!=null)new ProductionResultDAO(cone).update(production.getProductionResult());
				else new ProductionResultDAO(cone).save(production.getProductionResult());
				new ProductionResultDetailDAO(cone).delete(production.getProductionResult().getProdResultCode());
				for(ProductionResultDetail prd : production.getProductionResult().getListOfProductionResultDetail()){
					new ProductionResultDetailDAO(cone).save(prd);
				}
				flagProductionResult=true;
			}
			if(production.getListOfProdRM()!=null){
				if(production.getListOfProdRM().size()!=0){
					new ProdRMDAO(cone).delete(production.getProductionCode());
					for(ProdRM prodRM :production.getListOfProdRM()){
						new ProdRMDAO(cone).save(prodRM);
					}
					flagProductionRawMaterial=true;
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
}
