package com.freestack.controllers;

import com.freestack.models.Renting;
import com.freestack.models.User;
import com.freestack.repository.RentingRepository;
import com.freestack.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/renting")
public class RentingController {

	private final RentingRepository rentingRepository;
	private final UserRepository userRepository;

	public RentingController(RentingRepository rentingRepository, UserRepository userRepository) {
		this.rentingRepository = rentingRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("/allByUser")
	public List<Renting> listByUser(Authentication authentication) {
		Optional<User> userOptional = userRepository.findByUsername(((UserDetails)authentication.getPrincipal()).getUsername());
		if(userOptional.isPresent()) {
			return rentingRepository.findRentingsByUser(userOptional.get());
		}
		return new ArrayList<Renting>();
	}

	@GetMapping("/all")
	public List<Renting> listAll() {
		return rentingRepository.findAll();
	}


	@PostMapping
	public ResponseEntity<Renting> save(@RequestBody Renting renting) {
		if(renting.getId() == null){
			if(rentingRepository.existsByBook_IdAndEndDateNull(renting.getBook().getId())){
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
			renting.setStartDate(LocalDateTime.now());
			renting.setDueDate(LocalDate.now().plusDays(14));
		}

		return ResponseEntity.ok(rentingRepository.save(renting));
	}

}
