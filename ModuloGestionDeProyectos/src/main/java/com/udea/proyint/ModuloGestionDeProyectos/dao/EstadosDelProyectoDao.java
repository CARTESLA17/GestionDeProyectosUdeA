package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.EstadoDelProyectoDto;


public class EstadosDelProyectoDao extends HibernateDaoSupport implements EstadosDelProyectoDaoInt{	

	public EstadosDelProyectoDao() {
		super();		
	}

	public ArrayList<EstadoDelProyectoDto> buscarEstadosDelProyecto() {
		ArrayList<EstadoDelProyectoDto> lista= new ArrayList<EstadoDelProyectoDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select e from EstadoDelProyectoDto e");
			lista=(ArrayList<EstadoDelProyectoDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}			
		return lista;
	}
	
	public String nombre(int id){
		ArrayList<EstadoDelProyectoDto> lista= new ArrayList<EstadoDelProyectoDto>();
		EstadoDelProyectoDto estado = null;
		Session session = null;
		String nombre;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select e from EstadoDelProyectoDto e where e.idn=?");
			query.setInteger(0,id);
			lista=(ArrayList<EstadoDelProyectoDto>) query.list();
			if( (lista!=null) && (lista.size()==1)){
				estado = lista.get(0);
			}
			nombre = estado.getNombre();
			return nombre;
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}	
		return null;
	}

}
