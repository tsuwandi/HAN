package module.sales.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import controller.ServiceFactory;
import module.customer.dao.CustomerDAO;
import module.customer.model.CustAddress;
import module.customer.model.Customer;
import module.dailyclosing.bl.DailyClosingBL;
import module.dailyclosing.dao.ConfirmDAO;
import module.dailyclosing.model.Confirm;
import module.pembelian.model.Product;
import module.sn.uom.model.Uom;
import module.sales.dao.SalesDAO;
import module.sales.dao.SalesDetailDAO;
import module.sales.dao.SalesInsuranceDetailDAO;
import module.sales.dao.SalesShipmentDetailDAO;
import module.sales.model.Sales;
import module.sales.model.SalesDetail;
import module.sales.model.SalesInsuranceDetail;
import module.sales.model.ShipmentSalesOrder;
import module.sn.bank.dao.BankDAO;
import module.sn.bank.model.Bank;
import module.sn.bank.model.BankCust;
import module.sn.city.dao.CityDAO;
import module.sn.city.model.City;
import module.sn.country.dao.CountryDAO;
import module.sn.country.model.Country;
import module.sn.currency.dao.CurrencyDAO;
import module.sn.currency.model.Currency;
import module.sn.insurance.dao.InsuranceDAO;
import module.sn.insurance.model.Insurance;
import module.sn.province.dao.ProvinceDAO;
import module.sn.province.model.Province;
import module.sn.shipment.dao.ShipmentDAO;
import module.sn.shipment.model.Shipment;
import module.sn.vehicletype.dao.VehicleTypeDAO;
import module.sn.vehicletype.model.VehicleType;
import module.util.DateUtil;

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

	public List<Insurance> getAllInsurance() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new InsuranceDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<Shipment> getAllShipment() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ShipmentDAO(con).getAll();
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

	public int isCustCodeExists(String custCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new CustomerDAO(con).isCustCodeExists(custCode);
		} finally {
			con.close();
		}
	}

	public void save(Sales sales, List<SalesDetail> salesDetails, List<SalesInsuranceDetail> salesInsuranceDetails,
			List<ShipmentSalesOrder> salesShipmentDetails) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			new SalesDAO(con).save(sales);

			for (SalesDetail s : salesDetails) {
				s.setSalesId(ServiceFactory.getSalesBL().getLatestIncrementSalesId());
				s = new SalesDetailDAO(con).save(s);
			}

			for (SalesInsuranceDetail s : salesInsuranceDetails) {
				s.setSalesId(ServiceFactory.getSalesBL().getLatestIncrementSalesId());
				s = new SalesInsuranceDetailDAO(con).save(s);
			}

			for (ShipmentSalesOrder s : salesShipmentDetails) {
				s.setSalesOrderId(ServiceFactory.getSalesBL().getLatestIncrementSalesId());
				s = new SalesShipmentDetailDAO(con).save(s);
			}
			
			Confirm confirm = new Confirm();
			confirm.setConfirmCode(DailyClosingBL.makeConfirmCode());
			confirm.setModule("Sales");
			confirm.setDailyClosingDate(null);
			new ConfirmDAO(con).save(confirm);

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public void update(Sales sales, List<SalesDetail> salesDetail, List<SalesDetail> salesDetailDeleted,
			List<ShipmentSalesOrder> shipmentSalesDetail, List<ShipmentSalesOrder> shipmentSalesDetailDeleted, List<SalesInsuranceDetail> insuranceSalesDetail,
			List<SalesInsuranceDetail> insuranceSalesDetailDeleted) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new SalesDAO(con).update(sales);

			for (SalesDetail s : salesDetail) {
				if (s.getId() == 0) {
					s.setSalesId(sales.getId());
					s = new SalesDetailDAO(con).save(s);
				} else {
					new SalesDetailDAO(con).update(s);
				}
			}

			for (SalesDetail s : salesDetailDeleted) {
				if (s.getId() != 0) {
					new SalesDetailDAO(con).deleteById(s.getId());
				}
			}
			
			for (ShipmentSalesOrder s : shipmentSalesDetail) {
				if (s.getId() == 0) {
					s.setSalesOrderId(sales.getId());
					s = new SalesShipmentDetailDAO(con).save(s);
				} else {
					new SalesShipmentDetailDAO(con).update(s);
				}
			}

			for (ShipmentSalesOrder s : shipmentSalesDetailDeleted) {
				if (s.getId() != 0) {
					new SalesShipmentDetailDAO(con).deleteById(s.getId());
				}
			}
			
			for (SalesInsuranceDetail s : insuranceSalesDetail) {
				if (s.getId() == 0) {
					s.setSalesId(sales.getId());
					s = new SalesInsuranceDetailDAO(con).save(s);
				} else {
					new SalesInsuranceDetailDAO(con).update(s);
				}
			}

			for (SalesDetail s : salesDetailDeleted) {
				if (s.getId() != 0) {
					new SalesDetailDAO(con).deleteById(s.getId());
				}
			}

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public void deleteAll(Sales sales) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new SalesDAO(con).delete(sales.getId());
			new SalesDetailDAO(con).deleteAll(sales.getId());
			new SalesShipmentDetailDAO(con).deleteAll(sales.getId());
			new SalesInsuranceDetailDAO(con).deleteAll(sales.getId());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public Sales getSalesById(int id) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getById(id);
		} finally {
			con.close();
		}
	}

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

	public List<BankCust> getBankCustByCustCode(String custCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getAllBankCustByCustCode(custCode);
		} finally {
			con.close();
		}
	}

	public List<SalesDetail> getSalesDetailBySalesId(int salesId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDetailDAO(con).getAllBySalesId(salesId);
		} finally {
			con.close();
		}
	}

	public List<ShipmentSalesOrder> getSalesShipmentDetailBySalesId(int salesId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesShipmentDetailDAO(con).getAllBySalesId(salesId);
		} finally {
			con.close();
		}
	}

	public List<SalesInsuranceDetail> getSalesInsuranceDetailBySalesId(int salesId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesInsuranceDetailDAO(con).getAllBySalesId(salesId);
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

	public List<Product> getAllProduct() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getAllProduct();
		} finally {
			con.close();
		}
	}

	public List<Product> getSearchProduct(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new SalesDAO(con).getAllProductBySimpleSearch(value);
		} finally {
			con.close();
		}
	}
}
