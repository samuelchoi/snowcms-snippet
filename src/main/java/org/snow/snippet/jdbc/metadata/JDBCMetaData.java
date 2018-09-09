package org.snow.snippet.jdbc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snow.snippet.dbutil.threadlocal.JDBCUtils;


public class JDBCMetaData {
	
	private static Logger logger = LogManager.getLogger(JDBCMetaData.class);
	
	public static void databaseMetaData() throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		DatabaseMetaData dbMetaData = conn.getMetaData();
		
		logger.info(dbMetaData.getURL());
		logger.info(dbMetaData.getDatabaseProductName());
		logger.info(dbMetaData.getDatabaseProductVersion());
		
		logger.info(dbMetaData.getSQLKeywords());
		logger.info(dbMetaData.getMaxColumnsInTable());
		logger.info(dbMetaData.getMaxConnections());

		ResultSet rs = dbMetaData.getClientInfoProperties();
		
		resultsetList(rs);
		JDBCUtils.close();
	}
	
	public static void statementMetaData() throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT * FROM USERS";
		PreparedStatement state = conn.prepareStatement(sql);
		ParameterMetaData paramterMetaData = state.getParameterMetaData();
		
		logger.info(paramterMetaData.getParameterCount());
		// logger.info(paramterMetaData.getParameterType(1));
		JDBCUtils.close();
	}
	

	private static void resultsetMetaData() throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT * FROM USERS";
		PreparedStatement state = conn.prepareStatement(sql);
		
		ResultSet rs = state.executeQuery();
		ResultSetMetaData rsMetaData= rs.getMetaData();
		String columnTypeName = rsMetaData.getColumnTypeName(2);
		logger.info(columnTypeName);
		
		JDBCUtils.close();
	}
	
	public static List resultsetList(ResultSet rs) throws SQLException {
		
		List<Map> rt = new ArrayList<Map>();
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int colCount = rsMetaData.getColumnCount();

		while (rs.next()) {
			for (int i = 0; i < colCount; i++) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				rowData.put(rsMetaData.getColumnName(i), rs.getObject(i));
				logger.info("{}:{}", rsMetaData.getColumnName(i), rs.getObject(i));
				rt.add(rowData);
			}
		}
		
		return rt;
	}

	public static void main(String[] args) {
		try {
			databaseMetaData();
			
			statementMetaData();
			
			resultsetMetaData();
		} catch (SQLException e) {
			logger.error("obtain meta data error", e);
		}
	}

}
