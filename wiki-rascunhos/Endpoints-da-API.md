# Endpoints da API

Base URL local: http://localhost:8080

## Rotas disponíveis

| Método | Rota | Descrição |
| --- | --- | --- |
| POST | /api/transacoes/comando-voz | Processa um comando de voz e retorna a transcrição, a resposta textual e o áudio em Base64. |
| GET | /api/transacoes | Lista as transações persistidas. |

## Processar comando por voz

### POST /api/transacoes/comando-voz

Envie o áudio como multipart/form-data.

| Parâmetro | Local | Tipo | Obrigatório | Descrição |
| --- | --- | --- | --- | --- |
| file | Form-data | Arquivo | Sim | Arquivo de áudio a ser transcrito. |

Exemplo:

~~~bash
curl --request POST "http://localhost:8080/api/transacoes/comando-voz" \
  --form "file=@./comando.mp3"
~~~

Resposta de sucesso:

~~~json
{
  "textoTranscrito": "Registre uma despesa de 25 reais com transporte",
  "respostaTexto": "Transação registrada com sucesso.",
  "audioBase64": "..."
}
~~~

| Campo de resposta | Tipo | Descrição |
| --- | --- | --- |
| textoTranscrito | string | Texto retornado pelo serviço de transcrição. |
| respostaTexto | string | Resposta produzida após o processamento do comando. |
| audioBase64 | string | Áudio da resposta codificado em Base64. |

## Listar transações

### GET /api/transacoes

Não recebe parâmetros. A rota retorna o resultado da listagem de todas as transações armazenadas.

~~~bash
curl "http://localhost:8080/api/transacoes"
~~~

Cada transação possui os campos id, descricao, valor, tipo, categoria e dataCriacao. O tipo é RECEITA ou DESPESA.
