# Detalhes Técnicos

## Fluxo do comando de voz

1. O TransacaoController recebe o arquivo no endpoint POST /api/transacoes/comando-voz.
2. O AudioService envia os bytes do arquivo ao OpenAiAudioTranscriptionModel e obtém a transcrição.
3. O ChatClient recebe o texto transcrito com a instrução de responder em português brasileiro.
4. O Spring AI disponibiliza as funções registrarTransacao e consultarSaldo ao modelo.
5. Quando necessário, as ferramentas acionam o TransacaoService, que persiste ou consulta dados por meio do TransacaoRepository.
6. O AudioService usa o OpenAiAudioSpeechModel para sintetizar a resposta.
7. A API retorna um RespostaAudioDTO com transcrição, texto e áudio em Base64.

## Organização das responsabilidades

| Componente | Responsabilidade |
| --- | --- |
| TransacaoController | Expõe as rotas HTTP de voz e listagem de transações. |
| AudioService | Integra a transcrição e a síntese de fala da OpenAI. |
| SpringAiConfig | Configura o ChatClient, a instrução de sistema e as funções disponíveis ao modelo. |
| TransacaoTools | Declara as funções registrarTransacao e consultarSaldo para Tool Calling. |
| TransacaoService | Salva transações, lista registros e monta o resumo de saldo. |
| TransacaoRepository | Usa Spring Data JPA e calcula totais de receitas e despesas. |

## Operações financeiras

A função registrarTransacao recebe descricao, valor, tipo e categoria por meio de NovaTransacaoDTO. O tipo de transação é representado pelo enum TipoTransacao, com os valores RECEITA e DESPESA.

A função consultarSaldo usa as consultas totalReceitas e totalDespesas do repositório. O saldo é calculado subtraindo as despesas do total de receitas.

## Persistência

A entidade Transacao é mapeada para tb_transacoes. Ela possui identificador gerado, descrição, valor, tipo, categoria e data de criação. A data de criação é preenchida antes da persistência.
