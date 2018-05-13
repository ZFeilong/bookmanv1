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
			//��һ�����������
			stmt.setFloat(1, -10);
			//�ڶ������������
			stmt.registerOutParameter(2,Types.FLOAT);
			stmt.execute();
			//ȥ�ò���ֵ
			System.out.println(stmt.getFloat(2));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtile.free(rs, stmt, conn);
		}
	}
}
