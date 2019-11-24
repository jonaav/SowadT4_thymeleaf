package com.AppT4.Entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class DetalleServicioMecanico {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@JoinColumn(name="serv_id")
	@OneToOne
	@JsonIgnore
	private ServicioMecanico servicioMecanico;
	@JoinColumn(name="srv_id")
	@OneToOne
	private Servicio servicio;
	private String marca;
	private String modelo;
	private String placa;
	private String contenido;
	
	public DetalleServicioMecanico()
	{
		servicio=new Servicio();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ServicioMecanico getServicioMecanico() {
		return servicioMecanico;
	}
	public void setServicioMecanico(ServicioMecanico servicioMecanico) {
		this.servicioMecanico = servicioMecanico;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
