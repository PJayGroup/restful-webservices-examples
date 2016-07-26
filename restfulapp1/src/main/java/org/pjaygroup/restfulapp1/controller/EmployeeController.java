package org.pjaygroup.restfulapp1.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pjaygroup.restfulapp1.model.Employee;
import org.pjaygroup.restfulapp1.model.Message;
import org.pjaygroup.restfulapp1.service.EmployeeService;
import org.pjaygroup.restfulapp1.serviceimpl.EmployeeServiceImpl;

/**
 * @author Vijay Konduru
 * https://mvnrepository.com/artifact/com.sun.jersey.samples
 * https://mvnrepository.com/artifact/com.sun.jersey.samples/spring-annotations
 * https://mvnrepository.com/artifact/com.sun.jersey.samples/simple-servlet
 * https://mvnrepository.com/artifact/com.sun.jersey.contribs
 * https://mvnrepository.com/artifact/com.sun.jersey.contribs/jersey-spring
 *
 */
@Path("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	// Initializer block
	{
		employeeService = new EmployeeServiceImpl();
	}

	@GET
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response listEmployees(){
		List<Employee> employees = employeeService.listEmployees();
		// Build a model object to hold collection data and send it as response or do as mention below with "new GenericEntity<List<Employee>>(employees){}".
		// Looks like name of the enclosing tag is "employees" for total list xml generated as plural to @XmlRootElement(name="employee") name
		if(null != employees && !employees.isEmpty()){
			//return Response.ok(employees).build();
			return Response.ok(new GenericEntity<List<Employee>>(employees){}).build();
		}
//		return Response.ok(new GenericEntity<List<Employee>>(employees){}).build();
		return Response.status(Response.Status.NO_CONTENT).entity(new Message("No employees found")).build();
	}
	
	@GET
	@Path("/{emp_id}")
	@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getEmployee(@PathParam("emp_id") Integer emp_id){
		if(null == emp_id){return Response.status(Response.Status.NO_CONTENT).entity(new Message("Employee info not valid")).build();}
		Employee employee = employeeService.getEmployee(emp_id);
		if(null != employee){
			return Response.ok(employee).build();
		}
		return Response.status(Response.Status.NO_CONTENT).entity(new Message("No employee found with employee id " + emp_id)).build();
	}
	
	@POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addEmployee(Employee employee){
		if(null == employee){return Response.status(Response.Status.NO_CONTENT).entity(new Message("Employee info not valid")).build();}
		boolean createsuccess = employeeService.addEmployee(employee);
		if(createsuccess){
			return Response.status(Response.Status.CREATED).entity(new Message("Employee created successfully")).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(new Message("Employee creation failed")).build();
	}
	
	@PUT
	@Path("/update")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateEmployee(Employee employee){
		if(null == employee){return Response.status(Response.Status.NO_CONTENT).entity(new Message("Employee info not valid")).build();}
		boolean updatesuccess = employeeService.updateEmployee(employee);
		if(updatesuccess){
			return Response.ok(new Message("Employee updated successfully")).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(new Message("Employee not updated")).build();
	}
	
	@DELETE
	//@Path("/{emp_id}/delete")
	@Path("/destroy/{emp_id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response destroyEmployee(@PathParam("emp_id") Integer emp_id){//deleteEmployee(@PathParam("emp_id") String emp_id)
		if(null == emp_id){return Response.status(Response.Status.NO_CONTENT).entity(new Message("Employee info not valid")).build();}
		boolean destroysuccess = employeeService.destroyEmployee(emp_id);
		if(destroysuccess){
			return Response.ok(new Message("Employee destroyed successfully")).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(new Message("Employee destruction failed")).build();
	}

}
