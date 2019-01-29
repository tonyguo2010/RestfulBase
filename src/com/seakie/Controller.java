package com.seakie;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "", description = "Restful framework", protocols = "http")
@RestController
public class Controller {

	@ApiOperation(value = "Get all the messages")
	@GetMapping(value = "/messages")
	public ConcurrentHashMap<Integer, Message> getAllMessages() {
		return Messages.getAll();
	}

	@ApiOperation(notes = "Entity requiring", response = String.class, value = "")
	@GetMapping(value = "/message/{id}")
	public Message getMessage(@PathVariable int id, HttpServletResponse response) {
		Message result = Messages.find(id);
		
		if (result != null) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		
		return result;
	}

	@ApiOperation(notes = "Creating", response = String.class, value = "")
//	@ApiImplicitParams({ 
//		@ApiImplicitParam(value = "ID", required = true, dataType = "int"),
//		@ApiImplicitParam(value = "Some name", required = true, type = "String"),
//		@ApiImplicitParam(value = "Some value", required = true, type = "String") 
//		})
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Good"), 
		@ApiResponse(code = 406, message = "Existed") 
		})
	@PostMapping(value = "/message")
	public ConcurrentHashMap<Integer, Message> postMessage(Message message, HttpServletResponse response) {
		if (Messages.addMessage(message) == false) {
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		} else {
			response.setStatus(HttpServletResponse.SC_CREATED);
		}

		return Messages.getAll();
	}

	@ApiOperation(notes = "Entity updating", response = String.class, value = "")
	@PutMapping(value = "/message")
	public String putMessage(Message message, HttpServletResponse response) {
		if (Messages.putMessage(message)){
			return "Item updated";
		} else {
			return "Item set";
		}
	}
	
	@ApiOperation(notes = "Entity updating", response = String.class, value = "")
	@PatchMapping(value = "/message/{id}")
	public String patchMessage(@PathVariable int id, Message message, HttpServletResponse response) {
		if (Messages.updateMessage(id, message)){
			response.setStatus(HttpServletResponse.SC_FOUND);
			return "Item updated";
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "Item not found";
		}
	}

	@ApiOperation(notes = "Entity deleting", response = String.class, value = "")
	@DeleteMapping(value = "/message/{id}")
	public String delMessage(@PathVariable int id, HttpServletResponse response) {
		Message result = Messages.find(id);
		
		if (result != null) {
			Messages.remove(id);
			response.setStatus(HttpServletResponse.SC_OK);
			return "Deleted";
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "Item not found";
		}
		
	}
	
}
