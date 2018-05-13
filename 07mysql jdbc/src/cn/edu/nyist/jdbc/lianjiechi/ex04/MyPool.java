package cn.edu.nyist.jdbc.lianjiechi.ex04;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;
public class MyPool implements DataSource {
//实际项目是多线程并发要考虑线程安全
	private static List<Connection> pool=Collections.synchronizedList(new ArrayList<>());
	static {
		for(int i=1;i<=4;i++) {
			try {
				pool.add(JDBCUtile.getConn());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		if(pool.size()>0) {
			Connection conn=pool.remove(0);//被代理
			return (Connection) Proxy.newProxyInstance(null, new Class[] {Connection.class}, new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					System.out.println("--------------");
					System.out.println(method);
					if(method.getName().equals("close")) {
						System.out.println("放回池中了");
						pool.add(conn);
					}else {
						return method.invoke(conn, args);
					}
					return null;
				}
			});
		}
		return null;
	}

	@Override
	public Connection getConnection(String arg0, String arg1) throws SQLException {
		return null;
	}

}
