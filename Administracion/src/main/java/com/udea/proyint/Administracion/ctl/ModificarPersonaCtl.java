package com.udea.proyint.Administracion.ctl;

import java.io.IOException;
import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.udea.proyint.Administracion.ngc.PersonaNgc;
import com.udea.proyint.Administracion.ngc.PersonaNgcInt;
import com.udea.proyint.Administracion.ngc.TipoIdentificacionNgcInt;
import com.udea.proyint.Dominio.dto.TipoDocumentoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public class ModificarPersonaCtl extends GenericForwardComposer {
	
	private TipoIdentificacionNgcInt tipoIdentificacionNgc;
	private PersonaNgcInt personaNgc;
	private UsuarioDto personaDto;
	
	private Combobox cbxIdentificiacion;
	private Textbox tbxIdentificacion;
	private Div div;
	private boolean crearVista = true;
	
	private Textbox tbxIdentificacionModificar;
	private Textbox tbxNombres;
	//private ArrayList<UsuarioDto> informacionUsuario;
	
	
	//Constructor
	public void setTipoIdentificacionNgc(
		TipoIdentificacionNgcInt tipoIdentificacionNgc) {
		this.tipoIdentificacionNgc = tipoIdentificacionNgc;
	}
	
	public void setPersonaNgc(PersonaNgcInt personaNgc) {
		this.personaNgc = personaNgc;
	}	
	
		public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); 
        cargarComboBoxTipoId();
    }


	public void cargarComboBoxTipoId() {
		ArrayList<TipoDocumentoDto> lista = tipoIdentificacionNgc.buscarTiposId();
		if (lista != null) {
			for(TipoDocumentoDto td: lista){
				Comboitem comboItem = llenarComboboxTipoIdentificacion(td);
				cbxIdentificiacion.appendChild(comboItem);
			}
		}
	}	
	
	private Comboitem llenarComboboxTipoIdentificacion(TipoDocumentoDto td) {
		final Comboitem comboI= new Comboitem();
		comboI.setLabel(td.getNombre());
		comboI.setId("citd"+td.getIdn());
		comboI.setSclass("sidebar-fn");
		return comboI;
	}
	
	public void onClick$btnBuscar(Event e) throws IOException{
		boolean validacion = validarCampos();
		if(validacion){
			String tipoId = cbxIdentificiacion.getSelectedItem().getId().trim();
			tipoId = tipoId.substring(4);
			System.out.println("tipo id: "+tipoId);
			System.out.println("id: "+tbxIdentificacion.getText());
			personaDto = personaNgc.buscarUsuarioModificar(Integer.parseInt(tipoId), Integer.parseInt(tbxIdentificacion.getText()));
			//CargarPersonaModificarCtl.llenarCampos(personaDto);
			System.out.println("llega a llenarCampos");
			llenarCampos(personaDto);
			System.out.println("paso llenarCampos");
			
			if (crearVista){
				//Messagebox.show("Campos Correctos" , "Informacion", Messagebox.OK, Messagebox.INFORMATION);
				java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/udea/proyint/Administracion/vista/cargarPersonaModificar.zul") ;
				java.io.Reader zulReader = new java.io.InputStreamReader(zulInput);
				Window win = (Window)Executions.createComponentsDirectly(zulReader,"zul",null,null);
				div.appendChild(win);
				crearVista = false;
			}	
		}else{
			Messagebox.show("Campos Incorrectos. Validar Informacion" , "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}


	private void llenarCampos(UsuarioDto personaDto2) {
		//cbxIdentificiacion.setContext(personaDto.getTipoDocumento().getIdn());
		//tbxIdentificacionModificar.setText(personaDto.getIdentificacion().toString());
		//tbxNombres.setText(personaDto.getNombres());
		System.out.println("en el metodo llenarCampos");
		
	}

	private boolean validarCampos() {
		if(cbxIdentificiacion.getText().equals("")){
			marcarCampoError(cbxIdentificiacion);
			return false;
		}
		if((tbxIdentificacion.getText().equals("")) || !(tbxIdentificacion.getText().matches("^([0-9])*$"))){
			marcarCampoError(tbxIdentificacion);
			return false;
		}
		return true;
	}

	private void marcarCampoError(Textbox campo){
		campo.setStyle("border:1px solid red");
	}
	
	public void onClick$tbxIdentificacion(Event e){
		marcarCampoCorrecto(tbxIdentificacion);
	}
	
	public void onClick$cbxIdentificiacion(Event e){
		marcarCampoCorrecto(cbxIdentificiacion);
	}
	
	private void marcarCampoCorrecto(Textbox campo){
		campo.setStyle("border:1px solid black");
	}
}
