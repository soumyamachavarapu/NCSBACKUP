package com.example.demo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValidOfferDTO {
	
	    private Long offerId;
	    private String offerName;
	    private String offerDescription;
	    private String offerType;
	    private LocalDate activationDate;
	    private LocalDate expirationDate;
	    private List<SubOfferDTO> subOffers=new ArrayList<>();
	    
		public Long getOfferId() {
			return offerId;
		}
		public void setOfferId(Long offerId) {
			this.offerId = offerId;
		}
		public String getOfferName() {
			return offerName;
		}
		public void setOfferName(String offerName) {
			this.offerName = offerName;
		}
		public String getOfferDescription() {
			return offerDescription;
		}
		public void setOfferDescription(String offerDescription) {
			this.offerDescription = offerDescription;
		}
		public String getOfferType() {
			return offerType;
		}
		public void setOfferType(String offerType) {
			this.offerType = offerType;
		}
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
		public List<SubOfferDTO> getSubOffers() {
			return subOffers;
		}
		public void setSubOffers(List<SubOfferDTO> subOffers) {
			this.subOffers = subOffers;
		}
		
		@Override
		public String toString() {
			return "ValidOfferDTO [offerId=" + offerId + ", offerName=" + offerName + ", offerDescription="
					+ offerDescription + ", offerType=" + offerType + ", activationDate=" + activationDate
					+ ", expirationDate=" + expirationDate + ", subOffers=" + subOffers + "]";
		}
	    
	    
	    
	    

}
