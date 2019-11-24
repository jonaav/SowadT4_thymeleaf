package com.AppT4.Persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppT4.Entidad.Personal;
@Repository
public interface IPersonalDao extends CrudRepository<Personal, Integer> {
	
	public List<Personal> findAll();
}
