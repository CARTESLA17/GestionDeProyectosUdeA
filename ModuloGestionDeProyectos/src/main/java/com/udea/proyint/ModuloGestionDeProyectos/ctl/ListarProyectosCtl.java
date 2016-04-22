package com.udea.proyint.ModuloGestionDeProyectos.ctl;

import java.io.IOException;
import java.util.ArrayList;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

import com.udea.proyint.Dominio.dto.AsesorXProyectoDto;
import com.udea.proyint.Dominio.dto.EstadoDelProyectoDto;
import com.udea.proyint.Dominio.dto.ParticipanteXProyectoDto;
import com.udea.proyint.Dominio.dto.ProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.AsesorXProyectoNgcInt;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.EstadosDelProyectoNgcInt;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.ParticipanteXProyectoNgcInt;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.ProyectoNgcInt;

public class ListarProyectosCtl extends GenericForwardComposer{
	
	//beans
	private EstadosDelProyectoNgcInt estadosDelProyectoNgc;
	private ProyectoNgcInt proyectoNgc;
	private AsesorXProyectoNgcInt asesorXProyectoNgc;
	private ParticipanteXProyectoNgcInt participanteXProyectoNgc;
	
	private UsuarioDto usuarioDto=null;
	private ProyectoDto proyectoDto;
	
	private Combobox cbxEstados;
	private Grid gridListarProyectos;
	private Div div;
	
	//Partes de los nombres de las filas de las grid.	
	private String parteIdCIEstadosDelProyecto="ciep_";
	private String parteIdRowProyecto="rp_";
	private String parteIdLblNombre="lbln_";
	private String parteIdLblFechaInicial="lblfi_";
	private String parteIdLblFechaFinal="lblff_";
	private String parteIdLblTipo="lblt_";
	private String parteIdLblRol="lblr_";
	private String parteIdLblAvance="lvla_";
	private String parteIdCbAcciones="cba_";
		
	private final int ROLRESPONSABLE=1;
	private final int ROLASESOR=2; 
	private final int ROLPARTICIPANTE=3; 
	
	private final int ID_VISUALIZAR_PROYECTO=1;
	private final int ID_MODIFICAR_PROYECTO=2;
	private final int ID_VISUALIZAR_ACTIVIDADES=3;
	private final int ID_VISUALIZAR_CRONOGRAMA=4;
	private final int ID_SUSPENDER_PROYECTO=5;
	private final int ID_CANCELAR_PROYECTO=6;
	private final int ID_FINALIZAR_PROYECTO=7;
	private final int ID_REANUDAR_PROYECTO=8;
	
	private final int ESTADO_PROYECTO_ABIERTO=1;
	private final int ESTADO_PROYECTO_EN_PROCESO=2;
	private final int ESTADO_PROYECTO_SUSPENDIDO=3;
	private final int ESTADO_PROYECTO_CANCELADO=4;
	private final int ESTADO_PROYECTO_VENCIDO=5;
	private final int ESTADO_PROYECTO_FINALIZADO=6;
	private final int ESTADO_PROYECTO_TODOS=7;
	
	private final int ROL_SISTEMA_SUPERUSUARIO=1;
	private final int ROL_SISTEMA_PROFESOR=2;
	public static int ROL_SISTEMA_ESTUDIANTE=3;
	public static int ROL_SISTEMA_JEFE_DEPARTAMENTO=4;
	
	public void setEstadosDelProyectoNgc(
			EstadosDelProyectoNgcInt estadosDelProyectoNgc) {
		this.estadosDelProyectoNgc = estadosDelProyectoNgc;
	}	

	public void setProyectoNgc(ProyectoNgcInt proyectoNgc) {
		this.proyectoNgc = proyectoNgc;
	}

	public void setAsesorXProyectoNgc(AsesorXProyectoNgcInt asesorXProyectoNgc) {
		this.asesorXProyectoNgc = asesorXProyectoNgc;
	}

