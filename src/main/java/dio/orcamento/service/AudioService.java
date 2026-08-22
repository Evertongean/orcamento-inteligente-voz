package dio.orcamento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AudioService {

    private final OpenAiAudioTranscriptionModel transcriptionModel;
    private final OpenAiAudioSpeechModel speechModel;

    public String transcreverAudio(MultipartFile file) throws IOException {
        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        var response = transcriptionModel.call(new AudioTranscriptionPrompt(resource));
        return response.getResult().getOutput();
    }

    public byte[] gerarAudio(String texto) {
        return speechModel.call(new SpeechPrompt(texto)).getResult().getOutput();
    }
}