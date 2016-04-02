package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ActividadesDto;

public interface ActividadesNgcInt {
	
	public void ingresarActividades(ArrayList<ActividadesDto> listaActividades);
	
	public ArrayList<ActividadesDto> extraerActividades(Integer idnObjetivoEspecifico);

}
