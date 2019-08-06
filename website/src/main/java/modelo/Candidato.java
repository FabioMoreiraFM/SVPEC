package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe Candidato
 * 
 * Container utilizado para acessar a base de dados via Hibernate. Relacionado à tabela Candidato.
 * 
 * @author Fabio Moreira
 * @version 1.0
 */
@Entity
@Table(name = "candidato")
public class Candidato implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** Quantidade de candidatos cadastrados no sistema. */
	public static int nCandidato = 0; 

	@Id
	@Column(name = "id", unique = true)
	private int id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "nomeFoto", nullable = false)
	private String nomeFoto;
	
	@Column(name = "id_cargo", nullable = false)
	private int id_cargo;
	
	public Candidato() {
		
	}
	
	public Candidato(String nome, String nomeFoto, int id_cargo) {
		this.id = ++Candidato.nCandidato;
		this.nome = nome;
		this.nomeFoto = nomeFoto;
		this.id_cargo = id_cargo;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNomeFoto(String nomeFoto) {
		this.nomeFoto = nomeFoto;
	}
	
	public void setIdCargo(int id_cargo) {
		this.id_cargo = id_cargo;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getNomeFoto() {
		return this.nomeFoto;
	}
	
	public int getId_cargo() {
		return this.id_cargo;
	}
}
