package cn.edu.nyist.jdbc.ex02;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import java.sql.Connection;

public class AntiSqlInject {
   /*
    *  1.输入一个数据库存在用户
    *  2.输入数据库不存在，例如‘or 1=1 or’这样用户名，可以成功---sql注入
    * 
    */
	//使用preparestatement预防sql注入
	public static void main(String[] args) {
		//1 接受用户输入
		String name =JOptionPane.showInputDialog("请输入用户名");
		String pwd =JOptionPane.showInputDialog("请输入密码");
		//2 到数据库查询
		Connection conn=null;
		//修改1：Statement stmt=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean ret=false;
		try {
			conn=JDBCUtile.getConn();
			//修改二：不要拼接字符串，用占位符法
			//String sql="select * from t_user where name='"+ name +"'and pwd='"+ pwd +"'";
			//？就是占位符，表示将来要用一个具体来代替该位置
			String sql="select * from t_user where name=? and pwd=?";
			System.out.println(sql);
			//stmt=conn.createStatement();
			stmt=conn.prepareStatement(sql);
			//对特殊字符转义
			stmt.setString(1, name);
			stmt.setString(2, pwd);
			//修改3：因为上面已经传入并为占位符赋值了，所以不要在传了
			//rs=stmt.executeQuery(sql);
			rs=stmt.executeQuery();
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
