package module.dryin.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import module.dryin.DryInType;
import module.dryin.dao.DryInDAO;
import module.dryin.dao.DryInPalletDAO;
import module.dryin.dao.PicTallyDAO;
import module.dryin.model.DryIn;
import module.dryin.model.DryInPallet;
import module.dryin.model.PicTally;
import module.employee.dao.EmployeeDAO;
import module.employee.model.Employee;
import module.pembelian.dao.PalletCardDAO;
import module.pembelian.model.PalletCard;
import module.sn.chamber.dao.ChamberDAO;
import module.sn.chamber.model.Chamber;

public class DryInBL {

	private DataSource dataSource;
	
	public DryInBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Employee> getAllEmployee() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAllForPicTallyDialog();
		} finally {
			con.close();
		}
	}

	public List<Employee> getAllEmployeeBySearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAllBySearchForPicTallyDialog(value);
		} finally {
			con.close();
		}
	}

	public List<PalletCard> getAllPallet() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PalletCardDAO(con).getAllForDryInPallet();
		} finally {
			con.close();
		}
	}

	public List<PalletCard> getAllPalletBySearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PalletCardDAO(con).getAllForDryInPalletBySearch(value);
		} finally {
			con.close();
		}
	}

	public String getOrdinalOfCodeNumber() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return String.format("%04d",
					new DryInDAO(con).getOrdinalOfCodeNumberByYear(Calendar.getInstance().get(Calendar.YEAR)) + 1);

		} finally {
			con.close();
		}
	}

	public PalletCard getPalletByPalletCardCode(String palletCardCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PalletCardDAO(con).getPalletForDryInPalletByPalletCardCode(palletCardCode);
		} finally {
			con.close();
		}
	}

	public List<Chamber> getAllChamber() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ChamberDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public int isDryInCodeExists(String dryInCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryInDAO(con).isDryInCodeExists(dryInCode);
		} finally {
			con.close();
		}
	}

	public void save(DryIn dryIn, List<DryInPallet> listOfDryInPallet) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			dryIn.setStatus(DryInType.COMPLETED.toString());
			
			new DryInDAO(con).save(dryIn);
			
			for (DryInPallet dip : listOfDryInPallet) {
				dip.setDryInCode(dryIn.getDryInCode());
				new DryInPalletDAO(con).save(dip);
			}

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}

	}

	public void update(DryIn dryIn, List<DryInPallet> listOfDryInPallet, List<DryInPallet> listOfDeletedDryInPallet)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new DryInDAO(con).update(dryIn);

			for (DryInPallet dip : listOfDryInPallet) {
				if (dip.getId() == 0) {
					dip.setDryInCode(dryIn.getDryInCode());
					new DryInPalletDAO(con).save(dip);
				} else {
					new DryInPalletDAO(con).update(dip);
				}
			}

			for (DryInPallet s : listOfDeletedDryInPallet) {
				if (s.getId() != 0)
					new DryInPalletDAO(con).deleteById(s.getId());
			}

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}

	}

	public List<DryIn> getAllDryIn() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryInDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<DryIn> getAllDryInBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryInDAO(con).getAllDryInBySimpleSearch(value);
		} finally {
			con.close();
		}
	}

	public void deleteAll(DryIn dryIn) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new DryInDAO(con).delete(dryIn.getId());
			new DryInPalletDAO(con).deleteAll(dryIn.getDryInCode());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public DryIn getDryInById(Integer dryInId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryInDAO(con).getById(dryInId);
		} finally {
			con.close();
		}
	}

	public List<PicTally> getPicTallyByDryInCode(String dryInCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PicTallyDAO(con).getAllByDryInCode(dryInCode);
		} finally {
			con.close();
		}
	}

	public List<DryInPallet> getDryInPalletByDryInCode(String dryInCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryInPalletDAO(con).getAllByDryInCode(dryInCode);
		} finally {
			con.close();
		}
	}
}
