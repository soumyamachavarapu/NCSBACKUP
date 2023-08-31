package com.example.demo.dto;

import com.example.demo.model.ParentRelation;

public class SubOfferDTO {
	
	private Long subOfferId;
    private String subOfferName;
    private Double price;
    private Integer validity;
    private Long offerId;
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
	public ParentRelation getParentRelation() {
		return parentRelation;
	}
	public void setParentRelation(ParentRelation parentRelation) {
		this.parentRelation = parentRelation;
	}
	public Long getOfferId() {
		return offerId;
	}
	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	
	@Override
	public String toString() {
		return "SubOfferDTO [subOfferId=" + subOfferId + ", subOfferName=" + subOfferName + ", price=" + price
				+ ", validity=" + validity + ", offerId=" + offerId + ", parentRelation=" + parentRelation + "]";
	}
    
    

}
