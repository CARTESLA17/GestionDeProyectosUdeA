package com.udea.proyint.Dominio.dto;

import java.io.Serializable;

public class RolXPermisoDtoId implements Serializable{
	
	private RolSistemaDto rol;
	private PermisoDto permiso;
	
	public RolXPermisoDtoId() {
	}

	public RolSistemaDto getRol() {
		return rol;
	}

	public void setRol(RolSistemaDto rol) {
		this.rol = rol;
	}

	public PermisoDto getPermiso() {
		return permiso;
	}

	public void setPermiso(PermisoDto permiso) {
		this.permiso = permiso;
	}
	
}
