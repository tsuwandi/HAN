package controller;

import java.sql.SQLException;

import dao.UserDao;
import module.employee.dao.EmployeeDao;
import module.product.dao.ProductDao;


/**
 * 
 * <p>
 * Description : Class to return Dao object
 * </p>
 * 
 * @author EOP
 * @version 1.0
 * 
 *          <table>
 *          <tr>
 *          <td colspan='4'>History of modification :</td>
 *          </tr>
 *          <tr>
 *          <td>Version</td>
 *          <td>Updated Date</td>
 *          <td>Updated By</td>
 *          <td>Description</td>
 *          </tr>
 *          <tr>
 *          <td>1.0</td>
 *          <td>24-Jan-2014</td>
 *          <td>EOP</td>
 *          <td>Draft coding</td>
 *          </tr>
 *          <tr>
 *          <td>2.0</td>
 *          <td>19-Mar-2014</td>
 *          <td>EOP</td>
 *          <td>Remove Service package</td>
 *          </tr>
 *          </table>
 *
 */
public class DaoFactory {

	/**
	 * Method to init or return {@link UserService}
	 * 
	 * @return {@link UserService}
	 */
	public static UserDao getUserDao() {
		try {
			return new UserDao(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static EmployeeDao getEmployeeDao() {
		try {
			return new EmployeeDao(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ProductDao getProductDao() {
		try {
			return new ProductDao(DataSourceFactory.getDataSource());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method to init or return {@link SupplierService}
	 * 
	 * @return {@link SupplierService}
	 */
//	public static SupplierDao getSupplierDao() {
//		try {
//			return new SupplierDao(DataSourceFactory.getDataSource());
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
