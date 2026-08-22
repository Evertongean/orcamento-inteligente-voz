package dio.orcamento.dto;

import dio.orcamento.model.TipoTransacao;
import java.math.BigDecimal;

public record NovaTransacaoDTO(
        String descricao,
        BigDecimal valor,
        TipoTransacao tipo,
        String categoria
) {}