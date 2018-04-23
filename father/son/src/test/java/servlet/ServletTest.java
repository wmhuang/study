package servlet;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public void init() throws ServletException {
		message = "Hello World!";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			out.println("<h1> " + message + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void destroy() {

	}
}
