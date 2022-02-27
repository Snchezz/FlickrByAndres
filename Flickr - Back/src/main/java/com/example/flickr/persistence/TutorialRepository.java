package com.example.flickr.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// Creamos el repositorio, los métodos que vmaos a usar, con JpaRepository tenemos
// metodos como add, eliminate, actualizar, modificar
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
	List<Tutorial> findByPublicado(boolean publicado);

	List<Tutorial> findByTituloContaining(String titulo);
}