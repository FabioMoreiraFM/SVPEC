package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import modelo.Candidato;
import modelo.Cargo;
import modelo.Eleicao;
import modelo.Eleitor;
import modelo.Voto;

/**
 * Classe InserirClasse
 * 
 * Contém os métodos que inserem informações na base de dados.  
 * 
 * @author Fabio Moreira
 * @version 1.0
 */
public class InserirClasse {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Eleicoes");
	
	/**
	 * Insere os votos de um eleitor na base de dados. 
	 * 
	 * @param cargos Lista de cargos relacionados à eleição em andamento.
	 * @param idCandidatos Lista com os ids de candidatos que receberam votos.
	 * @param eleicaoEscolhida Id da eleição em andamento.
	 */
	public static void insereNovoVoto(List<Cargo> cargos, String[] idCandidatos, int eleicaoEscolhida) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			for (int i = 0; i < idCandidatos.length; i++) {
				et = em.getTransaction();
				et.begin();
				
				Voto voto = new Voto(eleicaoEscolhida, cargos.get(i).getId(), Integer.parseInt(idCandidatos[i]));
				
				em.persist(voto);
				et.commit();
			}			
		} catch(Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}	
	}
	
	/**
	 * Insere um novo eleitor na base de dados.
	 * 
	 * @param nome Nome do eleitor.
	 * @param cpf CPF do eleitor.
	 * @param protocolo Protocolo alfanumérico de 16 dígitos gerado após o eleitor finalizar a votação.
	 * @param idEleicaoEscolhida Id da eleição em andamento.
	 */
	public static void insereEleitor(String nome, String cpf, String protocolo, int idEleicaoEscolhida) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			
			Eleitor cust = new Eleitor(nome, cpf, protocolo, idEleicaoEscolhida);
			
			em.persist(cust);
			et.commit();
		} catch(Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	/**
	 * Insere um novo candidato na base de dados.
	 * 
	 * @param nome Nome do candidato.
	 * @param nomeFoto Nome do arquivo que contém a foto do candidato.  
	 * @param idCargo Id do cargo a ser disputado.
	 */
	public static void insereNovoCandidato(String nome, String nomeFoto, int idCargo) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			
			Candidato cust = new Candidato(nome, nomeFoto, idCargo);
			
			em.persist(cust);
			et.commit();
		} catch(Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	/**
	 * Insere um novo cargo na base de dados.
	 * 	
	 * @param nome Nome do cargo.
	 */
	public static void insereNovoCargo(String nome) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			
			Cargo cust = new Cargo(nome, Eleicao.nEleicao);
			
			em.persist(cust);
			et.commit();
		} catch(Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	/**
	 * Insere uma nova eleição na base de dados.
	 * 
	 * @param nome Nome da eleição.
	 * @param dataInicio Data de início da eleição.
	 * @param dataFim Data de fim da eleição.
	 */
	public static void insereNovaEleicao(String nome, String dataInicio, String dataFim) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			
			Eleicao cust = new Eleicao(nome, dataInicio, dataFim);
			
			em.persist(cust);
			et.commit();
		} catch(Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
}
