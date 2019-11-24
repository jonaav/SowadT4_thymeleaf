package com.AppT4.Persistencia;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AppT4.Entidad.DetalleServicioMecanico;

@Repository
public interface IDetalleServicioMecanicoDao extends JpaRepository<DetalleServicioMecanico, Integer>  {

}
