package com.springboot.rest.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest.api.model.User;
import com.springboot.rest.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUserData(User user) {
		return userRepository.save(user);
	}

	public List<User> getListOfUser() {
		return userRepository.findAll();
	}

	public User getSingleUser(long empid) throws IllegalAccessException {
		User user = userRepository.findById(empid).orElseThrow(() -> new IllegalAccessException("NOt FOund"));
		return user;
	}

	public User getUpdateUser(User user, long empid) {
		User existingUser = userRepository.findById(empid)
				.orElseThrow(() -> new IllegalArgumentException("Id not Found"));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());

		User updateUser = userRepository.save(existingUser);
		return updateUser;
	}

	public void getDeleteUser(long userid) {
		User user = userRepository.findById(userid).orElseThrow(() -> new IllegalArgumentException("No id Found"));
		userRepository.delete(user);
	}

}
