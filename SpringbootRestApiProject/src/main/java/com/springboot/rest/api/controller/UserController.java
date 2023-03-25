package com.springboot.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.api.model.User;
import com.springboot.rest.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "User Management System", description = "Operations Pertaining to User in User Management System")
@RestController
public class UserController {

	@Autowired(required = true)
	private UserService userService;

	@ApiOperation(value = "Saving an user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SuccessFully Retrieved List"),
			@ApiResponse(code = 401, message = "You are not authorize to view resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/add")
	public ResponseEntity<User> addUserData(
			@ApiParam(value = "Employee Object Store in database table", required = true) @RequestBody User user) {
		return new ResponseEntity<User>(userService.addUserData(user), HttpStatus.CREATED);
	}

	@ApiOperation(value = "view list of available user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SuccessFully Retrieved List"),
			@ApiResponse(code = 401, message = "You are not authorize to view resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(path = "/list", produces = "application/json")
	public ResponseEntity<List<User>> getListOfUser() {
		return new ResponseEntity<List<User>>(userService.getListOfUser(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get an User By Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "SuccessFully Retrieved List"),
			@ApiResponse(code = 401, message = "You are not authorize to view resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(path = "/single/{empid}", produces = "application/json")
	public ResponseEntity<User> getSingleUser( 
			@ApiParam(value = "User Id from which User Object will retrieve", required = true)
			@PathVariable("empid") long empid)
			throws IllegalAccessException {
		return new ResponseEntity<User>(userService.getSingleUser(empid), HttpStatus.OK);
	}

	@ApiOperation(value = "Update an User")
	@PutMapping(path = "/update/{empid}", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SuccessFully Retrieved List"),
			@ApiResponse(code = 401, message = "You are not authorize to view resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<User> getUpdateUser(
			@ApiParam(value = "Update User Object",required = true)
			@RequestBody User user, 
			@ApiParam(value = "User Id to update User Object",required = true) @PathVariable("empid") long empid) {
		return new ResponseEntity<User>(userService.getUpdateUser(user, empid), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete an User")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "SuccessFully Retrieved List"),
			@ApiResponse(code = 401, message = "You are not authorize to view resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@DeleteMapping(path = "/delete/{userid}", produces = "application/json")
	public String getDeleteUser(
			@ApiParam(value = "User id from which user object will delete from user table",required = true)
			@PathVariable("userid") long userid) {
		userService.getDeleteUser(userid);
		return "User has been SuccessFully Deleted with " + userid;
	}
}
