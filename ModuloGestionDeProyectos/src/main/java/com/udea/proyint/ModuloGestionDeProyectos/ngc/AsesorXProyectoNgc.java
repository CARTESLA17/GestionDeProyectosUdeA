package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.AsesorXProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;
import com.udea.proyint.ModuloGestionDeProyectos.dao.AsesorXProyectoDaoInt;

public class AsesorXProyectoNgc implements AsesorXProyectoNgcInt{
	
	private AsesorXProyectoDaoInt asesorXProyectoDao;	

	public void setAsesorXProyectoDao(AsesorXProyectoDaoInt asesorXProyectoDao) {
		this.asesorXProyectoDao = asesorXProyectoDao;
	}

	public AsesorXProyectoNgc() {
	}

	public ArrayList<AsesorXProyectoDto> buscarProyectosPorEstadoYRolAsesor(
			UsuarioDto usuarioDto, Integer idnEstado) {
		return asesorXProyectoDao.buscarProyectosPorEstadoYRolAsesor(usuarioDto, idnEstado);
	}	

}
