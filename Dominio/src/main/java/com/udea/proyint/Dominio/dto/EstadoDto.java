package com.udea.proyint.Dominio.dto;

import java.io.Serializable;



public class EstadoDto implements Serializable{

	private Integer idn;
	private String descripcion;

	public EstadoDto(){
	}
	
	public Integer obtenerIdn(){
		return idn;
	}
	
	public String obtenerDescripcion(){
		return descripcion;
	}
	
	public void asignarIdn(Integer idn){
		this.idn=idn;
	}
	
	public void asignarDescripcion(String descripcion){
		this.descripcion=descripcion;
	}


}