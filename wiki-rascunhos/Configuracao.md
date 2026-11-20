# Configuração

As propriedades da aplicação estão em src/main/resources/application.yml.

## Chave da OpenAI

A chave não deve ser gravada no repositório. Defina-a no ambiente com o nome abaixo, que corresponde à propriedade spring.ai.openai.api-key:

~~~bash
export SPRING_AI_OPENAI_API_KEY="sua_chave_da_openai"
~~~

No PowerShell:

~~~powershell
$env:SPRING_AI_OPENAI_API_KEY="sua_chave_da_openai"
~~~

Essa variável substitui o valor configurado para a chave quando a aplicação é iniciada.

## Propriedades configuradas

| Propriedade | Valor atual | Finalidade |
| --- | --- | --- |
| server.port | 8080 | Porta HTTP da aplicação. |
| spring.datasource.url | jdbc:h2:mem:orcamentodb | Banco H2 em memória. |
| spring.datasource.username | sa | Usuário configurado para o H2. |
| spring.h2.console.enabled | true | Habilita o console administrativo do H2. |
| spring.h2.console.path | /h2-console | Caminho do console H2. |
| spring.jpa.hibernate.ddl-auto | update | Atualiza o esquema JPA durante a execução. |
| spring.ai.openai.model | gpt-4o-mini | Modelo indicado para o processamento do comando. |
| spring.ai.openai.audio.transcription.model | whisper-1 | Modelo de transcrição de áudio. |
| spring.ai.openai.audio.speech.model | tts-1 | Modelo de síntese de fala. |
| spring.ai.openai.audio.speech.voice | alloy | Voz utilizada na síntese. |

## Persistência

O H2 usa a URL jdbc:h2:mem:orcamentodb, portanto os registros existem apenas enquanto a aplicação está em execução. A entidade Transacao é mapeada para a tabela tb_transacoes.
