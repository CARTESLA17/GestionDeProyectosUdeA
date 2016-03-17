package com.udea.proyint.Dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class ActividadesDto implements Serializable {
	private Integer idn;
	private Integer porcentajeActividad;
	private String nombreActividad;
	private String adtUsuario;
	private String calificacion;
	private String justificacionCancelacion;
	private String justificacionSuspencion;
	private Date fechaCreacion;
	private Date fechaInicial;
	private Date fechaFinal;
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

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public String getJustificacionCancelacion() {
		return justificacionCancelacion;
	}

	public void setJustificacionCancelacion(String justificacionCancelacion) {
		this.justificacionCancelacion = justificacionCancelacion;
	}

	public String getJustificacionSuspencion() {
		return justificacionSuspencion;
	}

	public void setJustificacionSuspencion(String justificacionSuspencion) {
		this.justificacionSuspencion = justificacionSuspencion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
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
