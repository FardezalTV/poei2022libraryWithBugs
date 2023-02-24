package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Renting;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RentingRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
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
	public Renting create(@RequestBody Renting renting) {
		// TODO on ne peut pas créer de location
		return rentingRepository.save(renting);
	}

}
