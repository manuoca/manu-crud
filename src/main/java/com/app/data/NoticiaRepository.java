package com.app.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Noticia;

@Repository
public interface NoticiaRepository extends CrudRepository<Noticia, Long> {

}
