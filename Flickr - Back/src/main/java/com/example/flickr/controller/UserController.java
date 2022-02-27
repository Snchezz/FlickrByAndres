package com.example.flickr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flickr.persistence.TutorialRepository;
import com.example.flickr.persistence.Tutorial;
import com.example.flickr.persistence.User;
import com.example.flickr.services.UserManagementServiceI;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserManagementServiceI userService;
	@Autowired
	TutorialRepository tutorialRepository;

	// Método para obtener todos los usuarios
	@GetMapping("/get")
	public List<User> getAll() {
		return userService.buscarTodos();
	}

	// Método para añadir un usuario
	@PostMapping("/insert")
	public void insert(@RequestBody User user) {
		userService.insertarUsuario(user);
	}

	// Método para eliminar un usuario
	@DeleteMapping("/delete")
	public void delete(@RequestBody User user) {
		userService.eliminarUsuario(user);
	}

	// Método para login
	@PostMapping("/find")
	public User searchByMailAndPassword(@RequestBody User user) {
		return userService.buscarPorMailYContraseña(user.getMail(), user.getPassword());
	}

	// Método para buscar si un nombre de usuario existe (sin uso)
	@PostMapping("/findname")
	public List<User> searchByName(@RequestBody User user) {
		return userService.buscarPorNombre(user.getName());
	}

	// Método para obtener todas las imagenes
	@GetMapping("/imagen")
	public ResponseEntity<List<Tutorial>> getTodosTutoriales(@RequestParam(required = false) String titulo) {
		try {
			List<Tutorial> tutoriales = new ArrayList<Tutorial>();

			if (titulo == null)
				tutorialRepository.findAll().forEach(tutoriales::add);
			else
				tutorialRepository.findByTituloContaining(titulo).forEach(tutoriales::add);

			if (tutoriales.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutoriales, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Metodo para obtener el id de la imagen 
	@GetMapping("/imagen/{id}")
	public ResponseEntity<Tutorial> getTutorialPorId(@PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Metodo para crear una imagen 
	@PostMapping("/imagen")
	public ResponseEntity<Tutorial> crearTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitulo(), tutorial.getDescripcion(), false));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Metodo para actualizar la imagen 
	@PutMapping("/imagen/{id}")
	public ResponseEntity<Tutorial> actualizarTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitulo(tutorial.getTitulo());
			_tutorial.setDescripcion(tutorial.getDescripcion());
			_tutorial.setPublicado(tutorial.esPublicado());
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Metodo para eliminar una imagen
	@DeleteMapping("/imagen/{id}")
	public ResponseEntity<HttpStatus> eliminarTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Metodo para eliminar todos los tutoriales
	@DeleteMapping("/imagen")
	public ResponseEntity<HttpStatus> eliminarTodosTutoriales() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Metodo para publicar / despublicar (sin usar)
	@GetMapping("/imagen/publicado")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			List<Tutorial> tutoriales = tutorialRepository.findByPublicado(true);

			if (tutoriales.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutoriales, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
