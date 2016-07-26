package org.pjaygroup.restfulapp1.dao;

import org.pjaygroup.restfulapp1.model.Login;

/**
 * @author Vijay Konduru
 *
 */
public interface LoginDao {

	boolean doVerifyLogin(Login login);

	boolean addLoginCredentials(Login login);

	boolean updateLoginCredentials(Login login);

}
