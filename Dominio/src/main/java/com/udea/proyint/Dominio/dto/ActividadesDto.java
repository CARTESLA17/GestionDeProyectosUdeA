package com.udea.proyint.Dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class ActividadesDto implements Serializable {
	private Integer idn;
	private String nombreActividad;
	private Date fechaCreacion;
	private Integer porcentajeActividad;
	private String adtUsuario;
	private Date adtFecha;
	private ObjetivoEspecificoDto objEspecif;
	private EstadoDelProyectoDto estActivid;
	
	public ActividadesDto(){
	}

	public Integer getIdn() {
		return idn;
	}

	public void setIdn(Integer idn) {
		this.idn = idn;
	}

	public Integer getPorcentajeActividad() {
		return porcentajeActividad;
	}

	public void setPorcentajeActividad(Integer porcentajeActividad) {
		this.porcentajeActividad = porcentajeActividad;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getAdtUsuario() {
		return adtUsuario;
	}

	public void setAdtUsuario(String adtUsuario) {
		this.adtUsuario = adtUsuario;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getAdtFecha() {
		return adtFecha;
	}

	public void setAdtFecha(Date adtFecha) {
		this.adtFecha = adtFecha;
	}

	public ObjetivoEspecificoDto getObjEspecif() {
		return objEspecif;
	}

	public void setObjEspecif(ObjetivoEspecificoDto objEspecif) {
		this.objEspecif = objEspecif;
	}

	public EstadoDelProyectoDto getEstActivid() {
		return estActivid;
	}

	public void setEstActivid(EstadoDelProyectoDto estActivid) {
		this.estActivid = estActivid;
	}

}
