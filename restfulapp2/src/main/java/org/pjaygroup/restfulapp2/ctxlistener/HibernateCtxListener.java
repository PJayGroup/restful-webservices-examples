package org.pjaygroup.restfulapp2.ctxlistener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.pjaygroup.restfulapp2.utils.HibernateUtils;

/**
 * @author Vijay Konduru
 *
 */
public class HibernateCtxListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent ctxevent) {
		HibernateUtils.getSessionFactory().close();
		System.out.println(" ---- Hibernate Session Factory Closed ----- ");
	}

	@Override
	public void contextInitialized(ServletContextEvent ctxevent) {
		HibernateUtils.getSessionFactory();
		System.out.println(" ---- Hibernate Session Factory initialized ----- ");
	}

}
