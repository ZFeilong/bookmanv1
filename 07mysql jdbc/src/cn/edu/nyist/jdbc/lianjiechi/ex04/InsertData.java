package cn.edu.nyist.jdbc.lianjiechi.ex04;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class InsertData {

	public static void main(String[] args) throws SQLException {
		//2 �����ͻ���--ע����������װ������
		//META-INF/services/java.sql.Driver�в鿴
		DriverManager.registerDriver(new Driver());
		//3���ӵ����ݿ�
		String url="jdbc:mysql://localhost:3306/test";
		String user="root";
		String password="mysql";
		Connection conn=DriverManager.getConnection(url,user,password);
		//4����sql��ִ��sql
		//insert into t_user(name,pwd,age,birthday) values('xiaobai','666',23,'1999-9-9'); 
	   String sql="insert into t_user(name,pwd,age,birthday) values('xiaobai','666',23,'1999-9-9')";
	   //��������ȵ��ж���
	   Statement stmt=conn.createStatement();
	   int ret=stmt.executeUpdate(sql);
	   //5�鿴���
	   System.out.println(ret+"����Ӱ��");
	   //6 �رգ��ȸ���statement˵�����ٶϿ�����
	   stmt.close();
	   conn.close();
	}

}
