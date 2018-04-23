package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet(name = "HelloWorld2", urlPatterns = { "/HelloWorld2" })
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public HelloWorld() {
        super();
    }
    
    public void init(){
    	System.out.println("init........");
    }
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
