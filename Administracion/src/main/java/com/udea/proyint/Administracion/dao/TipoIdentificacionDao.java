package com.udea.proyint.Administracion.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;




import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.TipoDocumentoDto;

public class TipoIdentificacionDao extends HibernateDaoSupport implements TipoIdentificacionDaoInt {
	
	//Constructor
	public TipoIdentificacionDao() {
	}

	public ArrayList<TipoDocumentoDto> buscarTiposId() {
		ArrayList<TipoDocumentoDto> lista= new ArrayList<TipoDocumentoDto>();
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select t from TipoDocumentoDto t");
			lista=(ArrayList<TipoDocumentoDto>) query.list();
						
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
