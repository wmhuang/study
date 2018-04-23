package aop;

import aop.service.UserService;
import aop.serviceImpl.UserServiceImpl;

public class Test {
	public static void main(String[] args) {
		UserService a = new UserServiceImpl();
		a.create();
	}
}
