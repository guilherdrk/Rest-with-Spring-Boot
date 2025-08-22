package br.com.guilherdrk.repositories;

import br.com.guilherdrk.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
