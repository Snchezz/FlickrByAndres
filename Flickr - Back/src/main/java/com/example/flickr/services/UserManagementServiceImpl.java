package com.example.flickr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flickr.persistence.User;
import com.example.flickr.persistence.UserRepositoryI;

// Todos nuestros metodos implementados
@Service
public class UserManagementServiceImpl implements UserManagementServiceI {

	// Llamamos a nuestro repositorio
	@Autowired
	private UserRepositoryI userRepo;

	// Buscar todos los usuarios
	@Override
	public List<User> buscarTodos() {
		return userRepo.findAll();
	}

	// Añadir un usuario
	@Override
	public void insertarUsuario(User user) {
		userRepo.save(user);
	}

	// Eliminar un usuario
	@Override
	public void eliminarUsuario(User user) {
		userRepo.delete(user);
	}

	// Login
	@Override
	public User buscarPorMailYContraseña(String mail, String password) {
		return userRepo.findByMailAndPassword(mail, password);
	}

	// Buscar por nombre
	@Override
	public List<User> buscarPorNombre(String name) {
		return userRepo.findByName(name);
	}
}