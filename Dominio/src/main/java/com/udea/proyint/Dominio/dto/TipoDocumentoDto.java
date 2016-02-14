package com.udea.proyint.Dominio.dto;

import java.io.Serializable;


public class TipoDocumentoDto implements Serializable{

	private Integer idn;
	private String nombre;	

	public TipoDocumentoDto(){
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

}