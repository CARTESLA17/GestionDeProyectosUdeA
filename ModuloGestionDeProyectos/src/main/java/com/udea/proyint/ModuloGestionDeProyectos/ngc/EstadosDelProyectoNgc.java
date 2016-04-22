package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.EstadoDelProyectoDto;
import com.udea.proyint.ModuloGestionDeProyectos.dao.EstadosDelProyectoDaoInt;

public class EstadosDelProyectoNgc implements EstadosDelProyectoNgcInt {
	
	private EstadosDelProyectoDaoInt estadosDelProyectoDao;	
	
	public void setEstadosDelProyectoDao(
			EstadosDelProyectoDaoInt estadosDelProyectoDao) {
		this.estadosDelProyectoDao = estadosDelProyectoDao;
	}

	public EstadosDelProyectoNgc() {
		super();		
	}

	public ArrayList<EstadoDelProyectoDto> buscarEstadosDelProyecto() {		
		return estadosDelProyectoDao.buscarEstadosDelProyecto();
	}

	public String nombre(int id){
		return estadosDelProyectoDao.nombre(id);
	}
}
