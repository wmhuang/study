package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LogFilter implements Filter{

	@Override
	public void destroy() {
		 /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//输出站点名称
		System.out.println("拦截器开始拦截。。。");
	 
		if("123".equals(request.getParameter("name"))) {
			System.out.println("验证通过！");
			//把请求转回过滤链
			chain.doFilter(request, response);
		}else{
			System.out.println("验证未通过！");
			throw new ServletException();
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String site=config.getInitParameter("Site");
		System.out.println("网站名称："+site);
	}
}
