package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.bookmanv1.biz.BookBiz;
import cn.edu.nyist.bookmanv1.biz.impl.BookBizImpl;
import cn.edu.nyist.bookmanv1.vo.BookVo;


@WebServlet("/toBookEdit")
public class ToBookEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ToBookEditServlet() {
        super();
        
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */ 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1
		String strId=request.getParameter("id");
		int id=Integer.parseInt(strId);
		//2
		BookBiz bookBiz=new BookBizImpl();
		BookVo bookVo=bookBiz.findBookById(id);
		//3 转发到编辑页
		request.setAttribute("bookVo", bookVo);
		request.getRequestDispatcher("bookEdit.jsp").forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
