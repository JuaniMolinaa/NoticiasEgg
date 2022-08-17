package com.EggNoticias.Controladores;

import com.EggNoticias.Entidades.Noticia;
import com.EggNoticias.Excepciones.MiException;
import com.EggNoticias.Servicio.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/cargar")//localhost:8080/noticia/cargar
    public String cargar() {
        return "cargar_noticia.html";
    }//cierra cargar

    @PostMapping("/llenado")//localhost:8080/noticia/llenado
    public String llenarNoticia(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) throws MiException {
        try {
            noticiaServicio.crearNoticia(titulo, cuerpo);
            modelo.put("exito", "La noticia fue cargada correctamente");
        } catch (MiException e) {
            //Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, e); muestra error en output
            modelo.put("error", e.getMessage());
            return "cargar_noticia.html";
        }
        return "index.html";
    }//cierra llenado

    @GetMapping("/listado")//localhost:8080/noticia/listado
    public String listar(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "noticias_list.html";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap modelo) {

        modelo.put("noticia", noticiaServicio.getOne(id));

        return "noticias_editar.html";
    }

    @PostMapping("/editar/{id}")//localhost:8080/noticia/listado
    public String editar(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo) throws MiException {
        try {
            noticiaServicio.modificarNoticia(id, titulo, cuerpo);
            return "redirect:../listado";
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "noticias_editar.html";
        }
    }
    
    @GetMapping("/mostrarnoticias")//localhost:8080/noticia/mostrarnoticias
    public String mostrarNoticias(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "mostrar_noticias.html";
    }

}//cierra NoticiaControlador
