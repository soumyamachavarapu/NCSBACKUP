package com.example.demo.service;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ValidOfferDTO;
import com.example.demo.dto.SubOfferDTO;
import com.example.demo.model.Offer;
import com.example.demo.model.ParentRelation;
import com.example.demo.model.SubOffer;
import com.example.demo.repo.OfferRepository;
import com.example.demo.repo.SubOfferRepository;

import jakarta.validation.Valid;

@Service
public class OfferService implements IofferService {
	
	@Autowired
    private OfferRepository offerRepository;
	
	@Autowired
    private SubOfferRepository subOfferRepository;

	
	@Override
	public Offer createOffer(@Valid Offer offer) {
	    // Save the offer to generate offerId
	    Offer savedOffer = offerRepository.save(offer);

	    // Get the generated offerId
	    Long offerId = savedOffer.getOfferId();

	    // Associate sub-offers with the offerId and save them
	    for (SubOffer subOffer : savedOffer.getSubOffers()) {
	        subOffer.setOfferId(offerId);  // Set the offerId
	        subOfferRepository.save(subOffer);
	    }

	    return savedOffer;
	}

	@Override
	public List<Offer> getAllOffersWithSubOffers() {
		 List<Offer> allOffers = offerRepository.findAll();

		    for (Offer offer : allOffers) {
		        List<SubOffer> subOffers = subOfferRepository.findByOfferId(offer.getOfferId());
		        offer.setSubOffers(subOffers);
		    }

		    return allOffers;
	}
	
	
	@Override
	@Transactional
	public Offer updateOffer(Long offerId, Offer offerDetails) {
		Offer existingOffer = offerRepository.findById(offerId).orElse(null);

	    if (existingOffer == null) {
	        throw new RuntimeException("Offer with ID " + offerId + " not found");
	    }

	    // Update activation and expiration dates
	    existingOffer.setActivationDate(offerDetails.getActivationDate());
	    existingOffer.setExpirationDate(offerDetails.getExpirationDate());

	    // Update existing subOffers and add new ones
	    List<SubOffer> updatedSubOffers = new ArrayList<>();
	    for (SubOffer subOffer : offerDetails.getSubOffers()) {
	        if (subOffer.getSubOfferId() == null) {
	            // This is a new subOffer, set its offerId and save it
	            subOffer.setOfferId(existingOffer.getOfferId());
	            updatedSubOffers.add(subOfferRepository.save(subOffer));
	        } else {
	            SubOffer existingSubOffer = subOfferRepository.findById(subOffer.getSubOfferId()).orElse(null);
	            if (existingSubOffer != null) {
	                ParentRelation newParentRelation = subOffer.getParentRelation();
	                ParentRelation existingParentRelation = existingSubOffer.getParentRelation();
	                
	                // Check if the parent relation is being modified from O/D to M
	                if ((existingParentRelation == ParentRelation.O || existingParentRelation == ParentRelation.D) &&
	                    newParentRelation == ParentRelation.M) {
	                    throw new RuntimeException("Cannot modify parentRelation Optional/Mandatory to Default for an existing suboffer");
	                }
	                
	                // Check if the parent relation is being modified from M to O/D
//	                if (existingParentRelation == ParentRelation.M &&
//	                    (newParentRelation == ParentRelation.O || newParentRelation == ParentRelation.D)) {
//	                    throw new RuntimeException("Cannot modify parentRelation from M for existing subOffer");
//	                }
	                
	                // Update other allowed fields
	                existingSubOffer.setSubOfferName(subOffer.getSubOfferName());
	                existingSubOffer.setPrice(subOffer.getPrice());
	                existingSubOffer.setValidity(subOffer.getValidity());
	                // Update other fields as needed
	                updatedSubOffers.add(subOfferRepository.save(existingSubOffer));
	            }
	        }
	    }

	    // Set the updated subOffers to the existingOffer
	    existingOffer.setSubOffers(updatedSubOffers);

	    return existingOffer;
	}


	@Override
	@Transactional
	public boolean deleteOfferAndSubOffers(Long offerId) {
		Optional<Offer> offer = offerRepository.findById(offerId);

	    if (offer.isPresent()) {
	        subOfferRepository.deleteByOfferId(offerId);
	        offerRepository.deleteById(offerId);
	        return true;
	    }

	    return false;
	}

	@Override
	public List<ValidOfferDTO> getValidOffersAndSubOffers() {
		 List<Offer> validOffers = offerRepository.findValidOffersNative(LocalDate.now());

		    Map<Long, ValidOfferDTO> offerIdToDTO = new HashMap<>();  // Use a map to prevent duplicates

		    for (Offer offer : validOffers) {
		        if (!offerIdToDTO.containsKey(offer.getOfferId())) {
		            ValidOfferDTO validOfferDTO = new ValidOfferDTO();
		            validOfferDTO.setOfferId(offer.getOfferId());
		            validOfferDTO.setOfferName(offer.getOfferName());
		            validOfferDTO.setOfferDescription(offer.getOfferDescription());
		            validOfferDTO.setOfferType(offer.getOfferType());
		            validOfferDTO.setActivationDate(offer.getActivationDate());
		            validOfferDTO.setExpirationDate(offer.getExpirationDate());
		            
		            // Retrieve suboffers for the current offer
		            List<SubOffer> subOffers = subOfferRepository.findByOfferId(offer.getOfferId());
		            List<SubOfferDTO> subOfferDTOs = new ArrayList<>();
		            for (SubOffer subOffer : subOffers) {
		                SubOfferDTO subOfferDTO = new SubOfferDTO();
		                subOfferDTO.setSubOfferId(subOffer.getSubOfferId());
		                subOfferDTO.setSubOfferName(subOffer.getSubOfferName());
		                subOfferDTO.setPrice(subOffer.getPrice());
		                subOfferDTO.setValidity(subOffer.getValidity());
		                subOfferDTO.setParentRelation(subOffer.getParentRelation());
		                subOfferDTOs.add(subOfferDTO);
		            }
		            validOfferDTO.setSubOffers(subOfferDTOs);

		            offerIdToDTO.put(offer.getOfferId(), validOfferDTO);  // Add to map to avoid duplicates
		        }
		    }

		    return new ArrayList<>(offerIdToDTO.values());
    }
	
	

	

}


