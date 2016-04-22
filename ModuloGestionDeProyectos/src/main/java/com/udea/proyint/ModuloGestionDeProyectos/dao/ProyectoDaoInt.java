package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.EstadoDelProyectoDto;
import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;
import com.udea.proyint.Dominio.dto.ProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public interface ProyectoDaoInt {
	
	public ProyectoDto almacenarProyecto(ProyectoDto proyectoDto,  ArrayList<UsuarioDto> listadoAsesores,
			ArrayList<UsuarioDto> listadoParticipantes, ArrayList<ObjetivoEspecificoDto> listadoObjetivosEspecificos);

	public ArrayList<ProyectoDto> buscarProyectosPorEstadoYRolResponsable(
			UsuarioDto usuarioDto, Integer idnEstado);

	public ProyectoDto buscarProyectoModificar(int idProyecto);
	
	public void actualizar(int id, ProyectoDto proyectoDto);

}


