package com.example.demo.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.SubOffer;

@Repository
public interface SubOfferRepository extends JpaRepository<SubOffer, Long> {

	List<SubOffer> findByOfferId(Long offerId);

	void deleteByOfferId(Long offerId);
	
	

}
