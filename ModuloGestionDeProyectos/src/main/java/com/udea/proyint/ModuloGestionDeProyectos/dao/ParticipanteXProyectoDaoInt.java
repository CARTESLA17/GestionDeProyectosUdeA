package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ParticipanteXProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public interface ParticipanteXProyectoDaoInt {
	
	public ArrayList<ParticipanteXProyectoDto> buscarProyectosPorEstadoYRolParticipante(
			UsuarioDto usuarioDto, Integer idnEstado);

	public ArrayList<ParticipanteXProyectoDto> buscarParticipanteXProyecto(int idProyecto);

}
