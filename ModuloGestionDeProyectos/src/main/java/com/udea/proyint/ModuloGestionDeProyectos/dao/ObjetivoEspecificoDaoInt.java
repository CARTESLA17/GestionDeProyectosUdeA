package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;

public interface ObjetivoEspecificoDaoInt {

	public ArrayList<ObjetivoEspecificoDto> buscarObjetivoEspecificoXProyecto(int idProyecto);
}
