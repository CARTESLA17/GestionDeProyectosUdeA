package com.udea.proyint.ModuloGestionDeProyectos.ctl;

import java.util.ArrayList;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

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
	
	//private AsesorXProyectoDto asesor = new AsesorXProyectoDto();
	
	private Label lblNombreResponsable;
	private Textbox tbxNombreProyecto;
	private Combobox cbxTipoProyecto;
	private Combobox cbxModalidad;
	private Grid gridAsesores;
	private Grid gridParticipantes;
	private Datebox dateFechaInicial;
	private Datebox dateFechaFinal;
	private Textbox tbxObjetivoGeneral;
	private Grid gridObjetivosEspecificos;
	
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
	
	
//	private Array
	
	
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
			System.out.println("entra al if de sesion");
			cargarCbxTiposDeProyecto();
			cargarCbxModalidades();
			llenarCampos();
		}else{
			
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
		System.out.println("entra a llenar campos");
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
		System.out.println("trajo la info");
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
		System.out.println("trajo la info");
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
		//btnEliminar.addEventListener(Events.ON_CLICK, actionListenerActividades);	
		btnEliminar.setTooltip("mAnexar");
		btnEliminar.setLabel(Labels.getLabel("btnEliminar"));
		btnEliminar.setVisible(true);
		row.appendChild(btnEliminar);
		
		return row;
	}

	public void gridAsesores(int idProyecto) {
		Rows rows = gridAsesores.getRows();
		ArrayList<AsesorXProyectoDto> asesores = asesorXProyectoNgc.buscarAsesorXProyecto(idProyecto);
		System.out.println("trajo la info");
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
		//btnEliminar.addEventListener(Events.ON_CLICK, actionListenerActividades);	
		btnEliminar.setTooltip("mAnexar");
		btnEliminar.setLabel(Labels.getLabel("btnEliminar"));
		btnEliminar.setVisible(true);
		row.appendChild(btnEliminar);
		
		return row;
	}

	public void limpiarRows(Grid grid) {
		if( grid != null){
			grid.removeChild(grid.getRows());
			grid.appendChild(new Rows());
		}
	}
} 
