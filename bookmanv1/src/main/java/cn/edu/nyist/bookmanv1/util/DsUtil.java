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
 * �������ӳز��õ�ʱ��Ҫ�ر�
 * ����--->webӦ��ֹͣ�ˣ�1��Ӧ�ñ��ӷ������Ƴ��� 2��������ֹͣ��
 * ��XXXִ��ʲô-->�¼����
 *   
 * 
 */
public static void close() {
	cds.close();
}
public static void free(Statement stmt,Connection conn) {
	//���Ȳ�����һ��try����Ϊstmtû�йرգ�conn��Ҫ�ر�
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
	//���Ȳ�����һ��try����Ϊstmtû�йرգ�conn��Ҫ�ر�
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
