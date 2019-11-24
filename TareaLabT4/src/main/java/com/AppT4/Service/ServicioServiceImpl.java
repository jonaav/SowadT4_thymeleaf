package com.AppT4.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppT4.Entidad.Servicio;
import com.AppT4.Entidad.ServicioMecanico;
import com.AppT4.Persistencia.IServicioDao;

@Service
public class ServicioServiceImpl implements IServicioService{

	@Autowired
	private IServicioDao servicioDao;
	
	@Override
	public List<Servicio> listarServicios() {
		// TODO Auto-generated method stub
		return   servicioDao.findAll();
	}

	@Override
	public Servicio getOne(int idServicio) {
		// TODO Auto-generated method stub
		return servicioDao.getOne(idServicio);
	}

	
	

}
