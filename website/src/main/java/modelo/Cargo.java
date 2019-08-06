package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe Cargo
 * 
 * Container utilizado para acessar a base de dados via Hibernate. Relacionado à tabela Cargo.
 * 
 * @author Fabio Moreira
 * @version 1.0
 */
@Entity
@Table(name = "cargo")
public class Cargo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** Quantidade de cargos cadastrados no sistema. */
	public static int nCargo = 0;
	
	@Id
	@Column(name = "id", unique=true)
	private int id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "id_eleicao", nullable = false)
	private int id_eleicao;
	
	public Cargo() {
		
	}
	
	public Cargo(String nome, int id_eleicao) {
		this.id = ++Cargo.nCargo;
		this.nome = nome;
		this.id_eleicao = id_eleicao;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int getIdEleicao() {
		return this.id_eleicao;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setIdEleicao(int id_eleicao) {
		this.id_eleicao = id_eleicao;
	}
}
