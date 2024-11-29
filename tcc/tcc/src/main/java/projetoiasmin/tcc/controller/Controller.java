package projetoiasmin.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoiasmin.tcc.model.Model;
import projetoiasmin.tcc.repository.Repository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/models")
public class Controller {

    @Autowired
    private Repository repository;

    // Listar todos os registros
    @GetMapping
    public List<Model> getAll() {
        return repository.findAll();
    }

    // Obter um registro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Model> getById(@PathVariable Long id) {
        Optional<Model> model = repository.findById(id);
        return model.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar um novo registro
    @PostMapping
    public ResponseEntity<Model> create(@RequestBody Model model) {
        Model savedModel = repository.save(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedModel);
    }

    // Atualizar um registro existente
    @PutMapping("/{id}")
    public ResponseEntity<Model> update(@PathVariable Long id, @RequestBody Model updatedModel) {
        return repository.findById(id)
                .map(existingModel -> {
                    existingModel.setOcasiao(updatedModel.getOcasiao());
                    existingModel.setTitulo(updatedModel.getTitulo());
                    existingModel.setDescricao(updatedModel.getDescricao());
                    repository.save(existingModel);
                    return ResponseEntity.ok(existingModel);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Deletar um registro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

