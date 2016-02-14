package com.udea.proyint.Dominio.dto;

import java.io.Serializable;

public class ParticipanteXProyectoDtoId implements Serializable{
	
	private UsuarioDto participante;
	private	ProyectoDto proyecto;
	
	public ParticipanteXProyectoDtoId() {
	}

	public UsuarioDto getParticipante() {
		return participante;
	}

	public void setParticipante(UsuarioDto participante) {
		this.participante = participante;
	}

	public ProyectoDto getProyecto() {
		return proyecto;
	}

	public void setProyecto(ProyectoDto proyecto) {
		this.proyecto = proyecto;
	}	

}
