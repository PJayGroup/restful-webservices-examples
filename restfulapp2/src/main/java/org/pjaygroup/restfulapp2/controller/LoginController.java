package org.pjaygroup.restfulapp2.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pjaygroup.restfulapp2.model.Login;
import org.pjaygroup.restfulapp2.model.Message;
import org.pjaygroup.restfulapp2.service.UserService;
import org.pjaygroup.restfulapp2.serviceimpl.UserServiceImpl;

/**
 * @author Vijay Konduru
 *
 */
@Path("/login")
public class LoginController {
	
	private UserService userService;
	
	// Initializer block
	{
		userService = new UserServiceImpl();
	}

	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doVerifyLogin(Login login) {
		if(null == login){return Response.status(Response.Status.UNAUTHORIZED).entity(new Message("Login info entered is not valid")).build();}
		boolean loginsuccess = userService.doVerifyLogin(login);
		if (loginsuccess) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).entity(new Message("Usename/Password entered is incorrect")).build();
	}

}
