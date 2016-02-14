package com.udea.proyint.Administracion.dao;

import java.util.ArrayList;

import com.udea.proyint.Dominio.dto.UsuarioDto;

public interface PersonaDaoInt {
	
	public UsuarioDto verificarUsuario(String usuario, String contrasena);
	
	public ArrayList<UsuarioDto> buscarUsuariosPorTidIdNombresYApellidos(int tipoId, int id,String nombres, String apellidos);

	public ArrayList<UsuarioDto> buscarUsuariosPorTidYId(int tipoId, int id);

	public ArrayList<UsuarioDto> buscarUsuariosPorNombres(String nombres);
	
	public ArrayList<UsuarioDto> buscarUsuariosPorApellidos(String apellidos);
	
	public ArrayList<UsuarioDto> buscarUsuariosPorTidIdYNombres(int tipoId, int id, String nombres);
		
	public ArrayList<UsuarioDto> buscarUsuariosPorTidIdYApellidos(int tipoId, int id, String apellidos);
	
	public ArrayList<UsuarioDto> buscarUsuariosPorNombresYApellidos(String nombres, String apellidos);

	public ArrayList<UsuarioDto> buscarUsuariosPorIdNombresYApellidos(int id, String nombres, String apellidos);

	public ArrayList<UsuarioDto> buscarUsuariosPorIdYNombres(int id, String nombres);

	public ArrayList<UsuarioDto> buscarUsuariosPorIdYApellidos(int id, String apellidos);

	public ArrayList<UsuarioDto> buscarUsuariosPorId(int id);
	
	public void crearPersona(UsuarioDto usuarioDto);
	
	public void crearPersonaSession(UsuarioDto usuarioDto);
	
	public UsuarioDto buscarUsuarioModificar(int tipoId, int id);

}
