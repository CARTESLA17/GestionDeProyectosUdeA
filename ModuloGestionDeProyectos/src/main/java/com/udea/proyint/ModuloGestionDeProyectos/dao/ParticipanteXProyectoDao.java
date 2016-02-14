package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.ParticipanteXProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public class ParticipanteXProyectoDao extends HibernateDaoSupport implements ParticipanteXProyectoDaoInt {

	private final int ESTADO_PROYECTO_TODOS=7;
	
	public ArrayList<ParticipanteXProyectoDto> buscarProyectosPorEstadoYRolParticipante(
			UsuarioDto usuarioDto, Integer idnEstado) {
		ArrayList<ParticipanteXProyectoDto> lista=null;
		Session session = null;
		StringBuilder consulta=new StringBuilder();
		try{			
			session = this.getSession();
			Query query=null;
			consulta.append("select pxp from ParticipanteXProyectoDto pxp where pxp.participanteXProyectoDtoId.participante.idn=? ");
			if( idnEstado == ESTADO_PROYECTO_TODOS ){
				query=session.createQuery(consulta.toString());				
			}else{
				consulta.append("and pxp.participanteXProyectoDtoId.proyecto.estadoDelProyecto.idn=? ");
				query=session.createQuery(consulta.toString());
				query.setInteger(1,idnEstado);	
			}
			query.setInteger(0,usuarioDto.getIdn());					
			lista=(ArrayList<ParticipanteXProyectoDto>) query.list();						
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
