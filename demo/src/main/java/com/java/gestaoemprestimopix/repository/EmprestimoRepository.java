package com.java.gestaoemprestimopix.repository;

import com.java.gestaoemprestimopix.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
