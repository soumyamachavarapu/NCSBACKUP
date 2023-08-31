package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ValidOfferDTO;
import com.example.demo.model.Offer;
import com.example.demo.service.IofferService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/offers")
public class OfferController {

	@Autowired
    private IofferService offerService;
	
	private ResponseEntity<?> responseEntity;
	
	
	@PostMapping("/add")
	public ResponseEntity<?> createOffer(@Valid @RequestBody Offer offer) {
	    Offer createdOffer = offerService.createOffer(offer);
	    responseEntity = new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
	    return responseEntity;
	}

	
	@GetMapping("/getAllOffers")
	public ResponseEntity<List<Offer>> getAllOffers() {
	    List<Offer> allOffers = offerService.getAllOffersWithSubOffers();

	    if (!allOffers.isEmpty()) {
	        return new ResponseEntity<>(allOffers, HttpStatus.OK);
	    }

	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateOffer/{offerId}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long offerId, @Valid @RequestBody Offer offerDetails) {
        Offer updatedOffer = offerService.updateOffer(offerId, offerDetails);
        return ResponseEntity.ok(updatedOffer);
    }

	
	@DeleteMapping("/deleteOffer/{offerId}")
	public ResponseEntity<String> deleteOffer(@PathVariable Long offerId) {
	    boolean deleted = offerService.deleteOfferAndSubOffers(offerId);

	    if (deleted) {
	        return ResponseEntity.ok("Offer and related sub-offers deleted successfully");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@GetMapping("/validOffersAndSubOffers")
    public ResponseEntity<List<ValidOfferDTO>> getValidOffersAndSubOffers() {
        List<ValidOfferDTO> validOffersAndSubOffers = offerService.getValidOffersAndSubOffers();
        System.out.println(validOffersAndSubOffers);
        return ResponseEntity.ok(validOffersAndSubOffers);
    }


	
}
