package com.AppT4.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppT4.Entidad.Usuario;
import com.AppT4.Persistencia.UserRepository;
@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private UserRepository usuarioDao;
	@Override
	public Usuario findByUsername(String usuario) {
		// TODO Auto-generated method stub
		return usuarioDao.findByusuario(usuario);
	}
	

}
