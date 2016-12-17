package module.productionwaste.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import module.dryin.dao.DryInDAO;
import module.production.dao.GroupShiftDAO;
import module.production.dao.LineDAO;
import module.production.dao.ProdRMDAO;
import module.production.dao.ProductionResultDAO;
import module.production.dao.ProductionResultDetailDAO;
import module.production.dao.ProductionTypeDAO;
import module.production.dao.ShiftDAO;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProdRM;
import module.production.model.ProductionResult;
import module.production.model.ProductionResultProduct;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.productionwaste.dao.PWProductDAO;
import module.productionwaste.dao.ProductWasteResultDAO;
import module.productionwaste.dao.ProductionWasteDAO;
import module.productionwaste.dao.ProductionWasteResultProductDAO;
import module.productionwaste.model.PWProduct;
import module.productionwaste.model.ProductionResultProductWaste;
import module.productionwaste.model.ProductionResultWaste;
import module.productionwaste.model.ProductionWaste;

public class ProductionWasteBL  {
	private DataSource dataSource;
	
	ProductWasteResultDAO productionWasteResultDAO;
	ProductionWasteResultProductDAO productionWasteResultProductDAO;
	ProductionWasteDAO productionWasteDAO;
	
	public ProductionWasteBL(DataSource dataSource) {
		this.dataSource = dataSource;
		Connection con;
		try {
			con = dataSource.getConnection();
			productionWasteResultDAO = new ProductWasteResultDAO(con);
			productionWasteResultProductDAO = new ProductionWasteResultProductDAO(con);
			productionWasteDAO = new ProductionWasteDAO(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<GroupShift> getAllGroupShift() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new GroupShiftDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<Shift> getAllShift() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ShiftDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<Line> getAllLine() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new LineDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<ProductionType> getAllProductionType() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionTypeDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public ProductionWaste getProductionWasteById(int id) throws SQLException {
		return productionWasteDAO.getById(id);
	}
	
	public List<PWProduct> getPWProductByPwCode(String pwCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PWProductDAO(con).getAllByPwCode(pwCode);
		} finally {
			con.close();
		}
	}
	
	public List<ProductionResultWaste> getProductResultWasteByCode(String pwCode) throws SQLException{
		List<ProductionResultWaste> prodWasteResult = productionWasteResultDAO.getAllByCode(pwCode);
		if(prodWasteResult!=null){
			if(prodWasteResult.size()>0){
				for (ProductionResultWaste prodPKResult : prodWasteResult) {
					prodPKResult.setListProductionResultProduct(getProductResultWasteProduct(prodPKResult.getId()));
				}
				return prodWasteResult;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public int getLastProductWasteResultID() throws SQLException {
		return productionWasteResultDAO.getLastID()+1;
	}
	
	public List<ProductionResultProductWaste> getProductResultWasteProduct(int resultID) throws SQLException{
		return productionWasteResultProductDAO.getAllByProdPKResultID(resultID);
	}
	
	public List<ProductionWaste> getAllProductionWaste() throws SQLException {
		List<ProductionWaste> productionWastes =  productionWasteDAO.getAll();
		for (ProductionWaste productionWaste : productionWastes) {
			if(getProductResultWasteByCode(productionWaste.getPwCode())!=null)productionWaste.setProductionResultWastes(getProductResultWasteByCode(productionWaste.getPwCode()));
		}
		return productionWastes;
	}

	public List<ProductionWaste> getAllProductionWasteBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionWasteDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}
	
	public void save(ProductionWaste pw)
			throws SQLException {
		Connection con = null;
		boolean flagProductionResult=false;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			ProductWasteResultDAO productionWasteResultDAO = new ProductWasteResultDAO(con);
			ProductionWasteResultProductDAO productionWasteResultProductDAO = new ProductionWasteResultProductDAO(con);
			ProductionWasteDAO productionWasteDAO = new ProductionWasteDAO(con);
			if(pw.getProductionResultWastes()!=null){
				if(pw.getProductionResultWastes().size()!=0){
					int id = getLastProductWasteResultID();
					for (ProductionResultWaste prodWaste : pw.getProductionResultWastes()) {
						prodWaste.setId(id);
						prodWaste.setProdCode(pw.getPwCode());
						productionWasteResultDAO.save(prodWaste);
						for (ProductionResultProductWaste prodResultProduct : prodWaste.getListProductionResultProduct()) {
							prodResultProduct.setProdResultID(id);
							productionWasteResultProductDAO.save(prodResultProduct);
						}
						id++;
					}
					flagProductionResult=true;
				}
			}
			
			if(!flagProductionResult) pw.setStatus("INCOMPLETE");
			else pw.setStatus("COMPLETED");
			productionWasteDAO.save(pw);

			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public void update(ProductionWaste pw)
			throws SQLException {
		Connection con = null;
		ProductWasteResultDAO productionWasteResultDAO = new ProductWasteResultDAO(con);
		ProductionWasteResultProductDAO productionWasteResultProductDAO = new ProductionWasteResultProductDAO(con);
		ProductionWasteDAO productionWasteDAO = new ProductionWasteDAO(con);
		boolean flagProductionResult=false;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			if(pw.getProductionResultWastes()!=null){
				if(pw.getProductionResultWastes().size()!=0){
					int id = getLastProductWasteResultID();
					for (ProductionResultWaste prodWaste : pw.getProductionResultWastes()) {
						if(prodWaste.getId()==0){
							prodWaste.setId(id);
							prodWaste.setProdCode(pw.getPwCode());
							productionWasteResultDAO.save(prodWaste);
							for (ProductionResultProductWaste prodResultProduct : prodWaste.getListProductionResultProduct()) {
								prodResultProduct.setProdResultID(id);
								productionWasteResultProductDAO.save(prodResultProduct);
							}
							id++;
						}else{
							productionWasteResultDAO.update(prodWaste);
							for (ProductionResultProductWaste prodResultProduct : prodWaste.getListProductionResultProduct()) {
								productionWasteResultProductDAO.update(prodResultProduct);
							}
						}
					}
					flagProductionResult=true;
				}
			}
			if(pw.getDeletedProductResultWaste().size()!=0){
				for(ProductionResultWaste pr : pw.getDeletedProductResultWaste().values()){
					productionWasteResultDAO.delete(pr);
					for (ProductionResultProductWaste prp : pr.getListProductionResultProduct()) {
						productionWasteResultProductDAO.updateDelete(prp);
					}
				}
			}
			
			if(!flagProductionResult) pw.setStatus("INCOMPLETE");
			else pw.setStatus("COMPLETED");
			productionWasteDAO.save(pw);
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public void deleteAll(ProductionWaste pw) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			ProductWasteResultDAO productionWasteResultDAO = new ProductWasteResultDAO(con);
			ProductionWasteResultProductDAO productionWasteResultProductDAO = new ProductionWasteResultProductDAO(con);
			ProductionWasteDAO productionWasteDAO = new ProductionWasteDAO(con);
			if(pw.getProductionResultWastes()!=null){	
				if(pw.getProductionResultWastes().size()!=0){
					for (ProductionResultWaste prodWaste : pw.getProductionResultWastes()) {
						productionWasteResultDAO.delete(prodWaste);
						for (ProductionResultProductWaste prodResultProduct : prodWaste.getListProductionResultProduct()) {
							productionWasteResultProductDAO.updateDelete(prodResultProduct);
						}
					}
				}
			}
			productionWasteDAO.delete(pw.getId());
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public int isPWCodeExists(String pwCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionWasteDAO(con).isPWCodeExists(pwCode);
		} finally {
			con.close();
		}
	}
	
	public String getOrdinalOfCodeNumber(int year) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return String.format("%04d",
					new ProductionWasteDAO(con).getOrdinalOfCodeNumberByYear(year) + 1);

		} finally {
			con.close();
		}
	}
	
}
