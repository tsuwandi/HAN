package module.dryout.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import module.dryin.dao.PicTallyDAO;
import module.dryout.dao.DryOutDAO;
import module.dryout.dao.DryOutPalletDAO;
import module.dryout.model.DryOut;
import module.dryout.model.DryOutPallet;
import module.pembelian.dao.PalletCardDAO;
import module.pembelian.model.PalletCard;
import module.sn.chamber.dao.ChamberDAO;
import module.sn.chamber.model.Chamber;

public class DryOutBL {

	private DataSource dataSource;
	
	public DryOutBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<PalletCard> getAllPalletByChamberId(int chamberId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PalletCardDAO(con).getAllForDryOutPalletByChamberId(chamberId);
		} finally {
			con.close();
		}
	}

	public List<PalletCard> getAllPalletBySearchAndChamberId(String value, int chamberId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PalletCardDAO(con).getAllForDryOutPalletBySearchAndChamberId(value, chamberId);
		} finally {
			con.close();
		}
	}

	public String getOrdinalOfCodeNumber() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return String.format("%04d", new DryOutDAO(con).getOrdinalOfCodeNumberByYear(Calendar.getInstance().get(Calendar.YEAR)) + 1);
		} finally {
			con.close();
		}
	}

	public PalletCard getPalletByPalletCardCodeAndChamberId(String palletCardCode, int chamberId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new PalletCardDAO(con).getPalletForDryOutPalletByPalletCardCodeAndChamberId(palletCardCode, chamberId);
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

	public int isDryOutCodeExists(String dryOutCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryOutDAO(con).isDryOutCodeExists(dryOutCode);
		} finally {
			con.close();
		}
	}

	public void save(DryOut dryOut, List<DryOutPallet> listOfDryOutPallet) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			dryOut.setStatus(dryOut.STATUS_COMPLETED);
			
			new DryOutDAO(con).save(dryOut);

			for (DryOutPallet dop : listOfDryOutPallet) {
				dop.setDryOutCode(dryOut.getDryOutCode());
				new DryOutPalletDAO(con).save(dop);
			}

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}

	}

	public void update(DryOut dryOut, List<DryOutPallet> listOfDryOutPallet,
			List<DryOutPallet> listOfDeletedDryOutPallet) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new DryOutDAO(con).update(dryOut);

			for (DryOutPallet dip : listOfDryOutPallet) {
				if (dip.getId() == 0) {
					dip.setDryOutCode(dryOut.getDryOutCode());
					new DryOutPalletDAO(con).save(dip);
				} else {
					new DryOutPalletDAO(con).update(dip);
				}
			}

			for (DryOutPallet s : listOfDeletedDryOutPallet) {
				if (s.getId() != 0)
					new DryOutPalletDAO(con).deleteById(s.getId());
			}

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}

	}

	public List<DryOut> getAllDryOut() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryOutDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<DryOut> getAllDryOutBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryOutDAO(con).getAllDryOutBySimpleSearch(value);
		} finally {
			con.close();
		}
	}

	public void deleteAll(DryOut dryOut) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new DryOutDAO(con).delete(dryOut.getId());
			new PicTallyDAO(con).deleteAll(dryOut.getDryOutCode());
			new DryOutPalletDAO(con).deleteAll(dryOut.getDryOutCode());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public DryOut getDryOutById(Integer supplierId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryOutDAO(con).getById(supplierId);
		} finally {
			con.close();
		}
	}

	public List<DryOutPallet> getDryOutPalletByDryOutCode(String dryOutCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new DryOutPalletDAO(con).getAllByDryOutCode(dryOutCode);
		} finally {
			con.close();
		}
	}
}
