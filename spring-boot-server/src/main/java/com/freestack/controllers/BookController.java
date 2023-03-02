package com.freestack.controllers;

import com.freestack.models.Author;
import com.freestack.models.Book;
import com.freestack.repository.AuthorRepository;
import com.freestack.repository.BookRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	@GetMapping("/all")
	public List<Book> listAll() {
		return bookRepository.findAll();
	}

	@PostMapping
	public Book create(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@GetMapping("/{authorId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Book> listByAuthor(@PathVariable Long authorId) {
		Author author = authorRepository.getOne(authorId);
		return bookRepository.findBooksByAuthor(author);
	}

	@GetMapping("/recommendations")
	public List<Book> findRecommendations() {
		return bookRepository.findAllByOrderByIdAsc();
	}


}
