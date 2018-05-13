package cn.edu.nyist.jdbc.diaoyongcunchuguochengex03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import cn.edu.nyist.jdbc.ex01.JDBCUtile;

public class SpTest01 {

	public static void main(String[] args) {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtile.getConn();
			String sql="{call abs1(?,?)}";
			stmt=conn.prepareCall(sql);
			//第一个是输入参数
			stmt.setFloat(1, -10);
			//第二个是输出参数
			stmt.registerOutParameter(2,Types.FLOAT);
			stmt.execute();
			//去得参数值
			System.out.println(stmt.getFloat(2));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtile.free(rs, stmt, conn);
		}
	}
}
