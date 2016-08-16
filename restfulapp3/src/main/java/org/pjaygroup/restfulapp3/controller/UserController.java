package org.pjaygroup.restfulapp3.controller;

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

import org.pjaygroup.restfulapp3.exceptions.ResourceExistsException;
import org.pjaygroup.restfulapp3.model.Message;
import org.pjaygroup.restfulapp3.model.User;
import org.pjaygroup.restfulapp3.service.UserService;
import org.pjaygroup.restfulapp3.serviceimpl.UserServiceImpl;
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
@Path("/users")
public class UserController {
	
	private UserService userService;
	
	// Initializer block
	{
		userService = new UserServiceImpl();
	}

	@GET
	@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response listUsers(){
		List<User> users = userService.listUsers();
		if(null != users && !users.isEmpty()){
			return Response.ok(new GenericEntity<List<User>>(users){}).build();
		}
		return Response.status(Response.Status.NO_CONTENT).entity(new Message("No users found")).build();
	}
	
	@GET
	@Path("/{user_id}")
	@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getUser(@PathParam("user_id") String user_id){
		if(null == user_id){return Response.status(Response.Status.NO_CONTENT).entity(new Message("User info not valid")).build();}
		User user = userService.getUser(user_id);
		if(null != user){
			return Response.ok(user).build();
		}
		return Response.status(Response.Status.NO_CONTENT).entity(new Message("No user found with user id " + user_id)).build();
	}
	
	@POST
	@Path("/add")
	@Consumes(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addUser(User user){
		if(null == user){return Response.status(Response.Status.NO_CONTENT).entity(new Message("User info not valid")).build();}
		try {
			boolean usercreated = userService.addUser(user);
			if(usercreated){
				//return Response.status(Response.Status.CREATED).entity(user).build();
				return Response.status(Response.Status.CREATED).entity(new Message("User created successfully")).build();
			}
		} catch (ResourceExistsException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(new Message(e.getMessage())).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(new Message("User creation failed")).build();
	}
	
	@PUT
	@Path("/update")
	@Consumes(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateUser(User user){
		if(null == user){return Response.status(Response.Status.NO_CONTENT).entity(new Message("User info not valid")).build();}
		boolean userupdated = userService.updateUser(user);
		if(userupdated){
			return Response.ok(new Message("User updated successfully")).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(new Message("User not updated")).build();
	}
	
	@DELETE
	@Path("/destroy/{user_id}")
	@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response destroyUser(@PathParam("user_id") String user_id){
		if(null == user_id){return Response.status(Response.Status.NO_CONTENT).entity(new Message("User info not valid")).build();}
		boolean userdestroyed = userService.destroyUser(user_id);
		if(userdestroyed){
			return Response.ok(new Message("User destroyed successfully")).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(new Message("User destruction failed")).build();
	}
	
}
