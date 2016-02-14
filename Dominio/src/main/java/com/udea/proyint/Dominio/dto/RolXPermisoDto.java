package com.udea.proyint.Dominio.dto;

import java.io.Serializable;

public class RolXPermisoDto implements Serializable{
	
	private RolXPermisoDtoId rolXPermisoDtoId;

	public RolXPermisoDto() {		
	}

	public RolXPermisoDtoId getRolXPermisoDtoId() {
		return rolXPermisoDtoId;
	}

	public void setRolXPermisoDtoId(RolXPermisoDtoId rolXPermisoDtoId) {
		this.rolXPermisoDtoId = rolXPermisoDtoId;
	}	

}
