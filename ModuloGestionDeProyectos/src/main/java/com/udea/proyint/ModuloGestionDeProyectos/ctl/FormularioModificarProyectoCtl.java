package com.udea.proyint.ModuloGestionDeProyectos.ctl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

import com.udea.proyint.Administracion.ngc.PersonaNgcInt;
import com.udea.proyint.Dominio.dto.ModalidadesDelProyectoDto;
import com.udea.proyint.Dominio.dto.ObjetivoEspecificoDto;
import com.udea.proyint.Dominio.dto.ParticipanteXProyectoDto;
import com.udea.proyint.Dominio.dto.TiposDeProyectoDto;
import com.udea.proyint.Dominio.dto.UsuarioDto;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.AsesorXProyectoNgcInt;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.ModalidadesDelProyectoNgcInt;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.ObjetivoEspecificoNgcInt;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.ParticipanteXProyectoNgcInt;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.ProyectoNgcInt;
import com.udea.proyint.ModuloGestionDeProyectos.ngc.TiposDeProyectoNgcInt;
import com.udea.proyint.Dominio.dto.AsesorXProyectoDto;
import com.udea.proyint.Dominio.dto.EstadoDelProyectoDto;
import com.udea.proyint.Dominio.dto.ProyectoDto;

/**
 * 
 * @author user
 *
 */
public class FormularioModificarProyectoCtl extends GenericForwardComposer{
	
	private AsesorXProyectoNgcInt asesorXProyectoNgc;
	private ParticipanteXProyectoNgcInt participanteXProyectoNgc;
	private ObjetivoEspecificoNgcInt objetivoEspecificoNgc;
	private TiposDeProyectoNgcInt tiposDeProyectoNgc;
	private ModalidadesDelProyectoNgcInt modalidadesDelProyectoNgc;
	private PersonaNgcInt personaNgc;
	
	//private AsesorXProyectoDto asesor = new AsesorXProyectoDto();
	
	private Label lblNombreResponsable;
	private Textbox tbxNombreProyecto;
	private Combobox cbxTipoProyecto;
	private Combobox cbxModalidad;
	private Grid gridAsesores;
	private Grid gridParticipantes;
	private Grid gridBusquedaParticipantes;
	private Grid gridBusquedaAsesores;
	private Combobox cbxTipoIDAsesores;
	private Combobox cbxTipoIDParticipantes;
	private Textbox tbxIDAsesores;
	private Textbox tbxNombresAsesores;
	private Textbox tbxApellidosAsesores;
	private Textbox tbxIDParticipantes;
	private Textbox tbxNombresParticipantes;
	private Textbox tbxApellidosParticipantes;	
	
	private Datebox dateFechaInicial;
	private Datebox dateFechaFinal;
	private Textbox tbxObjetivoGeneral;
	private Grid gridObjetivosEspecificos;
	private Button btnAgregarAsesores;
	private Div div = null;
	
	private String etiquetaRow = "er_";
	private String etiquetaRowA = "era_";
	private String etiquetaRowP = "erp_";
	private String etiquetaTipoIdA = "etida_"; 
	private String etiquetaIdA = "eida_";
	private String etiquetaNombresA = "ena_";
	private String etiquetaApellidosA = "eaa_";
	private String etiquetaTipoIdP = "etidp_"; 
	private String etiquetaIdP = "eidp_";
	private String etiquetaNombresP = "enp_";
	private String etiquetaApellidosP = "eap_";
	private String etiquetaBtnEliminar = "ebe_";
	private String etiquetaBtnEliminarA = "ebea_";
	private String etiquetaBtnEliminarP = "ebep_";
	private String etiquetaBtnAgregar = "eba";
	private String etiquetaDescripcion = "ed_";
	private String etiquetaPorcentaje = "ep";
	private String etiquetaComboItemT = "ecit_";
	private String etiquetaComboItemM = "ecim_";
	
	private String parteIdRowBusquedaAsesores="rba_";
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
	private String parteIdRowBusquedaParticipantes="rbp_";
		
	private int idResponsableDelProyecto=-1;
	
	
//	private Array
	
	public void setPersonaNgc(PersonaNgcInt personaNgc) {
		this.personaNgc = personaNgc;
	}
	
	public void setAsesorXProyectoNgc(AsesorXProyectoNgcInt asesorXProyectoNgc) {
		this.asesorXProyectoNgc = asesorXProyectoNgc;
	}
	
