package com.yourorg.library.repository;

import com.yourorg.library.domain.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookItemRepository extends JpaRepository<BookItem, String> {
}
