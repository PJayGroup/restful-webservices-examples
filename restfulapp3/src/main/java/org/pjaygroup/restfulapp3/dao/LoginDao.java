package org.pjaygroup.restfulapp3.dao;

import org.pjaygroup.restfulapp3.model.Login;

/**
 * @author Vijay Konduru
 *
 */
public interface LoginDao {

	boolean doVerifyLogin(Login login);

	boolean addLoginCredentials(Login login);

	boolean updateLoginCredentials(Login login);

}
