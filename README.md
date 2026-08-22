# 🎙️ Orçamento Inteligente por Voz

Uma API REST desenvolvida em **Java** com **Spring Boot** e **Spring AI** que permite aos usuários gerenciarem suas finanças pessoais utilizando comandos de áudio.

O sistema realiza a transcrição do áudio, interpreta a intenção do usuário (cadastro de receita/despesa ou consulta de saldo), executa as operações financeiras em banco de dados através de **Tool Calling (Function Calling)** e devolve uma resposta estruturada em texto e áudio sintetizado.

---

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.x**
* **Spring AI (1.0.0-M2)**
    * **OpenAI Whisper:** Speech-to-Text (Transcrição de voz)
    * **OpenAI GPT-4o-mini:** Processamento de linguagem natural e Tool Calling
    * **OpenAI TTS (Text-to-Speech):** Síntese de voz na resposta
* **Spring Data JPA & Banco de Dados H2:** Persistência em memória
* **Lombok:** Produtividade e redução de código boilerplate

---

## 🛠️ Arquitetura e Fluxo de Dados

```text
[ Áudio (.mp3/.wav) ] ➔ [ Controller ] ➔ [ Whisper (STT) ]
                                                │
                                                ▼
[ Resposta (JSON + Áudio) ] ◄─ [ TTS (Voz) ] ◄─ [ LLM + Tool Calling ] ➔ [ H2 Database ]