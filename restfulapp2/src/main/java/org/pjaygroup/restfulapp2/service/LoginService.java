package org.pjaygroup.restfulapp2.service;

import org.pjaygroup.restfulapp2.model.Login;

/**
 * @author Vijay Konduru
 *
 */
public interface LoginService {

	boolean doVerifyLogin(Login login);

	boolean addLoginCredentials(Login login);

	boolean updateLoginCredentials(Login login);

}
