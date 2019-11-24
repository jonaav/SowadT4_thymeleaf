package com.AppT4.Entidad;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ServicioMecanico {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serv_id;
	
	private Date serv_fecha;
	
	@JoinColumn(name="per_id")
	@OneToOne
	private Personal per_id;
	@JoinColumn(name="cli_id")
	@OneToOne
	private Cliente cli_id;
	
	@OneToMany(mappedBy = "servicioMecanico",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleServicioMecanico> detalleServicioMecanico;
	
	public int getServ_id() {
		return serv_id;
	}
	public void setServ_id(int serv_id) {
		this.serv_id = serv_id;
	}
	public Date getServ_fecha() {
		return serv_fecha;
	}
	public void setServ_fecha(Date serv_fecha) {
		this.serv_fecha = serv_fecha;
	}
	public Personal getPer_id() {
		return per_id;
	}
	public void setPer_id(Personal per_id) {
		this.per_id = per_id;
	}
	public Cliente getCli_id() {
		return cli_id;
	}
	public void setCli_id(Cliente cli_id) {
		this.cli_id = cli_id;
	}
	public List<DetalleServicioMecanico> getDetalleServicioMecanico() {
		return detalleServicioMecanico;
	}
	public void setDetalleServicioMecanico(List<DetalleServicioMecanico> detalleServicioMecanico) {
		this.detalleServicioMecanico = detalleServicioMecanico;
	}
	
}
