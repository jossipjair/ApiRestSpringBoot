package com.huntsoft.blog.service;

import java.util.List;

import com.huntsoft.blog.dto.PublicacionDTO;

public interface IPublicacionServicio {

	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	
	public List<PublicacionDTO> listarTodo(int numberPage, int amountItems);
	
	public PublicacionDTO listarPorId(long id);
	
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);
	
	public void eliminarPublicacion(long id);
	
}
