package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "voto")
public class Voto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static int nVoto = 0;

	@Id
	@Column(name = "id", unique=true)
	private int id;
	
	@Column(name = "id_eleicao", nullable=false)
	private int idEleicao;
	
	@Column(name = "id_cargo", nullable=false)
	private int idCargo;
	
	@Column(name = "id_candidato", nullable=false)
	private int idCandidato;

	public Voto() {
		
	}
	
	public Voto(int idEleicao, int idCargo, int idCandidato) {
		this.id = ++nVoto;
		this.idEleicao = idEleicao;
		this.idCargo = idCargo;
		this.idCandidato = idCandidato;
	}

}
