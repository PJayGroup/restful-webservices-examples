package org.pjaygroup.restfulapp2.dao;

import org.pjaygroup.restfulapp2.model.Login;

/**
 * @author Vijay Konduru
 *
 */
public interface LoginDao {

	boolean doVerifyLogin(Login login);

	boolean addLoginCredentials(Login login);

	boolean updateLoginCredentials(Login login);

}
