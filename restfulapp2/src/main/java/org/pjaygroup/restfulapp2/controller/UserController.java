package org.pjaygroup.restfulapp2.controller;

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

import org.pjaygroup.restfulapp2.exceptions.ResourceExistsException;
import org.pjaygroup.restfulapp2.model.Message;
import org.pjaygroup.restfulapp2.model.User;
import org.pjaygroup.restfulapp2.service.UserService;
import org.pjaygroup.restfulapp2.serviceimpl.UserServiceImpl;

/**
 * @author Vijay Konduru
 * https://mvnrepository.com/artifact/org.glassfish.jersey.examples
 * https://mvnrepository.com/artifact/org.glassfish.jersey.examples/helloworld-spring-webapp
 * https://mvnrepository.com/artifact/org.glassfish.jersey.examples/helloworld-spring-annotations
 * http://stackoverflow.com/questions/18122336/cannot-change-version-of-project-facet-dynamic-web-module-to-3-0
 * http://stackoverflow.com/questions/12505141/only-using-jsonignore-during-serialization-but-not-deserialization
 * https://github.com/FasterXML/jackson-databind/issues/935
 * http://stackoverflow.com/questions/19894955/spring-jsonignore-not-working
 * http://stackoverflow.com/questions/19655184/no-compiler-is-provided-in-this-environment-perhaps-you-are-running-on-a-jre-ra
 * http://stackoverflow.com/questions/15618061/a-message-body-writer-for-java-class-java-util-arraylist-and-mime-media-type-t
 * http://stackoverflow.com/questions/3566146/setting-property-source-to-org-eclipse-jst-jee-serverjsftut-did-not-find-a
 * http://stackoverflow.com/questions/15601469/jar-not-loaded-see-servlet-spec-2-3-section-9-7-2-offending-class-javax-serv
 * http://stackoverflow.com/questions/15989325/severe-error-configuring-application-listener-of-class-org-springframework-web
 *
 */
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
