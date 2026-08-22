package dio.orcamento.repository;

import dio.orcamento.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t WHERE t.tipo = 'RECEITA'")
    BigDecimal totalReceitas();

    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t WHERE t.tipo = 'DESPESA'")
    BigDecimal totalDespesas();
}