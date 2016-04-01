package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.ActividadesDto;
import com.udea.proyint.ModuloGestionDeProyectos.dto.dtoActividades;

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

	public void guardarActividades(ArrayList<dtoActividades> listaActividades) {
		// TODO Auto-generated method stub

	}

	public void guardarActividad(String nombreActividad, Integer porcentajeActividad, String adtUsuario,
			Integer objEspecif, Integer estActivid) {
		// TODO Auto-generated method stub

	}

	public ActividadesDto listarUnaActividad(Integer idnObjetivoEspecifico, Integer identificacionActividade) {
		/***
		 * public ArrayList<AsesorXProyectoDto> buscarProyectosPorEstadoYRolAsesor(
			UsuarioDto usuarioDto, Integer idnEstado) {
		ArrayList<AsesorXProyectoDto> lista=null;
		Session session = null;
		StringBuilder consulta=new StringBuilder();
		try{			
			session = this.getSession();
			Query query=null;
			consulta.append("select axp from AsesorXProyectoDto axp where axp.asesorXProyectoDtoId.asesor.idn=? ");
			if( idnEstado == ESTADO_PROYECTO_TODOS){
				query=session.createQuery(consulta.toString());				
			}else{
				consulta.append("and axp.asesorXProyectoDtoId.proyecto.estadoDelProyecto.idn=? ");
				query=session.createQuery(consulta.toString());
				query.setInteger(1,idnEstado);	
			}
			query.setInteger(0,usuarioDto.getIdn());					
			lista=(ArrayList<AsesorXProyectoDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}			
		return lista;			
	}
		 */
		return null;
	}

	public void actualizarActividad(ActividadesDto actividad) {
		// TODO Auto-generated method stub
		
	}

	public void borrarActividad(Integer idnObjetivoEspecifico, Integer identificacionActividade) {
		// TODO Auto-generated method stub
		
	}

}
