package com.huntsoft.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huntsoft.blog.entity.Publicacion;

@Repository
public interface IPublicacionRepository extends JpaRepository<Publicacion, Long> {

}
