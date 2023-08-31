package com.example.demo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OfferUpdateRequest {
	
	 private LocalDate activationDate;
	 private LocalDate expirationDate;
	 private List<SubOfferUpdateRequest> subOffers= new ArrayList<>();
	 
	public LocalDate getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(LocalDate activationDate) {
		this.activationDate = activationDate;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public List<SubOfferUpdateRequest> getSubOffers() {
		return subOffers;
	}
	public void setSubOffers(List<SubOfferUpdateRequest> subOffers) {
		this.subOffers = subOffers;
	}
	
	 
	 
	    
	    

}
