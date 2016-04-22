package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ActividadesDto;

public interface ActividadesNgcInt {
	
	public ActividadesDto ingresarActividades(ArrayList<ActividadesDto> listaActividades, ActividadesDto actividades );
	
	public ArrayList<ActividadesDto> extraerActividades(Integer idnObjetivoEspecifico);

}
