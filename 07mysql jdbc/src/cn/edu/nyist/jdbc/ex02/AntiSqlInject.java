package cn.edu.nyist.jdbc.ex02;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import java.sql.Connection;

public class AntiSqlInject {
   /*
    *  1.����һ�����ݿ�����û�
    *  2.�������ݿⲻ���ڣ����确or 1=1 or�������û��������Գɹ�---sqlע��
    * 
    */
	//ʹ��preparestatementԤ��sqlע��
	public static void main(String[] args) {
		//1 �����û�����
		String name =JOptionPane.showInputDialog("�������û���");
		String pwd =JOptionPane.showInputDialog("����������");
		//2 �����ݿ��ѯ
		Connection conn=null;
		//�޸�1��Statement stmt=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean ret=false;
		try {
			conn=JDBCUtile.getConn();
			//�޸Ķ�����Ҫƴ���ַ�������ռλ����
			//String sql="select * from t_user where name='"+ name +"'and pwd='"+ pwd +"'";
			//������ռλ������ʾ����Ҫ��һ�������������λ��
			String sql="select * from t_user where name=? and pwd=?";
			System.out.println(sql);
			//stmt=conn.createStatement();
			stmt=conn.prepareStatement(sql);
			//�������ַ�ת��
			stmt.setString(1, name);
			stmt.setString(2, pwd);
			//�޸�3����Ϊ�����Ѿ����벢Ϊռλ����ֵ�ˣ����Բ�Ҫ�ڴ���
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
		//���ݲ�ѯ�����ʾ��¼�ɹ���ʧ��
		if(ret) {
			System.out.println("�Ϸ��û�");
		}else {
			System.out.println("�Ƿ��û�");
		}
	}

}
