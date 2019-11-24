package com.AppT4.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppT4.Entidad.Personal;
import com.AppT4.Persistencia.IPersonalDao;
@Service
public class PersonalServiceImpl implements IPersonalService{

	@Autowired
	private IPersonalDao personalDao;
	@Override
	public List<Personal> listarPersonal() {
		// TODO Auto-generated method stub
		return personalDao.findAll();
	}

}
