package com.udea.proyint.ModuloGestionDeProyectos.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import com.udea.proyint.Administracion.ngc.PersonaNgcInt;
import com.udea.proyint.Administracion.ngc.TipoIdentificacionNgcInt;
import com.udea.proyint.Dominio.dto.EstadoDelProyectoDto;
import com.udea.proyint.Dominio.dto.ModalidadesDelProyectoDto;
import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;
import com.udea.proyint.Dominio.dto.ProyectoDto;
import com.udea.proyint.Dominio.dto.TipoDocumentoDto;
import com.udea.proyint.Dominio.dto.TiposDeProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.ModalidadesDelProyectoNgcInt;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.ProyectoNgcInt;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.TiposDeProyectoNgcInt;

/**
 * @author Duvan Sanchez
 * @author Hader Pelaez
 * @author Carlos Rodriguez(CAR)
 * @version 1.0.0
 * Clase que controla la vista donde se generan los proyectos.
 */
public class CrearProyectoCtl extends GenericForwardComposer{
	
	//Componentes de la vista
	private Label lblCrearProyecto;
	private Label lblResponsable;
	private Label lblNombreResponsable;
	private Label lblNombreProyecto;
	private Textbox tbxNombreProyecto;
	private Label lblTipoProyecto;
	private Combobox cbxTipoProyecto;
	private Label lblModalidad;
	private Combobox cbxModalidad;
	private Label lblFechaInicial;
	private Datebox dateFechaInicial;
	private Label lblFechaFinal;
	private Datebox dateFechaFinal;
	private Label lblObjetivoGeneral;
	private Textbox tbxObjetivoGeneral;
	private Label lblObjetivosEspecificos;
	private Grid gridObjetivosEspecificos;
	private Combobox cbxTipoIDAsesores;
	private Combobox cbxTipoIDParticipantes;
	private Textbox tbxIDAsesores;
	private Textbox tbxNombresAsesores;
	private Textbox tbxApellidosAsesores;
	private Textbox tbxIDParticipantes;
	private Textbox tbxNombresParticipantes;
	private Textbox tbxApellidosParticipantes;
	private Grid gridBusquedaParticipantes;
	private Grid gridBusquedaAsesores;
	private Grid gridAsesores;
	private Grid gridParticipantes;
	private Textbox tbxObjetivoEspecifico;
	private Textbox tbxPorcentaje;
	
	/**
	 * Cantidad de filas que tendran los textbox para los objetivos especificos.
	 */
	private int filasObjEspecifico=3;
	
	/**
	 * Ancho que tendran los textbox para los objetivos especificos.
	 */
	private String anchoObjEspecifico="300px";
	
	/**
	 * Ancho que tendran los textbox para los porcentajes de los objetivos especificos.
	 */
	private String anchoPorcentajeObjEsp="90px";
	
	/**
	 * Es la variable que se incrementara cada vez que se construye 
	 * una fila, esta se asignara como id a la fila y a los componentes 
	 * de dicha fila.
	 */
	private int idContadorRows=1;
	
	/**
	 * Es la variable que controla la cantidad de filas que hay en la  grid 
	 * de objetivos especificos, si el valor de esta es mayor a uno permite
	 * borrar la fila del objetivo especifico, de lo contrario no.
	 */
	private int rowsEnGridObjEspecificos=0;
	
	/**
	 * Es la variable que tendra la sumatoria de los porcentajes de los objetivos 
	 * especificos, esta variable debe ser igual o menor a cien para permitir 
	 * almacenar el proyecto
	 */
	private int porcentajeTotalObjEspecificos=0;
	
	private EventQueue eventoQueueActivarVentanaMenu;
	
	private ArrayList<UsuarioDto> listadoAsesores= new ArrayList<UsuarioDto>();
	private ArrayList<UsuarioDto> listadoParticipantes= new ArrayList<UsuarioDto>();
	private ArrayList<ObjetivoEspecificoDto> listadoObjetivosEspecificos= new ArrayList<ObjetivoEspecificoDto>();
	private ProyectoDto proyectoDto = null;
	private UsuarioDto usuarioDto=null;
	
	//private static Logger logger=Logger.getLogger(CrearProyectoCtl.class);
	
	//beans
	private TiposDeProyectoNgcInt tiposDeProyectoNgc;
	private ModalidadesDelProyectoNgcInt modalidadesNgc;
	private TipoIdentificacionNgcInt tipoIdentificacionNgc;
	private PersonaNgcInt personaNgc;
	private ProyectoNgcInt proyectoNgc;
	
	//Partes de los nombres de las filas de las grid.	
	private String parteIdCIModalidades="cim_";
	private String parteIdCITipoProyecto="citp_";
	private String parteIdCIAsesores="citia_";	
	private String parteIdCIParticipantes="citip_";
	private String parteIdTbxObjEsp="tbxObjEsp_";
	private String parteIdTbxPorcentaje="tbxPor_";
	private String parteIdRowBusquedaAsesores="rba_";
	private String parteIdRowBusquedaParticipantes="rbp_";
	private String parteIdLblTidAsesores="lblTidA_";
	private String parteIdLblIdAsesores="lblIdA_";
	private String parteIdLblNombresAsesores="lblNombresA_";
	private String parteIdLblApellidosAsesores="lblApellidosA_";
	private String parteIdLblTidParticipantes="lblTidP_";
	private String parteIdLblIdParticipantes="lblIdP_";
	private String parteIdLblNombresParticipantes="lblNombresP_";
	private String parteIdLblApellidosParticipantes="lblApellidosP_";
	private String parteIdBtnAnexarAsesores="btnAnexarA_";
	private String parteIdBtnAnexarParticipantes="btnAnexarP_";	
	private String parteIdRowAsesoresInt="rai_";
	private String parteIdRowParticipantesInt="rpi_";
	private String parteIdLblTidAsesoresInt="lblTidAi_";
	private String parteIdLblIdAsesoresInt="lblIdAi_";
	private String parteIdLblNombresAsesoresInt="lblNombresAi_";
	private String parteIdLblApellidosAsesoresInt="lblApellidosAi_";
	private String parteIdLblTidParticipantesInt="lblTidPi_";
	private String parteIdLblIdParticipantesInt="lblIdPi_";
	private String parteIdLblNombresParticipantesInt="lblNombresPi_";
	private String parteIdLblApellidosParticipantesInt="lblApellidosPi_";
	private String parteIdBtnEliminarAsesores="btnEliminarA_";
	private String parteIdBtnEliminarParticipantes="btnEliminarP_";
	
	private final int ESTADO_PROYECTO_ABIERTO=1;
	private final String ESTADO_OBJ_ESP_ABIERTO="Abierto";
	Div div = null;
	
		
	public void setTiposDeProyectoNgc(TiposDeProyectoNgcInt tiposDeProyectoNgc) {
		this.tiposDeProyectoNgc = tiposDeProyectoNgc;
	}	

