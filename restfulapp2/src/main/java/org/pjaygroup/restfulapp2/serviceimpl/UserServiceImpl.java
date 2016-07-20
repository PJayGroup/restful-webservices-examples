package org.pjaygroup.restfulapp2.serviceimpl;

import java.util.List;

import org.pjaygroup.restfulapp2.dao.UserDao;
import org.pjaygroup.restfulapp2.daoimpl.UserDaoImpl;
import org.pjaygroup.restfulapp2.model.Login;
import org.pjaygroup.restfulapp2.model.User;
import org.pjaygroup.restfulapp2.service.UserService;

/**
 * @author Vijay Konduru
 *
 */
public class UserServiceImpl implements UserService{

	// You have business logic or custom rules to be applied on the business objects, then do it in this layer
	
	private UserDao userDao;
	
	// Initializer block
	{
		userDao = new UserDaoImpl();
	}
	
	@Override
	public List<User> listUsers() {
		return userDao.listUsers();
	}

	@Override
	public User getUser(String user_id) {
		return userDao.getUser(user_id);
	}

	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public boolean destroyUser(String user_id) {
		return userDao.destroyUser(user_id);
	}

	@Override
	public boolean doVerifyLogin(Login login) {
		return userDao.doVerifyLogin(login);
	}

}
