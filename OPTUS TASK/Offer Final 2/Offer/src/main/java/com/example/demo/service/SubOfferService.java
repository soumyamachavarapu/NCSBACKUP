package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Offer;
import com.example.demo.model.ParentRelation;
import com.example.demo.model.SubOffer;
import com.example.demo.repo.OfferRepository;
import com.example.demo.repo.SubOfferRepository;

@Service
public class SubOfferService implements IsubOfferService {
	
	
	@Autowired
    private SubOfferRepository subOfferRepository;

	@Override
	public SubOffer createSubOffer(SubOffer subOffer) {
		return subOfferRepository.save(subOffer);
	}

	@Override
	public List<SubOffer> getSubOffersByOfferId(Long offerId) {
		 return subOfferRepository.findByOfferId(offerId);
	
	}
	
	@Override
	@Transactional
	public SubOffer updateSubOffer(Long subOfferId, SubOffer subOfferDetails) {
		SubOffer existingSubOffer = subOfferRepository.findById(subOfferId).orElse(null);

	    if (existingSubOffer == null) {
	        throw new RuntimeException("SubOffer with ID " + subOfferId + " not found");
	    }

	    ParentRelation existingParentRelation = existingSubOffer.getParentRelation();
	    ParentRelation newParentRelation = subOfferDetails.getParentRelation();

	    // Check if the parent relation is being modified from O/D to M
	    if ((existingParentRelation == ParentRelation.O || existingParentRelation == ParentRelation.D) &&
	        newParentRelation == ParentRelation.M) {
	        throw new RuntimeException("Cannot modify parent relation from Optional/Mandatory to Default for an existing suboffer");
	    }

	    // Update all fields
	    existingSubOffer.setSubOfferName(subOfferDetails.getSubOfferName());
	    existingSubOffer.setPrice(subOfferDetails.getPrice());
	    existingSubOffer.setValidity(subOfferDetails.getValidity());
	    existingSubOffer.setParentRelation(subOfferDetails.getParentRelation());

	    return subOfferRepository.save(existingSubOffer);
	}

//	@Override
//	@Transactional
//	public boolean deleteSubOffer(Long subOfferId) {
//		 Optional<SubOffer> subOffer = subOfferRepository.findById(subOfferId);
//
//		    if (subOffer.isPresent()) {
//		        subOfferRepository.deleteById(subOfferId);
//		        return true;
//		    }
//
//		    return false;
//	}
	
	@Override
	@Transactional
	public boolean deleteSubOffer(Long subOfferId) {
	    Optional<SubOffer> subOffer = subOfferRepository.findById(subOfferId);

	    if (subOffer.isPresent()) {
	        SubOffer deletedSubOffer = subOfferRepository.findById(subOfferId).orElse(null);
	        Long offerId = deletedSubOffer.getOfferId();

	        List<SubOffer> remainingSubOffers = subOfferRepository.findByOfferId(offerId);

	        if (remainingSubOffers.size() > 1) {
	            subOfferRepository.deleteById(subOfferId);
	            return true;
	        } else {
	            // Offer has only one suboffer, cannot delete
	            return false;
	        }
	    }

	    return false;
	}

	
//	@Override
//	@Transactional
//	public boolean deleteSubOffer(Long subOfferId) {
//		Optional<SubOffer> subOfferOptional = subOfferRepository.findById(subOfferId);
//
//	    if (subOfferOptional.isPresent()) {
//	        SubOffer subOfferToDelete = subOfferOptional.get();
//	        
//	        // Find the associated Offer
//	        Offer associatedOffer = null;
//	        List<Offer> offers = offerRepository.findAll();
//	        for (Offer offer : offers) {
//	            if (offer.getSubOffers().contains(subOfferToDelete)) {
//	                associatedOffer = offer;
//	                break;
//	            }
//	        }
//
//	        if (associatedOffer != null) {
//	            List<SubOffer> remainingSubOffers = associatedOffer.getSubOffers();
//	            if (remainingSubOffers.size() > 1) {
//	                subOfferRepository.deleteById(subOfferId);
//	                return true;
//	            }
//	        }
//	    }
//
//	    return false;
//	}
	
	

	

}
