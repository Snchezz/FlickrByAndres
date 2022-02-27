package com.example.flickr.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASANCHEZ_USER")
public class User implements Serializable {

	/**
	 * SERIAL VERSION
	 */
	private static final long serialVersionUID = 1L;

	// Creamos la clave primaria, usuario contraseña y correo
	private long entytyID;

	private String name;

	private String mail;

	private String password;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	public long getEntytyID() {
		return entytyID;
	}

	public void setEntytyID(long entytyID) {
		this.entytyID = entytyID;
	}

	// Creamos los getter and setter y asignamos la columna dentro de la tabla
	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MAIL", nullable = false)
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
