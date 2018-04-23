package aop.serviceImpl;

import aop.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public void create() {
		System.out.println("create");
		
	}

	@Override
	public void del() {
		System.out.println("del");
		
	}

	@Override
	public void update() {
		System.out.println("update");
		
	}

}
