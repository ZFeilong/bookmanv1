package cn.edu.nyist.jdbc.lianjiechi.ex04;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class InsertData {

	public static void main(String[] args) throws SQLException {
		//2 启动客户端--注册驱动（安装驱动）
		//META-INF/services/java.sql.Driver中查看
		DriverManager.registerDriver(new Driver());
		//3连接到数据库
		String url="jdbc:mysql://localhost:3306/test";
		String user="root";
		String password="mysql";
		Connection conn=DriverManager.getConnection(url,user,password);
		//4输入sql，执行sql
		//insert into t_user(name,pwd,age,birthday) values('xiaobai','666',23,'1999-9-9'); 
	   String sql="insert into t_user(name,pwd,age,birthday) values('xiaobai','666',23,'1999-9-9')";
	   //发送语句先得有对象
	   Statement stmt=conn.createStatement();
	   int ret=stmt.executeUpdate(sql);
	   //5查看结果
	   System.out.println(ret+"行受影响");
	   //6 关闭：先告诉statement说话，再断开连接
	   stmt.close();
	   conn.close();
	}

}
