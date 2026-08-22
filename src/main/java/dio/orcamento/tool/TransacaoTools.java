package dio.orcamento.tool;

import dio.orcamento.dto.NovaTransacaoDTO;
import dio.orcamento.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class TransacaoTools {

    private final TransacaoService transacaoService;

    @Bean
    @Description("Registra uma nova receita ou despesa no sistema de orçamento do usuário.")
    public Function<NovaTransacaoDTO, String> registrarTransacao() {
        return dto -> {
            var salva = transacaoService.salvar(dto);
            return String.format("Transação de %s no valor de R$ %.2f com descrição '%s' foi registrada com sucesso sob ID %d.",
                    salva.getTipo(), salva.getValor(), salva.getDescricao(), salva.getId());
        };
    }

    @Bean
    @Description("Consulta o resumo financeiro com o total de receitas, total de despesas e o saldo atual.")
    public Function<Void, String> consultarSaldo() {
        return unused -> transacaoService.obterResumoSaldo();
    }
}