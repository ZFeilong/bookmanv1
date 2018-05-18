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
