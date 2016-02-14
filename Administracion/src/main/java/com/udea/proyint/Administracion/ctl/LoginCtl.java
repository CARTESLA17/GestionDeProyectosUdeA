package com.udea.proyint.Administracion.ctl;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.udea.proyint.Administracion.ngc.PersonaNgcInt;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public class LoginCtl extends GenericForwardComposer {
	
	private Textbox txtUsuario;
	private Textbox txtPassword;
	
	private PersonaNgcInt personaNgc;
	private UsuarioDto personaDto;	
	
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);		
	}	
	
	public void setPersonaNgc(PersonaNgcInt personaNgc) {
		this.personaNgc = personaNgc;
	}

	public void onOK$txtPassword(Event ev){
		validarInformacion();
	}
	public void onClick$btnIngresar(Event ev){
		validarInformacion();		
	}	

	public void validarInformacion(){
		String usuario = txtUsuario.getText();
		String contrasena = txtPassword.getText();		
		if(!usuario.equals("") && !contrasena.equals("") && !usuario.matches("((\\s)+)|((\\t)+)") && !contrasena.matches("((\\s)+)|((\\t)+)")){
			personaDto=personaNgc.verificarUsuario(usuario, contrasena);	
			if(personaDto!=null){				
				Sessions.getCurrent().setAttribute("usuario",personaDto);				
				Executions.sendRedirect("llamadoAlMenu.zul");
			}else{
				Messagebox.show(Labels.getLabel("mensajeDatosNoValidos"), Labels.getLabel("tituloMensajeDatosNoValidos"), Messagebox.OK, Messagebox.INFORMATION);
			}
		}else{
			Messagebox.show(Labels.getLabel("mensajeDatosNoValidos"), Labels.getLabel("tituloMensajeDatosNoValidos"), Messagebox.OK, Messagebox.INFORMATION);
		}
	}
}

	