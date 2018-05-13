package cn.edu.nyist.jdbc.ex01;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class SqlInject {
   /*
    *  1.输入一个数据库存在用户
    *  2.输入数据库不存在，例如‘or 1=1 or’这样用户名，可以成功---sql注入
    * 
    */
	public static void main(String[] args) {
		//1 接受用户输入
		String name =JOptionPane.showInputDialog("请输入用户名");
		String pwd =JOptionPane.showInputDialog("请输入密码");
		//2 到数据库查询
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		boolean ret=false;
		try {
			conn=(Connection) JDBCUtile.getConn();
			String sql="select * from t_user where name='"+ name +"'and pwd='"+ pwd +"'";
			System.out.println(sql);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				ret=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtile.free(rs, stmt, conn);
		}
		//根据查询结果显示登录成功和失败
		if(ret) {
			System.out.println("合法用户");
		}else {
			System.out.println("非法用户");
		}
	}

}
