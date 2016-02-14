package com.udea.proyint.Administracion.ctl;

import java.util.ArrayList;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.udea.proyint.Administracion.ngc.PersonaNgcInt;
import com.udea.proyint.Administracion.ngc.TipoIdentificacionNgcInt;
import com.udea.proyint.Administracion.ngc.TipoRolNgcInt;
import com.udea.proyint.Dominio.dto.EstadoDto;
import com.udea.proyint.Dominio.dto.RolSistemaDto;
import com.udea.proyint.Dominio.dto.TipoDocumentoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;

public class CrearPersonaCtl extends GenericForwardComposer {
	
	private TipoIdentificacionNgcInt tipoIdentificacionNgc;
	private TipoRolNgcInt tipoRolNgc;
	private PersonaNgcInt personaNgc;
	
	private Combobox cbxIdentificacion;
	private Combobox cbxRol;
	private Textbox tbxIdentificacion;
	private Textbox tbxNombres;
	private Textbox tbxApellidos;
	private Textbox tbxEmail;
	private Textbox tbxUsuario;
	private Textbox tbxClave;
	private Textbox tbxConfirmarClave;
	
	public void setTipoIdentificacionNgc(TipoIdentificacionNgcInt tipoIdentificacionNgc) {
		this.tipoIdentificacionNgc = tipoIdentificacionNgc;
	}
	
	public void setTipoRolNgc(TipoRolNgcInt tipoRolNgc) {
		this.tipoRolNgc = tipoRolNgc;
	}
	
	public void setPersonaNgc(PersonaNgcInt personaNgc) {
		this.personaNgc = personaNgc;
	}

