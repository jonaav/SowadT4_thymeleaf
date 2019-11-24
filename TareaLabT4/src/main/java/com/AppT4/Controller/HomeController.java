package com.AppT4.Controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AppT4.Entidad.Usuario;
import com.AppT4.Service.UsuarioServiceImpl;

@Controller
public class HomeController {
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
		@RequestMapping(value= {"/","/login"},method=RequestMethod.GET)
		public String listarCompra() {
			
			return "/Login";	
		}

		@RequestMapping(value= {"/Home"},method=RequestMethod.GET)
		public String Home(Model model,Principal principal) {
			 Usuario usuario=usuarioServiceImpl.findByUsername(principal.getName());
			 model.addAttribute("email",usuario.getEmail());
			 model.addAttribute("nombreCompleto",usuario.getNombres()+" "+usuario.getApellidos());
			 if(usuario.getAutorizacion().equals("ROLE_ADMIN")) model.addAttribute("tipoDeUsuario","Administrador");
			 else model.addAttribute("tipoDeUsuario","Usuario");
			 model.addAttribute("template","Home");
			return "/fragments/MenuDeUsuario";	
		}
		
		@RequestMapping(value= {"/InformacionCuenta"},method=RequestMethod.GET)
		public String InformacionCuenta(Model model,Principal principal) {
			 Usuario usuario=usuarioServiceImpl.findByUsername(principal.getName());
			 model.addAttribute("email",usuario.getEmail());
			 model.addAttribute("nombres",usuario.getNombres());
			 model.addAttribute("apellidos",usuario.getApellidos());
			 model.addAttribute("usuario",usuario.getUsuario());
			 model.addAttribute("nombreCompleto",usuario.getNombres()+" "+usuario.getApellidos());
			 if(usuario.getAutorizacion().equals("ROLE_ADMIN")) model.addAttribute("tipoDeUsuario","Administrador");
			 else model.addAttribute("tipoDeUsuario","Usuario");
			 model.addAttribute("template","DatosDeCuenta");
			return "/fragments/MenuDeUsuario";	
		}


}
