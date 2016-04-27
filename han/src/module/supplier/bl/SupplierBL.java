package module.supplier.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import module.sn.bank.dao.BankDAO;
import module.sn.city.dao.CityDAO;
import module.sn.currency.dao.CurrencyDAO;
import module.sn.province.dao.ProvinceDAO;
import module.sn.supptype.dao.SuppTypeDAO;
import module.sn.vehicletype.dao.VehicleTypeDAO;
import module.supplier.dao.SuppAddressDAO;
import module.supplier.dao.SuppCpDAO;
import module.supplier.dao.SuppVehicleDAO;
import module.supplier.dao.SupplierDAO;
import module.supplier.model.SuppAddress;
import module.supplier.model.SuppCp;
import module.supplier.model.SuppVehicle;
import module.supplier.model.Supplier;

public class SupplierBL {

	private DataSource dataSource;

	public SupplierBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public HashMap<String, Integer> getAllBank() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new BankDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public HashMap<String, Integer> getAllCityByProvinceId(int provinceId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new CityDAO(con).getAllByProvinceId(provinceId);
		} finally {
			con.close();
		}
	}

	public HashMap<String, Integer> getAllCurrency() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new CurrencyDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public HashMap<String, Integer> getAllProvince() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProvinceDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public HashMap<String, Integer> getAllSuppType() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SuppTypeDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public HashMap<String, Integer> getAllVehicleType() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new VehicleTypeDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public int isLicensePlateExists(String licensePlate) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SuppVehicleDAO(con).isLicensePlateExists(licensePlate);
		} finally {
			con.close();
		}
	}

	public int isSuppCodeExists(String suppCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SupplierDAO(con).isSuppCodeExists(suppCode);
		} finally {
			con.close();
		}
	}

	public void save(Supplier supplier, List<SuppAddress> suppAddress, List<SuppCp> suppCp,
			List<SuppVehicle> suppVehicle) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new SupplierDAO(con).save(supplier);

			for (SuppAddress s : suppAddress) {
				s.setSuppCode(supplier.getSuppCode());
				new SuppAddressDAO(con).save(s);
			}

			for (SuppCp s : suppCp) {
				s.setSuppCode(supplier.getSuppCode());
				new SuppCpDAO(con).save(s);
			}

			for (SuppVehicle s : suppVehicle) {
				s.setSuppCode(supplier.getSuppCode());
				new SuppVehicleDAO(con).save(s);
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

	public void update(Supplier supplier, List<SuppAddress> suppAddress, List<SuppAddress> suppAddressDeleted,
			List<SuppCp> suppCp, List<SuppCp> suppCpDeleted, List<SuppVehicle> suppVehicle,
			List<SuppVehicle> suppVehicleDeleted) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new SupplierDAO(con).update(supplier);

			for (SuppAddress s : suppAddress) {
				if (s.getId() == 0) {
					s.setSuppCode(supplier.getSuppCode());
					new SuppAddressDAO(con).save(s);
				} else {
					new SuppAddressDAO(con).update(s);
				}
			}

			for (SuppAddress s : suppAddressDeleted) {
				if (s.getId() != 0)
					new SuppAddressDAO(con).deleteById(s.getId());
			}

			for (SuppCp s : suppCp) {
				if (s.getId() == 0) {
					s.setSuppCode(supplier.getSuppCode());
					new SuppCpDAO(con).save(s);
				} else {
					new SuppCpDAO(con).update(s);
				}
			}

			for (SuppCp s : suppCpDeleted) {
				if (s.getId() != 0)
					new SuppCpDAO(con).deleteById(s.getId());
			}

			for (SuppVehicle s : suppVehicle) {
				if (s.getId() == 0) {
					s.setSuppCode(supplier.getSuppCode());
					new SuppVehicleDAO(con).save(s);
				} else {
					new SuppVehicleDAO(con).update(s);
				}
			}

			for (SuppVehicle s : suppVehicleDeleted) {
				if (s.getId() != 0)
					new SuppVehicleDAO(con).deleteById(s.getId());
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

	public void deleteAll(Supplier supplier) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new SupplierDAO(con).delete(supplier.getId());
			new SuppAddressDAO(con).deleteAll(supplier.getSuppCode());
			new SuppCpDAO(con).deleteAll(supplier.getSuppCode());
			new SuppVehicleDAO(con).deleteAll(supplier.getSuppCode());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public Supplier getSupplierById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SupplierDAO(con).getById(id);
		} finally {
			con.close();
		}
	}

	public List<SuppAddress> getSuppAddressBySuppCode(String suppCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SuppAddressDAO(con).getAllBySuppCode(suppCode);
		} finally {
			con.close();
		}
	}

	public List<SuppCp> getSuppCpBySuppCode(String suppCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SuppCpDAO(con).getAllBySuppCode(suppCode);
		} finally {
			con.close();
		}
	}

	public List<SuppVehicle> getSuppVehicleBySuppCode(String suppCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SuppVehicleDAO(con).getAllBySuppCode(suppCode);
		} finally {
			con.close();
		}
	}

	public List<Supplier> getAllSupplier() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SupplierDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<Supplier> getAllSupplierBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SupplierDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}
	
	public List<Supplier> getAllSupplierByAdvancedSearch(Supplier supplier) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SupplierDAO(con).getAllByAdvancedSearch(supplier);
		} finally {
			con.close();
		}
	}
}