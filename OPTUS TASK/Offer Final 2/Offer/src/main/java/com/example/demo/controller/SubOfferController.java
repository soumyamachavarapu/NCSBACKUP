package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SubOffer;
import com.example.demo.repo.SubOfferRepository;
import com.example.demo.service.IsubOfferService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/suboffers")
public class SubOfferController {
	
	@Autowired
    private SubOfferRepository subOfferRepository;

	@Autowired
    private IsubOfferService subOfferService;
	
	@PostMapping("/add")
	public ResponseEntity<SubOffer> createSubOffer(@RequestBody SubOffer subOffer) {
	    SubOffer createdSubOffer = subOfferService.createSubOffer(subOffer);
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdSubOffer);
	}
	
	@GetMapping("/getsubOffers/{offerId}")
	public ResponseEntity<List<SubOffer>> getSubOffersForOffer(@PathVariable Long offerId) {
	    List<SubOffer> subOffers = subOfferService.getSubOffersByOfferId(offerId);

	    if (!subOffers.isEmpty()) {
	        return new ResponseEntity<>(subOffers, HttpStatus.OK);
	    }

	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/Updatesuboffer/{subOfferId}")
    public ResponseEntity<SubOffer> updateSubOffer(@PathVariable Long subOfferId, @Valid @RequestBody SubOffer subOfferDetails) {
        SubOffer updatedSubOffer = subOfferService.updateSubOffer(subOfferId, subOfferDetails);
        return ResponseEntity.ok(updatedSubOffer);
    }
	
	@DeleteMapping("/deleteSubOffer/{subOfferId}")
	public ResponseEntity<String> deleteSubOffer(@PathVariable Long subOfferId) {
		boolean deleted = subOfferService.deleteSubOffer(subOfferId);

	    if (deleted) {
	        return ResponseEntity.ok("SubOffer deleted successfully");
	    } else {
	        
	        Optional<SubOffer> subOffer = subOfferRepository.findById(subOfferId);
	        
	        if (subOffer.isPresent()) {
	            
	            return ResponseEntity.badRequest().body("Offer has only one suboffer, cannot delete");
	        } else {
	            
	            return ResponseEntity.notFound().build();
	        }
	    }
	}



	
}
