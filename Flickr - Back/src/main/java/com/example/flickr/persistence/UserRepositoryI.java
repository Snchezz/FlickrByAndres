package com.example.flickr.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Creamos el repositorio, los métodos que vamos a usar, con JpaRepository tenemos
//metodos como add, eliminate, actualizar, modificar
@Repository
public interface UserRepositoryI extends JpaRepository<User, Long> {

	User findByMailAndPassword(final String mail, final String password);

	List<User> findByName(final String name);
}