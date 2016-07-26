package org.pjaygroup.restfulapp1.serviceimpl;

import java.util.List;

import org.pjaygroup.restfulapp1.dao.EmployeeDao;
import org.pjaygroup.restfulapp1.daoimpl.EmployeeDaoImpl;
import org.pjaygroup.restfulapp1.model.Employee;
import org.pjaygroup.restfulapp1.service.EmployeeService;

/**
 * @author Vijay Konduru
 *
 */
public class EmployeeServiceImpl implements EmployeeService{
	
	// You have business logic or custom rules to be applied on the business objects, then do it in this layer
	
	private EmployeeDao employeeDao;
	
	// Initializer block
	{
		employeeDao = new EmployeeDaoImpl();
	}

	@Override
	public List<Employee> listEmployees() {
		return employeeDao.listEmployees();
	}

	@Override
	public Employee getEmployee(Integer emp_id) {
		return employeeDao.getEmployee(emp_id);
	}

	@Override
	public boolean addEmployee(Employee employee) {
		return employeeDao.addEmployee(employee);
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);
	}

	@Override
	public boolean destroyEmployee(Integer emp_id) {
		return employeeDao.destroyEmployee(emp_id);
	}

}
