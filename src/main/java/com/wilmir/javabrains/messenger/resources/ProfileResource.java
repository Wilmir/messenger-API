package com.wilmir.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.wilmir.javabrains.messenger.model.Profile;
import com.wilmir.javabrains.messenger.service.ProfileService;

@Path("/profiles")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class ProfileResource {
	ProfileService profileService = new ProfileService();
	
	
	@GET
	public Response getProfiles(){
		List<Profile> profiles = profileService.getProfiles();
		return Response.status(Status.OK).entity(profiles).header("Access-Control-Allow-Origin", "*").build();	
	}
	

	@POST
	public Response addProfile(Profile profile) {
		Profile person = profileService.addProfile(profile);
		
		return Response.status(Status.OK).entity(person).header("Access-Control-Allow-Origin", "*").build();	

		
	}
	
	@GET
	@Path("/{profileName}")
	public Response getProfile(@PathParam("profileName") String profileName) {
		Profile person = profileService.getProfile(profileName);
		
		return Response.status(Status.OK).entity(person).header("Access-Control-Allow-Origin", "*").build();	
		
	}

	@PUT
	@Path("/{profileName}")
	public Response updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		Profile person =  profileService.updateProfile(profile);
		
		return Response.status(Status.OK).entity(person).header("Access-Control-Allow-Origin", "*").build();	

	}
	
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
		
	}
	
	
	
}
