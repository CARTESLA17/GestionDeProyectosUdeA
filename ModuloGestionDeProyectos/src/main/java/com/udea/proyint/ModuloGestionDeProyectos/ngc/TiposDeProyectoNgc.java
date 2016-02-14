package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.TiposDeProyectoDto;
import com.udea.proyint.ModuloGestionDeProyectos.dao.TiposDeProyectoDaoInt;

public class TiposDeProyectoNgc implements TiposDeProyectoNgcInt{

	private TiposDeProyectoDaoInt tiposDeProyectosDao;	
	
	public TiposDeProyectoNgc() {
	}

	public void setTiposDeProyectosDao(TiposDeProyectoDaoInt tiposDeProyectosDao) {
		this.tiposDeProyectosDao = tiposDeProyectosDao;
	}

	public ArrayList<TiposDeProyectoDto> buscarTiposDeProyecto() {
		return tiposDeProyectosDao.buscarTiposDeProyecto();
	}

}
