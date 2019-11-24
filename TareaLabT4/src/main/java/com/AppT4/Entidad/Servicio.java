package com.AppT4.Entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Servicio {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int srv_id;
	private String nombre;
	private double monto;
	
	public int getSrv_id() {
		return srv_id;
	}
	public void setSrv_id(int srv_id) {
		this.srv_id = srv_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
}
