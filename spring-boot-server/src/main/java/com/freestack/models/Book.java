package com.freestack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(	name = "books",
uniqueConstraints = {
@UniqueConstraint(columnNames = "title"),
@UniqueConstraint(columnNames = "isbn")
})
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	@JsonIgnore
	private List<Renting> rentings;
	@ManyToOne(optional = false)
	@JoinColumn(name = "author_id")
	private Author author;
	private String title;
	private String isbn;
	private LocalDate parutionDate;
	private String description;


	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getParutionDate() {
		return parutionDate;
	}

	public void setParutionDate(LocalDate parutionDate) {
		this.parutionDate = parutionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Renting> getRentings() {
		return rentings;
	}

	public void setRentings(List<Renting> rentings) {
		this.rentings = rentings;
	}
}
