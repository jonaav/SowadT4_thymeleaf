package com.AppT4.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppT4.Entidad.ServiciosSolicitados;
import com.AppT4.Persistencia.IServicioSolicitadoDao;
@Service
public class servicioSolicitadoServiceImpl implements IServicioSolicitadoService{

	@Autowired
	private IServicioSolicitadoDao servicioSolicitadoDao;
	
	@Override
	public void guardarServicioSolicitado(ServiciosSolicitados servicioSolicitado) {
		
		servicioSolicitadoDao.save(servicioSolicitado);
	}
	
}
