package com.example.projeto.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
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

import com.example.projeto.entities.Entities;
import com.example.projeto.entities.userDTO.UserDTO;
import com.example.projeto.repository.Repositorio;

import jakarta.validation.Valid;

@RestController
public class Controller {

	@Autowired
	Repositorio rp;
	
	@PostMapping("/usuario")
	public ResponseEntity<Entities> newUser(@RequestBody @Valid UserDTO ud){
		var user = new Entities();
		BeanUtils.copyProperties(ud, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(rp.save(user));
	}
	@PutMapping("/usuario/{idUser}")
	public ResponseEntity<Object> updateUser(@PathVariable(value="idUser") UUID id,
			@RequestBody @Valid UserDTO ud){
		Optional<Entities> user = rp.findById(id);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found!");
		}
		var userUpdate = user.get();
		BeanUtils.copyProperties(ud, userUpdate);
		return ResponseEntity.status(HttpStatus.CREATED).body(rp.save(userUpdate));
	}
	@DeleteMapping("/usuario/{idUser}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "idUser") UUID id){
		Optional<Entities> user = rp.findById(id);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found!");
		}
		rp.delete(user.get());
		return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully!");
		
	}
	@GetMapping("/usuario")
	public ResponseEntity<List<Entities>> getAllUser(){
		List<Entities> users = rp.findAll();
		if(!users.isEmpty()) {
			for(Entities oneUser : users) {
				UUID id = oneUser.getIdUser();
				oneUser.add(linkTo(methodOn(Controller.class).getOneUser(id)).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	@GetMapping("/usuario/{idUser}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value = "idUser") UUID id){
		Optional<Entities> user = rp.findById(id);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found!");
		}
		user.get().add(linkTo(methodOn(Controller.class).getAllUser()).withRel("Users: "));
		return ResponseEntity.status(HttpStatus.OK).body(user.get());
	}
	
}
