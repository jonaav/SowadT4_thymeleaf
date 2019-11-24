package com.AppT4.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.AppT4.Entidad.Usuario;
import com.AppT4.Persistencia.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
	
    @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    Usuario usuario = userRepository.findByusuario(username);
	    List grantList = new ArrayList();
	    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuario.getAutorizacion());
	    grantList.add(grantedAuthority);
			
	    return new User(usuario.getUsuario(), usuario.getPassword(), grantList);
          
    }
}