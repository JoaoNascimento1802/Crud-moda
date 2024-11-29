package projetoiasmin.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoiasmin.tcc.model.Model;

public interface Repository extends JpaRepository<Model, Long> {
}