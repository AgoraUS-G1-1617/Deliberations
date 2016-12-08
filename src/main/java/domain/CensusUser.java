package domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class CensusUser {

	// Constructors ------------------------------------------------------------

	public CensusUser() {
		super();
	}

	// Attributes -------------------------------------------------------------

	int idVotacion;
	String username;
	boolean result;
	int version;
	int id;
	String tituloVotacion;
	Date fechaInicioVotacion;
	Date fechaFinVotacion;
	String nombre_votacion;
	int votacion_id;
	Map<String, Boolean> voto_por_usuario;
	Date fecha_inicio;
	Date fecha_fin;

	@NotBlank
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotBlank
	public String getTituloVotacion() {
		return tituloVotacion;
	}

	public void setTituloVotacion(String tituloVotacion) {
		this.tituloVotacion = tituloVotacion;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFechaInicioVotacion() {
		return fechaInicioVotacion;
	}

	public void setFechaInicioVotacion(Date fechaInicioVotacion) {
		this.fechaInicioVotacion = fechaInicioVotacion;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFechaFinVotacion() {
		return fechaFinVotacion;
	}

	public void setFechaFinVotacion(Date fechaFinVotacion) {
		this.fechaFinVotacion = fechaFinVotacion;
	}

	@NotNull
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@NotBlank
	public String getNombre_votacion() {
		return nombre_votacion;
	}

	public void setNombre_votacion(String nombre_votacion) {
		this.nombre_votacion = nombre_votacion;
	}

	@NotNull
	public int getVotacion_id() {
		return votacion_id;
	}

	public void setVotacion_id(int votacion_id) {
		this.votacion_id = votacion_id;
	}

	public Map<String, Boolean> getVoto_por_usuario() {
		return voto_por_usuario;
	}

	public void setVoto_por_usuario(Map<String, Boolean> voto_por_usuario) {
		this.voto_por_usuario = voto_por_usuario;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	@NotNull
	public int getIdVotacion() {
		return idVotacion;
	}

	public void setIdVotacion(int idVotacion) {
		this.idVotacion = idVotacion;
	}

	@NotBlank
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	// Relationships ----------------------------------------------------------

}
