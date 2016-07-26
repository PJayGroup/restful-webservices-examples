package org.pjaygroup.restfulapp1.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.pjaygroup.restfulapp1.dao.LoginDao;
import org.pjaygroup.restfulapp1.exceptions.ResourceExistsException;
import org.pjaygroup.restfulapp1.model.Login;
import org.pjaygroup.restfulapp1.utils.HibernateUtils;

/**
 * @author Vijay Konduru
 *
 */
public class LoginDaoImpl implements LoginDao{
	
	private SessionFactory sessionFactory;
	
	// Initializer block
	{
		sessionFactory = HibernateUtils.getSessionFactory();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean doVerifyLogin(Login login) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Login.class);
		Criterion criterion_user_id = Restrictions.eq("user_id", login.getUser_id());
		Criterion criterion_password = Restrictions.eq("password", login.getPassword());
		Criterion criterion_user_and_pwd = Restrictions.and(criterion_user_id, criterion_password);
		criteria.add(criterion_user_and_pwd);
		List<Login> userlst = criteria.list();
		if(null != userlst && !userlst.isEmpty()){return true;}
		return false;
	}

	@Override
	public boolean addLoginCredentials(Login login) {
		Session session = sessionFactory.openSession();
		Login loginFromDB = getLoginByUserID(session, login.getUser_id());
		if(null != loginFromDB){
			session.close();
			throw new ResourceExistsException("Login info present with user id " + login.getUser_id());
		}
		Transaction transaction = session.beginTransaction();
		try {
			session.save(login);
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
	public boolean updateLoginCredentials(Login login) {
		Session session = sessionFactory.openSession();
		Login loginFromDB = getLoginByUserID(session, login.getUser_id());
		if(null != loginFromDB){
			// To solve the problem of not having or wrong id passed by user, we add the DB object id to user passed object before merge.
			// If we don't wan to deal with id. The we can create custom generator id generator class to give string user objects
			login.setId(loginFromDB.getId());
			Transaction transaction = session.beginTransaction();
			try {
				//session.save(login);//session.update(login); // use merge as there is one object already in cache from DB with same id or evict old object and save - else get ready to face org.hibernate.NonUniqueObjectException
				session.merge(login);
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
	
	private Login getLoginByUserID(Session session, String user_id) {
		Query query = session.createQuery("FROM Login WHERE user_id = :user_id");
		query.setParameter("user_id", user_id);
		// If we are sure to have only one entry per user_id in our table then you can use code as below
		Object login_obj = query.uniqueResult();
		//session.close(); // don't close session here as it is used further in other method calls, else you get "org.hibernate.SessionException: Session is closed!" error
		// If want to close session here. Open a new session here and don't pass session to this method, deal with new session in corresponding method calls 
		if(null != login_obj){
			return (Login)login_obj;
		}else{
			return null;
		}
	}

}
