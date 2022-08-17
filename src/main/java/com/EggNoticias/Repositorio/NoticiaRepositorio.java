package com.EggNoticias.Repositorio;

import com.EggNoticias.Entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.data.repository.query.QueryCreationException;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String> {

    /*@Query("SELECT n FROM noticia n where n.titulo = :titulo")
    public Noticia buscarPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT n FROM noticia n where n.id = :id")
    public Noticia buscarPorID(@Param("id") String id);*/

}
