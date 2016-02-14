package com.udea.proyint.Administracion.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.RolSistemaDto;
import com.udea.proyint.Dominio.dto.TipoDocumentoDto;

public class TipoRolDao extends HibernateDaoSupport implements TipoRolDaoInt{

	//Constructor
	public TipoRolDao() {
	}

	public ArrayList<RolSistemaDto> buscarTiposRol() {
		ArrayList<RolSistemaDto> lista = new ArrayList<RolSistemaDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select t from RolSistemaDto t");
			lista=(ArrayList<RolSistemaDto>) query.list();
						
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
