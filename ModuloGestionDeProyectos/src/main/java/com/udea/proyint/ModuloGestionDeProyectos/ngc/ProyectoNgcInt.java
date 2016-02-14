package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;
import com.udea.proyint.Dominio.dto.ProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public interface ProyectoNgcInt {
	
	public ProyectoDto almacenarProyecto(ProyectoDto proyectoDto,  ArrayList<UsuarioDto> listadoAsesores,
			ArrayList<UsuarioDto> listadoParticipantes, ArrayList<ObjetivoEspecificoDto> listadoObjetivosEspecificos);

	public ArrayList<ProyectoDto> buscarProyectosPorEstadoYRolResponsable(UsuarioDto usuarioDto, Integer idnEstado);

}
