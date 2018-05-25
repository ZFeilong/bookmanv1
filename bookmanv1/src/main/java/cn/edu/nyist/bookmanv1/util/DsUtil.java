package cn.edu.nyist.bookmanv1.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DsUtil {
private static ComboPooledDataSource cds=new ComboPooledDataSource();
public static Connection getConn() throws SQLException {
	return cds.getConnection();
}
/*
 * 我们连接池不用的时候，要关闭
 * 不用--->web应用停止了：1：应用被从服务器移除了 2：服务器停止了
 * 当XXX执行什么-->事件编程
 *   
 * 
 */
public static void close() {
	cds.close();
}
public static void free(Statement stmt,Connection conn) {
	//首先不能是一个try，因为stmt没有关闭，conn仍要关闭
	if(stmt !=null) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	if(conn !=null) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
public static void free(ResultSet rs,Statement stmt,Connection conn) {
	//首先不能是一个try，因为stmt没有关闭，conn仍要关闭
	if(rs !=null) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	if(stmt !=null) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	if(conn !=null) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
  }
}
