package org.pjaygroup.restfulapp3.serviceimpl;

import org.pjaygroup.restfulapp3.dao.LoginDao;
import org.pjaygroup.restfulapp3.daoimpl.LoginDaoImpl;
import org.pjaygroup.restfulapp3.model.Login;
import org.pjaygroup.restfulapp3.service.LoginService;

/**
 * @author Vijay Konduru
 *
 */
public class LoginServiceImpl implements LoginService{
	
	// You have business logic or custom rules to be applied on the business objects, then do it in this layer
	
	private LoginDao loginDao;
	
	//Initializer Block
	{
		loginDao = new LoginDaoImpl();
	}

	@Override
	public boolean doVerifyLogin(Login login) {
		return loginDao.doVerifyLogin(login);
	}

	@Override
	public boolean addLoginCredentials(Login login) {
		return loginDao.addLoginCredentials(login);
	}

	@Override
	public boolean updateLoginCredentials(Login login) {
		return loginDao.updateLoginCredentials(login);
	}

}
