# Como Executar

## Pré-requisitos

- Java 17 ou versão compatível com a configuração declarada no pom.xml.
- Maven instalado e disponível no terminal.
- Chave de API da OpenAI com acesso aos modelos configurados.

> O repositório não contém Maven Wrapper (mvnw ou mvnw.cmd). Utilize uma instalação local do Maven.

## Configurar a chave da OpenAI

Antes de iniciar a aplicação, defina a variável de ambiente abaixo. Ela fornece o valor da propriedade spring.ai.openai.api-key sem incluir credenciais no repositório.

~~~bash
export SPRING_AI_OPENAI_API_KEY="sua_chave_da_openai"
~~~

No Windows PowerShell:

~~~powershell
$env:SPRING_AI_OPENAI_API_KEY="sua_chave_da_openai"
~~~

Consulte [Configuração](Configuracao) para os demais valores da aplicação.

## Instalar e iniciar

No diretório raiz do projeto, execute:

~~~bash
# Baixa as dependências, compila e instala o projeto
mvn clean install

# Inicia a aplicação
mvn spring-boot:run
~~~

Após a inicialização, a API fica disponível em http://localhost:8080.

## Console do H2

Com a aplicação em execução, o console do H2 é disponibilizado em http://localhost:8080/h2-console. A base configurada é em memória; portanto, os dados não são mantidos após o encerramento da aplicação.
