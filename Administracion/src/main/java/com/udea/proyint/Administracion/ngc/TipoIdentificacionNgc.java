package com.udea.proyint.Administracion.ngc;

import java.util.ArrayList;

import com.udea.proyint.Administracion.dao.TipoIdentificacionDaoInt;
import com.udea.proyint.Dominio.dto.TipoDocumentoDto;

public class TipoIdentificacionNgc implements TipoIdentificacionNgcInt {
	
	private TipoIdentificacionDaoInt tipoIdentificacionDao;
	
	//Constructor
	public TipoIdentificacionNgc() {
	}
	
	public void setTipoIdentificacionDao(TipoIdentificacionDaoInt tipoIdentificacionDao) {
		this.tipoIdentificacionDao = tipoIdentificacionDao;
	}

	public ArrayList<TipoDocumentoDto> buscarTiposId() {
		return tipoIdentificacionDao.buscarTiposId();
	}

}
