package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Author;
import com.bezkoder.springjwt.models.Book;
import com.bezkoder.springjwt.repository.AuthorRepository;
import com.bezkoder.springjwt.repository.BookRepository;
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


	@GetMapping("/all")//TODO ameliorer le REST
	public List<Book> listAll() {
		return bookRepository.findAll();
	}

	@PostMapping
	public Book create(@RequestBody Book book) {
		// TODO on ne peut pas créer de livre avec le meme titre
		Author author = authorRepository.findById(book.getAuthor().getId()).get();
		book.setAuthor(author);
		return bookRepository.save(book);
	}

	@GetMapping("/{authorId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Book> listByAuthor(@PathVariable Long authorId) {
		Author author = authorRepository.getOne(authorId);
		return bookRepository.findBooksByAuthor(author);
	}

	@GetMapping("/recommendations")//TODO créer un systeme de recommandation selon les auteurs des livres lus par les users
	public List<Book> findRecommendations() {
		return bookRepository.findAllByOrderByIdAsc();
	}


}
