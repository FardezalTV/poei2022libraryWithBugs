package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Author;
import com.bezkoder.springjwt.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	//Optional<Author> findByFirstNameAnAndLastName(String firstName, String lastName);
}
