package cn.edu.nyist.bookmanv1.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;


import cn.edu.nyist.bookmanv1.dao.BookDao;
import cn.edu.nyist.bookmanv1.util.DsUtil;
import cn.edu.nyist.bookmanv1.vo.BookVo;

public class BookDaoJdbcImpl implements BookDao {

	@Override
	public int save(BookVo bookVo) {
	Connection conn=null;
	PreparedStatement stmt=null;
	try {
		conn=DsUtil.getConn();
		String sql="insert into t_book(tid,name,descri,photo,price,author,pubDate) values(?,?,?,?,?,?,?)";
		stmt=conn.prepareStatement(sql);
		stmt.setInt(1,bookVo.getTid());
		stmt.setString(2,bookVo.getName());
		stmt.setString(3,bookVo.getDescri());
		stmt.setString(4,bookVo.getPhoto());
		stmt.setDouble(5,bookVo.getPrice());
		stmt.setString(6,bookVo.getAuthor());
		//java.util.Date-->java.sql.Date
		stmt.setDate(7,new java.sql.Date(bookVo.getPubDate().getTime()));
		int ret=stmt.executeUpdate();
		return ret;
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		System.out.println("*********");
		DsUtil.free(stmt, conn);
		
	}
	return 0;
	}
	

}