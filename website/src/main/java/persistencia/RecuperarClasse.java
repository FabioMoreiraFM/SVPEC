package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Candidato;
import modelo.Cargo;
import modelo.Eleicao;

/**
 * Contém os métodos que recuperam tabelas inteiras (todas as colunas) da base de dados.
 * O Hibernate faz o mapeamento dessas informações com as classes existentes no pacote modelo.
 * 
 * @author Fabio Moreira
 * @version 1.0
 */
public class RecuperarClasse {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Eleicoes");

	/**
	 * Recupera um conjunto de eleições da base de dados.
	 * 
	 * @return Lista de Eleições.
	 */
	public static List<Eleicao> recuperaEleicoes() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT e FROM Eleicao e WHERE e.id IS NOT NULL AND STR_TO_DATE(dataFim, '%Y-%m-%d') > CURDATE() AND STR_TO_DATE(dataInicio, '%Y-%m-%d') < CURDATE()";
		
		TypedQuery<Eleicao> tq = em.createQuery(query, Eleicao.class);
		
		List<Eleicao> eleicoes = null;
		
		try {
			eleicoes = tq.getResultList();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return eleicoes;
	}
	
	/**
	 * Recupera um conjunto de candidatos da base de dados.
	 * 
	 * @return Lista de Eleições.
	 */
	public static List<Candidato> recuperaCandidatos(int idEleicaoEscolhida) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Candidato c WHERE c.id IS NOT NULL AND c.id_cargo IN (SELECT c.id FROM Cargo c WHERE c.id IS NOT NULL AND c.id_eleicao = :id_eleicao)";
		
		TypedQuery<Candidato> tq = em.createQuery(query, Candidato.class);
		tq.setParameter("id_eleicao", idEleicaoEscolhida);
		List<Candidato> candidatos = null;
		
		try {
			candidatos = tq.getResultList();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return candidatos;
	}
	
	/**
	 * Recupera um conjunto de cargos da base de dados.
	 * 
	 * @return Lista de Eleições.
	 */
	public static List<Cargo> recuperaCargos(int idEleicaoEscolhida) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Cargo c WHERE c.id IS NOT NULL AND c.id_eleicao = :id_eleicao";
		
		TypedQuery<Cargo> tq = em.createQuery(query, Cargo.class);
		tq.setParameter("id_eleicao", idEleicaoEscolhida);
		List<Cargo> cargos = null;
		
		try {
			cargos = tq.getResultList();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return cargos;
	}
		
}
