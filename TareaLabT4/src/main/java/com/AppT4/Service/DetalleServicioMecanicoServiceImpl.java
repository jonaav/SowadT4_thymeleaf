package com.AppT4.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppT4.Entidad.DetalleServicioMecanico;
import com.AppT4.Persistencia.IDetalleServicioMecanicoDao;

@Service
public class DetalleServicioMecanicoServiceImpl implements IDetalleServicioMecanicoService{

	@Autowired
	private IDetalleServicioMecanicoDao detalleServicioMecanicoDao;
	
	@Override
	public void guardarDetalleServicioMecanico(DetalleServicioMecanico detalleServicioMecanico) {
		
		detalleServicioMecanicoDao.save(detalleServicioMecanico);
		
	}

}
