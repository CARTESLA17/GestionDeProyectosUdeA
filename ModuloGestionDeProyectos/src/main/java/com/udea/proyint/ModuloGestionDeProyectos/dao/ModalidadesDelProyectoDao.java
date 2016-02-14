package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.ModalidadesDelProyectoDto;
import com.udea.proyint.Dominio.dto.TiposDeProyectoDto;

public class ModalidadesDelProyectoDao extends HibernateDaoSupport implements ModalidadesDelProyectoDaoInt{

	public ModalidadesDelProyectoDao() {
	}

	public ArrayList<ModalidadesDelProyectoDto> buscarModalidades() {
		ArrayList<ModalidadesDelProyectoDto> lista= new ArrayList<ModalidadesDelProyectoDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select m from ModalidadesDelProyectoDto m");
			lista=(ArrayList<ModalidadesDelProyectoDto>) query.list();						
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