	/**
	 * Metodo ejecutado cuando se carga la vista crearPersona.zul
	 * en el navegador.
	 */
	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); 
        cargarComboBoxTipoId();
        cargarComboBoxRoles();
    }
	
	public void cargarComboBoxTipoId(){
		ArrayList<TipoDocumentoDto> lista = tipoIdentificacionNgc.buscarTiposId();
		if (lista != null) {
			for(TipoDocumentoDto td: lista){
				Comboitem comboItem = llenarComboboxTipoIdentificacion(td);
				cbxIdentificacion.appendChild(comboItem);
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
	
	public void cargarComboBoxRoles(){
		ArrayList<RolSistemaDto> lista = tipoRolNgc.buscarTiposRol();
		if (lista != null) {
			for(RolSistemaDto rs: lista){
				Comboitem comboItem = llenarComboboxTipoRoles(rs);
				cbxRol.appendChild(comboItem);
			}
		}
	}
	
	private Comboitem llenarComboboxTipoRoles(RolSistemaDto rs) {
		final Comboitem comboI= new Comboitem();
		comboI.setLabel(rs.getNombre());
		comboI.setId("citr"+rs.getIdn());		
		comboI.setSclass("sidebar-fn");
		return comboI;
	}
	
	public void onClick$cbxIdentificacion(Event e){
		marcarCampoCorrecto(cbxIdentificacion);
	}
	
	public void onClick$cbxRol(Event e){
		marcarCampoCorrecto(cbxRol);
	}
	
	public void onClick$tbxIdentificacion(Event e){
		marcarCampoCorrecto(tbxIdentificacion);
	}
	
	public void onClick$tbxNombres(Event e){
		marcarCampoCorrecto(tbxNombres);
	}
	
	public void onClick$tbxApellidos(Event e){
		marcarCampoCorrecto(tbxApellidos);
	}
	
	public void onClick$tbxEmail(Event e){
		marcarCampoCorrecto(tbxEmail);
	}
	
	public void onClick$tbxUsuario(Event e){
		marcarCampoCorrecto(tbxUsuario);
	}
	
	public void onClick$tbxClave(Event e){
		marcarCampoCorrecto(tbxClave);
	}
	
	public void onClick$tbxConfirmarClave(Event e){
		marcarCampoCorrecto(tbxConfirmarClave);
	}
	
	public void onClick$btnCrear(Event e){
		UsuarioDto usuarioDto = new UsuarioDto();
		boolean formularioPersona = validarCampos();
		if(formularioPersona){
			Messagebox.show("Campos Correctos" , "Informacion", Messagebox.OK, Messagebox.INFORMATION);
			//****************RECORDAR QUE ESTO ES AUTOINCREMENTABLE********************************
			//usuarioDto.setIdn(2);
			//**************************************************************************************
			String tipoId = cbxIdentificacion.getSelectedItem().getId().trim();
			tipoId = tipoId.substring(4);
			TipoDocumentoDto tipoDocumentoDto = new TipoDocumentoDto();
			tipoDocumentoDto.setIdn(Integer.parseInt(tipoId));
			usuarioDto.setTipoDocumento(tipoDocumentoDto);
			usuarioDto.setIdentificacion(Integer.parseInt(tbxIdentificacion.getText()));
			usuarioDto.setNombres(tbxNombres.getText());
			usuarioDto.setApellidos(tbxApellidos.getText());
			usuarioDto.setEmail(tbxEmail.getText());
			usuarioDto.setUsuario(tbxUsuario.getText());
			usuarioDto.setContrasena(tbxClave.getText());
			usuarioDto.setAdtUsuario(capturarNombreUsuario());
			usuarioDto.setFechaHora(new Date());
			String tipoRol = cbxRol.getSelectedItem().getId().trim();
			tipoRol = tipoRol.substring(4);
			RolSistemaDto rolSistemaDto = new RolSistemaDto();
			rolSistemaDto.setIdn(Integer.parseInt(tipoRol));
			usuarioDto.setRol(rolSistemaDto);
			EstadoDto estadoDto = new EstadoDto();
			estadoDto.asignarIdn(1);
			usuarioDto.setEstado(estadoDto);
			personaNgc.crearPersona(usuarioDto);
			limpiarCampos();
			System.out.println("identificador: "+usuarioDto.getIdn());
			System.out.println("tipo Identificacion: "+tipoDocumentoDto.getIdn());
			System.out.println("numero id: "+usuarioDto.getIdentificacion());
			System.out.println("nombres: "+usuarioDto.getNombres());
			System.out.println("apellidos: "+usuarioDto.getApellidos());
			System.out.println("correo: "+usuarioDto.getEmail());
			System.out.println("usuario: "+usuarioDto.getUsuario());
			System.out.println("contraseña: "+ usuarioDto.getContrasena());
			System.out.println("usuario creador: "+usuarioDto.getAdtUsuario());
			System.out.println("fecha creacion: "+usuarioDto.getFechaHora());
			System.out.println("rol: "+rolSistemaDto.getIdn());	
			System.out.println("estado: "+ estadoDto.obtenerIdn());
		}else{
			Messagebox.show("Campos Incorrectos. Validar Informacion" , "Error", Messagebox.OK, Messagebox.ERROR);
		}	
	}
	
	private void limpiarCampos() {
		cbxIdentificacion.setText("");
		cbxRol.setText("");
		tbxIdentificacion.setText("");
		tbxNombres.setText("");
		tbxApellidos.setText("");
		tbxEmail.setText("");
		tbxUsuario.setText("");
		tbxClave.setText("");
		tbxConfirmarClave.setText("");
	}

	private boolean validarCampos(){
		if(cbxIdentificacion.getText().equals("")){
			marcarCampoError(cbxIdentificacion);
			return false;
		}
		if((tbxIdentificacion.getText().equals("")) || !(tbxIdentificacion.getText().matches("^([0-9])*$"))){
			marcarCampoError(tbxIdentificacion);
			return false;
		}
		if((tbxNombres.getText().equals("")) || !(tbxNombres.getText().matches("[a-zA-ZñÑ\\s]{2,50}"))){
			marcarCampoError(tbxNombres);
			return false;
		}
		if((tbxApellidos.getText().equals("")) || !(tbxApellidos.getText().matches("[a-zA-ZñÑ\\s]{2,50}"))){
			marcarCampoError(tbxApellidos);
			return false;
		}
		if((tbxEmail.getText().equals("")) || !(tbxEmail.getText().matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))){
			marcarCampoError(tbxEmail);
			return false;
		}
		if((tbxUsuario.getText().equals("")) || !(tbxUsuario.getText().matches("[a-zA-Z]((\\.|_|-)?[a-zA-Z0-9]+){3}"))){
			marcarCampoError(tbxUsuario);
			return false;
		}
		if((tbxClave.getText().equals("")) || !(tbxClave.getText().matches("^(?=.*\\d)(?=.*[a-zA-Z]).{6,15}$"))){
			marcarCampoError(tbxClave);
			return false;
		}
		if((tbxConfirmarClave.getText().equals("")) || !(tbxConfirmarClave.getText().equals(tbxClave.getText()))){
			marcarCampoError(tbxConfirmarClave);
			return false;
		}
		if(cbxRol.getText().equals("")){
			marcarCampoError(cbxRol);
			return false;
		}
		return true;
	}

	private void marcarCampoError(Textbox campo){
		campo.setStyle("border:1px solid red");
	}
	
	private void marcarCampoCorrecto(Textbox campo){
		campo.setStyle("border:1px solid black");
	}
	
	public String capturarNombreUsuario(){
		if(Sessions.getCurrent().hasAttribute("usuario")){
		   return (((UsuarioDto)Sessions.getCurrent().getAttribute("usuario")).getUsuario());
		}
		return null;
		//return ("hader.pelaez");
	}

}
