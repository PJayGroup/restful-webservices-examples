package org.pjaygroup.restfulapp3.service;

import java.util.List;

import org.pjaygroup.restfulapp3.model.Employee;

/**
 * @author Vijay Konduru
 *
 */
public interface EmployeeService {

	List<Employee> listEmployees();

	Employee getEmployee(Integer emp_id);

	boolean addEmployee(Employee employee);

	boolean updateEmployee(Employee employee);

	boolean destroyEmployee(Integer emp_id);

}
