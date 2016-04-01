package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;
import com.udea.proyint.Dominio.dto.ProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;
import com.udea.proyint.ModuloGestionDeProyectos.dao.ProyectoDaoInt;

public class ProyectoNgc implements ProyectoNgcInt{
	
	private ProyectoDaoInt proyectoDao;

	public ProyectoNgc() {
	}	

	public void setProyectoDao(ProyectoDaoInt proyectoDao) {
		this.proyectoDao = proyectoDao;
	}



	public ProyectoDto almacenarProyecto(ProyectoDto proyectoDto, ArrayList<UsuarioDto> listadoAsesores,
			ArrayList<UsuarioDto> listadoParticipantes, ArrayList<ObjetivoEspecificoDto> listadoObjetivosEspecificos) {
		return proyectoDao.almacenarProyecto(proyectoDto, listadoAsesores, listadoParticipantes, listadoObjetivosEspecificos);
	}

	public ArrayList<ProyectoDto> buscarProyectosPorEstadoYRolResponsable(UsuarioDto usuarioDto, Integer idnEstado) {
		return proyectoDao.buscarProyectosPorEstadoYRolResponsable(usuarioDto,idnEstado);	
	}

	public ProyectoDto buscarProyectoModificar(int idProyecto) {
		return proyectoDao.buscarProyectoModificar(idProyecto);
	}

}
