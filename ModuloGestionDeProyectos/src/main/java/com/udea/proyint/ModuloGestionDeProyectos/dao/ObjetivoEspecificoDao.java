package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;

public class ObjetivoEspecificoDao extends HibernateDaoSupport implements ObjetivoEspecificoDaoInt {

	public ArrayList<ObjetivoEspecificoDto> buscarObjetivoEspecificoXProyecto(int idProyecto) {
		ArrayList<ObjetivoEspecificoDto> lista=null;
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select oe from ObjetivoEspecificoDto oe where oe.proyecto.idn=? ");
			query.setInteger(0,idProyecto);
			//query.setInteger(2,estadoActivo);
			lista=(ArrayList<ObjetivoEspecificoDto>) query.list();
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
