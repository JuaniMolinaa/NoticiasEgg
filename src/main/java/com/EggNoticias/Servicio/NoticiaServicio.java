package com.EggNoticias.Servicio;

import com.EggNoticias.Entidades.Noticia;
import com.EggNoticias.Excepciones.MiException;
import com.EggNoticias.Repositorio.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticiaServicio {

    @Autowired //indica al sv que inicializa la variable 
    private NoticiaRepositorio noticiaRepositorio;

    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiException {

        validarDatos(titulo, cuerpo);

        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setAlta(new Date());

        noticiaRepositorio.save(noticia);

    }//cierra crearNoticia

    public List<Noticia> listarNoticias() {

        List<Noticia> noticias = new ArrayList();
        noticias = noticiaRepositorio.findAll();

        return noticias;
    }//cierra listarNoticias

    @Transactional
    public void modificarNoticia(String id, String titulo, String cuerpo) throws MiException {

        if (id == null || id.isEmpty()) {
            throw new MiException("El id ingresado no corresponde a una noticia");
        }
        validarDatos(titulo, cuerpo);

        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Noticia noticia = respuesta.get();

            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);

            noticiaRepositorio.save(noticia);
        }
    }//cierra modificarNoticia

    private void validarDatos(String titulo, String cuerpo) throws MiException {
        if (titulo == null || titulo.isEmpty()) {
            throw new MiException("El titulo no puede estar vacio");
        }
        if (cuerpo == null || cuerpo.isEmpty()) {
            throw new MiException("El cuerpo no puede estar vacio");
        }
    }//cierra validarDatos

    public Noticia getOne(String id){
        return noticiaRepositorio.getOne(id);
    }
}//cierra NoticiaServicio
