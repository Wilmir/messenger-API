package com.wilmir.javabrains.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wilmir.javabrains.messenger.model.Message;

public class RestApiClient {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		
		String message= client
				.target("http://localhost:8080/messenger/webapi/messages/2")
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		//Message message = response.readEntity(Message.class);
		//System.out.println(message.getMessage());
		System.out.println(message);
		
	}
}
 