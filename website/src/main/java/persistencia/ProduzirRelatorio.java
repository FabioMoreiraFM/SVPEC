package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Relatorio;

public class ProduzirRelatorio {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Eleicoes");
	
	@SuppressWarnings("unchecked")
	public static Relatorio carregarRelatorioFinal(String idEleicao) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		String candidatosPontuaram = "select ca.nome as nomeCargo, c.nome as nomeCandidato, COUNT(v.id_candidato) as qtdVotos From Voto v, Candidato c, Cargo ca WHERE v.id_candidato = c.id AND v.id_cargo=ca.id AND v.id_eleicao=:id_eleicao GROUP BY id_candidato ORDER BY ca.nome";
		Query candPontuaram = em.createNativeQuery(candidatosPontuaram);
		candPontuaram.setParameter("id_eleicao", idEleicao);
		
		String candidatosZerados = "select ca.nome as cargo, c.nome as candidato from cargo ca, candidato c where ca.id_eleicao=:id_eleicao and c.id_cargo=ca.id and c.id NOT IN (select id_candidato from Voto);";
		Query candZerados = em.createNativeQuery(candidatosZerados);
		candZerados.setParameter("id_eleicao", idEleicao);
				
		try {
			List<Object[]> pontuaram = candPontuaram.getResultList();
			List<Object[]> zeraram = candZerados.getResultList();
			
			Relatorio relatorioFinal = new Relatorio(pontuaram, zeraram);
			
			return relatorioFinal;
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			// Acontece quando a base está zerada
		} finally {
			em.close();
		}
		
		return null;
	}

	public static int carregarRelatorioParcial(String idEleicao) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		String qeleicao = "select COUNT(id) from Eleitor where id_eleicao = :id_eleicao";
		Query eleicao = em.createQuery(qeleicao);
		eleicao.setParameter("id_eleicao", idEleicao);
		
		try {
			return ((Long) eleicao.getSingleResult()).intValue();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			// Acontece quando a base está zerada
		} finally {
			em.close();
		}
		
		return -1;
	}
}
