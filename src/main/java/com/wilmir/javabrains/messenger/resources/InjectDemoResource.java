package com.wilmir.javabrains.messenger.resources;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/annotations")
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	public String getParamsUsingAnnotations(@MatrixParam("matrix") String matrix, @HeaderParam("Accept") String header, @CookieParam("JSESSIONID") String cookie) {
		return "MatrixParam: " + matrix + "; Accept: " + header + "; Cookie: " + cookie;
	}
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		
		
		String path = uriInfo.getAbsolutePath().toString();
		
		String cookies =  headers.getCookies().toString();
		
		return "Path: " + path + "\n\n" + "Cookies: " + cookies;

		
	}
	
	
}
