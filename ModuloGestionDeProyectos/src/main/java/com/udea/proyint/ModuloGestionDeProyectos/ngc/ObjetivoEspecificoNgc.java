package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;
import com.udea.proyint.ModuloGestionDeProyectos.dao.ObjetivoEspecificoDao;
import com.udea.proyint.ModuloGestionDeProyectos.dao.ObjetivoEspecificoDaoInt;

public class ObjetivoEspecificoNgc implements ObjetivoEspecificoNgcInt {
	
	private ObjetivoEspecificoDaoInt objetivoEspecificoDao;
	
	public void setObjetivoEspecificoDao(ObjetivoEspecificoDaoInt objetivoEspecificoDao) {
		this.objetivoEspecificoDao = objetivoEspecificoDao;
	}

	public ObjetivoEspecificoNgc(){
	}
	
	public ArrayList<ObjetivoEspecificoDto> buscarObjetivoEspecificoXProyecto(int idProyecto) {
		return objetivoEspecificoDao.buscarObjetivoEspecificoXProyecto(idProyecto);
	}

}
