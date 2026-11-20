<div align="center">

# 🎙️ Orçamento Inteligente por Voz

**API REST para registrar e consultar finanças pessoais a partir de comandos de voz, usando Spring AI e OpenAI.**

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring%20AI-1.0.0--M2-6DB33F?style=for-the-badge&logo=spring&logoColor=white)

</div>

## 📖 Sobre o Projeto

O **Orçamento Inteligente por Voz** é uma API REST que transforma comandos de áudio em operações de finanças pessoais. A aplicação transcreve o áudio com o **OpenAI Whisper**, interpreta a intenção do usuário com o **GPT-4o-mini** e utiliza **Tool Calling** para registrar receitas, despesas ou consultar o saldo.

Após processar o comando, a API devolve a transcrição, uma resposta em texto e o áudio sintetizado pelo **OpenAI TTS**, codificado em Base64. As transações são persistidas em um banco **H2** em memória.

## 🚀 Tecnologias Utilizadas

| Tecnologia | Finalidade |
| --- | --- |
| Java 17 | Linguagem e versão configurada no projeto. |
| Spring Boot 3.3.4 | Base da API REST e da configuração da aplicação. |
| Spring AI 1.0.0-M2 | Integração com os modelos da OpenAI e Tool Calling. |
| OpenAI Whisper (whisper-1) | Transcrição de áudio para texto (Speech-to-Text). |
| GPT-4o-mini | Interpretação de comandos em linguagem natural e escolha das ferramentas. |
| OpenAI TTS (tts-1) | Síntese da resposta textual em áudio. |
| Spring Data JPA | Persistência e consultas das transações. |
| H2 Database | Banco de dados em memória para os dados financeiros. |
| Lombok 1.18.34 | Redução de código repetitivo nas classes Java. |

> O pom.xml atual define Java 17. Por isso, o README não declara Java 21, embora essa seja uma referência presente na arte de arquitetura.

## 🏗️ Arquitetura e Fluxo de Dados

![Arquitetura e Fluxo de Dados do Orçamento Inteligente por Voz](docs/images/arquitetura-fluxo.png)

O endpoint recebe um arquivo de áudio e o encaminha ao serviço de transcrição. O texto resultante é enviado ao ChatClient, que pode acionar as ferramentas de registro ou consulta de saldo. Essas ferramentas utilizam os serviços e o H2 para acessar as transações; por fim, a resposta textual é convertida em áudio e retornada em JSON.

## ✨ Funcionalidades

- Registro de receitas por comando de voz.
- Registro de despesas por comando de voz.
- Consulta do resumo de saldo, receitas e despesas.
- Interpretação de comandos em linguagem natural em português brasileiro.
- Transcrição do áudio enviado e geração de resposta em texto.
- Geração de áudio para a resposta, retornado em Base64.
- Listagem das transações persistidas.

## 📂 Estrutura do Projeto

~~~text
.
├── docs/
│   └── images/
│       └── arquitetura-fluxo.png
├── src/
│   └── main/
│       ├── java/dio/orcamento/
│       │   ├── OrcamentoApplication.java
│       │   ├── config/
│       │   │   └── SpringAiConfig.java
│       │   ├── controller/
│       │   │   └── TransacaoController.java
│       │   ├── dto/
│       │   │   ├── NovaTransacaoDTO.java
│       │   │   └── RespostaAudioDTO.java
│       │   ├── model/
│       │   │   ├── TipoTransacao.java
│       │   │   └── Transacao.java
│       │   ├── repository/
│       │   │   └── TransacaoRepository.java
│       │   ├── service/
│       │   │   ├── AudioService.java
│       │   │   └── TransacaoService.java
│       │   └── tool/
│       │       └── TransacaoTools.java
│       └── resources/
│           └── application.yml
├── pom.xml
└── README.md
~~~

## ⚙️ Como Executar o Projeto

### Pré-requisitos

- Java 17 ou compatível com a versão configurada no pom.xml.
- Maven instalado e disponível no terminal.
- Uma chave de API da OpenAI com acesso aos modelos configurados.

> O repositório não contém Maven Wrapper (mvnw/mvnw.cmd); utilize o Maven instalado localmente.

### Configuração da OpenAI

Defina a chave como variável de ambiente. O nome abaixo segue o mapeamento de propriedades do Spring Boot e substitui a configuração de spring.ai.openai.api-key sem expor o segredo no repositório:

~~~bash
export SPRING_AI_OPENAI_API_KEY="sua_chave_da_openai"
~~~

No Windows PowerShell:

~~~powershell
$env:SPRING_AI_OPENAI_API_KEY="sua_chave_da_openai"
~~~

As configurações atuais estão em [src/main/resources/application.yml](src/main/resources/application.yml): porta 8080, H2 em memória (orcamentodb), console H2 em /h2-console, GPT-4o-mini, Whisper whisper-1 e TTS tts-1 com voz alloy.

### Instalação e execução

~~~bash
# Baixa as dependências, compila e instala o projeto
mvn clean install

# Inicia a aplicação
mvn spring-boot:run
~~~

Com a aplicação em execução, a API fica disponível em http://localhost:8080.

## 🔌 Endpoints da API

| Método | Rota | Descrição |
| --- | --- | --- |
| POST | /api/transacoes/comando-voz | Recebe um arquivo de áudio no campo multipart file, processa o comando e retorna transcrição, resposta textual e áudio em Base64. |
| GET | /api/transacoes | Lista todas as transações persistidas. |

### Processar comando por voz

~~~bash
curl --request POST "http://localhost:8080/api/transacoes/comando-voz" \
  --form "file=@./comando.mp3"
~~~

Exemplo de estrutura da resposta:

~~~json
{
  "textoTranscrito": "Registre uma despesa de 25 reais com transporte",
  "respostaTexto": "Transação registrada com sucesso.",
  "audioBase64": "..."
}
~~~

### Listar transações

~~~bash
curl "http://localhost:8080/api/transacoes"
~~~

## 🔮 Possíveis Melhorias Futuras

- Adicionar validação de tipo e tamanho dos arquivos de áudio recebidos.
- Persistir os dados em um banco relacional externo para além do ciclo de vida da aplicação.
- Incluir autenticação e isolamento das transações por usuário.
- Criar testes automatizados para os fluxos de serviços, ferramentas e endpoints.

## 👨‍💻 Autor

Desenvolvido por [Everton Gean](https://github.com/Evertongean).
