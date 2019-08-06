package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe Eleitor
 * 
 * Container utilizado para acessar a base de dados via Hibernate. Relacionado à tabela Eleitor.
 * 
 * @author Fabio Moreira
 * @version 1.0
 */
@Entity
@Table(name = "eleitor")
public class Eleitor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** Quantidade de eleitores cadastrados no sistema. */
	public static int nEleitor = 0;

	@Id
	@Column(name = "id", unique = true)
	private int id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "cpf", nullable = false)
	private String cpf;
	
	@Column(name = "protocolo", nullable = false)
	private String protocolo;
	
	@Column(name = "id_eleicao", nullable = false)
	private int idEleicaoEscolhida;
	
	public Eleitor() {
		
	}
	
	public Eleitor(String nome, String cpf, String protocolo, int idEleicaoEscolhida) {
		this.id = ++nEleitor;
		this.nome = nome;
		this.cpf = cpf;
		this.protocolo = protocolo;
		this.idEleicaoEscolhida = idEleicaoEscolhida;
	}

}
