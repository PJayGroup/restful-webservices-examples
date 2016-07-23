package org.pjaygroup.restfulapp2.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.pjaygroup.restfulapp2.dao.UserDao;
import org.pjaygroup.restfulapp2.exceptions.ResourceExistsException;
import org.pjaygroup.restfulapp2.model.User;
import org.pjaygroup.restfulapp2.utils.HibernateUtils;

/**
 * @author Vijay Konduru
 *
 */
public class UserDaoImpl implements UserDao{
	
	private SessionFactory sessionFactory;
	
	// Initializer block
	{
		sessionFactory = HibernateUtils.getSessionFactory();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM User U");
		List<User> users = query.list();
		session.close();
		return users;
	}

	@Override
	public User getUser(String user_id) {
		Session session = sessionFactory.openSession();
		User user = getUserByUserID(session, user_id);
		session.close();
		return user;
	}

	private User getUserByUserID(Session session, String user_id) {
		//Session session = sessionFactory.openSession();
		//session.get(User.class, user_id); // Non id field hence have to use query object to get data
		//Query query = session.createQuery("FROM User U WHERE U.user_id = :user_id");
		Query query = session.createQuery("FROM User WHERE user_id = :user_id");
		query.setParameter("user_id", user_id);
//		List<User> list = query.list();
//		if(null != list && !list.isEmpty()){
//			return list.get(0);
//		}else{
//			return new User();
//		}
		// If we are sure to have only one entry per user_id in our table then you can use code as below
		Object user_obj = query.uniqueResult();
		//session.close(); // don't close session here as it is used further in other method calls, else you get "org.hibernate.SessionException: Session is closed!" error
		// If want to close session here. Open a new session here and don't pass session to this method, deal with new session in corresponding method calls 
		if(null != user_obj){
			return (User)user_obj;
		}else{
			return null;
		}
	}

	@Override
	public boolean addUser(User user) {
		Session session = sessionFactory.openSession();
		User userFromDB = getUserByUserID(session, user.getUser_id());
		if(null != userFromDB){
			session.close();
			throw new ResourceExistsException("User already present with user id " + user.getUser_id());
		}
		Transaction transaction = session.beginTransaction();
		try {
			session.save(user);
			transaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			session.close();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		Session session = sessionFactory.openSession();
		User userFromDB = getUserByUserID(session, user.getUser_id());
		if(null != userFromDB){
			// To solve the problem of not having or wrong id passed by user, we add the DB object id to user passed object before merge.
			// If we don't wan to deal with id. The we can create custom generator id generator class to give string user objects
			user.setId(userFromDB.getId());
			Transaction transaction = session.beginTransaction();
			try {
				//session.save(user);
				session.merge(user);
				transaction.commit();
				session.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				transaction.rollback();
				session.close();
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean destroyUser(String user_id) {
		Session session = sessionFactory.openSession();
		User userFromDB = getUserByUserID(session, user_id);
		if(null != userFromDB){
			Transaction transaction = session.beginTransaction();
			try {
				session.delete(userFromDB);
				transaction.commit();
				session.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				transaction.rollback();
				session.close();
				return false;
			}
		}
		return false;
	}

}
