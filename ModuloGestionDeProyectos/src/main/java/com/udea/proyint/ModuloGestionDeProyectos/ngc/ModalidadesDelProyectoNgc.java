package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ModalidadesDelProyectoDto;
import com.udea.proyint.ModuloGestionDeProyectos.dao.ModalidadesDelProyectoDaoInt;

public class ModalidadesDelProyectoNgc implements ModalidadesDelProyectoNgcInt{

	private ModalidadesDelProyectoDaoInt modalidadesDao;	
	
	public ModalidadesDelProyectoNgc() {
	}	

	public void setModalidadesDao(ModalidadesDelProyectoDaoInt modalidadesDao) {
		this.modalidadesDao = modalidadesDao;
	}

	public ArrayList<ModalidadesDelProyectoDto> buscarModalidades() {
		return modalidadesDao.buscarModalidades();
	}

}
