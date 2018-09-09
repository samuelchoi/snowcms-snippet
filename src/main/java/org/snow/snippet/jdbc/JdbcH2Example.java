package org.snow.snippet.jdbc;

import java.sql.Connection;

import org.h2.jdbcx.JdbcConnectionPool;

public class JdbcH2Example {

	public static void main(String[] args) throws Exception {
		JdbcConnectionPool cp = JdbcConnectionPool.create(
		    "jdbc:h2:~/test", "sa", "sa");
		for (int i = 0; i < args.length; i++) {
		    Connection conn = cp.getConnection();
		    conn.createStatement().execute(args[i]);
		    conn.close();
		}
		cp.dispose();
	}
}
