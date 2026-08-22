package dio.orcamento.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAiConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("""
                    Você é um assistente financeiro pessoal inteligente e amigável.
                    Sua função é interpretar comandos em linguagem natural para salvar transações ou informar saldos.
                    Sempre use as ferramentas registradas (registrarTransacao ou consultarSaldo) quando a intenção do usuário exigir.
                    Responda sempre em português brasileiro de forma sucinta e objetiva, ideal para conversão em áudio.
                """)
                .defaultFunctions("registrarTransacao", "consultarSaldo")
                .build();
    }
}