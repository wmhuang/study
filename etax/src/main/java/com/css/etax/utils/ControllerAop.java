package com.css.etax.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.alibaba.fastjson.JSON;

@Component
@Aspect
public class ControllerAop {
	
	//访问请求内容放到指定的log文件中
	Logger logger = Logger.getLogger("restLog");  
	// 匹配com.css.etax.controller包及其子包下的所有类的所有方法
	@Pointcut("execution(* com.css.etax.controller..*.*(..))")
	public void executeService() {

	}
	
	@Before("executeService()")
	public void doBeforeAdvice(JoinPoint joinPoint){
		logger.info("----------------------------------------------");
		logger.info("记录请求参数");
		// 获取目标方法的参数信息
		Object[] obj = joinPoint.getArgs();
		// AOP代理类的信息
		joinPoint.getThis();
		// 代理的目标对象
		joinPoint.getTarget();
		// 用的最多 通知的签名
		Signature signature = joinPoint.getSignature();
		// AOP代理类的名字
		logger.info(signature.getDeclaringTypeName());
		// 代理的是哪一个方法
		logger.info(signature.getName());
		// AOP代理类的类（class）信息
		signature.getDeclaringType();
		// 获取RequestAttributes
		RequestAttributes requestAttributes = RequestContextHolder
				.getRequestAttributes();
		// 从获取RequestAttributes中获取HttpServletRequest的信息
		HttpServletRequest request = (HttpServletRequest) requestAttributes
				.resolveReference(RequestAttributes.REFERENCE_REQUEST);
		// 如果要获取Session信息的话，可以这样写：
		// HttpSession session = (HttpSession)
		// requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
		logger.info("客户端地址:"+getIpAddress(request));
		logger.info("请求方式："+request.getMethod());
		Enumeration<String> enumeration = request.getParameterNames();
		Map<String, String> parameterMap = new HashMap<String, String>();
		while (enumeration.hasMoreElements()) {
			String parameter = enumeration.nextElement();
			parameterMap.put(parameter, request.getParameter(parameter));
		}
		String str = JSON.toJSONString(parameterMap);
		if (obj.length > 0) {
			logger.info("请求的参数信息为：" + str);
		}
	}
	
	@AfterReturning(pointcut="executeService()",returning="retValue")
	public void doAfterAdvice(Object retValue){
		logger.info("返回结果："+JSON.toJSONString(retValue));
	}
	

	public String getIpAddress(HttpServletRequest request) {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
}
