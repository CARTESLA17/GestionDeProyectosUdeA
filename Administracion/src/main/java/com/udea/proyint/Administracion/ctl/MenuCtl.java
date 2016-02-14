package com.udea.proyint.Administracion.ctl;

import java.io.IOException;
import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.udea.proyint.Dominio.dto.UsuarioDto;

public class MenuCtl extends GenericForwardComposer{
	
	private Label lblNombreUsuario;
	private Div div;
	
	private EventQueue eventoQueueCargarVentanaMenu;
	
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);	
		if(Sessions.getCurrent().hasAttribute("usuario")){
			lblNombreUsuario.setValue(((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getNombres()+" "
			+((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getApellidos());
			cargarVentanaMenu();
		}else{
			Executions.sendRedirect("index.zul");
		}
		eventoQueueCargarVentanaMenu = EventQueues.lookup("cargarVentanaMenu",EventQueues.DESKTOP, true);
		eventoQueueCargarVentanaMenu.subscribe(new EventListener() {
	        public void onEvent(Event evt) {
	        	cargarVentanaMenu();
	        }
	    });
	}
	
	public void onClick$lblCerrarSesion(Event ev){
		borrarDatosDeLaSesion();
		Executions.sendRedirect("index.zul");		
	}
	
	public void borrarDatosDeLaSesion(){
		Sessions.getCurrent().removeAttribute("usuario");
	}
	
	public void onClick$crearProyecto(Event ev) {
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
	
	public void cargarVentanaMenu() {
		java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/Administracion/vista/ventanaMenu.zul") ;
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
	
	public void onClick$crearPersona(Event ev) {
		java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/Administracion/vista/crearPersona.zul") ;
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
	
	public void onClick$listarProyectos(Event ev) {
		java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/ModuloGestionDeProyectos/vista/listarProyectos.zul") ;
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
	
	public void onClick$modificarPersona(Event ev) throws IOException{
		java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/Administracion/vista/modificarPersona.zul") ;
		java.io.Reader zulReader = new java.io.InputStreamReader(zulInput);
		Window win = (Window)Executions.createComponentsDirectly(zulReader,"zul",null,null);
		if(div.getFirstChild()!=null){
			div.removeChild(div.getFirstChild());
		}
		div.appendChild(win);
	}

}
