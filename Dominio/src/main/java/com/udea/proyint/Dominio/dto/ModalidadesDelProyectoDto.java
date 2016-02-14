package com.udea.proyint.Dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class ModalidadesDelProyectoDto implements Serializable{
	
	private Integer idn;
	private String nombreModalidad;
	private String adtUsuario;
	private Date adtFechaHora;		

	public ModalidadesDelProyectoDto() {		
	}	
	
	public Integer getIdn() {
		return idn;
	}
	
	public void setIdn(Integer idn) {
		this.idn = idn;
	}
	
	public String getNombre() {
		return nombreModalidad;
	}
	
	public void setNombre(String nombreModalidad) {
		this.nombreModalidad = nombreModalidad;
	}
	
	public String getAdtUsuario() {
		return  adtUsuario;
	}
	
	public void setAdtUsuario(String  adtUsuario) {
		this. adtUsuario =  adtUsuario;
	}
	
	public Date getAdtFechaHora() {
		return adtFechaHora;
	}
	
	public void setAdtFechaHora(Date adtFechaHora) {
		this.adtFechaHora = adtFechaHora;
	}

}
