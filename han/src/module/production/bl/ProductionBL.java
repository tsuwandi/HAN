package module.production.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.production.dao.GroupShiftDAO;
import module.production.dao.LineDAO;
import module.production.dao.MachineDAO;
import module.production.dao.ShiftDAO;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.Machine;
import module.production.model.Shift;

public class ProductionBL {
	private DataSource dataSource;
	private LineDAO lineDAO;
	private ShiftDAO shiftDAO;
	private GroupShiftDAO groupShiftDAO;
	private MachineDAO machineDAO;
	
	public ProductionBL(DataSource dataSource) {
		Connection con = null;
		this.dataSource = dataSource;
		try {
			con = dataSource.getConnection();
			shiftDAO = new ShiftDAO(con);
			lineDAO = new LineDAO(con);
			groupShiftDAO = new GroupShiftDAO(con);
			machineDAO = new MachineDAO(con);
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
}
