package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.AsesorXProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public interface AsesorXProyectoDaoInt {
	
	public ArrayList<AsesorXProyectoDto> buscarProyectosPorEstadoYRolAsesor(
			UsuarioDto usuarioDto, Integer idnEstado);

	public ArrayList<AsesorXProyectoDto> buscarAsesorXProyecto(int idProyecto);
	
}
