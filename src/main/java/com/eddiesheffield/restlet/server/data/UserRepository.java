package com.eddiesheffield.restlet.server.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
	private Map<Integer, User> users;
	private int maxId = 0;
	
	public UserRepository() {
		this.users = new HashMap<Integer, User>();
		
		// Add an initial user
		this.addUser("Eddie", 29, "Cheeze Whiz");
	}
	
	public User addUser(String name, int age, String favoriteFood) {
		maxId++;
		User user = new User(maxId, name, age, favoriteFood);
		users.put(user.getId(), user);
		
		return user;
	}
	
	public Collection<User> getUsers() {
		return users.values();
	}
	
	public User getUser(int id) {
		return users.get(id);
	}
	
	public void deleteUser(int id) {
		users.remove(id);
	}

	public void saveUser(User user) {
		users.put(user.getId(), user);
	}
}
