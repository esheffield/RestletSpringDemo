package com.eddiesheffield.restlet.server.security;

import org.restlet.security.LocalVerifier;

import com.eddiesheffield.restlet.server.data.User;
import com.eddiesheffield.restlet.server.data.UserRepository;

public class AwsApiKeyVerifier extends LocalVerifier
{
	private UserRepository repository;
	
	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public char[] getLocalSecret(String identifier)
	{
		User user = repository.getUser(Integer.valueOf(identifier));
		String apiKey = user.getApiKey();
		
		return apiKey.toCharArray();
	}

}
