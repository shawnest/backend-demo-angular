package com.rabit.herkoakos.bootjpacrud.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rabit.herkoakos.bootjpacrud.models.Advertisement;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{
	Page<Advertisement> findByUserId(Long userId, Pageable pageable);

}
