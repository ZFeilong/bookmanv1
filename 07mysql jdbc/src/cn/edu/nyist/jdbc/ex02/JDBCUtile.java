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
	//读取属性文件
	static {
		pro= new Properties();
		try {
			pro.load(JDBCUtile.class.getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//在静态代码块中，在类加载执行
	static {
		/*
		 * java.sql.Driver接口要求实现类自己创建实例并向DriverManager注册
		 * 因而下面两次：
		 * 1.com.mysql.jdbc.Driver类加载时候注册一次
		 * 2我们有注册
		 * 
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");//反射的原理
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn() throws SQLException {
	//2 启动客户端--注册驱动（安装驱动）
			//META-INF/services/java.sql.Driver中查看
			//DriverManager.registerDriver(new Driver());
			//3连接到数据库
			String url=pro.getProperty("url");
			String user=pro.getProperty("user");
			String password=pro.getProperty("password");
			Connection conn=DriverManager.getConnection(url,user,password);
			return conn;
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
