package com.AppT4.Persistencia;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.AppT4.Entidad.Usuario;

@Repository
public interface UserRepository extends CrudRepository<Usuario, Integer>  {
    public Usuario findByusuario(String usuario);
}