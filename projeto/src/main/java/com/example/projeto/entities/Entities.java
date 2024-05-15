package com.example.projeto.entities;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class Entities extends RepresentationModel<Entities> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idUser;
	private String name;
	private String email;
	
	public UUID getIdUser() {
		return idUser;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	
	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
