# Orçamento Inteligente por Voz

Documentação técnica da API REST que gerencia finanças pessoais a partir de comandos de áudio. A aplicação transcreve a fala, interpreta a intenção, executa operações financeiras e devolve uma resposta textual e em áudio.

## Visão geral

O fluxo principal recebe um áudio no endpoint de transações, usa o OpenAI Whisper para obter o texto, envia a transcrição ao GPT-4o-mini por meio do Spring AI e disponibiliza as ferramentas de registro e consulta de saldo. A resposta gerada é sintetizada com OpenAI TTS e retornada junto da transcrição.

As transações são mantidas em um banco H2 em memória durante a execução da aplicação.

## Índice

| Documento | Conteúdo |
| --- | --- |
| [Como Executar](Como-Executar) | Pré-requisitos, instalação e inicialização da API. |
| [Configuração](Configuracao) | Variável de ambiente da OpenAI e propriedades da aplicação. |
| [Endpoints da API](Endpoints-da-API) | Rotas disponíveis, parâmetro multipart e exemplos de resposta. |
| [Detalhes Técnicos](Detalhes-Tecnicos) | Fluxo interno, camadas e Tool Calling. |
| [Melhorias Futuras](Melhorias-Futuras) | Evoluções ainda não implementadas. |

## Repositório

O código-fonte e a visão de arquitetura estão disponíveis no [repositório do projeto](https://github.com/Evertongean/orcamento-inteligente-voz).

Desenvolvido por [Everton Gean](https://github.com/Evertongean).
