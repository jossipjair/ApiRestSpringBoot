package com.huntsoft.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String nombreRecurso;
	
	private String nombreCampo;
	
	private long valorCampo;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String nombreRecurso, String nombreCampo, long valorCampo) {
		super(String.format("%s no encontrada con campo %s cuyo valor es '%s'", nombreRecurso, nombreCampo, valorCampo ));
		this.nombreRecurso = nombreRecurso;
		this.nombreCampo = nombreCampo;
		this.valorCampo = valorCampo;
	}

	public String getNombreRecurso() {
		return nombreRecurso;
	}

	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public long getValorCampo() {
		return valorCampo;
	}

	public void setValorCampo(long valorCampo) {
		this.valorCampo = valorCampo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
