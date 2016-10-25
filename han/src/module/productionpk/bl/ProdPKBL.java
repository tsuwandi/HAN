package module.productionpk.bl;

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
import module.production.dao.ProductionTypeDAO;
import module.production.dao.ShiftDAO;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.Machine;
import module.production.model.ProdRM;
import module.production.model.Production;
import module.production.model.ProductionResultProduct;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.productionpk.dao.ProdPKDAO;
import module.productionpk.dao.ProdPKMaterialDAO;
import module.productionpk.dao.ProdPKResultDAO;
import module.productionpk.model.ProdPK;
import module.productionpk.model.ProdPKMaterial;

public class ProdPKBL {
	private DataSource dataSource;
	private LineDAO lineDAO;
	private ShiftDAO shiftDAO;
	private GroupShiftDAO groupShiftDAO;
	private ProdPKDAO prodPKDAO;
	private ProdPKMaterialDAO prodPKMaterialDAO;
	private ProdPKResultDAO prodPKResultDAO;
	
	public ProdPKBL(DataSource dataSource) {
		Connection con = null;
		this.dataSource = dataSource;
		try {
			con = dataSource.getConnection();
			shiftDAO = new ShiftDAO(con);
			lineDAO = new LineDAO(con);
			groupShiftDAO = new GroupShiftDAO(con);
			prodPKDAO = new ProdPKDAO(con);
			prodPKMaterialDAO = new ProdPKMaterialDAO(con);
			prodPKResultDAO = new ProdPKResultDAO(con);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Line> getLine() throws SQLException {
		return lineDAO.getAll();
	}
	
	public List<Shift> getShift() throws SQLException {
		return shiftDAO.getAll();
	}
	public List<GroupShift> getGroupShift() throws SQLException {
		return groupShiftDAO.getAll();
	}

	
	public String getProductionLastCode() throws SQLException{
		String lastCode = prodPKDAO.getLastCode();
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
	
	public List<ProdPK> getProduction() throws SQLException {
		List<ProdPK> productions = prodPKDAO.getAll();
		for (ProdPK production : productions) {
//			if(getProductionResultByCode(production.getProductionCode())!=null)production.setProductionResult(getProductionResultByCode(production.getProductionCode()));
			if(getProdPKMaterialByCode(production.getProdPKCode())!=null)production.setListPKMaterial(getProdPKMaterialByCode(production.getProdPKCode()));
		}
		return productions;
	}
	
	private List<ProdPKMaterial> getProdPKMaterialByCode(String prodPKCode)throws SQLException{
		return prodPKMaterialDAO.getAllByProdPKCode(prodPKCode);
	}
	
	public void saveAll(ProdPK production)throws SQLException {
		Connection cone = null;
		boolean flagProductionResult=false;
		boolean flagProductionRawMaterial=false;
		try {
			cone = dataSource.getConnection();
			cone.setAutoCommit(false);
//			if(ProdPK.getProductionResult()!=null){
//				new ProductionResultDAO(cone).save(production.getProductionResult());
//				for(ProductionResultProduct prd : production.getProductionResult().getListOfProductionResultDetail()){
//					new ProductionResultDetailDAO(cone).save(prd);
//				}
//				flagProductionResult=true;
//			}
			if(production.getListPKMaterial()!=null){
				if(production.getListPKMaterial().size()!=0){
					for(ProdPKMaterial prodPKMaterial :production.getListPKMaterial()){
						if(prodPKMaterial.getId()==0)new ProdPKMaterialDAO(cone).save(prodPKMaterial);
						else new ProdPKMaterialDAO(cone).update(prodPKMaterial);
					}
					flagProductionRawMaterial=true;
				}
				
			}
			if(flagProductionRawMaterial&&flagProductionResult)production.setStatus("Complete");	
			else production.setStatus("InComplete");
			new ProdPKDAO(cone).save(production);
			cone.commit();
		} catch (Exception e) {
			cone.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}finally {
			cone.close();
		}
	}
	
	public void updateAll(ProdPK production)throws SQLException {
		Connection cone = null;
		boolean flagProductionResult=false;
		boolean flagProductionRawMaterial=false;
		try {
			cone = dataSource.getConnection();
			cone.setAutoCommit(false);
//			if(production.getProductionResult()!=null){
//				if(getProductionResultByCode(production.getProductionCode())!=null)new ProductionResultDAO(cone).update(production.getProductionResult());
//				else new ProductionResultDAO(cone).save(production.getProductionResult());
//				new ProductionResultDetailDAO(cone).delete(production.getProductionResult().getProdResultCode());
//				for(ProductionResultProduct prd : production.getProductionResult().getListOfProductionResultDetail()){
//					new ProductionResultDetailDAO(cone).save(prd);
//				}
//				flagProductionResult=true;
//			}
			if(production.getListPKMaterial()!=null){
				if(production.getListPKMaterial().size()!=0){
					for(ProdPKMaterial prodPKMaterial :production.getListPKMaterial()){
						if(prodPKMaterial.getId()==0)new ProdPKMaterialDAO(cone).save(prodPKMaterial);
						else new ProdPKMaterialDAO(cone).update(prodPKMaterial);
					}
					flagProductionRawMaterial=true;
				}
			}
			if(flagProductionRawMaterial&&flagProductionResult)production.setStatus("Complete");
			else production.setStatus("InComplete");
			new ProdPKDAO(cone).update(production);
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
