package com.eddiesheffield.restlet.client;

import java.io.*;

import org.restlet.*;
import org.restlet.data.*;
import org.restlet.representation.*;

public class ClientApp
{
	public static void main(String[] args) throws IOException
	{
		// Prepare the request
		Request request = new Request(Method.GET, "http://localhost:3000/users/1");

		// Add the client authentication to the call
		ChallengeScheme scheme = ChallengeScheme.HTTP_AWS_S3;
		ChallengeResponse authentication = new ChallengeResponse(scheme, "1", "0123456789ABCDEF0123456789ABCDEF01234567");
		request.setChallengeResponse(authentication);

		// Send the HTTP GET request
		Client client = new Client(Protocol.HTTP);
		Response response = client.handle(request);
		
		  // Write the response entity on the console
        Representation output = response.getEntity();
        output.write(System.out);
	}
}
