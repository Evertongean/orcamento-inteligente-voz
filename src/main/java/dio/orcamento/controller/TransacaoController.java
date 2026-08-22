package dio.orcamento.controller;

import dio.orcamento.dto.RespostaAudioDTO;
import dio.orcamento.model.Transacao;
import dio.orcamento.service.AudioService;
import dio.orcamento.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final AudioService audioService;
    private final ChatClient chatClient;
    private final TransacaoService transacaoService;

    @PostMapping("/comando-voz")
    public ResponseEntity<RespostaAudioDTO> processarVoz(@RequestParam("file") MultipartFile file) throws IOException {
        // 1. Transcrever áudio recebido em texto
        String textoTranscrito = audioService.transcreverAudio(file);

        // 2. Processar comando com o ChatClient e chamar Function Calling automaticamente
        String respostaIA = chatClient.prompt()
                .user(textoTranscrito)
                .call()
                .content();

        // 3. Sintetizar a resposta da IA em áudio
        byte[] bytesAudioResposta = audioService.gerarAudio(respostaIA);
        String audioBase64 = Base64.getEncoder().encodeToString(bytesAudioResposta);

        // 4. Retornar resposta completa
        return ResponseEntity.ok(new RespostaAudioDTO(textoTranscrito, respostaIA, audioBase64));
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTodas() {
        return ResponseEntity.ok(transacaoService.listarTodas());
    }
}