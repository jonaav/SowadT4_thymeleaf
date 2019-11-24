package com.AppT4.Service;

import java.util.List;

import com.AppT4.Entidad.ServicioMecanico;

public interface IServicioMecanicoService {

	void guardarServicioMecanico(ServicioMecanico servicioMecanico);
	List<ServicioMecanico>findAll();
	void eliminarServicioMecanico(ServicioMecanico idServicioMecanico);
	ServicioMecanico getOne(int idServicioMecanico);
}
