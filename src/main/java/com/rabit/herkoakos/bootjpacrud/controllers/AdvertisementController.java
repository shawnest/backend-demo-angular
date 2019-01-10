package com.rabit.herkoakos.bootjpacrud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabit.herkoakos.bootjpacrud.exceptions.ResourceNotFoundException;
import com.rabit.herkoakos.bootjpacrud.models.Advertisement;
import com.rabit.herkoakos.bootjpacrud.repositories.AdvertisementRepository;
import com.rabit.herkoakos.bootjpacrud.repositories.UserRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AdvertisementController {

	@Autowired
	AdvertisementRepository advertisementRepository;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/advertisements")
	public List<Advertisement> getAdvertisements(){
		return advertisementRepository.findAll();
	}
	
	@GetMapping("/advertisements/{id}")
	public ResponseEntity<Advertisement> getAdvertisement(@PathVariable (value = "id") Long adId) throws ResourceNotFoundException{
		Advertisement advertisement = advertisementRepository.findById(adId).orElseThrow(() -> new ResourceNotFoundException("Advertisement not found at id " + adId));
		return ResponseEntity.ok().body(advertisement);
	}

	@PostMapping("/advertisements")
	public Advertisement createAdvertisement(@Valid @RequestBody Advertisement advertisement) {
		return advertisementRepository.save(advertisement);
	}
	
	@PutMapping("/advertisements/{id}")
	public ResponseEntity<Advertisement> updateAdvertisement(@PathVariable (value="id") Long adId, @Valid @RequestBody Advertisement adDetails) throws ResourceNotFoundException{
		Advertisement ad = advertisementRepository.findById(adId).orElseThrow(() -> new ResourceNotFoundException("Advertisement not found at id " + adId));
		ad.setTitle(adDetails.getTitle());
		ad.setUser(adDetails.getUser());
		final Advertisement updatedAd = advertisementRepository.save(ad);
		return ResponseEntity.ok(updatedAd);
	}
	
	@DeleteMapping("/advertisements/{id}")
	public Map<String, Boolean> deleteAdvertisement(@PathVariable (value="id") Long adId) throws ResourceNotFoundException {
		Advertisement ad = advertisementRepository.findById(adId).orElseThrow(() -> new ResourceNotFoundException("Advertisement not found at id " + adId));
		advertisementRepository.delete(ad);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}
	

}
