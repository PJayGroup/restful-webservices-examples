package org.pjaygroup.restfulapp2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vijay Konduru
 *
 */
@Entity
@Table(name = "employee")
@XmlRootElement(name="employee")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer emp_id;
	private String emp_name;
	private String emp_company;
	private String emp_department;

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_company() {
		return emp_company;
	}

	public void setEmp_company(String emp_company) {
		this.emp_company = emp_company;
	}

	public String getEmp_department() {
		return emp_department;
	}

	public void setEmp_department(String emp_department) {
		this.emp_department = emp_department;
	}

	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_company=" + emp_company
				+ ", emp_department=" + emp_department + "]";
	}

}
