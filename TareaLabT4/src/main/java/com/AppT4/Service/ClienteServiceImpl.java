package com.AppT4.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppT4.Entidad.Cliente;
import com.AppT4.Persistencia.IClienteDao;
@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	@Override
	public List<Cliente> listarClientes() {
		// TODO Auto-generated method stub
		return clienteDao.findAll();
	}

}
