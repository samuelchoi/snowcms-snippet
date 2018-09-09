package org.snow.snippet.dbutil;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbUtilsTranExample {
	
	/* 数据源 */
	public static DataSource dataSource = null;
	
	/* 数据连接 */
	public static Connection connection = null;
	
	/* logger*/
	public static Logger LOGGER = LogManager.getLogger(DbUtilsTranExample.class);
	
	public static void main(String[] arg) throws SQLException {
		dataSource = setupDataSource();
		transfer("A","B",100);
	}

	public static void transfer(String sourceName,String targetName,float money) throws SQLException{
	    
		try {
	        connection = dataSource.getConnection();
	        
	        // 开启事务
	        connection.setAutoCommit(false);
	        
			// 在创建QueryRunner对象时，不传递数据源给它，是为了保证这两条SQL在同一个事务中进行，
	        // 我们手动获取数据库连接，然后让这两条 SQL 使用同一个数据库连接执行
	        QueryRunner runner = new QueryRunner();
	        String sql1 = "update account set money=money-100 where name=?";
	        String sql2 = "update account set money=money+100 where name=?";
	        Object[] paramArr1 = {sourceName};
	        Object[] paramArr2 = {targetName};
	        
	        // 第一笔交易
	        runner.update(connection,sql1,paramArr1);
	        // 模拟程序出现异常让事务回滚
	        int x = 1/0;
	        // 第二笔交易
	        runner.update(connection,sql2,paramArr2);
	        
	        connection.commit();
	    } catch (Exception e) {
	        LOGGER.error("dbutils tran exception",e);
	    	
	        if (connection!=null) {
	            connection.rollback();
	        }
	    } finally {
	        connection.close();
	    }
	}
	
	public static DataSource setupDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setUrl("jdbc:mysql://localhost:3306/jdbc-example");
        return ds;
    }	
}
