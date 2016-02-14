package com.udea.proyint.Dominio.dto;

import java.io.Serializable;

public class ParticipanteXProyectoDto implements Serializable{
	
	private ParticipanteXProyectoDtoId participanteXProyectoDtoId;

	public ParticipanteXProyectoDto() {
	}

	public ParticipanteXProyectoDtoId getParticipanteXProyectoDtoId() {
		return participanteXProyectoDtoId;
	}

	public void setParticipanteXProyectoDtoId(
			ParticipanteXProyectoDtoId participanteXProyectoDtoId) {
		this.participanteXProyectoDtoId = participanteXProyectoDtoId;
	}		

}
