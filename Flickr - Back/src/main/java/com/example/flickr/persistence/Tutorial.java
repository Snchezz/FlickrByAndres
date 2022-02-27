package com.example.flickr.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Definimos el nombre de nuestra tabla
@Entity
@Table(name = "tutoriales")
public class Tutorial {

	// Generamos el ID y lo auto-incrementamos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	// Creamos neustras columnas
	@Column(name = "titulo")
	private String titulo;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "publicado")
	private boolean publicado;

	public Tutorial() {

	}

	// Pasamos los atributos
	public Tutorial(String titulo, String descripcion, boolean publicado) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.publicado = publicado;
	}

	// Creamos los gettes and setters
	public long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean esPublicado() {
		return publicado;
	}

	public void setPublicado(boolean esPublicado) {
		this.publicado = esPublicado;
	}

	// Y los pasamos a String
	@Override
	public String toString() {
		return "Tutorial [id= " + id + ", titulo= " + titulo + ", desc= " + descripcion + ", publicado= " + publicado
				+ "]";
	}
}
