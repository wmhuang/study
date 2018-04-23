package servlet;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String message;

	public void init() throws ServletException {
		System.out.println("init 初始化！");
		message = "Hello World!";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		//添加cookie
		Cookie cookie  = new Cookie("name","wmhuang");
		cookie.setDomain("http://localhost:8080/servlet");
		Cookie cookie2 = new Cookie("url",request.getRequestURI());
		cookie2.setDomain("http://localhost:8081/servlet");
		response.addCookie(cookie);
		response.addCookie(cookie2);
		System.out.println("执行doGet方法！");
		try {
			// response.setIntHeader("refresh", 5);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<h1> " + message + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("执行doPost方法！");
		try {
			doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void destroy() {
		System.out.println("servlet销毁！");
		super.destroy();
	}
}
