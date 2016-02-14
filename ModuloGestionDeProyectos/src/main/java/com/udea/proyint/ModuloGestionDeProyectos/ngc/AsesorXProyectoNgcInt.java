package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.AsesorXProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public interface AsesorXProyectoNgcInt {
	
	public ArrayList<AsesorXProyectoDto> buscarProyectosPorEstadoYRolAsesor(
			UsuarioDto usuarioDto, Integer idnEstado);

}
