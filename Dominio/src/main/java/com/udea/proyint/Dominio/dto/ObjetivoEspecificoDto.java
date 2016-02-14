package com.udea.proyint.Dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class ObjetivoEspecificoDto implements Serializable{
	
	private Integer idn;
	private String descripcion;
	private String adtUsuario;	
	private Date adtFecha;
	private String estado;
	private Integer porcentaje;
	private ProyectoDto proyecto;
	
	public ObjetivoEspecificoDto() {
	}

	public Integer getIdn() {
		return idn;
	}

	public void setIdn(Integer idn) {
		this.idn = idn;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public ProyectoDto getProyecto() {
		return proyecto;
	}

	public void setProyecto(ProyectoDto proyecto) {
		this.proyecto = proyecto;
	}	

}
