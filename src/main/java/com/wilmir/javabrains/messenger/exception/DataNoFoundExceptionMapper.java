package com.wilmir.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wilmir.javabrains.messenger.model.ErrorMessage;

@Provider
public class DataNoFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "https:wilmir.com");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}

}
