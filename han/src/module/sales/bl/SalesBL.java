package module.sales.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import controller.ServiceFactory;
import module.customer.dao.CustomerAddressDAO;
import module.customer.dao.CustomerDAO;
import module.customer.model.CustAddress;
import module.customer.model.Customer;
import module.pembelian.model.Product;
import module.product.model.Uom;
import module.sales.dao.SalesDAO;
import module.sales.dao.SalesDetailDAO;
import module.sales.model.Sales;
import module.sales.model.SalesDetail;
import module.sn.bank.dao.BankDAO;
import module.sn.bank.model.Bank;
import module.sn.city.dao.CityDAO;
import module.sn.city.model.City;
import module.sn.country.dao.CountryDAO;
import module.sn.country.model.Country;
import module.sn.currency.dao.CurrencyDAO;
import module.sn.currency.model.Currency;
import module.sn.province.dao.ProvinceDAO;
import module.sn.province.model.Province;
import module.sn.supptype.dao.SuppTypeDAO;
import module.sn.supptype.model.SuppType;
import module.sn.vehicletype.dao.VehicleTypeDAO;
import module.sn.vehicletype.model.VehicleType;
import module.supplier.dao.SuppAddressDAO;
import module.supplier.dao.SuppCpDAO;
import module.supplier.dao.SuppVehicleDAO;
import module.supplier.dao.SupplierDAO;
import module.supplier.model.SuppAddress;
import module.supplier.model.SuppCp;
import module.supplier.model.SuppVehicle;
import module.supplier.model.Supplier;

public class SalesBL {
	private DataSource dataSource;

	public SalesBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Bank> getAllBank() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new BankDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<City> getAllCityByProvinceId(int provinceId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new CityDAO(con).getAllByProvinceId(provinceId);
		} finally {
			con.close();
		}
	}

	public List<Currency> getAllCurrency() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new CurrencyDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<Province> getAllProvince() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProvinceDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<Country> getAllCountry() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new CountryDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<VehicleType> getAllVehicleType() throws SQLException {
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

	public int isCustCodeExists(String custCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new CustomerDAO(con).isCustCodeExists(custCode);
		} finally {
			con.close();
		}
	}

	public void save(Sales sales, List<SalesDetail> salesDetails) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			new SalesDAO(con).save(sales);

			for (SalesDetail s : salesDetails) {
				s.setSalesId(ServiceFactory.getSalesBL().getLatestIncrementSalesId());
				s = new SalesDetailDAO(con).save(s);
			}

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	/*
	 * public void update(Sales customer, List<CustAddress> custAddress,
	 * List<CustAddress> custAddressDeleted) throws SQLException { Connection con =
	 * null; try { con = dataSource.getConnection(); con.setAutoCommit(false);
	 * 
	 * new CustomerDAO(con).update(customer);
	 * 
	 * for (CustAddress s : custAddress) { if (s.getId() == 0) {
	 * s.setCustCode(customer.getCustCode()); s = new
	 * CustomerAddressDAO(con).save(s); } else { new
	 * CustomerAddressDAO(con).update(s); } }
	 * 
	 * for (CustAddress s : custAddressDeleted) { if (s.getId() != 0) { new
	 * CustomerAddressDAO(con).deleteById(s.getId()); } }
	 * 
	 * con.commit(); } catch (SQLException e) { con.rollback(); throw new
	 * SQLException(e.getMessage()); } finally { con.close(); } }
	 */

	/*
	 * public void deleteAll(Sales customer) throws SQLException { Connection con =
	 * null; try { con = dataSource.getConnection(); con.setAutoCommit(false);
	 * 
	 * new CustomerDAO(con).delete(customer.getId()); new
	 * CustomerAddressDAO(con).deleteAll(customer.getCustCode());
	 * 
	 * con.commit(); } catch (SQLException e) { con.rollback(); e.printStackTrace();
	 * throw new SQLException(e.getMessage()); } finally { con.close(); } }
	 */

	/*
	 * public Sales getCustomerById(int id) throws SQLException { Connection con =
	 * null; try { con = dataSource.getConnection(); return new
	 * CustomerDAO(con).getById(id); } finally { con.close(); } }
	 */

	public Integer getLatestIncrementSalesId() {
		Connection con = null;
		Integer latestInteger = 0;
		try {
			con = dataSource.getConnection();
			latestInteger = new SalesDAO(con).getLatestIncrementSalesId();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return latestInteger + 1;
	}

	public List<CustAddress> getCustAddressByCustCode(String custCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getAllByCustCode(custCode);
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

	public List<Sales> getAllSales() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<Sales> getAllSalesBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}

	public List<Sales> getAllSalesByAdvancedSearch(Sales sales) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getAllByAdvancedSearch(sales);
		} finally {
			con.close();
		}
	}

	public String getOrdinalOfCodeNumber(String suppTypeConstant) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();

			return String.format("%03d", new SupplierDAO(con).getOrdinalOfCodeNumber(suppTypeConstant) + 1);

		} finally {
			con.close();
		}
	}

	public Customer getCustomerByCode(String custCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getCustomerByCode(custCode);
		} finally {
			con.close();
		}
	}

	public List<CustAddress> getCustAddressByCustCode(int custCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getCustAddressByCustCode(custCode);
		} finally {
			con.close();
		}
	}

	public Product getProductByCode(String productCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getProductByCode(productCode);
		} finally {
			con.close();
		}
	}

	public Uom getUomByProductUomId(int productUomId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getUomByProductUomId(productUomId);
		} finally {
			con.close();
		}
	}
}
