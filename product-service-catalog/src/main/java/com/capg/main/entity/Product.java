package com.capg.main.entity;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("productinfo")
public class Product {
	@Id
	private String id;
	@Field
	private String pname;
	@Field
	private String category;
	@Field
	private double price;
	@Field
	private String desc;
	@Field
	private String image;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String id, String pname, String category, double price, String desc, String image) {
		super();
		this.id = id;
		this.pname = pname;
		this.category = category;
		this.price = price;
		this.desc = desc;
		this.image = image;
	}
	public String  getId() {
		return id;
	}
	public void setId(String  id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String desc) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", pname=" + pname + ", catagory=" + category + ", price=" + price + ", desc="
				+ desc + ", image=" + image + "]";
	}
	
	

}
