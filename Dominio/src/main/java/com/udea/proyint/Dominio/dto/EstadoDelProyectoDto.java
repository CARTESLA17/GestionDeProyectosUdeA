package com.udea.proyint.Dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class EstadoDelProyectoDto implements Serializable{
	
	private Integer idn;
	private String nombre;
	private String adtUsuario;
	private Date adtFecha;
	
	public EstadoDelProyectoDto() {
	}

	public Integer getIdn() {
		return idn;
	}

	public void setIdn(Integer idn) {
		this.idn = idn;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAdtUsuario() {
		return adtUsuario;
	}

	public void setAdtUsuario(String adtUsuario) {
		this.adtUsuario = adtUsuario;
	}

	public Date getAdtFecha() {
		return adtFecha;
	}

	public void setAdtFecha(Date adtFecha) {
		this.adtFecha = adtFecha;
	}	

}
