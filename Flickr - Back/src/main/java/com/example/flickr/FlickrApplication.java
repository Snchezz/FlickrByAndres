package com.example.flickr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.flickr.persistence.User;
import com.example.flickr.services.UserManagementServiceI;

@SpringBootApplication

// CommandLineRunner, para que cuando inicie realice las acciones
public class FlickrApplication implements CommandLineRunner {

	@Autowired
	private UserManagementServiceI userService;
	
	public static void main(String[] args) {
		SpringApplication.run(FlickrApplication.class, args);
	}

	@Override
	// Crar dos usuarios para poder iniciar sesión
	public void run(String... args) throws Exception {
		User user = new User();
		User user1 = new User();

		// Creamos y asignamos datos
		user.setName("Andrés");
		user.setMail("asanchez@gmail.com");
		user.setPassword("1234");
		
		user1.setName("Javi");
		user1.setMail("javidev@gmail.com");
		user1.setPassword("1234");
		
		userService.insertarUsuario(user);
		userService.insertarUsuario(user1);
	}	
}
