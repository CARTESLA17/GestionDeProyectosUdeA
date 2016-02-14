package com.udea.proyint.Administracion.ngc;

import java.util.ArrayList;

import com.udea.proyint.Administracion.dao.PersonaDaoInt;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public class PersonaNgc implements PersonaNgcInt {	
	
	private PersonaDaoInt personaDao;

	public PersonaNgc() {		
	}

	public void setPersonaDao(PersonaDaoInt personaDao) {
		this.personaDao = personaDao;
	}

	public UsuarioDto verificarUsuario(String usuario, String contrasena) {
		return personaDao.verificarUsuario(usuario,contrasena);		
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorTidIdNombresYApellidos(
			int tipoId, int id, String nombres, String apellidos) {
		return personaDao.buscarUsuariosPorTidIdNombresYApellidos(tipoId, id, nombres, apellidos);
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorTidYId(int tipoId, int id) {
		return personaDao.buscarUsuariosPorTidYId(tipoId, id);
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorNombres(String nombres) {
		return personaDao.buscarUsuariosPorNombres(nombres);
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorApellidos(String apellidos) {
		return personaDao.buscarUsuariosPorApellidos(apellidos);
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorNombresYApellidos(
			String nombres, String apellidos) {		
		return personaDao.buscarUsuariosPorNombresYApellidos(nombres, apellidos);
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorTidIdYNombres(int tipoId,
			int id, String nombres) {
		return personaDao.buscarUsuariosPorTidIdYNombres(tipoId, id, nombres);
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorTidIdYApellidos(int tipoId,
			int id, String apellidos) {		
		return personaDao.buscarUsuariosPorTidIdYApellidos(tipoId, id, apellidos);
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorIdNombresYApellidos( int id, String nombres, String apellidos) {
		return personaDao.buscarUsuariosPorIdNombresYApellidos( id, nombres, apellidos);
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorIdYNombres(int id, String nombres) {
		return personaDao.buscarUsuariosPorIdYNombres( id, nombres);
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorIdYApellidos(int id, String apellidos) {
		return personaDao.buscarUsuariosPorIdYApellidos( id, apellidos);
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorId(int id) {
		return personaDao.buscarUsuariosPorId( id);
	}
	
	public void crearPersona(UsuarioDto usuarioDto){
		try {
			personaDao.crearPersona(usuarioDto);
		//} catch (ExcepcionDao e) {
			//ExcepcionNgc expNgc = new ExcepcionNgc(e);			
			//throw expNgc;
		} catch (Exception e) {
			//ExcepcionNgc expNgc = new ExcepcionNgc(e);			
			//expNgc.setMensajeUsuario("Error validando información");
			//throw expNgc;
		}
	}

	public UsuarioDto buscarUsuarioModificar(int tipoId, int id) {
		System.out.println("entro a personaNgc");
		return personaDao.buscarUsuarioModificar(tipoId, id);
	}

}
