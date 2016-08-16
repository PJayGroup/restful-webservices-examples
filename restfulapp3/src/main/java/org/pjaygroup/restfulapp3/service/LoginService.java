package org.pjaygroup.restfulapp3.service;

import org.pjaygroup.restfulapp3.model.Login;

/**
 * @author Vijay Konduru
 *
 */
public interface LoginService {

	boolean doVerifyLogin(Login login);

	boolean addLoginCredentials(Login login);

	boolean updateLoginCredentials(Login login);

}
