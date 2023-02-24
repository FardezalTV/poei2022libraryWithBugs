package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Author;
import com.bezkoder.springjwt.models.Book;
import com.bezkoder.springjwt.models.Renting;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Long> {

	List<Renting> findRentingsByUser(User user);

}
