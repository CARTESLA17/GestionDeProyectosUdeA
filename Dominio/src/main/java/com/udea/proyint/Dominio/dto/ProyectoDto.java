package com.udea.proyint.Dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class ProyectoDto implements Serializable{
	
	private Integer idn;
	private String nombreDelProyecto;
	private String objetivoGeneral;
	private Date fechaCreacion;
	private Date fechaInicial;
	private Date fechaFinal;	
	private String adtUsuario;	
	private Date adtFechaHora;	
	private String calificacion;
	private String justificacionCancelacion;
	private String justificacionSuspencion;
	private String reconocimiento;
	private ModalidadesDelProyectoDto modalidad;
	private TiposDeProyectoDto tipoDeProyecto;	
	private EstadoDelProyectoDto estadoDelProyecto;
	private UsuarioDto responsable;
	
	public ProyectoDto() {
	}

	public Integer getIdn() {
		return idn;
	}

	public void setIdn(Integer idn) {
		this.idn = idn;
	}

	public String getNombreDelProyecto() {
		return nombreDelProyecto;
	}

	public void setNombreDelProyecto(String nombreDelProyecto) {
		this.nombreDelProyecto = nombreDelProyecto;
	}

	public String getObjetivoGeneral() {
		return objetivoGeneral;
	}

	public void setObjetivoGeneral(String objetivoGeneral) {
		this.objetivoGeneral = objetivoGeneral;
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

	public String getAdtUsuario() {
		return adtUsuario;
	}

	public void setAdtUsuario(String adtUsuario) {
		this.adtUsuario = adtUsuario;
	}

	public Date getAdtFechaHora() {
		return adtFechaHora;
	}

	public void setAdtFechaHora(Date adtFechaHora) {
		this.adtFechaHora = adtFechaHora;
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

	public String getReconocimiento() {
		return reconocimiento;
	}

	public void setReconocimiento(String reconocimiento) {
		this.reconocimiento = reconocimiento;
	}

	public ModalidadesDelProyectoDto getModalidad() {
		return modalidad;
	}

	public void setModalidad(ModalidadesDelProyectoDto modalidad) {
		this.modalidad = modalidad;
	}

	public TiposDeProyectoDto getTipoDeProyecto() {
		return tipoDeProyecto;
	}

	public void setTipoDeProyecto(TiposDeProyectoDto tipoDeProyecto) {
		this.tipoDeProyecto = tipoDeProyecto;
	}

	public EstadoDelProyectoDto getEstadoDelProyecto() {
		return estadoDelProyecto;
	}

	public void setEstadoDelProyecto(EstadoDelProyectoDto estadoDelProyecto) {
		this.estadoDelProyecto = estadoDelProyecto;
	}

	public UsuarioDto getResponsable() {
		return responsable;
	}

	public void setResponsable(UsuarioDto responsable) {
		this.responsable = responsable;
	}	

}
