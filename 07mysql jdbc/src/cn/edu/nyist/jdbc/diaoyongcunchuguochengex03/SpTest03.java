package cn.edu.nyist.jdbc.diaoyongcunchuguochengex03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.nyist.jdbc.ex01.JDBCUtile;

public class SpTest03 {
//·½·¨¶þ
	public static void main(String[] args) {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtile.getConn();
			String sql="{call getUserById(?)}";
			stmt=conn.prepareCall(sql);
			stmt.setFloat(1,2);
			boolean hasResult=stmt.execute();
			if(hasResult) {
				rs=stmt.getResultSet();
				if(rs.next()) {
					System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getDate("birthday"));
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtile.free(rs, stmt, conn);
		}
	}
}
