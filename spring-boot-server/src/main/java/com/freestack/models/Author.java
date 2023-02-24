package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "authors",
uniqueConstraints = {
@UniqueConstraint(columnNames = "firstName"),// TODO bug une seule occurence de prénom est possible
@UniqueConstraint(columnNames = "lastName")
})
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private LocalDate birthDate;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
