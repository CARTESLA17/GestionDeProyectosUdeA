package com.udea.proyint.ModuloGestionDeProyectos.ctl;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;
import com.udea.proyint.Dominio.dto.ProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public class ContinuarCreacionProyectoCtl extends GenericForwardComposer{
	
	private Grid objEsp;
	
	private String etiquetaRowContinuar="erc_";
	private String labelDescripcion="ld_";
	private String labelPorcentaje="lp_";
	private String labelBoton="lb_";
	
	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); 
        if(Sessions.getCurrent().hasAttribute("objetivosEspecificos")){
        	System.out.println("entro al if de session");
			completarGrid();
		}else{
			
		}				
    }

	public void completarGrid() {
		System.out.println("entro a completarGrid");
		Rows rows = objEsp.getRows();	
		if( (Sessions.getCurrent().getAttribute("objetivosEspecificos"))!=null ){
			ArrayList<ObjetivoEspecificoDto> listaObejtivoEspecifico = (ArrayList<ObjetivoEspecificoDto>) Sessions.getCurrent().getAttribute("objetivosEspecificos");
			for(ObjetivoEspecificoDto objEspecificos: listaObejtivoEspecifico){
				rows.appendChild(construirFila(objEspecificos));
				System.out.println(objEspecificos.toString());
			}		       
		}	
	}

	public Row construirFila(ObjetivoEspecificoDto objEspecificos) {
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
		Button botonActividades= new Button();
		botonActividades.setId(labelBoton+objEspecificos.getIdn());
		//botonActividades.addEventListener(Events.ON_CLICK, actionListenerAnexar);	
		//botonActividades.setTooltip("mAnexar");
		botonActividades.setLabel(Labels.getLabel("btnActividades"));
		botonActividades.setVisible(true);
		row.appendChild(botonActividades);
		return row;
	}

}
