package dio.orcamento.service;

import dio.orcamento.dto.NovaTransacaoDTO;
import dio.orcamento.model.Transacao;
import dio.orcamento.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository repository;

    public Transacao salvar(NovaTransacaoDTO dto) {
        Transacao transacao = Transacao.builder()
                .descricao(dto.descricao())
                .valor(dto.valor())
                .tipo(dto.tipo())
                .categoria(dto.categoria())
                .build();
        return repository.save(transacao);
    }

    public List<Transacao> listarTodas() {
        return repository.findAll();
    }

    public String obterResumoSaldo() {
        BigDecimal receitas = repository.totalReceitas();
        BigDecimal despesas = repository.totalDespesas();
        BigDecimal saldo = receitas.subtract(despesas);

        return String.format("Resumo Financeiro Atual: Total Receitas: R$ %.2f, Total Despesas: R$ %.2f, Saldo Atual: R$ %.2f",
                receitas, despesas, saldo);
    }
}