package com.huntsoft.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.huntsoft.blog.dto.PublicacionDTO;
import com.huntsoft.blog.entity.Publicacion;
import com.huntsoft.blog.exceptions.ResourceNotFoundException;
import com.huntsoft.blog.repository.IPublicacionRepository;

@Service
public class PublicacionServicioImp implements IPublicacionServicio {

	// Inyeccion de dependencia
	@Autowired
	private IPublicacionRepository publicacionRepository;

	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {

		/*
		 * //Conversion de DTO a Entidad Publicacion publicacion = new Publicacion();
		 * publicacion.setTitulo(publicacionDTO.getTitulo());
		 * publicacion.setDescripcion(publicacionDTO.getDescripcion());
		 * publicacion.setContenido(publicacionDTO.getContenido());
		 * 
		 * //Guardar publicacion Publicacion nuevaPublicacion =
		 * publicacionRepository.save(publicacion);
		 * 
		 * //Conversion de Entidad a DTO PublicacionDTO publicacionRespuesta = new
		 * PublicacionDTO(); publicacionRespuesta.setId(nuevaPublicacion.getId());
		 * publicacionRespuesta.setTitulo(nuevaPublicacion.getTitulo());
		 * publicacionRespuesta.setDescripcion(nuevaPublicacion.getDescripcion());
		 * publicacionRespuesta.setContenido(nuevaPublicacion.getContenido());
		 */

		Publicacion publicacion = mapearEntidad(publicacionDTO);
		Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
		PublicacionDTO publicacionRespuesta = mapearDto(nuevaPublicacion);

		return publicacionRespuesta;
	}

	@Override
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
		
		return mapearDto(publicacionActualizada);
	}

	@Override
	public List<PublicacionDTO> listarTodo(int numberPage, int amountItems) {

		//Agregar paginado
		Pageable pageable = PageRequest.of(numberPage, amountItems);
		Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);
		
		
		List<Publicacion> listaPublicaciones = publicaciones.getContent() ;//publicacionRepository.findAll();

		// Transformar entidad a dto mediante expresiones lambda
		return listaPublicaciones.stream().map(x -> mapearDto(x)).collect(Collectors.toList());
	}

	// Convetir entidad a DTO
	private PublicacionDTO mapearDto(Publicacion publicacion) {
		PublicacionDTO publicacionDTO = new PublicacionDTO();

		publicacionDTO.setId(publicacion.getId());
		publicacionDTO.setTitulo(publicacion.getTitulo());
		publicacionDTO.setDescripcion(publicacion.getDescripcion());
		publicacionDTO.setContenido(publicacion.getContenido());

		return publicacionDTO;
	}

	// Convetir DTO a Entidad
	private Publicacion mapearEntidad(PublicacionDTO publicacionDto) {
		var publicacion = new Publicacion();

		publicacion.setId(publicacionDto.getId());
		publicacion.setTitulo(publicacionDto.getTitulo());
		publicacion.setDescripcion(publicacionDto.getDescripcion());
		publicacion.setContenido(publicacionDto.getContenido());

		return publicacion;
	}

	@Override
	public PublicacionDTO listarPorId(long id) {

		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

		return mapearDto(publicacion);
	}

	@Override
	public void eliminarPublicacion(long id) {
		
		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(()  -> new ResourceNotFoundException("Publicacion", "d", id));
		
		publicacionRepository.delete(publicacion);
		
	}

}
