package com.udea.proyint.Dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class UsuarioDto implements Serializable{
	
	private Integer idn;
	private String usuario;
	private String contrasena;
	private String nombres;
	private String apellidos;
	private Integer identificacion;
	private String email;
	private String adtUsuario;	
	private Date fechaHora;
	private RolSistemaDto rol;
	private EstadoDto estado;
	private TipoDocumentoDto tipoDocumento;
	
	public UsuarioDto(){
	}			

	public RolSistemaDto getRol() {
		return rol;
	}

	public void setRol(RolSistemaDto rol) {
		this.rol = rol;
	}

	public EstadoDto getEstado() {
		return estado;
	}

	public void setEstado(EstadoDto estado) {
		this.estado = estado;
	}

	public TipoDocumentoDto getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoDto tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getAdtUsuario() {
		return adtUsuario;
	}

	public void setAdtUsuario(String adtUsuario) {
		this.adtUsuario = adtUsuario;
	}

	public Integer getIdn() {
		return idn;
	}

	public void setIdn(Integer idn) {
		this.idn = idn;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Integer identificacion) {
		this.identificacion = identificacion;
	}	

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
}
