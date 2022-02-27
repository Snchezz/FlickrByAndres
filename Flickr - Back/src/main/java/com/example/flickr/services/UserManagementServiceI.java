package com.example.flickr.services;

import java.util.List;

import com.example.flickr.persistence.User;

// Todos nuestros métodos declarados, sin importarlos
public interface UserManagementServiceI {

	List<User> buscarTodos();

	void insertarUsuario(User user);

	void eliminarUsuario(User user);

	User buscarPorMailYContraseña(String mail, String password);

	List<User> buscarPorNombre(String name);
}
