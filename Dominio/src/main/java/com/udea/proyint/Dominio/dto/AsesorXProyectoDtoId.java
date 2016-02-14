package com.udea.proyint.Dominio.dto;

import java.io.Serializable;

public class AsesorXProyectoDtoId implements Serializable{
	
	private UsuarioDto asesor;
	private	ProyectoDto proyecto;	

	public AsesorXProyectoDtoId() {
	}

	public UsuarioDto getAsesor() {
		return asesor;
	}

	public void setAsesor(UsuarioDto asesor) {
		this.asesor = asesor;
	}

	public ProyectoDto getProyecto() {
		return proyecto;
	}

	public void setProyecto(ProyectoDto proyecto) {
		this.proyecto = proyecto;
	}	

}
