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

}
