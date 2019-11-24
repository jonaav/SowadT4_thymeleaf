package com.AppT4.Persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppT4.Entidad.Cliente;

@Repository
public interface IClienteDao extends CrudRepository<Cliente, Integer> {
	public List<Cliente> findAll();
}
