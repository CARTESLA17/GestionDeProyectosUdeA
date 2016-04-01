package com.udea.proyint.ModuloGestionDeProyectos.ngc;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;

public interface ObjetivoEspecificoNgcInt {
	
	public ArrayList<ObjetivoEspecificoDto> buscarObjetivoEspecificoXProyecto(int idProyecto);
}
