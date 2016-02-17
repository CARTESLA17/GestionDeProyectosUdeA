package com.udea.proyint.Administracion.ctl;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import com.udea.proyint.Dominio.dto.UsuarioDto;

public class FormularioModificarPersonaCtl extends GenericForwardComposer{
	
	private Textbox tbxIdentificacion;
	private Textbox tbxNombres;
	private Textbox tbxApellidos;
	private Textbox tbxEmail;
	private Textbox tbxClave;
	private Textbox tbxConfirmarClave;
	private Combobox cbxRol;
	private Combobox cbxIdentificacion;
	
	
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);	
		if(Sessions.getCurrent().hasAttribute("usuario")){
			System.out.println("entra al if de sesion");
			llenarCampos();
		}else{
			
		}
	}


	private void llenarCampos() {
		System.out.println("entra a llenar campos");
		tbxIdentificacion.setText(((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getIdentificacion().toString());
		tbxNombres.setText(((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getNombres());
		tbxApellidos.setText(((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getApellidos());
		tbxEmail.setText(((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getEmail());
		tbxClave.setText(((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getContrasena());
		tbxConfirmarClave.setText(((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getContrasena());
		cbxRol.setText(((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getRol().getNombre());
		cbxIdentificacion.setText(((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getTipoDocumento().getNombre());
		
	}
}
