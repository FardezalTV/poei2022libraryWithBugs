package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Author;
import com.bezkoder.springjwt.models.Book;
import com.bezkoder.springjwt.repository.AuthorRepository;
import com.bezkoder.springjwt.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	public AuthorController(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	@PostMapping
	public Author createAuthor(@RequestBody Author author) {
		return authorRepository.save(author);
	}

	@GetMapping("/all")
	public List<Author> listAll() {
		return authorRepository.findAll();
	}

	@GetMapping("/{authorId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Book> listByAuthor(@PathVariable Long authorId) {
		Author author = authorRepository.getOne(authorId);
		return bookRepository.findBooksByAuthor(author);
	}

	@DeleteMapping("/{authorId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity delete(@PathVariable Long authorId) {
		//TODO on ne peut pas supprimer un auteur ayant un livre
		authorRepository.deleteById(authorId);
		return ResponseEntity.ok().build();
	}

}
