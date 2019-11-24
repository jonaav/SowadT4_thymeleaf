package com.AppT4.Service;

import java.util.List;

import com.AppT4.Entidad.Servicio;
import com.AppT4.Entidad.ServicioMecanico;

public interface IServicioService {
	
	List<Servicio> listarServicios();
	Servicio getOne(int idServicio);
}
