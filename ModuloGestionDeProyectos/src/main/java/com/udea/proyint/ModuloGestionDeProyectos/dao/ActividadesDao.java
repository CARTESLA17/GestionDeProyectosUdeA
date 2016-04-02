package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.ActividadesDto;

public class ActividadesDao extends HibernateDaoSupport implements ActividadesDaoInt {

	public ArrayList<ActividadesDto> listarActividadesxObjetivo(Integer idnObjetivoEspecifico) {
		
		ArrayList<ActividadesDto> actividades = null;
		Session session = null;
		try {
			session = this.getSession();
			Query query=session.createQuery("select axp from ActividadesDto axp where axp.objEspecif.idn=? ");
			query.setInteger(0,idnObjetivoEspecifico);
			actividades = (ArrayList<ActividadesDto>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if(session!=null){
				session.close();
			}
		}
		return actividades;
	}

	public void guardarActividades(ArrayList<ActividadesDto> listaActividades) {
		org.hibernate.Transaction tx = null;
		Session session = null;
		try{
			session = this.getSessionFactory().openSession();
			for (ActividadesDto actividad : listaActividades) {
				tx = session.beginTransaction();
				session.save(actividad);
				tx.commit();
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	public void guardarActividad(ActividadesDto actividad) {
		org.hibernate.Transaction tx = null;
		Session session = null;
		try{
			session = this.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(actividad);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	public ActividadesDto listarUnaActividad(Integer identificacionActividad) {
		// TODO Auto-generated method stub
		return null;
	}

	public void actualizarActividad(ActividadesDto actividad) {
		// TODO Auto-generated method stub
	}

	public void borrarActividad(Integer idnObjetivoEspecifico, Integer identificacionActividade) {
		// TODO Auto-generated method stub
	}

}
