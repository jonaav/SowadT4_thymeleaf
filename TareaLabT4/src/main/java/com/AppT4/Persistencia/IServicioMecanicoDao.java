package com.AppT4.Persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.AppT4.Entidad.ServicioMecanico;

@Repository
public interface IServicioMecanicoDao extends JpaRepository<ServicioMecanico, Integer> {

	public List<ServicioMecanico>findAll();
	
	
}
