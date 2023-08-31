package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.ValidOfferDTO;
import com.example.demo.model.Offer;

public interface IofferService {
	
	public Offer createOffer(Offer offer);
	
	public List<Offer> getAllOffersWithSubOffers();
	
	public Offer updateOffer(Long offerId, Offer offerDetails);
	
	public boolean deleteOfferAndSubOffers(Long offerId);
	
	 List<ValidOfferDTO> getValidOffersAndSubOffers();
	 
	 


}
