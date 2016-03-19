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
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

/**
 * 
 * @author user
 *
 */
public class VentanaTareasCtl extends GenericForwardComposer{
	
	private UsuarioDto usuarioDto=null;
	
	private Label lblVentanaTareas;
	private Grid gridTareas;
	private String parteIdTbxObjEsp="tbxObjEsp_";
	private String parteIdTbxPorcentaje="tbxPor_";
	
	/**
	 * Cantidad de filas que tendran los textbox para los objetivos especificos.
	 */
	private int filasTarea=3;
	
	/**
	 * Ancho que tendran los textbox para los objetivos especificos.
	 */
	private String anchoTarea="300px";
	
	/**
	 * Ancho que tendran los textbox para los porcentajes de los objetivos especificos.
	 */
	private String anchoPorcentajeTarea="90px";
	
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
	private int rowsEnGridTareas=0;
	
	/**
	 * Es la variable que tendra la sumatoria de los porcentajes de los objetivos 
	 * especificos, esta variable debe ser igual o menor a cien para permitir 
	 * almacenar el proyecto
	 */
	private int porcentajeTotalTareas=0;
	
	Div div = null;
	
	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); 
        if( (Sessions.getCurrent().hasAttribute("usuario")) ){
			usuarioDto = (UsuarioDto)(Sessions.getCurrent().getAttribute("usuario"));
			nombreObjetivo();
			anexarNuevaFilaObjetivoEsp();
		}else{
			Executions.sendRedirect("index.zul");	
		}  			
    }
	
	public void nombreObjetivo() {
		if( (Sessions.getCurrent().getAttribute("objetivosEspecificos"))!=null ){
			ArrayList<ObjetivoEspecificoDto> listaObejtivoEspecifico = (ArrayList<ObjetivoEspecificoDto>) Sessions.getCurrent().getAttribute("objetivosEspecificos");
			for(ObjetivoEspecificoDto objEspecificos: listaObejtivoEspecifico){
				int id = (Integer) Sessions.getCurrent().getAttribute("Objetivo");
				if(id==objEspecificos.getIdn()){
					lblVentanaTareas.setValue(Labels.getLabel("mensajeTarea") + " " +  objEspecificos.getDescripcion());
				}
			}		       
		}	
	}
	
	public boolean revisarDatosTareas(String objetivoEsp, String porcentaje){
		if( (!(objetivoEsp.trim().isEmpty() ) ) && (porcentaje.trim().matches("[1-9]{1}[0-9]*")) ){
			return true;			
		}
		return false;
	}
	
	
	public void anexarNuevaFilaObjetivoEsp(){
		 Rows rows = gridTareas.getRows(); 	
	     rows.appendChild(construirFilaTarea());  
	}
	
	
	public Row construirFilaTarea() {	
		final Row row = new Row();
		/* Cada fila tendra un atributo tareaCargada, este atributo sera iniciado
		* en activo que indicara que esta fila todavia no ha anexado una nueva fila objetivo
		* especifico(fila sin datos), cuando el atributo tareaCargada es desactivado es por que esta fila 
		* ya cargo una nueva fila objetivo especifico(fila con datos), cuando el atributo tareaCargada es
		* desactivadoUltimaFila es por que la ultima fila borrada no tenia datos
		* con esto se controla que no saturen el formulario con filas de objetivos especificos vacias.
		* tareaCargada = activo | desactivado | desactivadoUltimaFila
		*/
		row.setAttribute("tareaCargada","activo");
		row.setId(idContadorRows+"");
		Textbox tbxTarea= new Textbox();
		/*
		 * Los ids de los textboxs de los objetivos especificos de cada fila
		 * tendran este formato: 
		 * tbxObjEsp_idContadorRows
		 * donde idContadorRows es la variable idContadorRows que tendra el 
		 * valor que tenga en el momento.
		 */
		tbxTarea.setId("tbxObjEsp_"+idContadorRows);
		tbxTarea.setStyle("border:1px solid black; color: #000000!important; font-size: 13px");
		tbxTarea.setRows(filasTarea);
		tbxTarea.setWidth(anchoTarea);
		tbxTarea.setPlaceholder(Labels.getLabel("placeholderTarea"));
		tbxTarea.setVisible(true);		
		row.appendChild(tbxTarea);
		/*
		 * Los ids de los textboxs del porcentaje de cada fila
		 * tendran este formato: 
		 * tbxPor_idContadorRows
		 * donde idContadorRows es la variable idContadorRows que tendra el 
		 * valor que tenga en el momento.
		 */
		Textbox tbxPorcentaje= new Textbox();
		tbxPorcentaje.setId("tbxPor_"+idContadorRows);
		tbxPorcentaje.setStyle("border:1px solid black; color: #000000!important; font-size: 13px");
		tbxPorcentaje.setWidth(anchoPorcentajeTarea);
		tbxPorcentaje.setPlaceholder(Labels.getLabel("placeholderPorcentajeTarea"));
		tbxPorcentaje.setVisible(true);		
		row.appendChild(tbxPorcentaje);	
		
		EventListener<Event> actionListenerAnexar = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
				Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
				if( (filaDelClick != null) && (filaDelClick.hasAttribute("tareaCargada") ) ){
					String tareaCargada=((String) (filaDelClick.getAttribute("tareaCargada")) );
					if(tareaCargada.equals("activo")){
						String numeroId=filaDelClick.getId();
						Textbox tbDescripcionTarea=((Textbox) filaDelClick.getFellow(parteIdTbxObjEsp+numeroId));
						Textbox tbPorcentajeTarea=((Textbox) filaDelClick.getFellow(parteIdTbxPorcentaje+numeroId));
						String descripcion = tbDescripcionTarea.getValue();
						String porcentaje=tbPorcentajeTarea.getValue();
						if( revisarDatosTareas(descripcion, porcentaje) ){
							int porcentajeInt= Integer.parseInt(porcentaje);
							if( (porcentajeTotalTareas+porcentajeInt)<=100 ){						
								anexarNuevaFilaObjetivoEsp();
								//ObjetivoEspecificoDto objetivoEspecificoDto = new ObjetivoEspecificoDto();
								//objetivoEspecificoDto.setDescripcion(descripcion);
								//objetivoEspecificoDto.setPorcentaje(porcentajeInt);
								//row.setAttribute("objetivoEspecificoDto", objetivoEspecificoDto);
								row.setAttribute("tareaCargada","desactivado");
								tbDescripcionTarea.setDisabled(true);
								tbPorcentajeTarea.setDisabled(true);
								porcentajeTotalTareas+=porcentajeInt;							
							}else{
								Messagebox.show(Labels.getLabel("mensajePorcentajeSuperiorTarea"));
							}
						}else{
							Messagebox.show(Labels.getLabel("mensajeDatosErroneos"));
						}
					}else if(tareaCargada.equals("desactivadoUltimaFila")){
						filaDelClick.setAttribute("tareaCargada","desactivado");
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
		Button btnAnexarTarea=new Button();
		btnAnexarTarea.setId("btnAnexarObjEsp_"+idContadorRows);
		btnAnexarTarea.addEventListener(Events.ON_CLICK, actionListenerAnexar);	
		btnAnexarTarea.setTooltip("mAnexar");
		btnAnexarTarea.setLabel(Labels.getLabel("btnAnexar"));
		btnAnexarTarea.setVisible(true);
		row.appendChild(btnAnexarTarea);
		
		EventListener<Event> actionListenerBorrar = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
				if ( rowsEnGridTareas > 1 ){
					final Row filaDelClick= ((Row) ((Button)event.getTarget()).getParent());
					EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
			            public void onEvent(ClickEvent event) throws Exception {
			                if(Messagebox.Button.YES.equals(event.getButton())) {
			                	if( (filaDelClick != null) && (filaDelClick.hasAttribute("tareaCargada") ) ){
			                		String tareaCargada=((String) (filaDelClick.getAttribute("tareaCargada")) );
				                	if( tareaCargada.equals("desactivado") ){			        				
					                	int porcentaje=Integer.parseInt( ((Textbox) filaDelClick.getFellow(parteIdTbxPorcentaje+filaDelClick.getId())).getValue());
					                	porcentajeTotalTareas-=porcentaje;
					                	rowsEnGridTareas--;
					                	filaDelClick.setParent(null);
					                }else if( tareaCargada.equals("desactivadoUltimaFila") ){
				                		int porcentaje=Integer.parseInt( ((Textbox) filaDelClick.getFellow(parteIdTbxPorcentaje+filaDelClick.getId())).getValue());
				                		porcentajeTotalTareas-=porcentaje;
					                	rowsEnGridTareas--;
					                	filaDelClick.setParent(null);
					                	((Row) (gridTareas.getRows().getLastChild())).setAttribute("tareaCargada","desactivadoUltimaFila");				                	
				                	}else{
				                		rowsEnGridTareas--;
					                	filaDelClick.setParent(null);
					                	((Row) (gridTareas.getRows().getLastChild())).setAttribute("tareaCargada","desactivadoUltimaFila");			                	
				                	}
			                	}
			                }
			            }
			        };
			        org.zkoss.zul.Messagebox.show(Labels.getLabel("mensajeBorrarTarea"),Labels.getLabel("tituloMensajeSeguridad"), new Messagebox.Button[]{
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
		Button btnBorrarTarea=new Button();
		btnBorrarTarea.setId("btnBorrarObjEsp_"+idContadorRows);
		btnBorrarTarea.addEventListener(Events.ON_CLICK, actionListenerBorrar);	
		btnBorrarTarea.setTooltip("mBorrar");
		btnBorrarTarea.setLabel(Labels.getLabel("btnBorrar"));
		btnBorrarTarea.setVisible(true);
		row.appendChild(btnBorrarTarea);		
		row.setAlign("center");
		row.setSclass("sidebar-fn");			
		row.setVflex("min");
		idContadorRows++;
		rowsEnGridTareas++;
		return row;
	}
	
	public void onClick$btnCancelar(Event ev) {
		java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/ModuloGestionDeProyectos/vista/continuarCreacionProyecto.zul") ;
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
}