	public void setParticipanteXProyectoNgc(
			ParticipanteXProyectoNgcInt participanteXProyectoNgc) {
		this.participanteXProyectoNgc = participanteXProyectoNgc;
	}

	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        if( (Sessions.getCurrent().hasAttribute("usuario")) ){
			usuarioDto = (UsuarioDto)(Sessions.getCurrent().getAttribute("usuario"));
		}else{
			Executions.sendRedirect("index.zul");	
		} 
        cargarEstadosDelProyecto();
	}

	public void cargarEstadosDelProyecto(){
		ArrayList<EstadoDelProyectoDto> lista = estadosDelProyectoNgc.buscarEstadosDelProyecto();
		if (lista != null) {
			for(EstadoDelProyectoDto estado: lista){
				Comboitem comboItem = llenarComboboxEstadosDelProyecto(estado,parteIdCIEstadosDelProyecto);
				cbxEstados.appendChild(comboItem);
			}
			cbxEstados.appendChild(generarComboItemTodos(parteIdCIEstadosDelProyecto));			
		}
	}
	
	public Comboitem llenarComboboxEstadosDelProyecto(EstadoDelProyectoDto estado, String parteNombreId) {
		final Comboitem comboI= new Comboitem();
		comboI.setLabel(estado.getNombre());
		comboI.setId(parteNombreId+estado.getIdn());		
		comboI.setSclass("sidebar-fn");
		return comboI;
	}
	
	public Comboitem generarComboItemTodos(String parteNombreId){
		final Comboitem comboI= new Comboitem();
		comboI.setLabel(Labels.getLabel("ciTodos")); 
		comboI.setId(parteNombreId+ESTADO_PROYECTO_TODOS);		
		comboI.setSclass("sidebar-fn");
		return comboI;
	}
	
	public void onClick$buscarProyectos(Event ev) {
		limpiarRows(gridListarProyectos);
		Comboitem comboItem=cbxEstados.getSelectedItem();
		String estado=null;
		Integer idnEstado=0;
		ArrayList<ProyectoDto> proyectosRolResponsable=null;
		ArrayList<AsesorXProyectoDto> proyectosRolAsesor=null;
		ArrayList<ParticipanteXProyectoDto> proyectosRolParticipante=null;
		if( comboItem!=null ){
			estado= comboItem.getId();
			if( (estado!=null) && (estado.length()>=5) ){ 
				estado=estado.substring(5);
				if( estado.matches("[0-9]+") ){
					idnEstado=Integer.parseInt(estado);
				}else{
					return;
				}
			}else{
				return;
			}
		}else{
			return;
		}
		if( (usuarioDto.getRol().getIdn()==ROL_SISTEMA_SUPERUSUARIO) || (usuarioDto.getRol().getIdn()==ROL_SISTEMA_JEFE_DEPARTAMENTO) 
				|| (usuarioDto.getRol().getIdn()==ROL_SISTEMA_PROFESOR) ){
			proyectosRolResponsable=proyectoNgc.buscarProyectosPorEstadoYRolResponsable(usuarioDto, idnEstado);
		}
		proyectosRolAsesor=asesorXProyectoNgc.buscarProyectosPorEstadoYRolAsesor(usuarioDto, idnEstado);
		proyectosRolParticipante=participanteXProyectoNgc.buscarProyectosPorEstadoYRolParticipante(usuarioDto, idnEstado);
		 Rows rows = gridListarProyectos.getRows(); 
		if( proyectosRolResponsable!=null ){			
			 for(ProyectoDto proyecto: proyectosRolResponsable){
				 rows.appendChild(construirFilaProyecto(proyecto, ROLRESPONSABLE));				 
			 }		       
		}	
		
	}
	
	public Row construirFilaProyecto(ProyectoDto proyecto,  final int rol){	
		final Row row = new Row();
		row.setId(parteIdRowProyecto+proyecto.getIdn());
		Label lblNombre= new Label();
		lblNombre.setId(parteIdLblNombre+proyecto.getIdn());
		lblNombre.setStyle("color: #000000!important; font-size: 13px");
		lblNombre.setValue(proyecto.getNombreDelProyecto());
		row.appendChild(lblNombre);
		Label lblFechaInicial= new Label();
		lblFechaInicial.setId(parteIdLblFechaInicial+proyecto.getIdn());
		lblFechaInicial.setStyle("color: #000000!important; font-size: 13px");
		lblFechaInicial.setValue(proyecto.getFechaInicial().toString());
		row.appendChild(lblFechaInicial);
		Label lblFechaFinal= new Label();
		lblFechaFinal.setId(parteIdLblFechaFinal+proyecto.getIdn());
		lblFechaFinal.setStyle("color: #000000!important; font-size: 13px");
		lblFechaFinal.setValue(proyecto.getFechaFinal().toString());
		row.appendChild(lblFechaFinal);
		Label lblTipo= new Label();
		lblTipo.setId(parteIdLblTipo+proyecto.getIdn());
		lblTipo.setStyle("color: #000000!important; font-size: 13px");
		lblTipo.setValue(proyecto.getTipoDeProyecto().getNombre());
		row.appendChild(lblTipo);	
		Label lblRol= new Label();
		lblRol.setId(parteIdLblRol+proyecto.getIdn());
		lblRol.setStyle("color: #000000!important; font-size: 13px");
		switch (rol) {
			case ROLRESPONSABLE:
				lblRol.setValue(Labels.getLabel("lblRolResponsable"));
				break;
			case ROLASESOR:
				lblRol.setValue(Labels.getLabel("lblRolAsesor"));
				break;
			case ROLPARTICIPANTE :
				lblRol.setValue(Labels.getLabel("lblRolParticipante"));
				break;
			default:
				break;
		}		
		row.appendChild(lblRol);	
		Label lblAvance= new Label();
		lblAvance.setId(parteIdLblAvance+proyecto.getIdn());
		lblAvance.setStyle("color: #000000!important; font-size: 13px");
		lblAvance.setValue("");
		row.appendChild(lblAvance);
		Combobox comboboxAcciones= new Combobox();
		comboboxAcciones.setId(parteIdCbAcciones+proyecto.getIdn());
		switch (rol) {
			case ROLRESPONSABLE:
				switch (proyecto.getEstadoDelProyecto().getIdn()) {
					case ESTADO_PROYECTO_ABIERTO:
					case ESTADO_PROYECTO_EN_PROCESO:
						Comboitem ciVisualizarProyecto=new Comboitem(Labels.getLabel("lblVisualizarProyecto"));
						ciVisualizarProyecto.setId(ID_VISUALIZAR_PROYECTO+"");
						comboboxAcciones.appendChild(ciVisualizarProyecto);
						Comboitem ciModificarProyecto=new Comboitem(Labels.getLabel("lblModificarProyecto"));
						ciModificarProyecto.setId(ID_MODIFICAR_PROYECTO+"");
						comboboxAcciones.appendChild(ciModificarProyecto);
						Comboitem ciVisualizarActividades=new Comboitem(Labels.getLabel("lblVisualizarActividades"));
						ciVisualizarActividades.setId(ID_VISUALIZAR_ACTIVIDADES+"");
						comboboxAcciones.appendChild(ciVisualizarActividades);
						Comboitem ciVisualizarCronograma=new Comboitem(Labels.getLabel("lblVisualizarCronograma"));
						ciVisualizarCronograma.setId(ID_VISUALIZAR_CRONOGRAMA+"");
						comboboxAcciones.appendChild(ciVisualizarCronograma);	
						Comboitem ciSuspenderProyecto=new Comboitem(Labels.getLabel("lblSuspenderProyecto"));
						ciSuspenderProyecto.setId(ID_SUSPENDER_PROYECTO+"");
						comboboxAcciones.appendChild(ciSuspenderProyecto);				
						Comboitem ciCancelarProyecto=new Comboitem(Labels.getLabel("lblCancelarProyecto"));
						ciCancelarProyecto.setId(ID_CANCELAR_PROYECTO+"");
						comboboxAcciones.appendChild(ciCancelarProyecto);				
						Comboitem ciFinalizarProyecto=new Comboitem(Labels.getLabel("lblFinalizarProyecto"));
						ciFinalizarProyecto.setId(ID_FINALIZAR_PROYECTO+"");
						comboboxAcciones.appendChild(ciFinalizarProyecto);						
						break;
					case ESTADO_PROYECTO_SUSPENDIDO:
						Comboitem ciVisualizarProy=new Comboitem(Labels.getLabel("lblVisualizarProyecto"));
						ciVisualizarProy.setId(ID_VISUALIZAR_PROYECTO+"");
						comboboxAcciones.appendChild(ciVisualizarProy);
						Comboitem ciReanudarProyecto=new Comboitem(Labels.getLabel("lblReanudarProyecto"));
						ciReanudarProyecto.setId(ID_REANUDAR_PROYECTO+"");
						comboboxAcciones.appendChild(ciReanudarProyecto);
						break;
					case ESTADO_PROYECTO_CANCELADO:
					case ESTADO_PROYECTO_VENCIDO:
					case ESTADO_PROYECTO_FINALIZADO:
						Comboitem ciVisuaProy=new Comboitem(Labels.getLabel("lblVisualizarProyecto"));
						ciVisuaProy.setId(ID_VISUALIZAR_PROYECTO+"");
						comboboxAcciones.appendChild(ciVisuaProy);						
						break;
					default:
						break;
				}
				break;
			case ROLASESOR:				
			case ROLPARTICIPANTE :
				switch (proyecto.getEstadoDelProyecto().getIdn()) {
					case ESTADO_PROYECTO_ABIERTO:
					case ESTADO_PROYECTO_EN_PROCESO:
						Comboitem ciVisualizarProyecto=new Comboitem(Labels.getLabel("lblVisualizarProyecto"));
						ciVisualizarProyecto.setId(ID_VISUALIZAR_PROYECTO+"");
						comboboxAcciones.appendChild(ciVisualizarProyecto);
						Comboitem ciVisualizarActividades=new Comboitem(Labels.getLabel("lblVisualizarActividades"));
						ciVisualizarActividades.setId(ID_VISUALIZAR_ACTIVIDADES+"");
						comboboxAcciones.appendChild(ciVisualizarActividades);
						Comboitem ciVisualizarCronograma=new Comboitem(Labels.getLabel("lblVisualizarCronograma"));
						ciVisualizarCronograma.setId(ID_VISUALIZAR_CRONOGRAMA+"");
						comboboxAcciones.appendChild(ciVisualizarCronograma);						
						break;	
					case ESTADO_PROYECTO_SUSPENDIDO:
					case ESTADO_PROYECTO_CANCELADO:
					case ESTADO_PROYECTO_VENCIDO:
					case ESTADO_PROYECTO_FINALIZADO:
						Comboitem ciVisuaProy=new Comboitem(Labels.getLabel("lblVisualizarProyecto"));
						ciVisuaProy.setId(ID_VISUALIZAR_PROYECTO+"");
						comboboxAcciones.appendChild(ciVisuaProy);	
						break;
					default:
						break;
				}				
				break;
			default:
				break;
		}	
		
		EventListener<Event> actionListenerAnexar = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
//				Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
//				if( (filaDelClick != null) && (filaDelClick.hasAttribute("proyecto")) ){
					
//				}
				Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
				String getId = filaDelClick.getId();
				int idProyecto = Integer.parseInt(getId.substring(3));
				System.out.println("id delafila: "+filaDelClick.getId());
				System.out.println("id del proyecto : "+idProyecto);
				
				proyectoDto = proyectoNgc.buscarProyectoModificar(idProyecto);
				if(proyectoDto!=null){
					System.out.println("sesion creada");
					Sessions.getCurrent().setAttribute("proyecto",proyectoDto);				
					//Executions.sendRedirect("llamadoAlMenu.zul");
				}else{
					System.out.println("fallo");
				}

				java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/ModuloGestionDeProyectos/vista/formularioModificarProyecto.zul") ;
				java.io.Reader zulReader = new java.io.InputStreamReader(zulInput);
				Window win = null;
				try {
					win = (Window)Executions.createComponentsDirectly(zulReader,"zul",null,null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(div.getFirstChild()!=null){
					div.removeChild(div.getFirstChild());
				}
				div.appendChild(win);
			}	
			
		};
		EventListener<Event> actionListenerbtnCambiarEstado = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
				Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
				String getId = filaDelClick.getId();
				int idProyecto = Integer.parseInt(getId.substring(3));
				proyectoDto = proyectoNgc.buscarProyectoModificar(idProyecto);
				Sessions.getCurrent().setAttribute("proyectos",proyectoDto);	
				Sessions.getCurrent().setAttribute("rol",rol);
				java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/ModuloGestionDeProyectos/vista/cambiarEstado.zul") ;
				java.io.Reader zulReader = new java.io.InputStreamReader(zulInput);
				Window win = null;
				try {
					win = (Window)Executions.createComponentsDirectly(zulReader,"zul",null,null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(div.getFirstChild()!=null){
					div.removeChild(div.getFirstChild());
				}
				div.appendChild(win);
			}
		};
		Button btnAnexarAsesores=new Button();
		//btnAnexarAsesores.setId(parteIdBtnAnexarAsesores+usuario.getIdn());
		btnAnexarAsesores.addEventListener(Events.ON_CLICK, actionListenerAnexar);	
		btnAnexarAsesores.setLabel(Labels.getLabel("btnEditar"));
		btnAnexarAsesores.setVisible(true);
		row.appendChild(btnAnexarAsesores);		
		Button btnCambiarEstado=new Button();
		btnCambiarEstado.addEventListener(Events.ON_CLICK, actionListenerbtnCambiarEstado);	
		btnCambiarEstado.setLabel(Labels.getLabel("btnCambiarEstado"));
		btnCambiarEstado.setVisible(true);
		row.appendChild(btnCambiarEstado);	
		row.setAlign("center");
		row.setSclass("sidebar-fn");			
		row.setVflex("min");
		row.setAttribute("proyecto", proyecto);
		return row;
	}
	
	public void limpiarRows(Grid grid){
		if( grid != null){
			grid.removeChild(grid.getRows());
			grid.appendChild(new Rows());
		}
	}
	
}
