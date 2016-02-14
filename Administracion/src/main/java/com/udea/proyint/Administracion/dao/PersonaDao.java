package com.udea.proyint.Administracion.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.UsuarioDto;

public class PersonaDao extends HibernateDaoSupport implements PersonaDaoInt {
	
	//Estados que puede tener un usuario en el SI
	private int estadoActivo=1;
	private int estadoInactivo=2;

	public PersonaDao() {		
	}

	public UsuarioDto verificarUsuario(String usuario, String contrasena){	
		UsuarioDto usuarioDto=null;
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where u.usuario=? and u.contrasena=? and u.estado.idn=?");
			query.setString(0,usuario);
			query.setString(1,contrasena);
			query.setInteger(2,estadoActivo);
			List<UsuarioDto> lista=query.list();
			if( (lista!=null) && (lista.size()==1)){
				usuarioDto=lista.get(0);
			}			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}			
		return usuarioDto;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorTidIdNombresYApellidos(
			int tipoId, int id, String nombres, String apellidos) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where (u.estado.idn=?) and ( (u.tipoDocumento.idn=? and u.identificacion=?) "
					+ "  or (u.nombres like :nombres) or (u.apellidos like :apellidos) )");
			query.setInteger(0,estadoActivo);
			query.setInteger(1,tipoId);
			query.setInteger(2,id);			
			query.setString("nombres","%"+nombres+"%");
			query.setString("apellidos","%"+apellidos+"%");			
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorTidYId(int tipoId, int id) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where u.tipoDocumento.idn=? and u.identificacion=? and u.estado.idn=?");
			query.setInteger(0,tipoId);
			query.setInteger(1,id);
			query.setInteger(2,estadoActivo);
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorNombres(String nombres) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where (u.estado.idn=?) and (u.nombres like :nombres)");
			query.setInteger(0,estadoActivo);
			query.setString("nombres","%"+nombres+"%");			
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorApellidos(String apellidos) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where (u.estado.idn=?) and (u.apellidos like :apellidos)");
			query.setInteger(0,estadoActivo);
			query.setString("apellidos","%"+apellidos+"%");			
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorNombresYApellidos(
			String nombres, String apellidos) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where (u.estado.idn=?) and ( (u.nombres like :nombres) or (u.apellidos like :apellidos) )");
			query.setInteger(0,estadoActivo);
			query.setString("nombres","%"+nombres+"%");
			query.setString("apellidos","%"+apellidos+"%");			
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorTidIdYNombres(int tipoId,int id, String nombres) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where (u.estado.idn=?) and ( (u.tipoDocumento.idn=? and u.identificacion=?)  or (u.nombres like :nombres) )");
			query.setInteger(0,estadoActivo);
			query.setInteger(1,tipoId);
			query.setInteger(2,id);			
			query.setString("nombres","%"+nombres+"%");			
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorTidIdYApellidos(int tipoId, int id, String apellidos) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where (u.estado.idn=?) and ( (u.tipoDocumento.idn=? and u.identificacion=?) or (u.apellidos like :apellidos) )");
			query.setInteger(0,estadoActivo);
			query.setInteger(1,tipoId);
			query.setInteger(2,id);			
			query.setString("apellidos","%"+apellidos+"%");			
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorIdNombresYApellidos(int id, String nombres, String apellidos) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where (u.estado.idn=?) and ( (u.identificacion=?) or (u.nombres like :nombres) or (u.apellidos like :apellidos) )");
			query.setInteger(0,estadoActivo);
			query.setInteger(1,id);	
			query.setString("nombres","%"+nombres+"%");
			query.setString("apellidos","%"+apellidos+"%");			
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorIdYNombres(int id, String nombres) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where (u.estado.idn=?) and ( (u.identificacion=?) or (u.nombres like :nombres) )");
			query.setInteger(0,estadoActivo);
			query.setInteger(1,id);
			query.setString("nombres","%"+nombres+"%");
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorIdYApellidos(int id, String apellidos) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where (u.estado.idn=?) and ( (u.identificacion=?) or (u.apellidos like :apellidos) )");
			query.setInteger(0,estadoActivo);
			query.setInteger(1,id);
			query.setString("apellidos","%"+apellidos+"%");			
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}

	public ArrayList<UsuarioDto> buscarUsuariosPorId(int id) {
		ArrayList<UsuarioDto> lista= new ArrayList<UsuarioDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where u.identificacion=? and u.estado.idn=?");
			query.setInteger(0,id);
			query.setInteger(1,estadoActivo);
			lista=(ArrayList<UsuarioDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return lista;
	}
	
	public void crearPersonaSession(UsuarioDto usuarioDto){
		Session session = null;
		try{
			session = getSession();
			session.save(usuarioDto);
			session.flush(); 
		}catch(Exception e){
			//ExcepcionDao expDao = new ExcepcionDao();
			//expDao.setExcepcionOrigen(e);
			//expDao.setMensajeUsuario("Error almacenando persona");
			//throw expDao;
		}
		finally{
			session.close();
		}
	}
	
	public void crearPersona(UsuarioDto usuarioDto){
		Session session = null;
		try{
			session = this.getSession();
			session.save(usuarioDto);
			session.flush();
		}catch(Exception e){
			//ExcepcionDao expDao = new ExcepcionDao();
			//expDao.setExcepcionOrigen(e);
			//expDao.setMensajeUsuario("Error almacenando persona");
			//throw expDao;
		}
		finally{
			if(session!=null){
				session.close();
			}
		}		

	}
	
	public UsuarioDto buscarUsuarioModificar(int tipoId, int id) {
		UsuarioDto usuarioDto=null;
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from UsuarioDto u where u.tipoDocumento.idn=? and u.identificacion=?");
			query.setInteger(0,tipoId);
			query.setInteger(1,id);
			//query.setInteger(2,estadoActivo);
			List<UsuarioDto> lista=query.list();
			if( (lista!=null) && (lista.size()==1)){
				usuarioDto=lista.get(0);
			}			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return usuarioDto;
	}

}
