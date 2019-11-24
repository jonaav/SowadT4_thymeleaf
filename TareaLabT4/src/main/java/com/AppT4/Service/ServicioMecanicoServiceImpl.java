package com.AppT4.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppT4.Entidad.ServicioMecanico;
import com.AppT4.Persistencia.IServicioDao;
import com.AppT4.Persistencia.IServicioMecanicoDao;

@Service
public class ServicioMecanicoServiceImpl implements IServicioMecanicoService{

	@Autowired
	private IServicioMecanicoDao servicioMecanicoDao;
	@Override
	public void guardarServicioMecanico(ServicioMecanico servicioMecanico) {
		
		servicioMecanicoDao.save(servicioMecanico);
		
	}
	@Override
	public List<ServicioMecanico> findAll() {
		// TODO Auto-generated method stub
		return servicioMecanicoDao.findAll();
	}
	@Override
	public void eliminarServicioMecanico(ServicioMecanico servicioMecanico) {
		servicioMecanicoDao.delete(servicioMecanico);
		
	}
	@Override
	public ServicioMecanico getOne(int  idServicioMecanico) {
		// TODO Auto-generated method stub
		//servicioMecanicoDao.findById(id)
		return servicioMecanicoDao.getOne(idServicioMecanico);
	}

	

}
