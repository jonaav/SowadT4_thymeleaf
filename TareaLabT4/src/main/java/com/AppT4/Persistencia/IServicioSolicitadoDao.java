package com.AppT4.Persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AppT4.Entidad.ServiciosSolicitados;

public interface IServicioSolicitadoDao extends JpaRepository<ServiciosSolicitados, Integer> {

}
