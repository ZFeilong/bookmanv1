package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.bookmanv1.biz.TypeBiz;
import cn.edu.nyist.bookmanv1.biz.impl.TypeBizImpl;
import cn.edu.nyist.bookmanv1.vo.TypeVo;

@WebServlet("/findAllTypes")
public class FindAllTypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public FindAllTypesServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取参数
		//2获取业务层
		TypeBiz typeBiz=new TypeBizImpl();
		List<TypeVo> ls=typeBiz.findAllTypes();
		//3给用户一个响应
       request.setAttribute("ls",ls );
       request.getRequestDispatcher("bookAdd.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
