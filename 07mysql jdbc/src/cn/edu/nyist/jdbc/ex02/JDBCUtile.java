package cn.edu.nyist.jdbc.ex02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class JDBCUtile {
	private static Properties pro;
	//��ȡ�����ļ�
	static {
		pro= new Properties();
		try {
			pro.load(JDBCUtile.class.getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//�ھ�̬������У��������ִ��
	static {
		/*
		 * java.sql.Driver�ӿ�Ҫ��ʵ�����Լ�����ʵ������DriverManagerע��
		 * ����������Σ�
		 * 1.com.mysql.jdbc.Driver�����ʱ��ע��һ��
		 * 2������ע��
		 * 
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");//�����ԭ��
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn() throws SQLException {
	//2 �����ͻ���--ע����������װ������
			//META-INF/services/java.sql.Driver�в鿴
			//DriverManager.registerDriver(new Driver());
			//3���ӵ����ݿ�
			String url=pro.getProperty("url");
			String user=pro.getProperty("user");
			String password=pro.getProperty("password");
			Connection conn=DriverManager.getConnection(url,user,password);
			return conn;
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
