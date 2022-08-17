package com.EggNoticias.Repositorio;

import com.EggNoticias.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
    
    @Query("SELECT u FROM Usuario u Where u.email = :email")
    public Usuario buscarPorEmail(@Param("email")String email);
    
}
