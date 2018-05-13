package cn.edu.nyist.jdbc.diaoyongcunchuguochengex03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import cn.edu.nyist.jdbc.ex01.JDBCUtile;

public class FunTest {

	public static void main(String[] args) {
		//���÷���
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtile.getConn();
			//��һ����ʾ����ֵ
			String sql="{��=call upper(?)}";
			stmt=conn.prepareCall(sql);
			stmt.registerOutParameter(1,Types.VARCHAR);
			stmt.setString(2, "xiaobai");
			stmt.execute();
			System.out.println(stmt.getString(1));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtile.free(rs, stmt, conn);
		}
	}

	}


