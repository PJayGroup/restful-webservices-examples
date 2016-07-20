package org.pjaygroup.restfulapp2.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.pjaygroup.restfulapp2.dao.EmployeeDao;
import org.pjaygroup.restfulapp2.model.Employee;
import org.pjaygroup.restfulapp2.utils.HibernateUtils;

/**
 * @author Vijay Konduru
 *
 */
public class EmployeeDaoImpl implements EmployeeDao {
	
	private SessionFactory sessionFactory;
	
	// Initializer block
	{
		sessionFactory = HibernateUtils.getSessionFactory();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listEmployees() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM Employee e");// HQL
		List<Employee> employees = query.list();
		session.close();
		return employees;
	}

	@Override
	public Employee getEmployee(Integer emp_id) {
		Session session = sessionFactory.openSession();
		//session.beginTransaction();// Not necessary for a select statement
		Employee employee = session.get(Employee.class, emp_id);
		session.close();
		return employee;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			session.save(employee);
			transaction.commit();
			session.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			session.close();
			return false;
		}
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		Session session = sessionFactory.openSession();
		Employee employeeFromDB = session.get(Employee.class, employee.getEmp_id());
		if(null != employeeFromDB){
			Transaction transaction = session.beginTransaction();
			try{
				//session.update(employee);
				session.merge(employee);
				transaction.commit();
				// org.hibernate.NonUniqueObjectException: A different object with the same identifier value was already associated with the session : [org.pjaygroup.restfulapp2.model.Employee#2]
				// As data is selected from DB before updating, we need to merge as two objects are present. If not then use the same object to save instead of verify in DB and then saving (Which results in above error)
				// So using merge
				
				// Note: Find info regarding below error, happens when undeploying application
				// SEVERE: IOException while saving persisted sessions: java.io.FileNotFoundException: C:\JavaWorkspaceJeeMars\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\work\Catalina\localhost\restfulapp2\SESSIONS.ser (The system cannot find the path specified)
				// java.io.FileNotFoundException: C:\JavaWorkspaceJeeMars\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\work\Catalina\localhost\restfulapp2\SESSIONS.ser (The system cannot find the path specified)
				// http://stackoverflow.com/questions/12316540/tomcat-is-not-deploying-my-web-project-from-eclipse
				// Some times this was solved by removing deployed app, cleaning tomcat, clean build project, run maven install and deploy it back. (Looks like this issue is related project facet change to 3.0 Please verify)
				session.close();
				return true;
			}catch(Exception e){
				e.printStackTrace();
				transaction.rollback();
				session.close();
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean destroyEmployee(Integer emp_id) {
		Session session = sessionFactory.openSession();
		Employee employeeFromDB = session.get(Employee.class, emp_id);
		if(null != employeeFromDB){
			Transaction transaction = session.beginTransaction();
			try{
				session.delete(employeeFromDB);
				transaction.commit();
				session.close();
				return true;
			}catch(Exception e){
				e.printStackTrace();
				transaction.rollback();
				session.close();
				return false;
			}
		}
		return false;
	}

}
