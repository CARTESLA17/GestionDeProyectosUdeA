package com.udea.proyint.Administracion.ngc;

import java.util.ArrayList;

import com.udea.proyint.Administracion.dao.TipoRolDaoInt;
import com.udea.proyint.Dominio.dto.RolSistemaDto;

public class TipoRolNgc implements TipoRolNgcInt {

	private TipoRolDaoInt TipoRolDao;
	
	//Constructor
	public TipoRolNgc() {
	}
	
	public void setTipoRolDao(TipoRolDaoInt tipoRolDao) {
		TipoRolDao = tipoRolDao;
	}

	public ArrayList<RolSistemaDto> buscarTiposRol() {
		return TipoRolDao.buscarTiposRol();
	}

}
