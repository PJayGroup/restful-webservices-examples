package org.pjaygroup.restfulapp2.dao;

import java.util.List;

import org.pjaygroup.restfulapp2.model.User;

/**
 * @author Vijay Konduru
 *
 */
public interface UserDao {

	List<User> listUsers();

	User getUser(String user_id);

	boolean addUser(User user);

	boolean updateUser(User user);

	boolean destroyUser(String user_id);

}
