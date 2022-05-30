package com.zensar.entity;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="stocks")
public class StockDocument {
	
	@Id
	public int id;
	
	private String name;
	private String marketName;
	private int price;
	public StockDocument() {
		super();
	}
	public StockDocument(int id, String name, String marketName, int price) {
		super();
		this.id = id;
		this.name = name;
		this.marketName = marketName;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "StockDocument [id=" + id + ", name=" + name + ", marketName=" + marketName + ", price=" + price + "]";
	}
	
}
