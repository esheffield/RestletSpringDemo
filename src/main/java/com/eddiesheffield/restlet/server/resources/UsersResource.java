package com.eddiesheffield.restlet.server.resources;

import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.eddiesheffield.restlet.server.data.User;
import com.eddiesheffield.restlet.server.data.UserRepository;

public class UsersResource extends ServerResource {
	private UserRepository userRepository;
	
	public UsersResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Get
	public Representation getUsers() {
		Collection<User> users = userRepository.getUsers();
		JSONArray ja = new JSONArray();
		for(User user : users) {
			JSONObject json = new JSONObject(user);
			ja.put(json);
		}
		return new JsonRepresentation(ja);
	}
	
	@Post("form")
	public Representation addUser(Form form) {
		String name = form.getFirstValue("name");
		Integer age = Integer.parseInt(form.getFirstValue("age"));
		String favoriteFood = form.getFirstValue("favoriteFood");
		
		User user = userRepository.addUser(name, age, favoriteFood);
		
		return new JsonRepresentation(user);
	}
}
