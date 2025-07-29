package com.yourorg.library.repository;

import com.yourorg.library.domain.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, Long> {
}
