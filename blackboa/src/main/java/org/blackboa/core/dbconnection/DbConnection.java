
package org.blackboa.core.dbconnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConnection {
	// private static final String JDBC_URL ="jdbc:oracle:thin:@192.168.146.130:1521:orcl";
	// private static final String USER = "RISK";
	// private static final String PASSWORD = "181818";
	// private static final String DRIVER_CLASS = "oracle.jdbc.OracleDriver";

	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	// 获取连接
	private static Connection getConnections(String driver, String url, String user, String pwd) throws Exception {
		Connection conn = null;
		Properties props = new Properties();
		props.put("remarksReporting", "true");
		props.put("user", user);
		props.put("password", pwd);
		Class.forName(driver);
		conn = DriverManager.getConnection(url, props);
		return conn;
	}

	@Bean(value = "conn")
	public Connection conn() throws Exception {
		// return getConnections(DRIVER_CLASS, JDBC_URL, USER, PASSWORD);
		return getConnections(driverClassName, url, username, password);
	}

	@Bean(value = "dbmd")
	public DatabaseMetaData dbmd() throws Exception {
		return this.conn().getMetaData();
	}

	@Bean(value = "catalog")
	public String catalog() throws Exception {
		return this.conn().getCatalog();
	}
}
