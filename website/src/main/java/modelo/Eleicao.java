package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe Eleicao
 * 
 * Container utilizado para acessar a base de dados via Hibernate. Relacionado à tabela Eleicao.
 * 
 * @author Fabio Moreira
 * @version 1.0
 */
@Entity
@Table(name = "eleicao")
public class Eleicao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** Quantidade de eleições cadastradas no sistema. */
	public static int nEleicao = 0;
	
	@Id
	@Column(name = "id", unique = true)
	private int id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "dataInicio", nullable = false)
	private String dataInicio;
	
	@Column(name = "dataFim", nullable = false)
	private String dataFim;
	
	public Eleicao() {
		
	}
	
	public Eleicao(String nome, String dataInicio, String dataFim) {
		this.id = ++Eleicao.nEleicao;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDataInicio() {
		return this.dataInicio;
	}
	
	public String getDataFim() {
		return this.dataFim;
	}

	
}
