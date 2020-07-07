package com.catalogue.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "inventory")
public class Catalogue{
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	@JsonIgnore
	private Long id;
	
	private String category;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "sub_title")
	private String subCategory;
	
	private Integer price;

	private String currency;
	
	@Column(name = "seller_Id")
	private String sellerId;
	
	@Column(name = "left_over")
	private Integer leftOver;
	
	@Transient
	private String image;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage() {
		return this.image;//this.getBookCode().concat(".jpg");
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getLeftOver() {
		return leftOver;
	}
	public void setLeftOver(Integer leftOver) {
		this.leftOver = leftOver;
	}
}
