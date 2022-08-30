package com.huntsoft.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huntsoft.blog.dto.PublicacionDTO;
import com.huntsoft.blog.service.IPublicacionServicio;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

	@Autowired
	private IPublicacionServicio publicacionServicio;

	@PostMapping
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
		return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<PublicacionDTO> listarPublicaciones(
			@RequestParam(value = "PageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "PageSize", defaultValue = "10", required = false) int amountItems) {
		return publicacionServicio.listarTodo(pageNumber, amountItems);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> listarPorId(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(publicacionServicio.listarPorId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizarPublicacion(@RequestBody PublicacionDTO publicacionDTO,
			@PathVariable(name = "id") long id) {

		PublicacionDTO publicacionRespuesta = publicacionServicio.actualizarPublicacion(publicacionDTO, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id) {
		publicacionServicio.eliminarPublicacion(id);
		return new ResponseEntity<>("¡Publicación con id " + id + " eliminado con éxito!", HttpStatus.OK);
	}

}
