package org.pjaygroup.restfulapp3.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pjaygroup.restfulapp3.exceptions.ResourceExistsException;
import org.pjaygroup.restfulapp3.model.Login;
import org.pjaygroup.restfulapp3.model.Message;
import org.pjaygroup.restfulapp3.service.LoginService;
import org.pjaygroup.restfulapp3.serviceimpl.LoginServiceImpl;
import org.springframework.stereotype.Component;

/**
 * @author Vijay Konduru
 * https://mvnrepository.com/artifact/com.sun.jersey
 * https://mvnrepository.com/artifact/com.sun.jersey.samples
 * https://mvnrepository.com/artifact/com.sun.jersey.samples/spring-annotations
 * https://mvnrepository.com/artifact/com.sun.jersey.samples/simple-servlet
 * https://mvnrepository.com/artifact/com.sun.jersey.contribs
 * https://mvnrepository.com/artifact/com.sun.jersey.contribs/jersey-spring
 * 
 * https://dzone.com/articles/restful-web-applications
 * https://www.mkyong.com/webservices/jax-rs/jersey-spring-integration-example/
 *
 */
//@Resource//not working
@Component
@Path("/login")
public class LoginController {
	
	private LoginService loginService;
	
	// Initializer block
	{
		loginService = new LoginServiceImpl();
	}

	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doVerifyLogin(Login login) {
		if(null == login){return Response.status(Response.Status.UNAUTHORIZED).entity(new Message("Login info entered is not valid")).build();}
		boolean loginsuccess = loginService.doVerifyLogin(login);
		if (loginsuccess) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).entity(new Message("Usename/Password entered is incorrect")).build();
	}
	
	@POST()
	@Path("/add")
	@Consumes(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response addLoginCredentials(Login login){
		if(null == login){return Response.status(Response.Status.NO_CONTENT).entity(new Message("Login info entered is not valid")).build();}
		boolean createsuccess = false;
		try {
			createsuccess = loginService.addLoginCredentials(login);
		} catch (ResourceExistsException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(new Message(e.getMessage())).build();
		}
		if(createsuccess){
			//return Response.ok().build();
			return Response.status(Response.Status.CREATED).entity(new Message("Login credentials created successfully")).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(new Message("Login credentials creation failed")).build();
	}
	
	@PUT
	@Path("/update")
	@Consumes(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response updateLoginCredentials(Login login){
		if(null == login){return Response.status(Response.Status.NO_CONTENT).entity(new Message("Login info entered is not valid")).build();}
		boolean updatesuccess = loginService.updateLoginCredentials(login);
		if(updatesuccess){
			return Response.status(Response.Status.CREATED).entity(new Message("Login credentials updated successfully")).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(new Message("Login credentials update failed")).build();
	}

}
