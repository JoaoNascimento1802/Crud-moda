package projetoiasmin.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projetoiasmin.tcc.config.CloudinaryService;
import projetoiasmin.tcc.model.Model;
import projetoiasmin.tcc.repository.Repository;

import java.util.Map;

@RestController
@RequestMapping("/image")
public class ControllerImage {


    @Autowired
    private CloudinaryService imageService;

    @Autowired
    private Repository repository;

    @PostMapping("/upload/{id}")
    public ResponseEntity<Map<String, String>> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("Iniciando upload de imagem para ID: " + id);
            if (file.isEmpty()) {
                System.err.println("Erro: Arquivo vazio.");
                throw new IllegalArgumentException("O arquivo enviado está vazio.");
            }

            Map<String, Object> uploadResult = imageService.uploadImage(file);
            System.out.println("Resultado do upload: " + uploadResult);

            String imageUrl = (String) uploadResult.get("url");
            Model model = repository.findById(id).orElseThrow(() -> {
                System.err.println("Erro: Registro não encontrado para o ID: " + id);
                return new RuntimeException("Registro não encontrado com ID: " + id);
            });

            model.setImagURL(imageUrl);
            repository.save(model);
            System.out.println("Imagem salva com sucesso no registro de ID: " + id);

            return ResponseEntity.ok(Map.of("url", imageUrl));
        } catch (Exception e) {
            System.err.println("Erro durante o upload: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(400).body(Map.of(
                    "error", "Falha no upload",
                    "message", e.getMessage()
            ));
        }
    }

}