	public void setParticipanteXProyectoNgc(ParticipanteXProyectoNgcInt participanteXProyectoNgc) {
		this.participanteXProyectoNgc = participanteXProyectoNgc;
	}
	
	public void setObjetivoEspecificoNgc(ObjetivoEspecificoNgcInt objetivoEspecificoNgc) {
		this.objetivoEspecificoNgc = objetivoEspecificoNgc;
	}
	
	public void setTiposDeProyectoNgc(TiposDeProyectoNgcInt tiposDeProyectoNgc) {
		this.tiposDeProyectoNgc = tiposDeProyectoNgc;
	}
		
	public void setModalidadesDelProyectoNgc(ModalidadesDelProyectoNgcInt modalidadesDelProyectoNgc) {
		this.modalidadesDelProyectoNgc = modalidadesDelProyectoNgc;
	}

	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); 
        if(Sessions.getCurrent().hasAttribute("proyecto")){
			cargarCbxTiposDeProyecto();
			cargarCbxModalidades();
			idResponsableDelProyecto = ((ProyectoDto)Sessions.getCurrent().getAttribute("proyecto")).getResponsable().getIdn();
			llenarCampos();
			dateFechaInicial.setButtonVisible(false);
			dateFechaInicial.setDisabled(true);
		}
    }
	
	public void cargarCbxModalidades(){
		ArrayList<ModalidadesDelProyectoDto> lista = modalidadesDelProyectoNgc.buscarModalidades();
		if (lista != null) {
			for(ModalidadesDelProyectoDto modalidad: lista){
				Comboitem comboItem = crearComboItemModalidadDelProyecto(modalidad);
				cbxModalidad.appendChild(comboItem);
			}
		}
	}
	
	public Comboitem crearComboItemModalidadDelProyecto(ModalidadesDelProyectoDto modalidad) {
		final Comboitem comboI= new Comboitem();
		comboI.setLabel(modalidad.getNombre());
		comboI.setId(etiquetaComboItemM + modalidad.getIdn());		
		comboI.setSclass("sidebar-fn");
		return comboI;
	}
	
	public void cargarCbxTiposDeProyecto(){
		ArrayList<TiposDeProyectoDto> lista = tiposDeProyectoNgc.buscarTiposDeProyecto();
		if (lista != null) {
			for(TiposDeProyectoDto tp: lista){
				Comboitem comboItem = crearComboItemTiposDeProyecto(tp);
				cbxTipoProyecto.appendChild(comboItem);
			}
		}
	}
	
	public Comboitem crearComboItemTiposDeProyecto(TiposDeProyectoDto tp) {
		final Comboitem comboI= new Comboitem();
		comboI.setLabel(tp.getNombre());
		comboI.setId(etiquetaComboItemT + tp.getIdn());		
		comboI.setSclass("sidebar-fn");
		return comboI;
	}
	
	public void llenarCampos() {
		lblNombreResponsable.setValue(((ProyectoDto)Sessions.getCurrent().getAttribute("proyecto")).getResponsable().getNombres()
				+" "+((ProyectoDto)Sessions.getCurrent().getAttribute("proyecto")).getResponsable().getApellidos());
		tbxNombreProyecto.setText(((ProyectoDto)Sessions.getCurrent().getAttribute("proyecto")).getNombreDelProyecto());
		cbxTipoProyecto.setText(((ProyectoDto)Sessions.getCurrent().getAttribute("proyecto")).getTipoDeProyecto().getNombre());
		cbxModalidad.setText((((ProyectoDto)Sessions.getCurrent().getAttribute("proyecto")).getModalidad().getNombre()));
		int idProyecto = (((ProyectoDto)Sessions.getCurrent().getAttribute("proyecto")).getIdn());
		gridAsesores(idProyecto);
		gridParticipantes(idProyecto);
		dateFechaInicial.setPlaceholder((((ProyectoDto)Sessions.getCurrent().getAttribute("proyecto")).getFechaInicial().toString().substring(0, 10)));
		dateFechaFinal.setPlaceholder((((ProyectoDto)Sessions.getCurrent().getAttribute("proyecto")).getFechaFinal().toString().substring(0, 10)));
		tbxObjetivoGeneral.setText((((ProyectoDto)Sessions.getCurrent().getAttribute("proyecto")).getObjetivoGeneral()));
		gridObjetivosEspecificos(idProyecto);
		
		
	}

	public void gridObjetivosEspecificos(int idProyecto) {
		Rows rows = gridObjetivosEspecificos.getRows();
		ArrayList<ObjetivoEspecificoDto> listaObjEsp = objetivoEspecificoNgc.buscarObjetivoEspecificoXProyecto(idProyecto);
		for(ObjetivoEspecificoDto listaOE: listaObjEsp){
			rows.appendChild(construirFilaObejtivoEsp(listaOE));
			System.out.println("Objetivo: "+listaOE.getDescripcion());
		}	
		
	}

	public Row construirFilaObejtivoEsp(ObjetivoEspecificoDto listaOE) {
		final Row row = new Row();
		row.setId(etiquetaRow + listaOE.getIdn());
		
		Label lblDescripcion = new Label();
		lblDescripcion.setId(etiquetaDescripcion + listaOE.getIdn());
		lblDescripcion.setStyle("color: #000000!important; font-size: 13px");
		lblDescripcion.setValue(listaOE.getDescripcion());
		row.appendChild(lblDescripcion);
		
		Label lblPorcentaje = new Label();
		lblPorcentaje.setId(etiquetaPorcentaje + listaOE.getIdn());
		lblPorcentaje.setStyle("color: #000000!important; font-size: 13px");
		lblPorcentaje.setValue(listaOE.getPorcentaje().toString());
		row.appendChild(lblPorcentaje);
		
		final Button btnEliminar = new Button();
		btnEliminar.setId(etiquetaBtnEliminar + listaOE.getIdn());
		//btnEliminar.addEventListener(Events.ON_CLICK, actionListenerActividades);	
		btnEliminar.setTooltip("mAnexar");
		btnEliminar.setLabel(Labels.getLabel("btnEliminar"));
		btnEliminar.setVisible(true);
		row.appendChild(btnEliminar);
		
		return row;
	}

	public void gridParticipantes(int idProyecto) {
		Rows rows = gridParticipantes.getRows();
		ArrayList<ParticipanteXProyectoDto> participantes = participanteXProyectoNgc.buscarParticipanteXProyecto(idProyecto);
		for(ParticipanteXProyectoDto listaPxP:participantes){
			rows.appendChild(construirFilaParticipantes(listaPxP));
			System.out.println("Participantes: "+listaPxP.getParticipanteXProyectoDtoId().getParticipante().getNombres()+" "+listaPxP.getParticipanteXProyectoDtoId().getParticipante().getApellidos());
		}	
	}

	public Row construirFilaParticipantes(ParticipanteXProyectoDto listaPxP) {
		final Row row = new Row();
		row.setId(etiquetaRowP + listaPxP.getParticipanteXProyectoDtoId().getParticipante().getIdn());
		
		Label lblTipoId = new Label();
		lblTipoId.setId(etiquetaTipoIdP + listaPxP.getParticipanteXProyectoDtoId().getParticipante().getIdn());
		lblTipoId.setStyle("color: #000000!important; font-size: 13px");
		lblTipoId.setValue(listaPxP.getParticipanteXProyectoDtoId().getParticipante().getTipoDocumento().getNombre());
		row.appendChild(lblTipoId);
		
		Label lblId = new Label();
		lblId.setId(etiquetaIdP + listaPxP.getParticipanteXProyectoDtoId().getParticipante().getIdn());
		lblId.setStyle("color: #000000!important; font-size: 13px");
		lblId.setValue(listaPxP.getParticipanteXProyectoDtoId().getParticipante().getIdentificacion().toString());
		row.appendChild(lblId);
		
		Label lblnombres = new Label();
		lblnombres.setId(etiquetaNombresP + listaPxP.getParticipanteXProyectoDtoId().getParticipante().getIdn());
		lblnombres.setStyle("color: #000000!important; font-size: 13px");
		lblnombres.setValue(listaPxP.getParticipanteXProyectoDtoId().getParticipante().getNombres());
		row.appendChild(lblnombres);
		
		Label lblApellidos = new Label();
		lblApellidos.setId(etiquetaApellidosP + listaPxP.getParticipanteXProyectoDtoId().getParticipante().getIdn());
		lblApellidos.setStyle("color: #000000!important; font-size: 13px");
		lblApellidos.setValue(listaPxP.getParticipanteXProyectoDtoId().getParticipante().getApellidos());
		row.appendChild(lblApellidos);
		
		final Button btnEliminar = new Button();
		btnEliminar.setId(etiquetaBtnEliminarP + listaPxP.getParticipanteXProyectoDtoId().getParticipante().getIdn());
		btnEliminar.addEventListener(Events.ON_CLICK, actionListenerEliminar);	
		btnEliminar.setTooltip("mAnexar");
		btnEliminar.setLabel(Labels.getLabel("btnEliminar"));
		btnEliminar.setVisible(true);
		row.appendChild(btnEliminar);
		
		return row;
	}

	public void gridAsesores(int idProyecto) {
		Rows rows = gridAsesores.getRows();
		ArrayList<AsesorXProyectoDto> asesores = asesorXProyectoNgc.buscarAsesorXProyecto(idProyecto);
		for(AsesorXProyectoDto listaAxP:asesores){
			rows.appendChild(construirFilaAsesores(listaAxP));
			System.out.println("asesores: "+listaAxP.getAsesorXProyectoDtoId().getAsesor().getNombres()+" "+listaAxP.getAsesorXProyectoDtoId().getAsesor().getApellidos());
		}
	}

	public Row construirFilaAsesores(AsesorXProyectoDto listaAxP) {
		final Row row = new Row();
		row.setId(etiquetaRowA + listaAxP.getAsesorXProyectoDtoId().getAsesor().getIdn());
		
		Label lblTipoId = new Label();
		lblTipoId.setId(etiquetaTipoIdA + listaAxP.getAsesorXProyectoDtoId().getAsesor().getIdn());
		lblTipoId.setStyle("color: #000000!important; font-size: 13px");
		lblTipoId.setValue(listaAxP.getAsesorXProyectoDtoId().getAsesor().getTipoDocumento().getNombre());
		row.appendChild(lblTipoId);
		
		Label lblId = new Label();
		lblId.setId(etiquetaIdA + listaAxP.getAsesorXProyectoDtoId().getAsesor().getIdn());
		lblId.setStyle("color: #000000!important; font-size: 13px");
		lblId.setValue(listaAxP.getAsesorXProyectoDtoId().getAsesor().getIdentificacion().toString());
		row.appendChild(lblId);
		
		Label lblnombres = new Label();
		lblnombres.setId(etiquetaNombresA + listaAxP.getAsesorXProyectoDtoId().getAsesor().getIdn());
		lblnombres.setStyle("color: #000000!important; font-size: 13px");
		lblnombres.setValue(listaAxP.getAsesorXProyectoDtoId().getAsesor().getNombres());
		row.appendChild(lblnombres);
		
		Label lblApellidos = new Label();
		lblApellidos.setId(etiquetaApellidosA + listaAxP.getAsesorXProyectoDtoId().getAsesor().getIdn());
		lblApellidos.setStyle("color: #000000!important; font-size: 13px");
		lblApellidos.setValue(listaAxP.getAsesorXProyectoDtoId().getAsesor().getApellidos());
		row.appendChild(lblApellidos);
		
		final Button btnEliminar = new Button();
		btnEliminar.setId(etiquetaBtnEliminarA + listaAxP.getAsesorXProyectoDtoId().getAsesor().getIdn());
		btnEliminar.addEventListener(Events.ON_CLICK, actionListenerEliminar);	
		btnEliminar.setTooltip("mAnexar");
		btnEliminar.setLabel(Labels.getLabel("btnEliminar"));
		btnEliminar.setVisible(true);
		row.appendChild(btnEliminar);
		row.setAttribute("AsesorXProyectoDto", listaAxP);
		return row;
	}

	public void limpiarRows(Grid grid) {
		if( grid != null){
			grid.removeChild(grid.getRows());
			grid.appendChild(new Rows());
		}
	}
	
	EventListener<Event> actionListenerEliminar = new SerializableEventListener<Event>() {
		public void onEvent(Event event) throws Exception {	
			Row filaAEliminar = ((Row) ((Button)event.getTarget()).getParent());
			if( (filaAEliminar != null) ){
				filaAEliminar.setParent(null);
			}
		}
	};	
	
	public void onClick$btnAgregarAsesores(Event ev) throws IOException{
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
	
	public void onClick$buscarAsesores(Event ev) {
		limpiarRows(gridBusquedaAsesores);
		Comboitem comboItem=cbxTipoIDAsesores.getSelectedItem();
		String tipoId=obtenerTipoIdDeComboitem(comboItem);
		String id=tbxIDAsesores.getText();
		String nombres=tbxNombresAsesores.getText();
		String apellidos=tbxApellidosAsesores.getText();
		ArrayList<UsuarioDto> listaUsuarios=buscarUsuariosPorFiltros(tipoId, id, nombres, apellidos);
		if( (listaUsuarios!=null) && !(listaUsuarios.isEmpty()) ){
			 Rows rows = gridBusquedaAsesores.getRows(); 
			 for(UsuarioDto usuario: listaUsuarios){
				 if( usuario.getIdn().compareTo(idResponsableDelProyecto) != 0 ){
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
		String tipoId=obtenerTipoIdDeComboitem(comboItem);		
		String id=tbxIDParticipantes.getText();
		String nombres=tbxNombresParticipantes.getText();
		String apellidos=tbxApellidosParticipantes.getText();
		ArrayList<UsuarioDto> listaUsuarios= buscarUsuariosPorFiltros(tipoId, id, nombres, apellidos);
		if( (listaUsuarios!=null) && !(listaUsuarios.isEmpty()) ){
			 Rows rows = gridBusquedaParticipantes.getRows(); 
			 for(UsuarioDto usuario: listaUsuarios){
				 if( usuario.getIdn().compareTo(idResponsableDelProyecto) != 0 ){
					 rows.appendChild(construirFilaAnexarUsuariosParticipantes(usuario) );
				 }
			 }		       
		}		
	}	
	
	public void onClick$limpiarParticipantes(Event ev) {	
		limpiarRows(gridBusquedaParticipantes);
	}
	
	public String obtenerTipoIdDeComboitem(Comboitem comboItem){
		String tipoId=null;
		if( comboItem!=null ){
			tipoId= comboItem.getId();
			if( (tipoId!=null) && (tipoId.length()>=6) ){ 
				tipoId=tipoId.substring(6);
			}
		}
		return tipoId;		
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
					if( (gridAsesores.hasFellow(etiquetaRowA+integrante.getIdn()))
							|| (gridParticipantes.hasFellow(etiquetaRowP+integrante.getIdn())) ){	
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
		row.setId(etiquetaRowA+usuario.getIdn());
		Label lblTid= new Label();
		lblTid.setId(etiquetaTipoIdA+usuario.getIdn());
		lblTid.setStyle("color: #000000!important; font-size: 13px");
		lblTid.setValue(usuario.getTipoDocumento().getNombre());
		row.appendChild(lblTid);
		Label lblId= new Label();
		lblId.setId(etiquetaIdA+usuario.getIdn());
		lblId.setStyle("color: #000000!important; font-size: 13px");
		lblId.setValue(usuario.getIdentificacion().toString());
		row.appendChild(lblId);
		Label lblNombres= new Label();
		lblNombres.setId(etiquetaNombresA+usuario.getIdn());
		lblNombres.setStyle("color: #000000!important; font-size: 13px");
		lblNombres.setValue(usuario.getNombres());
		row.appendChild(lblNombres);
		Label lblApellidos= new Label();
		lblApellidos.setId(etiquetaApellidosA+usuario.getIdn());
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
		btnEliminarAsesores.setId(etiquetaBtnEliminarA+usuario.getIdn());
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
					if( (gridAsesores.hasFellow(etiquetaRowA+integrante.getIdn()))
							|| (gridParticipantes.hasFellow(etiquetaRowP+integrante.getIdn())) ){	
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
		row.setId(etiquetaRowP+usuario.getIdn());
		Label lblTid= new Label();
		lblTid.setId(etiquetaTipoIdP+usuario.getIdn());
		lblTid.setStyle("color: #000000!important; font-size: 13px");
		lblTid.setValue(usuario.getTipoDocumento().getNombre());
		row.appendChild(lblTid);
		Label lblId= new Label();
		lblId.setId(etiquetaIdP+usuario.getIdn());
		lblId.setStyle("color: #000000!important; font-size: 13px");
		lblId.setValue(usuario.getIdentificacion().toString());
		row.appendChild(lblId);
		Label lblNombres= new Label();
		lblNombres.setId(etiquetaNombresP+usuario.getIdn());
		lblNombres.setStyle("color: #000000!important; font-size: 13px");
		lblNombres.setValue(usuario.getNombres());
		row.appendChild(lblNombres);
		Label lblApellidos= new Label();
		lblApellidos.setId(etiquetaApellidosP+usuario.getIdn());
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
		btnEliminarParticipantes.setId(etiquetaBtnEliminarP+usuario.getIdn());
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
	
} 
