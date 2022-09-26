package myboard.util;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableCallableStatement;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import myboard.constants.DBCPConstants;

import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;

public class DBCPInitServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver() {
		try {
			Class.forName((String)DBCPConstants.props.get("JDBC_DRIVER_NAME"));
		} catch (ClassNotFoundException cnfe) {
			throw new RuntimeException("fail to load JDBC Driver", cnfe);
		}
	}
	
	private void initConnectionPool() {
		try {
			String JDBC_URL = (String)DBCPConstants.props.get("JDBC_URL");
			String JDBC_USER = (String)DBCPConstants.props.get("JDBC_USER");
			String JDBC_PASS = (String)DBCPConstants.props.get("JDBC_PATH");
			
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(JDBC_URL, JDBC_USER, JDBC_PASS);
			
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1");
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);
			
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName((String)DBCPConstants.props.get("JDBC_POOLING_DRIVER_NAME"));
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver((String)DBCPConstants.props.get("JDBC_POOLING_DRIVER"));
			driver.registerPool((String)DBCPConstants.props.get("JDBC_POOL_NAME"), connectionPool);
		} catch (Exception ex) {
			throw new RuntimeException(ex);

		}
	}
}
