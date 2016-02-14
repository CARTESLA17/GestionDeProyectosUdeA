package com.udea.proyint.Dominio.dto;

import java.io.Serializable;

public class AsesorXProyectoDto implements Serializable{
	
	private AsesorXProyectoDtoId asesorXProyectoDtoId;	

	public AsesorXProyectoDto() {
	}

	public AsesorXProyectoDtoId getAsesorXProyectoDtoId() {
		return asesorXProyectoDtoId;
	}

	public void setAsesorXProyectoDtoId(AsesorXProyectoDtoId asesorXProyectoDtoId) {
		this.asesorXProyectoDtoId = asesorXProyectoDtoId;
	}

}
