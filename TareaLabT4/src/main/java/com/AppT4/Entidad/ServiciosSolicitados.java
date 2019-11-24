package com.AppT4.Entidad;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ServiciosSolicitados {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ssrv_id;
	@JoinColumn(name="srv_id")
	@OneToOne
	private Servicio servicio;
	private String fecha;
	@JoinColumn(name="cli_id")
	@OneToOne
	private Cliente cliente;
	public int getSsrv_id() {
		return ssrv_id;
	}
	public void setSsrv_id(int ssrv_id) {
		this.ssrv_id = ssrv_id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	
	
}
