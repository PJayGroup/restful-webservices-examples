package org.pjaygroup.restfulapp2.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pjaygroup.restfulapp2.exceptions.ResourceExistsException;
import org.pjaygroup.restfulapp2.model.Login;
import org.pjaygroup.restfulapp2.model.Message;
import org.pjaygroup.restfulapp2.service.LoginService;
import org.pjaygroup.restfulapp2.serviceimpl.LoginServiceImpl;

/**
 * @author Vijay Konduru
 *
 */
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
			return Response.status(Response.Status.EXPECTATION_FAILED).entity(new Message(e.getMessage())).build();
		}
		if(createsuccess){
			//return Response.ok().build();
			return Response.status(Response.Status.CREATED).entity(new Message("Login credentials created successfully")).build();
		}
		return Response.status(Response.Status.EXPECTATION_FAILED).entity(new Message("Login credentials creation failed")).build();
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
		return Response.status(Response.Status.EXPECTATION_FAILED).entity(new Message("Login credentials update failed")).build();
	}

}
