package controller;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

/**
 * 
 * <p>Description : Class to produce datasource</p>
 * 
 * @author EOP
 * @version 1.0
 * 
 * <table>
 * 	<tr><td colspan='4'>History of modification : </td></tr>
 * 	<tr><td>Version</td><td>Updated Date</td><td>Updated By</td><td>Description</td></tr>
 * 	<tr><td>1.0</td><td>24-Jan-2014</td><td>EOP</td><td>Draft coding</td></tr>
 * </table>
 *
 */
public class DataSourceFactory {

	private static Properties props = null;
	private static Logger logger = Logger.getLogger(DataSourceFactory.class);
	private static DataSource dataSource;
	
	/**
	 * Method to setup dataSource
	 * @return DataSource
	 * @throws Exception
	 */
	private static DataSource setUpDataSource() throws Exception {
		
    	String driver = getProperty("jdbc.driver");
    	String url = getProperty("jdbc.url");
    	String username = getProperty("jdbc.username");
    	String password = getProperty("jdbc.password");
    	
		Class.forName(driver).newInstance();
		
		GenericObjectPool connectionPool = new GenericObjectPool();
		connectionPool.setMaxActive(5);
		connectionPool.setMinIdle(1);
		connectionPool.setMaxWait(10000);

		ConnectionFactory cf = new DriverManagerConnectionFactory(
				url, username,password);

		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf,
				connectionPool, null, null, false, true);

		return new PoolingDataSource(connectionPool);
	}
	
	/**
	 * Method to return dataSource
	 * @return DataSource
	 */
	public static DataSource getDataSource() {
		if (dataSource == null) {
			try {
				dataSource = setUpDataSource();
			} catch (Exception e) {				
				e.printStackTrace();
				logger.error("Failed to get datasource : " + e.getCause());
			}
		}
		return dataSource;
	}
	
	/**
	 * Method to read property on file
	 * @param key String as a key on properties file
	 * @return String
	 */
	private static String getProperty(String key) {
		try {
			if (props == null) {
				InputStream input = DataSourceFactory.class
				.getResourceAsStream("/jdbc.properties");
				props = new Properties();
				props.load(input);
			}
			return props.getProperty(key);
		} catch (Exception e) {
			logger.error("Failed to read properties file : " + e.getCause());
			return e.getMessage();
		}
	}	

}
