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

public class InserirClasse {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Eleicoes");
	
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
