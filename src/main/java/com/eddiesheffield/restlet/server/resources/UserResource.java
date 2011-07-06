package com.eddiesheffield.restlet.server.resources;

import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.eddiesheffield.restlet.server.data.User;
import com.eddiesheffield.restlet.server.data.UserRepository;

public class UserResource extends ServerResource {
	private UserRepository userRepository;
	
	public UserResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Get
	public Representation getUser() {
		Integer userId = Integer.valueOf((String)getRequestAttributes().get("userId"));
		User user = userRepository.getUser(userId);
		
		JSONObject json = new JSONObject(user);
		JsonRepresentation rep = new JsonRepresentation(json);
		return rep;
	}
	
	@Put("form")
	public void updateUser(Form form) {
		Integer userId = Integer.valueOf((String)getRequestAttributes().get("userId"));
		User user = userRepository.getUser(userId);

		String name = form.getFirstValue("name");
		Integer age = Integer.parseInt(form.getFirstValue("age"));
		String favoriteFood = form.getFirstValue("favoriteFood");
		
		user.setName(name);
		user.setAge(age);
		user.setFavoriteFood(favoriteFood);
		
		userRepository.saveUser(user);
	}
	
	@Delete
	public void deleteUser() {
		Integer userId = Integer.valueOf((String)getRequestAttributes().get("userId"));
		userRepository.deleteUser(userId);
	}
}
