package com.EggNoticias.Controladores;

import com.EggNoticias.Entidades.Usuario;
import com.EggNoticias.Servicio.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/dashboard")//localhost:8080/admin/dashboard
    public String panelAdministrativo() {
        return "panel.html";
    }

    @GetMapping("/usuarios")//localhost:8080/admin/usuarios
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);

        return "usuario_list";
    }

    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id) {

        usuarioServicio.cambiarRol(id);
        return "redirect:/admin/usuarios";
    }

}
