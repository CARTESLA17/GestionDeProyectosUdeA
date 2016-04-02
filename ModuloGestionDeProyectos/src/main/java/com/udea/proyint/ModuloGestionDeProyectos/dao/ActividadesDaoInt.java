package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ActividadesDto;

public interface ActividadesDaoInt {
	
public ArrayList<ActividadesDto> listarActividadesxObjetivo(Integer idnObjetivoEspecifico);
	
	public ActividadesDto listarUnaActividad(Integer identificacionActividad);
	
	public void guardarActividades(ArrayList<ActividadesDto> listaActividades);
	
	public void guardarActividad(ActividadesDto actividad);
	
	public void actualizarActividad(ActividadesDto actividad);
	
	public void borrarActividad(Integer idnObjetivoEspecifico, Integer identificacionActividade);
}