	public void setModalidadesNgc(ModalidadesDelProyectoNgcInt modalidadesNgc) {
		this.modalidadesNgc = modalidadesNgc;
	}
	
	public void setTipoIdentificacionNgc(TipoIdentificacionNgcInt tipoIdentificacionNgc) {
		this.tipoIdentificacionNgc = tipoIdentificacionNgc;
	}
	
	public void setPersonaNgc(PersonaNgcInt personaNgc) {
		this.personaNgc = personaNgc;
	}	

	public void setProyectoNgc(ProyectoNgcInt proyectoNgc) {
		this.proyectoNgc = proyectoNgc;
	}

	/**
	 * Metodo ejecutado cuando se carga la vista crearProyecto.zul
	 * en el navegador.
	 */
	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);   
        if( (Sessions.getCurrent().hasAttribute("usuario")) ){
			usuarioDto = (UsuarioDto)(Sessions.getCurrent().getAttribute("usuario"));
		}else{
			Executions.sendRedirect("index.zul");	
		}
        if(Sessions.getCurrent().getAttribute("idProyecto")!= null){
        	cargarNombreResponsableDelProyecto();
        	cargarCbxTiposDeProyecto();
	        cargarCbxModalidades();
        	tbxNombreProyecto.setText((String)Sessions.getCurrent().getAttribute("nombreDelProyecto"));
        	tbxObjetivoGeneral.setText((String)Sessions.getCurrent().getAttribute("objetivoGeneral"));
        	//dateFechaInicial.setText(Sessions.getCurrent().getAttribute("fechaInicial").toString());
        	//dateFechaFinal.setText(Sessions.getCurrent().getAttribute("fechaFinal").toString());
        	if( (Sessions.getCurrent().getAttribute("listadoAsesores"))!=null ){
        		ArrayList<UsuarioDto> asesores =(ArrayList<UsuarioDto> ) Sessions.getCurrent().getAttribute("listadoAsesores");
        		for(UsuarioDto asesor: asesores){
        			gridAsesores.getRows().appendChild(construirFilaIntegranteAsesor(asesor));
        		}
        	}
        	if( (Sessions.getCurrent().getAttribute("listadoParticipantes"))!=null ){
        		ArrayList<UsuarioDto> participantes =(ArrayList<UsuarioDto> ) Sessions.getCurrent().getAttribute("listadoParticipantes");
        		for(UsuarioDto participante: participantes){
        			gridParticipantes.getRows().appendChild(construirFilaIntegranteParticipante(participante));
        		}
        	}
        	if( (Sessions.getCurrent().getAttribute("objetivosEspecificos"))!=null ){
    			ArrayList<ObjetivoEspecificoDto> listaObejtivoEspecifico = (ArrayList<ObjetivoEspecificoDto>) Sessions.getCurrent().getAttribute("objetivosEspecificos");
    			for(ObjetivoEspecificoDto objEspecificos: listaObejtivoEspecifico){
    				anexarNuevaFilaObjetivoEsp();
    			}	
    			Sessions.getCurrent().setAttribute("idProyecto2", null);
    		}	
        	
        }else{
	        cargarNombreResponsableDelProyecto();
	        cargarCbxTiposDeProyecto();
	        cargarCbxModalidades();
	        anexarNuevaFilaObjetivoEsp();
	        cargarTiposDeIDsEnAsesores();
	        cargarTiposDeIDsEnParticipantes();
        }
    }
	
	/**
	 * Metodo que carga los tipos de identificacion en la zona de busqueda de
	 * los participantes.
	 */
	public void cargarTiposDeIDsEnParticipantes(){
		ArrayList<TipoDocumentoDto> lista = tipoIdentificacionNgc.buscarTiposId();
		if (lista != null) {
			for(TipoDocumentoDto td: lista){
				Comboitem comboItem = llenarComboboxTipoIdentificacion(td,parteIdCIParticipantes);
				cbxTipoIDParticipantes.appendChild(comboItem);
			}
		}
	}
	
	/**
	 * Metodo que carga los tipos de identificacion en la zona de busqueda de
	 * los asesores.
	 */
	public void cargarTiposDeIDsEnAsesores(){
		ArrayList<TipoDocumentoDto> lista = tipoIdentificacionNgc.buscarTiposId();
		if (lista != null) {
			for(TipoDocumentoDto td: lista){
				Comboitem comboItem = llenarComboboxTipoIdentificacion(td, parteIdCIAsesores);
				cbxTipoIDAsesores.appendChild(comboItem);
			}
		}
	}
	
	/**
	 * Metodo que genera un comboItem con la informacion de una tipo de 
	 * identificacion.
	 * @param td es un objeto del tipo TipoDocumentoDto
	 * @return el comboItem con la informacion de la tipo de documento.
	 */
	public Comboitem llenarComboboxTipoIdentificacion(TipoDocumentoDto td, String parteNombreId) {
		final Comboitem comboI= new Comboitem();
		comboI.setLabel(td.getNombre());
		comboI.setId(parteNombreId+td.getIdn());		
		comboI.setSclass("sidebar-fn");
		return comboI;
	}
	
	/**
	 * Metodo que carga en la vista de crearProyecto el comboBox para las 
	 * modalidades.
	 */
	public void cargarCbxModalidades(){
		ArrayList<ModalidadesDelProyectoDto> lista = modalidadesNgc.buscarModalidades();
		if (lista != null) {
			for(ModalidadesDelProyectoDto modalidad: lista){
				Comboitem comboItem = crearComboItemModalidadDelProyecto(modalidad);
				cbxModalidad.appendChild(comboItem);
			}
		}
	}
	
	/**
	 * Metodo que genera un comboItem con la informacion de una modalidad del
	 * proyecto.
	 * @param modalidad es un objeto del tipo ModalidadesDelProyectoDto
	 * @return el comboItem con la informacion de la modalidad.
	 */
	public Comboitem crearComboItemModalidadDelProyecto(ModalidadesDelProyectoDto modalidad) {
		final Comboitem comboI= new Comboitem();
		comboI.setLabel(modalidad.getNombre());
		comboI.setId(parteIdCIModalidades+modalidad.getIdn());		
		comboI.setSclass("sidebar-fn");
		return comboI;
	}
	
	/**
	 * Metodo que carga en la vista de crearProyecto el comboBox para los 
	 * tipos de proyecto.
	 */
	public void cargarCbxTiposDeProyecto(){
		ArrayList<TiposDeProyectoDto> lista = tiposDeProyectoNgc.buscarTiposDeProyecto();
		if (lista != null) {
			for(TiposDeProyectoDto tp: lista){
				Comboitem comboItem = crearComboItemTiposDeProyecto(tp);
				cbxTipoProyecto.appendChild(comboItem);
			}
		}
	}
	
	/**
	 * Metodo que genera un comboItem con la informacion de un tipo de proyecto.
	 * @param tp es un objeto del tipo TiposDeProyectoDto
	 * @return el comboItem con la informacion del tipo de proyecto.
	 */
	public Comboitem crearComboItemTiposDeProyecto(TiposDeProyectoDto tp) {
		final Comboitem comboI= new Comboitem();
		comboI.setLabel(tp.getNombre());
		comboI.setId(parteIdCITipoProyecto+tp.getIdn());		
		comboI.setSclass("sidebar-fn");
		return comboI;
	}
	
	/**
	 * Metodo que carga en la vista de crearProyecto el nombre del reponsable
	 * del proyecto.
	 */
	public void cargarNombreResponsableDelProyecto(){
		if(Sessions.getCurrent().hasAttribute("usuario")){
			lblNombreResponsable.setValue(((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getNombres()+" "
			+((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getApellidos());
			Sessions.getCurrent().setAttribute("responsable",lblNombreResponsable.toString());	
		}
	}
	
	/**
	 * Metodo que construye una fila para la grid de objetivos especificos
	 * en la vista crearProyecto.zul
	 * @return: La fila construida.
	 */
	public Row construirFilaObjetivoEspecifico() {	
		final Row row = new Row();
		/* Cada fila tendra un atributo objetivoEspCargado, este atributo sera iniciado
		* en activo que indicara que esta fila todavia no ha anexado una nueva fila objetivo
		* especifico(fila sin datos), cuando el atributo objetivoEspCargado es desactivado es por que esta fila 
		* ya cargo una nueva fila objetivo especifico(fila con datos), cuando el atributo objetivoEspCargado es
		* desactivadoUltimaFila es por que la ultima fila borrada no tenia datos
		* con esto se controla que no saturen el formulario con filas de objetivos especificos vacias.
		* objetivoEspCargado = activo | desactivado | desactivadoUltimaFila
		*/
		row.setAttribute("objetivoEspCargado","activo");
		row.setId(idContadorRows+"");
		
		
		if(Sessions.getCurrent().getAttribute("idProyecto2")!= null){
			ArrayList<ObjetivoEspecificoDto> listaObejtivoEspecifico = (ArrayList<ObjetivoEspecificoDto>) Sessions.getCurrent().getAttribute("objetivosEspecificos");
			
			tbxObjetivoEspecifico = new Textbox();
			tbxObjetivoEspecifico.setId("tbxObjEsp_"+idContadorRows);
			tbxObjetivoEspecifico.setStyle("border:1px solid black; color: #000000!important; font-size: 13px");
			tbxObjetivoEspecifico.setRows(filasObjEspecifico);
			tbxObjetivoEspecifico.setWidth(anchoObjEspecifico);
			tbxObjetivoEspecifico.setText((String)listaObejtivoEspecifico.get(idContadorRows -1).getDescripcion());
			tbxObjetivoEspecifico.setVisible(true);		
			row.appendChild(tbxObjetivoEspecifico);
			
			tbxPorcentaje = new Textbox();
			tbxPorcentaje.setId("tbxPor_"+idContadorRows);
			tbxPorcentaje.setStyle("border:1px solid black; color: #000000!important; font-size: 13px");
			tbxPorcentaje.setWidth(anchoPorcentajeObjEsp);
			tbxPorcentaje.setText(listaObejtivoEspecifico.get(idContadorRows - 1 ).getPorcentaje().toString());
			tbxPorcentaje.setVisible(true);		
			row.appendChild(tbxPorcentaje);	
		}else{
			/*
			 * Los ids de los textboxs de los objetivos especificos de cada fila
			 * tendran este formato: 
			 * tbxObjEsp_idContadorRows
			 * donde idContadorRows es la variable idContadorRows que tendra el 
			 * valor que tenga en el momento.
			 */
			tbxObjetivoEspecifico = new Textbox();
			tbxObjetivoEspecifico.setId("tbxObjEsp_"+idContadorRows);
			tbxObjetivoEspecifico.setStyle("border:1px solid black; color: #000000!important; font-size: 13px");
			tbxObjetivoEspecifico.setRows(filasObjEspecifico);
			tbxObjetivoEspecifico.setWidth(anchoObjEspecifico);
			tbxObjetivoEspecifico.setPlaceholder(Labels.getLabel("placeholderObjEspecifico"));
			tbxObjetivoEspecifico.setVisible(true);		
			row.appendChild(tbxObjetivoEspecifico);
			/*
			 * Los ids de los textboxs del porcentaje de cada fila
			 * tendran este formato: 
			 * tbxPor_idContadorRows
			 * donde idContadorRows es la variable idContadorRows que tendra el 
			 * valor que tenga en el momento.
			 */
			tbxPorcentaje = new Textbox();
			tbxPorcentaje.setId("tbxPor_"+idContadorRows);
			tbxPorcentaje.setStyle("border:1px solid black; color: #000000!important; font-size: 13px");
			tbxPorcentaje.setWidth(anchoPorcentajeObjEsp);
			tbxPorcentaje.setPlaceholder(Labels.getLabel("placeholderPorcentajeObjEsp"));
			tbxPorcentaje.setVisible(true);		
			row.appendChild(tbxPorcentaje);	
		}
		
		
		
		EventListener<Event> actionListenerAnexar = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
				Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
				if( (filaDelClick != null) && (filaDelClick.hasAttribute("objetivoEspCargado") ) ){
					String objetivoEspCargado=((String) (filaDelClick.getAttribute("objetivoEspCargado")) );
					if(objetivoEspCargado.equals("activo")){
						String numeroId=filaDelClick.getId();
						Textbox tbDescripcionObjEsp=((Textbox) filaDelClick.getFellow(parteIdTbxObjEsp+numeroId));
						Textbox tbPorcentajeObjEsp=((Textbox) filaDelClick.getFellow(parteIdTbxPorcentaje+numeroId));
						String descripcion = tbDescripcionObjEsp.getValue();
						String porcentaje=tbPorcentajeObjEsp.getValue();
						if( revisarDatosObjetivoEsp(descripcion, porcentaje) ){
							int porcentajeInt= Integer.parseInt(porcentaje);
							if( (porcentajeTotalObjEspecificos+porcentajeInt)<=100 ){						
								anexarNuevaFilaObjetivoEsp();
								ObjetivoEspecificoDto objetivoEspecificoDto = new ObjetivoEspecificoDto();
								objetivoEspecificoDto.setDescripcion(descripcion);
								objetivoEspecificoDto.setPorcentaje(porcentajeInt);
								row.setAttribute("objetivoEspecificoDto", objetivoEspecificoDto);
								row.setAttribute("objetivoEspCargado","desactivado");
								tbDescripcionObjEsp.setDisabled(true);
								tbPorcentajeObjEsp.setDisabled(true);
								porcentajeTotalObjEspecificos+=porcentajeInt;							
							}else{
								Messagebox.show(Labels.getLabel("mensajePorcentajeSuperior"));
							}
						}else{
							Messagebox.show(Labels.getLabel("mensajeDatosErroneos"));
						}
					}else if(objetivoEspCargado.equals("desactivadoUltimaFila")){
						filaDelClick.setAttribute("objetivoEspCargado","desactivado");
						anexarNuevaFilaObjetivoEsp();
					}
				}
			}							
		};
		/*
		 * Los ids de los botones de anexar objetivo especifico de cada fila
		 * tendran este formato: 
		 * btnAnexarObjEsp_idContadorRows
		 * donde idContadorRows es la variable idContadorRows que tendra el 
		 * valor que tenga en el momento.
		 */
		Button btnAnexarObjetivoEsp=new Button();
		btnAnexarObjetivoEsp.setId("btnAnexarObjEsp_"+idContadorRows);
		btnAnexarObjetivoEsp.addEventListener(Events.ON_CLICK, actionListenerAnexar);	
		btnAnexarObjetivoEsp.setTooltip("mAnexar");
		btnAnexarObjetivoEsp.setLabel(Labels.getLabel("btnAnexar"));
		btnAnexarObjetivoEsp.setVisible(true);
		row.appendChild(btnAnexarObjetivoEsp);
		
		EventListener<Event> actionListenerBorrar = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
				if ( rowsEnGridObjEspecificos > 1 ){
					final Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
					EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
			            public void onEvent(ClickEvent event) throws Exception {
			                if(Messagebox.Button.YES.equals(event.getButton())) {
			                	if( (filaDelClick != null) && (filaDelClick.hasAttribute("objetivoEspCargado") ) ){
			                		String objetivoEspCargado=((String) (filaDelClick.getAttribute("objetivoEspCargado")) );
				                	if( objetivoEspCargado.equals("desactivado") ){			        				
					                	int porcentaje=Integer.parseInt( ((Textbox) filaDelClick.getFellow(parteIdTbxPorcentaje+filaDelClick.getId())).getValue());
					                	porcentajeTotalObjEspecificos-=porcentaje;
					                	rowsEnGridObjEspecificos--;
					                	filaDelClick.setParent(null);
					                }else if( objetivoEspCargado.equals("desactivadoUltimaFila") ){
				                		int porcentaje=Integer.parseInt( ((Textbox) filaDelClick.getFellow(parteIdTbxPorcentaje+filaDelClick.getId())).getValue());
					                	porcentajeTotalObjEspecificos-=porcentaje;
					                	rowsEnGridObjEspecificos--;
					                	filaDelClick.setParent(null);
					                	((Row) (gridObjetivosEspecificos.getRows().getLastChild())).setAttribute("objetivoEspCargado","desactivadoUltimaFila");				                	
				                	}else{
				                		rowsEnGridObjEspecificos--;
					                	filaDelClick.setParent(null);
					                	((Row) (gridObjetivosEspecificos.getRows().getLastChild())).setAttribute("objetivoEspCargado","desactivadoUltimaFila");			                	
				                	}
			                	}
			                }
			            }
			        };
			        org.zkoss.zul.Messagebox.show(Labels.getLabel("mensajeBorrarObjEsp"),Labels.getLabel("tituloMensajeSeguridad"), new Messagebox.Button[]{
			                Messagebox.Button.NO, Messagebox.Button.YES }, org.zkoss.zul.Messagebox.QUESTION, clickListener);	
				}
			}							
		};
		/*
		 * Los ids de los botones de borrar objetivo especifico de cada fila
		 * tendran este formato: 
		 * btnBorrarObjEsp_idContadorRows
		 * donde idContadorRows es la variable idContadorRows que tendra el 
		 * valor que tenga en el momento.
		 */
		Button btnBorrarObjetivoEsp=new Button();
		btnBorrarObjetivoEsp.setId("btnBorrarObjEsp_"+idContadorRows);
		btnBorrarObjetivoEsp.addEventListener(Events.ON_CLICK, actionListenerBorrar);	
		btnBorrarObjetivoEsp.setTooltip("mBorrar");
		btnBorrarObjetivoEsp.setLabel(Labels.getLabel("btnBorrar"));
		btnBorrarObjetivoEsp.setVisible(true);
		row.appendChild(btnBorrarObjetivoEsp);		
		row.setAlign("center");
		row.setSclass("sidebar-fn");			
		row.setVflex("min");
		idContadorRows++;
		rowsEnGridObjEspecificos++;
		return row;
	}
	
	/**
	 * Metodo que revisa que los datos de los objetivos especificos esten bien.
	 * @param objetivoEsp: Objetivo especifico ingresado en el formulario.
	 * @param porcentaje: Porcentaje del especifico ingresado en el formulario.
	 * @return El metodo retorna verdadero si los datos estan bien, de lo contrario 
	 *  retorna falso.
	 */
	public boolean revisarDatosObjetivoEsp(String objetivoEsp, String porcentaje){
		if( (!(objetivoEsp.trim().isEmpty() ) ) && (porcentaje.trim().matches("[1-9]{1}[0-9]*")) ){
			return true;			
		}
		return false;
	}
	
	/**
	 * Metodo que anexa una nueva fila objetivo especifico al formulario existente.
	 */
	public void anexarNuevaFilaObjetivoEsp(){
		 Rows rows = gridObjetivosEspecificos.getRows(); 	
	     rows.appendChild(construirFilaObjetivoEspecifico());  
	}
	
	public void onClick$buscarAsesores(Event ev) {
		limpiarRows(gridBusquedaAsesores);
		Comboitem comboItem=cbxTipoIDAsesores.getSelectedItem();
		String tipoId=null;
		if( comboItem!=null ){
			tipoId= comboItem.getId();
			if( (tipoId!=null) && (tipoId.length()>=6) ){ 
				tipoId=tipoId.substring(6);
			}
		}		
		String id=tbxIDAsesores.getText();
		String nombres=tbxNombresAsesores.getText();
		String apellidos=tbxApellidosAsesores.getText();
		ArrayList<UsuarioDto> listaUsuarios=buscarUsuariosPorFiltros(tipoId, id, nombres, apellidos);
		if( (listaUsuarios!=null) && !(listaUsuarios.isEmpty()) ){
			 Rows rows = gridBusquedaAsesores.getRows(); 
			 for(UsuarioDto usuario: listaUsuarios){
				 if( usuario.getIdn().compareTo(usuarioDto.getIdn()) != 0 ){
					 rows.appendChild(construirFilaAnexarUsuariosAsesores(usuario) ); 
				 }
			 }		       
		}
	}
	
	public void onClick$limpiarAsesores(Event ev) {	
		limpiarRows(gridBusquedaAsesores);
	}
	
	public void onClick$buscarParticipantes(Event ev) {	
		limpiarRows(gridBusquedaParticipantes);
		Comboitem comboItem=cbxTipoIDParticipantes.getSelectedItem();
		String tipoId=null;
		if( comboItem!=null ){
			tipoId= comboItem.getId();
			if( (tipoId!=null) && (tipoId.length()>=6) ){ 
				tipoId=tipoId.substring(6);
			}
		}
		String id=tbxIDParticipantes.getText();
		String nombres=tbxNombresParticipantes.getText();
		String apellidos=tbxApellidosParticipantes.getText();
		ArrayList<UsuarioDto> listaUsuarios= buscarUsuariosPorFiltros(tipoId, id, nombres, apellidos);
		if( (listaUsuarios!=null) && !(listaUsuarios.isEmpty()) ){
			 Rows rows = gridBusquedaParticipantes.getRows(); 
			 for(UsuarioDto usuario: listaUsuarios){
				 if( usuario.getIdn().compareTo(usuarioDto.getIdn()) != 0 ){
					 rows.appendChild(construirFilaAnexarUsuariosParticipantes(usuario) );
				 }
			 }		       
		}		
	}
	
	public void onClick$limpiarParticipantes(Event ev) {	
		limpiarRows(gridBusquedaParticipantes);
	}
	
	public Row construirFilaAnexarUsuariosAsesores(UsuarioDto usuario){	
		final Row row = new Row();
		row.setId(parteIdRowBusquedaAsesores+usuario.getIdn());
		Label lblTid= new Label();
		lblTid.setId(parteIdLblTidAsesores+usuario.getIdn());
		lblTid.setStyle("color: #000000!important; font-size: 13px");
		lblTid.setValue(usuario.getTipoDocumento().getNombre());
		row.appendChild(lblTid);
		Label lblId= new Label();
		lblId.setId(parteIdLblIdAsesores+usuario.getIdn());
		lblId.setStyle("color: #000000!important; font-size: 13px");
		lblId.setValue(usuario.getIdentificacion().toString());
		row.appendChild(lblId);
		Label lblNombres= new Label();
		lblNombres.setId(parteIdLblNombresAsesores+usuario.getIdn());
		lblNombres.setStyle("color: #000000!important; font-size: 13px");
		lblNombres.setValue(usuario.getNombres());
		row.appendChild(lblNombres);
		Label lblApellidos= new Label();
		lblApellidos.setId(parteIdLblApellidosAsesores+usuario.getIdn());
		lblApellidos.setStyle("color: #000000!important; font-size: 13px");
		lblApellidos.setValue(usuario.getApellidos());
		row.appendChild(lblApellidos);		
		
		EventListener<Event> actionListenerAnexar = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
				Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
				if( (filaDelClick != null) && (filaDelClick.hasAttribute("usuario")) ){
					UsuarioDto integrante=(UsuarioDto)(filaDelClick.getAttribute("usuario"));
					if( (gridAsesores.hasFellow(parteIdRowAsesoresInt+integrante.getIdn()))
							|| (gridParticipantes.hasFellow(parteIdRowParticipantesInt+integrante.getIdn())) ){	
						Messagebox.show(Labels.getLabel("mensajeYaEstaElIntegrante"), Labels.getLabel("tituloYaEstaElIntegrante"), Messagebox.OK, Messagebox.INFORMATION);
					}else{
						gridAsesores.getRows().appendChild(construirFilaIntegranteAsesor(integrante));
						filaDelClick.setParent(null);					
					}
				}
			}							
		};
		Button btnAnexarAsesores=new Button();
		btnAnexarAsesores.setId(parteIdBtnAnexarAsesores+usuario.getIdn());
		btnAnexarAsesores.addEventListener(Events.ON_CLICK, actionListenerAnexar);	
		btnAnexarAsesores.setLabel(Labels.getLabel("btnAnexar"));
		btnAnexarAsesores.setVisible(true);
		row.appendChild(btnAnexarAsesores);		
		row.setAlign("center");
		row.setSclass("sidebar-fn");			
		row.setVflex("min");
		row.setAttribute("usuario", usuario);
		return row;
	}
	
	public Row construirFilaIntegranteAsesor(UsuarioDto usuario){	
		final Row row = new Row();
		row.setId(parteIdRowAsesoresInt+usuario.getIdn());
		Label lblTid= new Label();
		lblTid.setId(parteIdLblTidAsesoresInt+usuario.getIdn());
		lblTid.setStyle("color: #000000!important; font-size: 13px");
		lblTid.setValue(usuario.getTipoDocumento().getNombre());
		row.appendChild(lblTid);
		Label lblId= new Label();
		lblId.setId(parteIdLblIdAsesoresInt+usuario.getIdn());
		lblId.setStyle("color: #000000!important; font-size: 13px");
		lblId.setValue(usuario.getIdentificacion().toString());
		row.appendChild(lblId);
		Label lblNombres= new Label();
		lblNombres.setId(parteIdLblNombresAsesoresInt+usuario.getIdn());
		lblNombres.setStyle("color: #000000!important; font-size: 13px");
		lblNombres.setValue(usuario.getNombres());
		row.appendChild(lblNombres);
		Label lblApellidos= new Label();
		lblApellidos.setId(parteIdLblApellidosAsesoresInt+usuario.getIdn());
		lblApellidos.setStyle("color: #000000!important; font-size: 13px");
		lblApellidos.setValue(usuario.getApellidos());
		row.appendChild(lblApellidos);		
		
		EventListener<Event> actionListenerEliminar = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
				Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
				if( (filaDelClick != null) ){
					filaDelClick.setParent(null);
				}
			}							
		};
		Button btnEliminarAsesores=new Button();
		btnEliminarAsesores.setId(parteIdBtnEliminarAsesores+usuario.getIdn());
		btnEliminarAsesores.addEventListener(Events.ON_CLICK, actionListenerEliminar);	
		btnEliminarAsesores.setLabel(Labels.getLabel("btnEliminarIntegrante"));
		btnEliminarAsesores.setVisible(true);
		row.appendChild(btnEliminarAsesores);		
		row.setAlign("center");
		row.setSclass("sidebar-fn");			
		row.setVflex("min");
		row.setAttribute("usuario", usuario);
		return row;
	}
	
	public Row construirFilaAnexarUsuariosParticipantes(UsuarioDto usuario){	
		final Row row = new Row();
		row.setId(parteIdRowBusquedaParticipantes+usuario.getIdn());
		Label lblTid= new Label();
		lblTid.setId(parteIdLblTidParticipantes+usuario.getIdn());
		lblTid.setStyle("color: #000000!important; font-size: 13px");
		lblTid.setValue(usuario.getTipoDocumento().getNombre());
		row.appendChild(lblTid);
		Label lblId= new Label();
		lblId.setId(parteIdLblIdParticipantes+usuario.getIdn());
		lblId.setStyle("color: #000000!important; font-size: 13px");
		lblId.setValue(usuario.getIdentificacion().toString());
		row.appendChild(lblId);
		Label lblNombres= new Label();
		lblNombres.setId(parteIdLblNombresParticipantes+usuario.getIdn());
		lblNombres.setStyle("color: #000000!important; font-size: 13px");
		lblNombres.setValue(usuario.getNombres());
		row.appendChild(lblNombres);
		Label lblApellidos= new Label();
		lblApellidos.setId(parteIdLblApellidosParticipantes+usuario.getIdn());
		lblApellidos.setStyle("color: #000000!important; font-size: 13px");
		lblApellidos.setValue(usuario.getApellidos());
		row.appendChild(lblApellidos);		
		
		EventListener<Event> actionListenerAnexar = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
				Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
				if( (filaDelClick != null) && (filaDelClick.hasAttribute("usuario")) ){
					UsuarioDto integrante=(UsuarioDto)(filaDelClick.getAttribute("usuario"));
					if( (gridAsesores.hasFellow(parteIdRowAsesoresInt+integrante.getIdn()))
							|| (gridParticipantes.hasFellow(parteIdRowParticipantesInt+integrante.getIdn())) ){	
						Messagebox.show(Labels.getLabel("mensajeYaEstaElIntegrante"), Labels.getLabel("tituloYaEstaElIntegrante"), Messagebox.OK, Messagebox.INFORMATION);
					}else{
						gridParticipantes.getRows().appendChild(construirFilaIntegranteParticipante(integrante));
						filaDelClick.setParent(null);					
					}
				}
			}							
		};
		Button btnAnexarParticipantes=new Button();
		btnAnexarParticipantes.setId(parteIdBtnAnexarParticipantes+usuario.getIdn());
		btnAnexarParticipantes.addEventListener(Events.ON_CLICK, actionListenerAnexar);	
		btnAnexarParticipantes.setLabel(Labels.getLabel("btnAnexar"));
		btnAnexarParticipantes.setVisible(true);
		row.appendChild(btnAnexarParticipantes);		
		row.setAlign("center");
		row.setSclass("sidebar-fn");			
		row.setVflex("min");
		row.setAttribute("usuario", usuario);
		return row;
	}
	
	public Row construirFilaIntegranteParticipante(UsuarioDto usuario){	
		final Row row = new Row();
		row.setId(parteIdRowParticipantesInt+usuario.getIdn());
		Label lblTid= new Label();
		lblTid.setId(parteIdLblTidParticipantesInt+usuario.getIdn());
		lblTid.setStyle("color: #000000!important; font-size: 13px");
		lblTid.setValue(usuario.getTipoDocumento().getNombre());
		row.appendChild(lblTid);
		Label lblId= new Label();
		lblId.setId(parteIdLblIdParticipantesInt+usuario.getIdn());
		lblId.setStyle("color: #000000!important; font-size: 13px");
		lblId.setValue(usuario.getIdentificacion().toString());
		row.appendChild(lblId);
		Label lblNombres= new Label();
		lblNombres.setId(parteIdLblNombresParticipantesInt+usuario.getIdn());
		lblNombres.setStyle("color: #000000!important; font-size: 13px");
		lblNombres.setValue(usuario.getNombres());
		row.appendChild(lblNombres);
		Label lblApellidos= new Label();
		lblApellidos.setId(parteIdLblApellidosParticipantesInt+usuario.getIdn());
		lblApellidos.setStyle("color: #000000!important; font-size: 13px");
		lblApellidos.setValue(usuario.getApellidos());
		row.appendChild(lblApellidos);		
		
		EventListener<Event> actionListenerEliminar = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
				Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
				if( (filaDelClick != null) ){
					filaDelClick.setParent(null);
				}
			}							
		};
		Button btnEliminarParticipantes=new Button();
		btnEliminarParticipantes.setId(parteIdBtnEliminarParticipantes+usuario.getIdn());
		btnEliminarParticipantes.addEventListener(Events.ON_CLICK, actionListenerEliminar);	
		btnEliminarParticipantes.setLabel(Labels.getLabel("btnEliminarIntegrante"));
		btnEliminarParticipantes.setVisible(true);
		row.appendChild(btnEliminarParticipantes);		
		row.setAlign("center");
		row.setSclass("sidebar-fn");			
		row.setVflex("min");
		row.setAttribute("usuario", usuario);
		return row;
	}
	
	public ArrayList<UsuarioDto> buscarUsuariosPorFiltros(String tipoId, String id,String nombres, String apellidos){
		if( (tipoId!=null) && (tipoId.matches("[0-9]+")) && (id!=null) && (id.matches("[0-9]+")) && (nombres!=null) 
				&& !(nombres.trim().isEmpty()) && (apellidos!=null) && !(apellidos.trim().isEmpty()) ){
				return personaNgc.buscarUsuariosPorTidIdNombresYApellidos(Integer.parseInt(tipoId), Integer.parseInt(id), nombres, apellidos);			
		}else if( (tipoId!=null) && (tipoId.matches("[0-9]+")) && (id!=null) && (id.matches("[0-9]+")) && (nombres!=null) && !(nombres.trim().isEmpty()) ){
				return personaNgc.buscarUsuariosPorTidIdYNombres(Integer.parseInt(tipoId), Integer.parseInt(id), nombres);			
		}else if( (tipoId!=null) && (tipoId.matches("[0-9]+")) && (id!=null) && (id.matches("[0-9]+")) 
				&& (apellidos!=null) && !(apellidos.trim().isEmpty()) ){
			return personaNgc.buscarUsuariosPorTidIdYApellidos(Integer.parseInt(tipoId), Integer.parseInt(id), apellidos);
		}else if( (id!=null) && (id.matches("[0-9]+")) && (nombres!=null) && !(nombres.trim().isEmpty()) 
				&& (apellidos!=null) && !(apellidos.trim().isEmpty()) ){
			return personaNgc.buscarUsuariosPorIdNombresYApellidos( Integer.parseInt(id), nombres, apellidos);
		}else if( (tipoId!=null) && (tipoId.matches("[0-9]+")) && (id!=null) && (id.matches("[0-9]+")) ){
			return personaNgc.buscarUsuariosPorTidYId(Integer.parseInt(tipoId), Integer.parseInt(id));
		}else if( (id!=null) && (id.matches("[0-9]+")) && (nombres!=null) && !(nombres.trim().isEmpty()) ){
			return personaNgc.buscarUsuariosPorIdYNombres( Integer.parseInt(id), nombres);
		}else if( (id!=null) && (id.matches("[0-9]+")) && (apellidos!=null) && !(apellidos.trim().isEmpty()) ){
			return personaNgc.buscarUsuariosPorIdYApellidos( Integer.parseInt(id), apellidos);
		}else if( (nombres!=null) && !(nombres.trim().isEmpty()) && (apellidos!=null) && !(apellidos.trim().isEmpty()) ){
			return personaNgc.buscarUsuariosPorNombresYApellidos(nombres, apellidos);
		}else if( (nombres!=null) && !(nombres.trim().isEmpty()) ){
			return personaNgc.buscarUsuariosPorNombres(nombres);
		}else if( (apellidos!=null) && !(apellidos.trim().isEmpty()) ){
			return personaNgc.buscarUsuariosPorApellidos(apellidos);
		}else if( (id!=null) && (id.matches("[0-9]+")) ){
			return personaNgc.buscarUsuariosPorId( Integer.parseInt(id));
		}else{
			return new ArrayList<UsuarioDto>();
		}		
	}	

	/**
	 * Metodo que recibe el evento del click en el boton Guardar, cuando este evento
	 * sucede guarda el proyecto en la base de datos si todos los datos cumplen con los
	 * parametros del negocio, de lo contrario se muestra un mensaje indicando por que no
	 * se guardo el proyecto. 
	 * @param ev
	 */
	public void onClick$btnGuardar(Event ev) {			
		if (verificarDatosFormularioCrearProyecto()) {
			proyectoDto = proyectoNgc.almacenarProyecto(proyectoDto, listadoAsesores, listadoParticipantes, listadoObjetivosEspecificos);
			if( (proyectoDto.getIdn()!=null) && (proyectoDto.getIdn()!=0) ){
				EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
		            public void onEvent(ClickEvent event) throws Exception {
		            	activarEventoQueueCargarVentanaMenu();
		            }
		        };
		        Messagebox.show(Labels.getLabel("mensajeProyectoCreado")+" "+proyectoDto.getIdn(),Labels.getLabel("tituloMensajeProyectoCreado"), new Messagebox.Button[]{
		               Messagebox.Button.OK }, org.zkoss.zul.Messagebox.QUESTION, clickListener);							
			}
		}
	}
	
	public boolean verificarDatosFormularioCrearProyecto(){
		if( proyectoDto != null ){
			proyectoDto= null;
		}
		proyectoDto = new ProyectoDto();
		if( (tbxNombreProyecto != null) ){
			String nombreDelProyecto= tbxNombreProyecto.getText().trim();
			if( ( !(nombreDelProyecto.isEmpty()) ) ){
				proyectoDto.setNombreDelProyecto(nombreDelProyecto);
				Sessions.getCurrent().setAttribute("nombreDelProyecto", nombreDelProyecto);
			}else{
				Messagebox.show(Labels.getLabel("mensajeFaltaNombreDelProyecto"), Labels.getLabel("mensajeAlertaCrearProyecto"), Messagebox.OK, Messagebox.INFORMATION);
				return false;			
			}			
		}else{	
			Messagebox.show(Labels.getLabel("mensajeFaltaNombreDelProyecto"), Labels.getLabel("mensajeAlertaCrearProyecto"), Messagebox.OK, Messagebox.INFORMATION);
			return false;
		}
		if( (cbxTipoProyecto.getSelectedItem() !=null) && (cbxTipoProyecto.getSelectedItem().getId().length() > 5 ) ){
			Integer idnTipoProyecto = Integer.parseInt(cbxTipoProyecto.getSelectedItem().getId().substring(5));
			TiposDeProyectoDto tipoDeProyecto = new TiposDeProyectoDto();
			tipoDeProyecto.setIdn(idnTipoProyecto);
			proyectoDto.setTipoDeProyecto(tipoDeProyecto);		
			Sessions.getCurrent().setAttribute("tipoDeProyecto", tipoDeProyecto.getNombre());
		}else{	
			Messagebox.show(Labels.getLabel("mensajeFaltaTipoDeProyecto"), Labels.getLabel("mensajeAlertaCrearProyecto"), Messagebox.OK, Messagebox.INFORMATION);
			return false;
		}
		if( (cbxModalidad.getSelectedItem() !=null) && (cbxModalidad.getSelectedItem().getId().length() > 4 ) ){			
			Integer idnModalidad = Integer.parseInt(cbxModalidad.getSelectedItem().getId().substring(4));
			ModalidadesDelProyectoDto modalidad = new ModalidadesDelProyectoDto();
			modalidad.setIdn(idnModalidad);
			proyectoDto.setModalidad(modalidad);
			Sessions.getCurrent().setAttribute("modalidad", modalidad.getNombre());
			
		}else{	
			Messagebox.show(Labels.getLabel("mensajeFaltaTipoDeModalidad"), Labels.getLabel("mensajeAlertaCrearProyecto"), Messagebox.OK, Messagebox.INFORMATION);
			return false;
		}
		if( (gridAsesores.getRows().getVisibleItemCount() > 0) && (gridParticipantes.getRows().getVisibleItemCount() > 0)){
			cargarListaAsesoresOParticipantes( listadoAsesores, gridAsesores.getRows());
			cargarListaAsesoresOParticipantes( listadoParticipantes, gridParticipantes.getRows());
			Sessions.getCurrent().setAttribute("listadoAsesores", listadoAsesores);
			Sessions.getCurrent().setAttribute("listadoParticipantes", listadoParticipantes);
		}else{
			Messagebox.show(Labels.getLabel("mensajeFaltanAsesoresY_OParticipantes"), Labels.getLabel("mensajeAlertaCrearProyecto"), Messagebox.OK, Messagebox.INFORMATION);
			return false;		
		}
		if( (dateFechaInicial != null) && (dateFechaFinal != null) ){
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-mm-yyyy"); 
			Date fechaInicial = null;
			Date fechaFinal = null;
			try {
				fechaInicial = formatoFecha.parse(dateFechaInicial.getText());
				fechaFinal = formatoFecha.parse(dateFechaFinal.getText());
			} catch (WrongValueException e) {
				e.printStackTrace();
				return false;
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
			if( fechaFinal.after(fechaInicial) ){
				proyectoDto.setFechaInicial(fechaInicial);
				proyectoDto.setFechaFinal(fechaFinal);		
				Sessions.getCurrent().setAttribute("fechaInicial", fechaInicial);
				Sessions.getCurrent().setAttribute("fechaFinal", fechaFinal);
			}else{	
				Messagebox.show(Labels.getLabel("mensajeErrorEnFechas"), Labels.getLabel("mensajeAlertaCrearProyecto"), Messagebox.OK, Messagebox.INFORMATION);
				return false;
			}
		}else{	
			Messagebox.show(Labels.getLabel("mensajeErrorEnFechas"), Labels.getLabel("mensajeAlertaCrearProyecto"), Messagebox.OK, Messagebox.INFORMATION);
			return false;
		}
		if( (tbxObjetivoGeneral != null) ){
			String objetivoGeneral= tbxObjetivoGeneral.getText().trim();
			if( ( !(objetivoGeneral.isEmpty()) ) ){
				proyectoDto.setObjetivoGeneral(objetivoGeneral);
				Sessions.getCurrent().setAttribute("objetivoGeneral", objetivoGeneral);
			}else{
				Messagebox.show(Labels.getLabel("mensajeFaltaObjetivoGeneral"), Labels.getLabel("mensajeAlertaCrearProyecto"), Messagebox.OK, Messagebox.INFORMATION);
				return false;			
			}			
		}else{	
			Messagebox.show(Labels.getLabel("mensajeFaltaObjetivoGeneral"), Labels.getLabel("mensajeAlertaCrearProyecto"), Messagebox.OK, Messagebox.INFORMATION);
			return false;
		}
		if( porcentajeTotalObjEspecificos == 100){
			cargarListaObjetivosEspecificos( listadoObjetivosEspecificos,gridObjetivosEspecificos.getRows(), usuarioDto.getUsuario());
		}else{
			Messagebox.show(Labels.getLabel("mensajeFaltanObjetivosEspecificos"), Labels.getLabel("mensajeAlertaCrearProyecto"), Messagebox.OK, Messagebox.INFORMATION);
			return false;		
		}
		proyectoDto.setFechaCreacion(new Date());
		proyectoDto.setAdtFechaHora(new Date());
		proyectoDto.setAdtUsuario(usuarioDto.getUsuario());
		EstadoDelProyectoDto estadoDelProyectoDto = new EstadoDelProyectoDto();
		estadoDelProyectoDto.setIdn(ESTADO_PROYECTO_ABIERTO);
		proyectoDto.setEstadoDelProyecto(estadoDelProyectoDto);
		proyectoDto.setResponsable(usuarioDto);
		return true;
	}
	
	public void cargarListaObjetivosEspecificos( ArrayList<ObjetivoEspecificoDto> listadoObjetivosEsp, Rows rows, String usuario){
		ObjetivoEspecificoDto objetivoEspecificoDto=null;
		listadoObjetivosEsp.clear();
		List<Row> listaRows=rows.getChildren();		
		for(Row row: listaRows){
			if( (row.hasAttribute("objetivoEspCargado")) && !( ((String)(row.getAttribute("objetivoEspCargado"))).equals("activo") ) 
				&& (row.hasAttribute("objetivoEspecificoDto")) ){
				objetivoEspecificoDto = (ObjetivoEspecificoDto) (row.getAttribute("objetivoEspecificoDto")); 
				objetivoEspecificoDto.setEstado(ESTADO_OBJ_ESP_ABIERTO);
				objetivoEspecificoDto.setAdtFecha(new Date());
				objetivoEspecificoDto.setAdtUsuario(usuario);
				listadoObjetivosEsp.add(objetivoEspecificoDto);
					//Executions.sendRedirect("llamadoAlMenu.zul");
				
			}
		}
		Sessions.getCurrent().setAttribute("objetivosEspecificos",listadoObjetivosEsp);			
	}
	
	public void cargarListaAsesoresOParticipantes(ArrayList<UsuarioDto> integrantes, Rows rows){
		List<Row> listaRows=rows.getChildren();
		integrantes.clear();
		for(Row row: listaRows){
			if( (row.hasAttribute("usuario")) ){
				integrantes.add( (UsuarioDto)(row.getAttribute("usuario")) );
			}
		}		
	}
	
	public void limpiarRows(Grid grid){
		if( grid != null){
			grid.removeChild(grid.getRows());
			grid.appendChild(new Rows());
		}
	}
	
	public void activarEventoQueueCargarVentanaMenu(){
		eventoQueueActivarVentanaMenu = EventQueues.lookup("cargarVentanaMenu", EventQueues.DESKTOP,true);			
		eventoQueueActivarVentanaMenu.publish(new Event("renderizar",null,null));
	}
	
	public void onClick$btnContinuar(Event ev) throws IOException {
		if (verificarDatosFormularioCrearProyecto()) {
			
			/*if( (proyectoDto.getIdn()!=null) && (proyectoDto.getIdn()!=0) ){
				EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
		            public void onEvent(ClickEvent event) throws Exception {
		            	activarEventoQueueCargarVentanaMenu();
		            }
		        };
		        Messagebox.show(Labels.getLabel("mensajeProyectoCreado")+" "+proyectoDto.getIdn(),Labels.getLabel("tituloMensajeProyectoCreado"), new Messagebox.Button[]{
		               Messagebox.Button.OK }, org.zkoss.zul.Messagebox.QUESTION, clickListener);							
			}*/
			if(Sessions.getCurrent().getAttribute("idProyecto")!= null){
				
				if(Sessions.getCurrent().getAttribute("idProyecto")!= proyectoDto.getIdn()){
					proyectoDto = proyectoNgc.almacenarProyecto(proyectoDto, listadoAsesores, listadoParticipantes, listadoObjetivosEspecificos);
					cargarListaObjetivosEspecificos( listadoObjetivosEspecificos,gridObjetivosEspecificos.getRows(), usuarioDto.getUsuario());
					Sessions.getCurrent().setAttribute("idProyecto", proyectoDto.getIdn());
					Sessions.getCurrent().setAttribute("idProyecto2", proyectoDto.getIdn());
					java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/ModuloGestionDeProyectos/vista/continuarCreacionProyecto.zul") ;
					java.io.Reader zulReader = new java.io.InputStreamReader(zulInput);
					Window win = (Window)Executions.createComponentsDirectly(zulReader,"zul",null,null);
					if(div.getFirstChild()!=null){
						div.removeChild(div.getFirstChild());
					}
					div.appendChild(win);
				}else{
					//Almacenar el proyecto modificado no uno nuevo
				}
			}else{
				proyectoDto = proyectoNgc.almacenarProyecto(proyectoDto, listadoAsesores, listadoParticipantes, listadoObjetivosEspecificos);
				cargarListaObjetivosEspecificos( listadoObjetivosEspecificos,gridObjetivosEspecificos.getRows(), usuarioDto.getUsuario());
				Sessions.getCurrent().setAttribute("idProyecto", proyectoDto.getIdn());
				Sessions.getCurrent().setAttribute("idProyecto2", proyectoDto.getIdn());
				java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/ModuloGestionDeProyectos/vista/continuarCreacionProyecto.zul") ;
				java.io.Reader zulReader = new java.io.InputStreamReader(zulInput);
				Window win = (Window)Executions.createComponentsDirectly(zulReader,"zul",null,null);
				if(div.getFirstChild()!=null){
					div.removeChild(div.getFirstChild());
				}
				div.appendChild(win);
			}
		}
		
	}
	
}
