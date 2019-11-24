package com.AppT4.Persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AppT4.Entidad.Servicio;

@Repository
public interface IServicioDao extends JpaRepository<Servicio, Integer>  {
	
	
	public List<Servicio> findAll();

}
