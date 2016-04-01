package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.AsesorXProyectoDto;
import com.udea.proyint.Dominio.dto.ProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public class AsesorXProyectoDao extends HibernateDaoSupport implements AsesorXProyectoDaoInt{
	
	private final int ESTADO_PROYECTO_TODOS=7;

	public AsesorXProyectoDao() {		
	}

	public ArrayList<AsesorXProyectoDto> buscarProyectosPorEstadoYRolAsesor(
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

	public ArrayList<AsesorXProyectoDto> buscarAsesorXProyecto(int idProyecto) {
		ArrayList<AsesorXProyectoDto> lista=null;
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select axp from AsesorXProyectoDto axp where axp.asesorXProyectoDtoId.proyecto.idn=? ");
			query.setInteger(0,idProyecto);
			//query.setInteger(2,estadoActivo);
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

}
