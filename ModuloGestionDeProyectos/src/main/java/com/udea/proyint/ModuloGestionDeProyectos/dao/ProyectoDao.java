package com.udea.proyint.ModuloGestionDeProyectos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Spring;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.udea.proyint.Dominio.dto.AsesorXProyectoDto;
import com.udea.proyint.Dominio.dto.AsesorXProyectoDtoId;
import com.udea.proyint.Dominio.dto.EstadoDelProyectoDto;
import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;
import com.udea.proyint.Dominio.dto.ParticipanteXProyectoDto;
import com.udea.proyint.Dominio.dto.ParticipanteXProyectoDtoId;
import com.udea.proyint.Dominio.dto.ProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public class ProyectoDao extends HibernateDaoSupport implements ProyectoDaoInt{
	
	private final int ESTADO_PROYECTO_TODOS=7;
	
	public ProyectoDao() {
	}

	public ProyectoDto almacenarProyecto(ProyectoDto proyectoDto, ArrayList<UsuarioDto> listadoAsesores,
			ArrayList<UsuarioDto> listadoParticipantes, ArrayList<ObjetivoEspecificoDto> listadoObjetivosEspecificos) {
			return guardarProyecto(proyectoDto, listadoAsesores, listadoParticipantes,listadoObjetivosEspecificos, this.getSession());
	}
	
	private static synchronized ProyectoDto guardarProyecto(ProyectoDto proyectoDto, ArrayList<UsuarioDto> listadoAsesores,
			ArrayList<UsuarioDto> listadoParticipantes, ArrayList<ObjetivoEspecificoDto> listadoObjetivosEspecificos,
			Session session) { 
		Transaction tx=null;
		try {			
			tx=session.beginTransaction();
			session.save(proyectoDto);
			session.flush();
			Query query = session.createQuery("select p from ProyectoDto p where p.idn = (select max(pd.idn) from ProyectoDto pd)");
			List<ProyectoDto> lista=query.list();
			if( (lista!=null) && (lista.size()==1)){
				proyectoDto=lista.get(0);				
			}
			for(ObjetivoEspecificoDto objetivoEspecificoDto: listadoObjetivosEspecificos){
				objetivoEspecificoDto.setProyecto(proyectoDto);
				session.save(objetivoEspecificoDto);
				session.flush();
			}
			AsesorXProyectoDtoId asesorXProyectoId= null;
			AsesorXProyectoDto asesorXProyectoDto=null;
			for(UsuarioDto usuarioDto: listadoAsesores){
				asesorXProyectoId= new AsesorXProyectoDtoId();
				asesorXProyectoId.setAsesor(usuarioDto);
				asesorXProyectoId.setProyecto(proyectoDto);
				asesorXProyectoDto= new AsesorXProyectoDto();
				asesorXProyectoDto.setAsesorXProyectoDtoId(asesorXProyectoId);
				session.save(asesorXProyectoDto);
				session.flush();				
			}
			ParticipanteXProyectoDtoId participanteXProyectoDtoId= null;
			ParticipanteXProyectoDto participanteXProyectoDto= null;
			for(UsuarioDto usuarioDto: listadoParticipantes){
				participanteXProyectoDtoId= new ParticipanteXProyectoDtoId();
				participanteXProyectoDtoId.setParticipante(usuarioDto);
				participanteXProyectoDtoId.setProyecto(proyectoDto);
				participanteXProyectoDto= new ParticipanteXProyectoDto();
				participanteXProyectoDto.setParticipanteXProyectoDtoId(participanteXProyectoDtoId);
				session.save(participanteXProyectoDto);
				session.flush();
			}			
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return proyectoDto;
	}

	public ArrayList<ProyectoDto> buscarProyectosPorEstadoYRolResponsable(
			UsuarioDto usuarioDto, Integer idnEstado) {
		ArrayList<ProyectoDto> lista=null;
		Session session = null;
		StringBuilder consulta=new StringBuilder();
		try{			
			session = this.getSession();
			Query query=null;
			consulta.append("select p from ProyectoDto p where p.responsable.idn=? ");
			if( idnEstado == ESTADO_PROYECTO_TODOS ){
				query=session.createQuery(consulta.toString());				
			}else{
				consulta.append("and p.estadoDelProyecto.idn=? ");
				query=session.createQuery(consulta.toString());
				query.setInteger(1,idnEstado);	
			}
			query.setInteger(0,usuarioDto.getIdn());					
			lista=(ArrayList<ProyectoDto>) query.list();						
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}			
		return lista;		
	}

	public ProyectoDto buscarProyectoModificar(int idProyecto) {
		ProyectoDto proyectoDto = null;
		Session session = null;
		try{			
			session = this.getSession();
			Query query=session.createQuery("select u from ProyectoDto u where u.idn=?");
			query.setInteger(0,idProyecto);
			//query.setInteger(2,estadoActivo);
			List<ProyectoDto> lista=query.list();
			if( (lista!=null) && (lista.size()==1)){
				proyectoDto=lista.get(0);
			}			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return proyectoDto;
	}
		
	public void actualizar(int id, ProyectoDto proyectoDto){
		Session session = null;
		Transaction tx=null;
		try{			
			session = this.getSession();
			ProyectoDto proyectoDtoAux = null;
			Query query=session.createQuery("select p from ProyectoDto p where p.idn=?");
			query.setInteger(0,id);
			List<ProyectoDto> lista=query.list();
			if( (lista!=null) && (lista.size()==1)){
				proyectoDtoAux=lista.get(0);
				}
			proyectoDtoAux.setEstadoDelProyecto(proyectoDto.getEstadoDelProyecto());
			tx=session.beginTransaction();
			session.save(proyectoDtoAux);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}

}

