package cn.edu.nyist.jdbc.ex01;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class SqlInject {
   /*
    *  1.����һ�����ݿ�����û�
    *  2.�������ݿⲻ���ڣ����确or 1=1 or�������û��������Գɹ�---sqlע��
    * 
    */
	public static void main(String[] args) {
		//1 �����û�����
		String name =JOptionPane.showInputDialog("�������û���");
		String pwd =JOptionPane.showInputDialog("����������");
		//2 �����ݿ��ѯ
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
		//���ݲ�ѯ�����ʾ��¼�ɹ���ʧ��
		if(ret) {
			System.out.println("�Ϸ��û�");
		}else {
			System.out.println("�Ƿ��û�");
		}
	}

}
