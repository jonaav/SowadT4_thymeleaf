package com.AppT4.Controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AppT4.Entidad.Cliente;
import com.AppT4.Entidad.DetalleServicioMecanico;
import com.AppT4.Entidad.Servicio;
import com.AppT4.Entidad.ServicioMecanico;
import com.AppT4.Entidad.ServiciosSolicitados;
import com.AppT4.Entidad.Usuario;
import com.AppT4.Service.ClienteServiceImpl;
import com.AppT4.Service.DetalleServicioMecanicoServiceImpl;
import com.AppT4.Service.PersonalServiceImpl;
import com.AppT4.Service.ServicioMecanicoServiceImpl;
import com.AppT4.Service.ServicioServiceImpl;
import com.AppT4.Service.UsuarioServiceImpl;
import com.AppT4.Service.servicioSolicitadoServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ServicioMecanicoController {

	 ObjectMapper Obj = new ObjectMapper();
	 @Autowired
     private ServicioMecanicoServiceImpl servicioMecanicoServiceImpl;
	 @Autowired
	 private ServicioServiceImpl servicioServiceImpl;
	 @Autowired
	 private PersonalServiceImpl personalServiceImpl;
	 @Autowired
	 private ClienteServiceImpl clienteServiceImpl;
	 @Autowired
	 private servicioSolicitadoServiceImpl servicioSolicitadoServiceImpl;
	 @Autowired
	 private DetalleServicioMecanicoServiceImpl detalleServicioMecanicoServiceImpl;
	 @ElementCollection
     private List<DetalleServicioMecanico> listaDetalleServicioMecanico=  new ArrayList<DetalleServicioMecanico>();
	 @Autowired
	 private UsuarioServiceImpl usuarioServiceImpl;
		
	@RequestMapping(value= {"/ServicioMecanico/listar"},method=RequestMethod.GET)
	public String listarServicios(Model model,Principal principal) {
		
		 Usuario usuario=usuarioServiceImpl.findByUsername(principal.getName());
		 model.addAttribute("nombreCompleto",usuario.getNombres()+" "+usuario.getApellidos());
		 model.addAttribute("email",usuario.getEmail());
		 if(usuario.getAutorizacion().equals("ROLE_ADMIN"))model.addAttribute("tipoDeUsuario","Administrador");
		 else model.addAttribute("tipoDeUsuario","Usuario");
		 
		model.addAttribute("template","ListarServicios");
		model.addAttribute("listaServicioMecanico",servicioMecanicoServiceImpl.findAll());
		return "/fragments/MenuDeUsuario";	
	}

	@RequestMapping(value= {"/ServicioMecanico/Gestionar"},method=RequestMethod.GET)
	public String gestionarServicios(Model model,Principal principal) {
		
		Usuario usuario=usuarioServiceImpl.findByUsername(principal.getName());
		 model.addAttribute("nombreCompleto",usuario.getNombres()+" "+usuario.getApellidos());
		 model.addAttribute("email",usuario.getEmail());
		 if(usuario.getAutorizacion().equals("ROLE_ADMIN"))model.addAttribute("tipoDeUsuario","Administrador");
		 else model.addAttribute("tipoDeUsuario","Usuario");
		 
		 listaDetalleServicioMecanico.clear();
		model.addAttribute("template","GestionarServicios");
		model.addAttribute("listaServicios",servicioServiceImpl.listarServicios());
		return "/fragments/MenuDeUsuario";	
	}
	
	@RequestMapping(value="/ServicioMecanico/listarPersonal", method = RequestMethod.GET,produces = "application/json")
	@ResponseBody 
	public String listaPersonal() throws JsonProcessingException
	{
		return  Obj.writeValueAsString(personalServiceImpl.listarPersonal()); 
	}
	
	@RequestMapping(value="/ServicioMecanico/listarClientes", method = RequestMethod.GET,produces = "application/json")
	@ResponseBody 
	public String listaClientes() throws JsonProcessingException
	{
		return  Obj.writeValueAsString(clienteServiceImpl.listarClientes()); 
	}
	
	@RequestMapping(value="/lista", method = RequestMethod.GET,produces = "application/json")
	@ResponseBody 
	public String lista(HttpServletRequest request) throws JsonProcessingException
	{
		return  Obj.writeValueAsString(listaDetalleServicioMecanico); 
	}
		
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/ServicioMecanico/Guardar" , method = RequestMethod.POST)
	public @ResponseBody String guardarServicioMecanico(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{	
		
		ServicioMecanico servicioMecanico= Obj.readValue(request.getParameter("ServicioMecanico"),ServicioMecanico.class);
		servicioMecanicoServiceImpl.guardarServicioMecanico(servicioMecanico);
		
		for (DetalleServicioMecanico detalleServicioMecanico : listaDetalleServicioMecanico) {
			ServiciosSolicitados servicioSolicitado=new ServiciosSolicitados();
			Cliente cliente=new Cliente();
			cliente.setCli_id(servicioMecanico.getCli_id().getCli_id());
			servicioSolicitado.setCliente(cliente);
			servicioSolicitado.setFecha(servicioMecanico.getServ_fecha().toString());
			servicioSolicitado.setServicio(detalleServicioMecanico.getServicio());
			
			detalleServicioMecanico.setServicioMecanico(servicioMecanico);
			
			servicioSolicitadoServiceImpl.guardarServicioSolicitado(servicioSolicitado);
			detalleServicioMecanicoServiceImpl.guardarDetalleServicioMecanico(detalleServicioMecanico);
		}
		
		listaDetalleServicioMecanico.clear();
	    return request.getParameter("guardado");
	
	}
	
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/ServicioMecanico/Editar" , method = RequestMethod.POST)
	public @ResponseBody String editarServicioMecanico(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{	
		
		ServicioMecanico servicioMecanico= Obj.readValue(request.getParameter("ServicioMecanico"),ServicioMecanico.class);
		servicioMecanico.getServ_fecha().setDate(servicioMecanico.getServ_fecha().getDate()+1);
		servicioMecanico.setDetalleServicioMecanico(listaDetalleServicioMecanico);
		servicioMecanicoServiceImpl.guardarServicioMecanico(servicioMecanico);
		for (DetalleServicioMecanico detalleServicioMecanico : listaDetalleServicioMecanico) {
			/*
			ServiciosSolicitados servicioSolicitado=new ServiciosSolicitados();
			Cliente cliente=new Cliente();
			cliente.setCli_id(servicioMecanico.getCli_id().getCli_id());
			servicioSolicitado.setCliente(cliente);
			servicioSolicitado.setFecha(servicioMecanico.getServ_fecha().toString());
			servicioSolicitado.setServicio(detalleServicioMecanico.getServicio());*/
			
			detalleServicioMecanico.setServicioMecanico(servicioMecanico);
			
			//servicioSolicitadoServiceImpl.guardarServicioSolicitado(servicioSolicitado);
			detalleServicioMecanicoServiceImpl.guardarDetalleServicioMecanico(detalleServicioMecanico);
		}
		listaDetalleServicioMecanico.clear();
	    return Obj.writeValueAsString("editado");
	
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/ServicioMecanico/agregarDetalleServicio" , method = RequestMethod.POST)
	public @ResponseBody String editUserpost(HttpServletRequest request) throws JsonParseException,IOException{
	{
		int idServicio=Integer.parseInt(request.getParameter("idServicio"));
		Servicio servicio=servicioServiceImpl.getOne(idServicio);
		String marca=request.getParameter("marca");
		String modelo=request.getParameter("modelo");
		String placa=request.getParameter("placa");
		String contenido=request.getParameter("contenido");
		
		DetalleServicioMecanico detalleServicioMecanico=new DetalleServicioMecanico();
		detalleServicioMecanico.setServicio(servicio);
		detalleServicioMecanico.setMarca(marca);
		detalleServicioMecanico.setModelo(modelo);
		detalleServicioMecanico.setPlaca(placa);
		detalleServicioMecanico.setContenido(contenido);
		verificarServicioRepetido(detalleServicioMecanico);
		return  Obj.writeValueAsString(servicio.getMonto());
	}
}	
	
	@RequestMapping(value= {"/ServicioMecanico/Eliminar"},method=RequestMethod.POST)
    @ResponseBody
    public String eliminarServicioMecanico(HttpServletRequest request) throws IOException {
		int idServicioMecanico=Integer.parseInt(request.getParameter("idServicioMecanico"));
		ServicioMecanico servicioMecanico= servicioMecanicoServiceImpl.getOne(idServicioMecanico);
		servicioMecanicoServiceImpl.eliminarServicioMecanico(servicioMecanico);
    	return  Obj.writeValueAsString("Servicio eliminado");
    }
	
	@RequestMapping(value= {"/ServicioMecanico/Editar{idServicioMecanico}"},method=RequestMethod.GET)
    public String editarServicioMecanico(@PathVariable(value="idServicioMecanico") int idServicioMecanico,Principal principal, Model model) throws IOException {
		ServicioMecanico servicioMecanico=servicioMecanicoServiceImpl.getOne(idServicioMecanico);
		listaDetalleServicioMecanico=servicioMecanico.getDetalleServicioMecanico();
		
		Usuario usuario=usuarioServiceImpl.findByUsername(principal.getName());
		model.addAttribute("nombreCompleto",usuario.getNombres()+" "+usuario.getApellidos());
		model.addAttribute("email",usuario.getEmail());
		if(usuario.getAutorizacion().equals("ROLE_ADMIN"))model.addAttribute("tipoDeUsuario","Administrador");
		else model.addAttribute("tipoDeUsuario","Usuario");
			
			model.addAttribute("ServicioMecanico",servicioMecanico);
			model.addAttribute("template","EditarServicioMecanico");
			model.addAttribute("listaServicios",servicioServiceImpl.listarServicios());
			return "/fragments/MenuDeUsuario";	
    }
	
	
	@RequestMapping(value= {"/ServicioMecanico/Informacion{idServicioMecanico}"},method=RequestMethod.GET)
    public String informacionServicioMecanico(@PathVariable(value="idServicioMecanico") int idServicioMecanico,Principal principal, Model model) throws IOException {
		ServicioMecanico servicioMecanico=servicioMecanicoServiceImpl.getOne(idServicioMecanico);
		listaDetalleServicioMecanico=servicioMecanico.getDetalleServicioMecanico();
		
		Usuario usuario=usuarioServiceImpl.findByUsername(principal.getName());
		model.addAttribute("nombreCompleto",usuario.getNombres()+" "+usuario.getApellidos());
		model.addAttribute("email",usuario.getEmail());
		if(usuario.getAutorizacion().equals("ROLE_ADMIN"))model.addAttribute("tipoDeUsuario","Administrador");
		else model.addAttribute("tipoDeUsuario","Usuario");
			
			model.addAttribute("ServicioMecanico",servicioMecanico);
			model.addAttribute("template","InformacionServicioMecanico");
			model.addAttribute("listaServicios",servicioServiceImpl.listarServicios());
			return "/fragments/MenuDeUsuario";	
    }
	
	private void verificarServicioRepetido(DetalleServicioMecanico detalleServicioMecanico)
	{
		boolean existe=false;
		List<DetalleServicioMecanico> listaDetalleServicioMecanicoOpe=  new ArrayList<DetalleServicioMecanico>();
		for (DetalleServicioMecanico detalleServicioMecanico2 : listaDetalleServicioMecanico) {
			if(detalleServicioMecanico.getServicio().getSrv_id()==detalleServicioMecanico2.getServicio().getSrv_id())
			{
				existe=true;
				listaDetalleServicioMecanicoOpe.add(detalleServicioMecanico);
			}else {
				listaDetalleServicioMecanicoOpe.add(detalleServicioMecanico2);
			}
		}
		if(existe)
		{
			listaDetalleServicioMecanico=listaDetalleServicioMecanicoOpe;
		}else {
			listaDetalleServicioMecanico.add(detalleServicioMecanico);
		}
		
	}
}
