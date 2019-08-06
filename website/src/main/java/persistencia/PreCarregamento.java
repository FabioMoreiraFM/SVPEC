package persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.Candidato;
import modelo.Cargo;
import modelo.Eleicao;
import modelo.Eleitor;
import modelo.Voto;
import utils.Pair;

/**
 * Contém métodos que inicializam o estado do sistema.
 * 
 * @author Fabio Moreira
 * @version 1.0
 */
public class PreCarregamento {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Eleicoes");
	
	/**
	 * Inicializa as variáveis relacionadas à contagem de objetos. 
	 * 
	 */
	public static void inicializacao() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		String qeleicao = "SELECT MAX(e.id) FROM Eleicao e";
		String qcargo = "SELECT MAX(c.id) FROM Cargo c";
		String qcandidato = "SELECT MAX(c.id) FROM Candidato c";
		String qeleitor = "SELECT MAX(c.id) FROM Eleitor c";
		String qvoto = "SELECT MAX(c.id) FROM Voto c";
		
		Query eleicao = em.createQuery(qeleicao);
		Query cargo = em.createQuery(qcargo);
		Query candidato = em.createQuery(qcandidato);
		Query eleitor = em.createQuery(qeleitor);
		Query voto = em.createQuery(qvoto);
		
		try {
			Eleicao.nEleicao = (int) eleicao.getSingleResult();
			Cargo.nCargo = (int) cargo.getSingleResult();
			Candidato.nCandidato = (int) candidato.getSingleResult();
			Eleitor.nEleitor = (int) eleitor.getSingleResult();
			Voto.nVoto = (int) voto.getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			// Acontece quando a base está zerada
		} finally {
			em.close();
		}
		
	}
	
	/**
	 * Filtra as eleições de acordo com a data atual.
	 * 
	 * Eleições com data de fim anteriores à data atual estão finalizadas.
	 * Eleições com data de fim posterior à data atual estão em andamento. 
	 * Eleições com data de início posterior à data atual não são contabilizadas, pois essas eleições ainda não iniciaram.
	 * 
	 * @return Par de listas. A primeira contém as eleições em andamento, a segunda contém as eleições encerradas.
	 */
	public static Pair<List<Eleicao>, List<Eleicao>> separaEleicoesPorStatus() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		String query = "select e from Eleicao e";
		
		TypedQuery<Eleicao> tq = em.createQuery(query, Eleicao.class);
		
		List<Eleicao> eleicoes = null;
		
		try {
			eleicoes = tq.getResultList();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		List<Eleicao> eleicoesAndamento = new ArrayList<Eleicao>();
		List<Eleicao> eleicoesFinalizadas = new ArrayList<Eleicao>();
		
		Date dataAtual = new Date();
		for (Eleicao eleicao : eleicoes) {
			Date dataInicio = null;
			Date dataFim = null;
			
			try {
				dataInicio = new SimpleDateFormat("yyyy-MM-dd").parse(eleicao.getDataInicio());
				dataFim = new SimpleDateFormat("yyyy-MM-dd").parse(eleicao.getDataFim());
			} catch (ParseException ex) {
				ex.printStackTrace();
			}			
						
			if (dataFim.after(dataAtual)) {
				if (dataInicio.before(dataAtual)) {
					eleicoesAndamento.add(eleicao);
				}
			} else {
				eleicoesFinalizadas.add(eleicao);
			}
		}
		
		Pair<List<Eleicao>, List<Eleicao>> resultado = new Pair<List<Eleicao>, List<Eleicao>>(eleicoesAndamento, eleicoesFinalizadas);
		
		return resultado;
	}
}
