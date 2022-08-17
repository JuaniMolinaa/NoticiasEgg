package com.EggNoticias.Controladores;

import com.EggNoticias.Entidades.Usuario;
import com.EggNoticias.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.servlet.function.RequestPredicates.headers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


@Controller
@RequestMapping("/imagen")
public class ImagenControlador {
    
    @Autowired
    UsuarioServicio usuarioServicio;
    
    @GetMapping("/perfil/{id}")//localhost:8080/imagen/perfil/id
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id){
        
        Usuario usuario = usuarioServicio.getOne(id);
        
        byte[] imagen = usuario.getImagen().getContenido();
        
        //dice al navegador que se devuelve una imagen
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
       
        return new ResponseEntity<>(imagen,headers,HttpStatus.OK);
        
    }
}
