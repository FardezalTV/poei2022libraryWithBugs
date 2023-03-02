package com.freestack.repository;

import com.freestack.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	//Optional<Author> findByFirstNameAnAndLastName(String firstName, String lastName);
}
