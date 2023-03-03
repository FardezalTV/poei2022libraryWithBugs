package com.freestack.repository;

import com.freestack.models.Book;
import com.freestack.models.Renting;
import com.freestack.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Long> {

	List<Renting> findRentingsByUser(User user);

	boolean existsByBook_IdAndEndDateNull(long bookId);
}
