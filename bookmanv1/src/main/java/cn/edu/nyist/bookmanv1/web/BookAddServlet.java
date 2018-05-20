package cn.edu.nyist.bookmanv1.web;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import cn.edu.nyist.bookmanv1.biz.BookBiz;
import cn.edu.nyist.bookmanv1.biz.impl.BookBizImpl;
import cn.edu.nyist.bookmanv1.util.MyBeanUtils;
import cn.edu.nyist.bookmanv1.vo.BookVo;
@WebServlet("/bookAdd")
@MultipartConfig
public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookAddServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 0 获取上传文件
		Part part = request.getPart("photo");
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		// 解决IE下路径问题
		fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);

		// 解决名字重复问题
		String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		String newFileName = UUID.randomUUID().toString() + "." + ext;
		part.write(request.getServletContext().getRealPath("upload/" + newFileName));
		// 1获取参数		
		BookVo bookVo=new BookVo();
		
		MyBeanUtils.populate(bookVo, request.getParameterMap(), "yyyy-MM-dd");
		bookVo.setPhoto(newFileName);
		// 2 调用业务层
		BookBiz bookBiz = new BookBizImpl();
		int ret = bookBiz.saveBook(bookVo);
		// 3 给用户一个界面
		response.setContentType("text/html;charset=utf-8");
		if (ret > 0) {
			response.getWriter().write("添加成功");
		} else {
			request.setAttribute("msg", "添加失败");
			request.getRequestDispatcher("bookAdd.jsp").forward(request, response);
		}

	}

}
