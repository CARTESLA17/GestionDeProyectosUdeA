package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ActividadesDto;
import com.udea.proyint.ModuloGestionDeProyectos.dao.ActividadesDaoInt;

public class ActividadesNgc implements ActividadesNgcInt {
	
	private ActividadesDaoInt actividad;

	public void ingresarActividades(ArrayList<ActividadesDto> listaActividades) {
		/**
		 * Esto aqui de seguro sera modificado
		 */
		actividad.guardarActividades(listaActividades);
	}

	public ArrayList<ActividadesDto> extraerActividades(Integer idnObjetivoEspecifico) {
		ArrayList<ActividadesDto> listaActividades = actividad.listarActividadesxObjetivo(idnObjetivoEspecifico);
		return listaActividades;
	}

}
