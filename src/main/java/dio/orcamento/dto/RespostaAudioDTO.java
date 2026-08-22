package dio.orcamento.dto;

public record RespostaAudioDTO(
        String textoTranscrito,
        String respostaTexto,
        String audioBase64
) {}