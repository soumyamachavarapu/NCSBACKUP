package com.example.demo.service;

import java.util.List;

import com.example.demo.model.SubOffer;

public interface IsubOfferService {
	
	public SubOffer createSubOffer(SubOffer subOffer);
	
	public List<SubOffer> getSubOffersByOfferId(Long offerId);
	
	public SubOffer updateSubOffer(Long subOfferId, SubOffer subOfferDetails);
	
	public boolean deleteSubOffer(Long subOfferId);

}
