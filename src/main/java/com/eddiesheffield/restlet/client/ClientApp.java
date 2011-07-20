package com.eddiesheffield.restlet.client;

import java.io.IOException;

import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;

public class ClientApp
{
	public static void main(String[] args) throws IOException
	{
		ChallengeResponse authentication = createChallengeResponse(0, "0123456789ABCDEF0123456789ABCDEF01234567");
		
		Request request = createGetUserRequest(authentication, 1);

		executeRequest(request);

		request = createNewUserRequest(authentication, "Frank", 38, "Vegemite");
		
		executeRequest(request);
	}

	private static Request createGetUserRequest(
			ChallengeResponse authentication, int i) {
		// Prepare the request
		Request request = new Request(Method.GET, "http://localhost:3000/users/" + i);

		// Set the AWS ChallengeResponse on the request
		request.setChallengeResponse(authentication);

		return request;
	}

	private static void executeRequest(Request request)
			throws IOException {
		Client client = new Client(Protocol.HTTP);
		Response response = client.handle(request);
		
		  // Write the response entity on the console
        Representation output = response.getEntity();
		System.out.println("Status: " + response.getStatus());
        output.write(System.out);
	}

	private static Request createNewUserRequest(ChallengeResponse authentication, String name, int age, String favoriteFood) {
		// Prepare the request
		Request request = new Request(Method.POST, "http://localhost:3000/users");

		// Set the AWS ChallengeResponse on the request
		request.setChallengeResponse(authentication);

		// Create the data Representation and add to request
		Representation representation = getNewUserRepresentation(name, age, favoriteFood);
		request.setEntity(representation);
		return request;
	}

	private static ChallengeResponse createChallengeResponse(int callerId, String apiKey) {
		// Create the AWS ChallengeResponse
		ChallengeScheme scheme = ChallengeScheme.HTTP_AWS_S3;
		ChallengeResponse authentication = new ChallengeResponse(scheme, String.valueOf(callerId), apiKey);
		return authentication;
	}

	private static Representation getNewUserRepresentation(String name,
			int age, String favoriteFood) {
		Form form = new Form();
		form.add("name", name);
		form.add("age", String.valueOf(age));
		form.add("favoriteFood", favoriteFood);
		return form.getWebRepresentation();
	}
}
