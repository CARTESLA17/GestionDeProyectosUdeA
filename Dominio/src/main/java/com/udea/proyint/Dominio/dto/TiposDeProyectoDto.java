package com.udea.proyint.Dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class TiposDeProyectoDto implements Serializable{
	
	private Integer idn;
	private String nombreTipoProyecto;
	private String adtUsuario;
	private Date adtFechaHora;		

	public TiposDeProyectoDto() {		
	}	
	
	public Integer getIdn() {
		return idn;
	}
	
	public void setIdn(Integer idn) {
		this.idn = idn;
	}
	
	public String getNombre() {
		return nombreTipoProyecto;
	}
	
	public void setNombre(String nombreTipoProyecto) {
		this.nombreTipoProyecto = nombreTipoProyecto;
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
