package com.example.demo.model;


import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity(name = "Table_Offer")
public class Offer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Offer_Id")
    private Long offerId;
    
	@Column(name = "Offer_Name", length = 30)
	@Pattern(regexp = "^[a-zA-Z0-9\\-\\$\\s]*$", message = "Offer Name can only contain letters, numbers, hyphens, dollar signs")
	private String offerName;

    
	@Column(name = "Offer_Description", length = 50)
    private String offerDescription;
    
	@Column(name = "Offer_Type", length = 20)
	@Pattern(regexp = "^(Unlimited|Topup|Validity|OTT Offers)$", message = "Invalid Offer Type. Allowed values: Unlimited, Topup, Validity, OTT Offers")
	@NotEmpty(message = "Offer Type is required")
	private String offerType;
    
	@Column(name = "Activation_Date")
	@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate activationDate;
	
	@Column(name = "Expiration_Date")
	@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expirationDate;
	
	@Valid
	@NotEmpty(message = "At least one SubOffer is required")
	@Transient
    private List<SubOffer> subOffers= new ArrayList<>();
	
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
	
	
	public List<SubOffer> getSubOffers() {
		return subOffers;
	}

	public void setSubOffers(List<SubOffer> subOffers) {
		this.subOffers = subOffers;
	}

	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", offerName=" + offerName + ", offerDescription=" + offerDescription
				+ ", offerType=" + offerType + ", activationDate=" + activationDate + ", expirationDate="
				+ expirationDate + ", subOffers=" + subOffers + "]";
	}
	
	@PrePersist
    public void validateDates() {
        LocalDate currentDate = LocalDate.now();
        if (activationDate.isAfter(expirationDate)) {
            throw new RuntimeException("Activation date cannot be after expiration date.");
        }
    }


    
    
    
}
