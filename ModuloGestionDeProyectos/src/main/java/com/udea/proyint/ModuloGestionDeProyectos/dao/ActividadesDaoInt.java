package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ActividadesDto;
import com.udea.proyint.ModuloGestionDeProyectos.dto.dtoActividades;

public interface ActividadesDaoInt {
	
public ArrayList<ActividadesDto> listarActividadesxObjetivo(Integer idnObjetivoEspecifico);
	
	public ActividadesDto listarUnaActividad(Integer idnObjetivoEspecifico, Integer identificacionActividade);
	
	public void guardarActividades(ArrayList<dtoActividades> listaActividades);
	
	public void guardarActividad(String nombreActividad, Integer porcentajeActividad, String adtUsuario, Integer objEspecif, Integer estActivid);
	
	public void actualizarActividad(ActividadesDto actividad);
	
	public void borrarActividad(Integer idnObjetivoEspecifico, Integer identificacionActividade);
}
