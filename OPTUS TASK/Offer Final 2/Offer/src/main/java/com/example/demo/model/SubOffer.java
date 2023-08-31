package com.example.demo.model;

import com.example.demo.validation.ValidValidity;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Pattern;

@Entity(name = "Table_SubOffer")
public class SubOffer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubOfferId")
    private Long subOfferId;

	@Column(name = "Sub_OfferName", length = 30)
	@Pattern(regexp = "^[a-zA-Z0-9\\-\\$\\s]*$", message = "Sub Offer Name can only contain letters, numbers, hyphens, dollar signs")
	private String subOfferName;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Validity")
    @ValidValidity
    private Integer validity;

    @Column(name="Offer_Id")
    @JoinColumn(name = "Offer_Id")
    private Long offerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Parent_Relation")
    private ParentRelation parentRelation;

	public Long getSubOfferId() {
		return subOfferId;
	}

	public void setSubOfferId(Long subOfferId) {
		this.subOfferId = subOfferId;
	}

	public String getSubOfferName() {
		return subOfferName;
	}

	public void setSubOfferName(String subOfferName) {
		this.subOfferName = subOfferName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public ParentRelation getParentRelation() {
		return parentRelation;
	}

	public void setParentRelation(ParentRelation parentRelation) {
		this.parentRelation = parentRelation;
	}

	@Override
	public String toString() {
		return "SubOffer [subOfferId=" + subOfferId + ", subOfferName=" + subOfferName + ", price=" + price
				+ ", validity=" + validity + ", offerId=" + offerId + ", parentRelation=" + parentRelation + "]";
	}
	
	

	
}
