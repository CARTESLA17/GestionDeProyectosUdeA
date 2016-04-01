package com.udea.proyint.ModuloGestionDeProyectos.ctl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;
import com.udea.proyint.Dominio.dto.ProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

/**
 * 
 * @author user
 *
 */
public class ContinuarCreacionProyectoCtl extends GenericForwardComposer{
	
	private Grid objEsp;
	
	private String etiquetaRowContinuar="erc_";
	private String labelDescripcion="ld_";
	private String labelPorcentaje="lp_";
	private String labelBoton="lb_";
	private ObjetivoEspecificoDto objetivo = new ObjetivoEspecificoDto();
	
	private final String ESTADO_OBJ_ESP_ABIERTO="Abierto";
	
	Div div = null;
	
	
	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); 
        if(Sessions.getCurrent().hasAttribute("objetivosEspecificos")){
			completarGrid();
		}else{
			
		}				
    }

	public void completarGrid() {
		Rows rows = objEsp.getRows();	
		if( (Sessions.getCurrent().getAttribute("objetivosEspecificos"))!=null ){
			ArrayList<ObjetivoEspecificoDto> listaObejtivoEspecifico = (ArrayList<ObjetivoEspecificoDto>) Sessions.getCurrent().getAttribute("objetivosEspecificos");
			for(ObjetivoEspecificoDto objEspecificos: listaObejtivoEspecifico){
				rows.appendChild(construirFila(objEspecificos));	
			}		       
		}	
	}

	public Row construirFila(ObjetivoEspecificoDto objEspecificos) {
		objetivo = objEspecificos;
		final Row row = new Row();
		row.setId(etiquetaRowContinuar+objEspecificos.getIdn());
		Label lblDescripcion= new Label();
		lblDescripcion.setId(labelDescripcion+objEspecificos.getIdn());
		lblDescripcion.setStyle("color: #000000!important; font-size: 13px");
		lblDescripcion.setValue(objEspecificos.getDescripcion());
		row.appendChild(lblDescripcion);
		Label lblPorcentaje= new Label();
		lblPorcentaje.setId(labelPorcentaje+objEspecificos.getIdn());
		lblPorcentaje.setStyle("color: #000000!important; font-size: 13px");
		lblPorcentaje.setValue(objEspecificos.getPorcentaje().toString());
		row.appendChild(lblPorcentaje);
		
		final int id = objEspecificos.getIdn();
		final Button botonActividades = new Button();
		
		EventListener<Event> actionListenerActividades = new SerializableEventListener<Event>() {
			public void onEvent(Event event) throws Exception {	
				Sessions.getCurrent().setAttribute("Objetivo", id);
				java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/ModuloGestionDeProyectos/vista/ventanaTareas.zul") ;
				java.io.Reader zulReader = new java.io.InputStreamReader(zulInput);
				Window win = (Window)Executions.createComponentsDirectly(zulReader,"zul",null,null);
				if(div.getFirstChild()!=null){
					div.removeChild(div.getFirstChild());
				}
				div.appendChild(win);
			}							
		};
		
		
		botonActividades.setId(labelBoton+objEspecificos.getIdn());
		botonActividades.addEventListener(Events.ON_CLICK, actionListenerActividades);	
		botonActividades.setTooltip("mAnexar");
		botonActividades.setLabel(Labels.getLabel("btnActividades"));
		botonActividades.setVisible(true);
		row.appendChild(botonActividades);
		return row;
	}	
	
	
	public void onClick$btnCancelar(Event ev) {
		java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/ModuloGestionDeProyectos/vista/crearProyecto.zul") ;
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
