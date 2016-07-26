package org.pjaygroup.restfulapp1.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Vijay Konduru
 *
 */
public class HibernateUtils {

	private static SessionFactory sessionFactory = new HibernateUtils().createSessionFactort();

	private HibernateUtils() {
	}
	
	private SessionFactory createSessionFactort(){
	    Configuration configuration = new Configuration();
	    //configuration.configure();
	    // StandardServiceRegistryBuilder() should be used from Hibernate 4.3, as ServiceRegistryBuilder() is deprecated
	    // ServiceRegistryBuilder() for Hibernate 4.0, 4.1, 4.2
	    //ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    //SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
	    return sessionFactory;
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
