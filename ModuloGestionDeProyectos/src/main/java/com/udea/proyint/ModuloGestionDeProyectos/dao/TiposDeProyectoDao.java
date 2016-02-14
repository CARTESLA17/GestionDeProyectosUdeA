package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.TiposDeProyectoDto;

public class TiposDeProyectoDao extends HibernateDaoSupport implements TiposDeProyectoDaoInt{

	public TiposDeProyectoDao() {	
	}

	public ArrayList<TiposDeProyectoDto> buscarTiposDeProyecto() {
		ArrayList<TiposDeProyectoDto> lista= new ArrayList<TiposDeProyectoDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select t from TiposDeProyectoDto t");
			lista=(ArrayList<TiposDeProyectoDto>) query.list();						
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
