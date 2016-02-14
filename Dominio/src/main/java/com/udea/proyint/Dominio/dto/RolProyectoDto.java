package com.udea.proyint.Dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class RolProyectoDto implements Serializable {
	
	private int nbIdn;	
	private String vrNombre;	
	private String vrAdtUsuario;
	private Date dtAdtFecha;
	
	public RolProyectoDto() {
	}

	public int getNbIdn() {
		return nbIdn;
	}

	public void setNbIdn(int nbIdn) {
		this.nbIdn = nbIdn;
	}

	public String getVrNombre() {
		return vrNombre;
	}

	public void setVrNombre(String vrNombre) {
		this.vrNombre = vrNombre;
	}

	public String getVrAdtUsuario() {
		return vrAdtUsuario;
	}

	public void setVrAdtUsuario(String vrAdtUsuario) {
		this.vrAdtUsuario = vrAdtUsuario;
	}

	public Date getDtAdtFecha() {
		return dtAdtFecha;
	}

	public void setDtAdtFecha(Date dtAdtFecha) {
		this.dtAdtFecha = dtAdtFecha;
	}
	
}
