package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ParticipanteXProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;
import com.udea.proyint.ModuloGestionDeProyectos.dao.ParticipanteXProyectoDaoInt;

public class ParticipanteXProyectoNgc implements ParticipanteXProyectoNgcInt {
	
	private ParticipanteXProyectoDaoInt participanteXProyectoDao;	

	public void setParticipanteXProyectoDao(
			ParticipanteXProyectoDaoInt participanteXProyectoDao) {
		this.participanteXProyectoDao = participanteXProyectoDao;
	}

	public ParticipanteXProyectoNgc() {		
	}

	public ArrayList<ParticipanteXProyectoDto> buscarProyectosPorEstadoYRolParticipante(
			UsuarioDto usuarioDto, Integer idnEstado) {		
		return participanteXProyectoDao.buscarProyectosPorEstadoYRolParticipante(usuarioDto, idnEstado);
	}

}
