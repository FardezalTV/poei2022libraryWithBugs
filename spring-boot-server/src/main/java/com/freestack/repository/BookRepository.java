package com.freestack.repository;

import com.freestack.models.Author;
import com.freestack.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByIsbn(String isbn);

	List<Book> findBooksByAuthor(Author author);

	List<Book> findAllByOrderByIdAsc();


}
