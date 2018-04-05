package by.htp.trucking.service;

import by.htp.trucking.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final UserService userService = new UserServiceImpl(); 
	
	private ServiceFactory() {
	}
	
	public static ServiceFactory getInstatnce() {
		return instance;
	}
	
	public UserService getUserService() {
		return userService;
	}
}
