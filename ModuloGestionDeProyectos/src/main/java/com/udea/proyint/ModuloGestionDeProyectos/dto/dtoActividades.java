package com.udea.proyint.ModuloGestionDeProyectos.dto;

public class dtoActividades {
	
	private String nombreActividad;
	private Integer porcentajeActividad;
	private String adtUsuario;
	private Integer objEspecif;
	private Integer estActivid;
	
	public dtoActividades(){
		
	}
	
	public String getNombreActividad() {
		return nombreActividad;
	}
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}
	public Integer getPorcentajeActividad() {
		return porcentajeActividad;
	}
	public void setPorcentajeActividad(Integer porcentajeActividad) {
		this.porcentajeActividad = porcentajeActividad;
	}
	public String getAdtUsuario() {
		return adtUsuario;
	}
	public void setAdtUsuario(String adtUsuario) {
		this.adtUsuario = adtUsuario;
	}
	public Integer getObjEspecif() {
		return objEspecif;
	}
	public void setObjEspecif(Integer objEspecif) {
		this.objEspecif = objEspecif;
	}
	public Integer getEstActivid() {
		return estActivid;
	}
	public void setEstActivid(Integer estActivid) {
		this.estActivid = estActivid;
	}

}
