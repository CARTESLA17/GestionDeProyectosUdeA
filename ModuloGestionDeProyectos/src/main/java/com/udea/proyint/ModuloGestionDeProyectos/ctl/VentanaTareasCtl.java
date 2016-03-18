package com.udea.proyint.ModuloGestionDeProyectos.ctl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

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
	
	Div div = null;
	
	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); 
        if( (Sessions.getCurrent().hasAttribute("usuario")) ){
			usuarioDto = (UsuarioDto)(Sessions.getCurrent().getAttribute("usuario"));
			nombreObjetivo();
		}else{
			Executions.sendRedirect("index.zul");	
		}  			
    }
	
	public void nombreObjetivo() {
		if( (Sessions.getCurrent().getAttribute("objetivosEspecificos"))!=null ){
			ArrayList<ObjetivoEspecificoDto> listaObejtivoEspecifico = (ArrayList<ObjetivoEspecificoDto>) Sessions.getCurrent().getAttribute("objetivosEspecificos");
			for(ObjetivoEspecificoDto objEspecificos: listaObejtivoEspecifico){
				if((Sessions.getCurrent().getAttribute("objetivo"))==objEspecificos.getIdn()){
					lblVentanaTareas.setValue(objEspecificos.getDescripcion());
				}
			}		       
		}	
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
