package module.mastershift.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.mastershift.dao.MasterShiftDAO;
import module.mastershift.dao.MasterShiftDetailDAO;
import module.mastershift.model.MasterShift;
import module.mastershift.model.MasterShiftDetail;

public class MasterShiftBL {
	MasterShiftDAO masterShiftDAO;
	MasterShiftDetailDAO masterShiftDetailDAO;
	DataSource dataSource;
	
	public MasterShiftBL(DataSource dataSource) {
		Connection con = null;
		this.dataSource = dataSource;
		try {
			con = dataSource.getConnection();
			masterShiftDAO = new MasterShiftDAO(con);
			masterShiftDetailDAO = new MasterShiftDetailDAO(con);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public List<MasterShift> getMasterShift(){
		List<MasterShift> masterShifts = new ArrayList<>();
		try {
			masterShifts= masterShiftDAO.getAll();
			for (MasterShift masterShift : masterShifts) {
				if(getMasterShiftDetail(masterShift.getId())!=null)masterShift.setMasterShiftDetails(getMasterShiftDetail(masterShift.getId()));
			}
			return masterShifts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<MasterShiftDetail> getMasterShiftDetail(int id){
		try {
			return masterShiftDetailDAO.getAllByShiftID(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getLastShiftID(){
		try {
			return masterShiftDAO.getLastID()==0?1:masterShiftDAO.getLastID()+1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public String getLastShiftCode(){
		String lastCode=null;
		try {
			lastCode = masterShiftDAO.getLastCode();
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
		} catch (SQLException e) {
			e.printStackTrace();
			return lastCode;
		}
	}
	
	public void save(MasterShift masterShift){
		try {
			int id = getLastShiftID();
			masterShift.setId(id);
			for (MasterShiftDetail msd : masterShift.getMasterShiftDetails()) {
				msd.setShiftID(id);
				masterShiftDetailDAO.save(msd);
			}
			masterShiftDAO.save(masterShift);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(MasterShift masterShift){
		try {
			for (MasterShiftDetail msd : masterShift.getMasterShiftDetails()) {
				if(msd.getId()!=0){
					masterShiftDetailDAO.update(msd);
				}else{
					msd.setShiftID(masterShift.getId());
					masterShiftDetailDAO.save(msd);
				}
			}
			for (MasterShiftDetail msd : masterShift.getDeletedShiftDetails()) {
				masterShiftDetailDAO.delete(msd);
			}
			masterShiftDAO.update(masterShift);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(MasterShift masterShift){
		try {
			for (MasterShiftDetail msd : masterShift.getMasterShiftDetails()) {
				masterShiftDetailDAO.delete(msd);
			}
			masterShiftDAO.delete(masterShift);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
