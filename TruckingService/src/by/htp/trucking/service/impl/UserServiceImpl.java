package by.htp.trucking.service.impl;

import by.htp.trucking.dao.DAOFactory;
import by.htp.trucking.dao.UserDAO;
import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;
import by.htp.trucking.service.UserService;
import by.htp.trucking.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	@Override
	public User create(User user, UserInfo userInfo, String locale) throws ServiceException {
		if (!user.getLogin().isEmpty()) {
			if (!user.getPassword().isEmpty()) {
				if (Validator.validatePassword(user.getPassword())) {
					if (!userInfo.getEmail().isEmpty()) {
						if (Validator.validateEmail(userInfo.getEmail())) {
							if (Validator.validatePhoneNumber(userInfo.getPhoneNumber())) {
								DAOFactory daoFactory = DAOFactory.getInstance();
								UserDAO userDAO = daoFactory.getUserDAO();
								try {
									user = userDAO.create(user, userInfo);
								} catch (DAOException e) {
									if (locale.equals("ru")) {
										throw new ServiceException("Ошибка создания пользователя");
									} else {
										throw new ServiceException("Error of creating user");
									}
								}
							} else {
								if (locale.equals("ru")) {
									throw new ServiceException("Вы ввели некорректный мобильный номер");
								} else {
									throw new ServiceException("You entered incorrect mobile number");
								}
							}
						} else {
							if (locale.equals("ru")) {
								throw new ServiceException("Вы ввели некорректный email");
							} else {
								throw new ServiceException("You entered incorrect email");
							}
						}
					} else {
						if (locale.equals("ru")) {
							throw new ServiceException("Поле email пусто");
						} else {
							throw new ServiceException("Email is empty");
						}
					}
				} else {
					if (locale.equals("ru")) {
						throw new ServiceException(
								"Ваш пароль должен состоять минимум из восьми символов, по крайней мере, одной заглавной буквы, одной строчной буквы и одной цифры");
					} else {
						throw new ServiceException(
								"Your password must consist of minimum eight characters, at least one uppercase letter, one lowercase letter and one number");
					}
				}
			} else {
				if (locale.equals("ru")) {
					throw new ServiceException("Поле пароль пусто");
				} else {
					throw new ServiceException("Password is empty");
				}
			}
		} else {
			if (locale.equals("ru")) {
				throw new ServiceException("Поле логин пусто");
			} else {
				throw new ServiceException("Login is empty");
			}
		}
		return user;
	}

	@Override
	public User logination(String login, String password, String locale) throws ServiceException {
		User user = null;

		// validation & regex
		if (!login.isEmpty()) {
			if (!password.isEmpty()) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				UserDAO userDAO = daoFactory.getUserDAO();

				try {
					user = userDAO.checkUser(login, password);
					if (user.getStatus().equals("false")) {
						if (locale.equals("ru")) {
							user = null;
							throw new ServiceException("Ваша учетная запись удалена или заблокирована");
						} else {
							user = null;
							throw new ServiceException("Your account is blocked or deleted");
						}
					}
				} catch (DAOException e) {
					throw new ServiceException();
				}
			} else {
				if (locale.equals("ru")) {
					throw new ServiceException("Поле пароль пусто");
				} else {
					throw new ServiceException("Password is empty");
				}
			}
		} else {
			if (locale.equals("ru")) {
				throw new ServiceException("Поле логин пусто");
			} else {
				throw new ServiceException("Login is empty");
			}
		}

		return user;
	}

	@Override
	public UserInfo getuserInfo(String login) throws ServiceException {
		UserInfo userInfo = null;

		if (login != null && !login.isEmpty()) {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			try {
				userInfo = userDAO.getuserInfo(login);
			} catch (DAOException e) {
				throw new ServiceException();
			}
		}

		return userInfo;
	}

	@Override
	public User checkLoginUser(String login) throws ServiceException {
		User user = null;

		// validation & regex
		if (login != null && !login.isEmpty()) {

			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();

			try {
				user = userDAO.checkLoginUser(login);
			} catch (DAOException e) {
				throw new ServiceException();
			}
		}

		return user;
	}

	@Override
	public boolean edit(User user, UserInfo userInfo, String locale) throws ServiceException {

		if (!user.getPassword().isEmpty()) {
			if (Validator.validatePassword(user.getPassword())) {
				if (!userInfo.getEmail().isEmpty()) {
					if (Validator.validateEmail(userInfo.getEmail())) {
						if (Validator.validatePhoneNumber(userInfo.getPhoneNumber())) {
							DAOFactory daoFactory = DAOFactory.getInstance();
							UserDAO userDAO = daoFactory.getUserDAO();
							try {
								return userDAO.edit(user, userInfo);
							} catch (DAOException e) {
								if (locale.equals("ru")) {
									throw new ServiceException("Ошибка создания пользователя");
								} else {
									throw new ServiceException("Error of creating user");
								}
							}
						} else {
							if (locale.equals("ru")) {
								throw new ServiceException("Вы ввели некорректный мобильный номер");
							} else {
								throw new ServiceException("You entered incorrect mobile number");
							}
						}
					} else {
						if (locale.equals("ru")) {
							throw new ServiceException("Вы ввели некорректный email");
						} else {
							throw new ServiceException("You entered incorrect email");
						}
					}
				} else {
					if (locale.equals("ru")) {
						throw new ServiceException("Поле email пусто");
					} else {
						throw new ServiceException("Email is empty");
					}
				}
			} else {
				if (locale.equals("ru")) {
					throw new ServiceException(
							"Ваш пароль должен состоять минимум из восьми символов, по крайней мере, одной заглавной буквы, одной строчной буквы и одной цифры");
				} else {
					throw new ServiceException(
							"Your password must consist of minimum eight characters, at least one uppercase letter, one lowercase letter and one number");
				}
			}
		} else {
			if (locale.equals("ru")) {
				throw new ServiceException("Поле пароль пусто");
			} else {
				throw new ServiceException("Password is empty");
			}
		}
	}

}
