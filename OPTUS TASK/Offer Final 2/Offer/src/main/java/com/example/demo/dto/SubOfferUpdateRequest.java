package com.example.demo.dto;

public class SubOfferUpdateRequest {
	
	private Long subOfferId; 
    private String subOfferName;
    private Double price;
    private Integer validity;
    private String parentRelation;
    
    public SubOfferUpdateRequest() {
    }
    
    
	public SubOfferUpdateRequest(Long subOfferId, String subOfferName, Double price, Integer validity,
			String parentRelation) {
		super();
		this.subOfferId = subOfferId;
		this.subOfferName = subOfferName;
		this.price = price;
		this.validity = validity;
		this.parentRelation = parentRelation;
	}
	
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
	public String getParentRelation() {
		return parentRelation;
	}
	public void setParentRelation(String parentRelation) {
		this.parentRelation = parentRelation;
	}
    
    


}
