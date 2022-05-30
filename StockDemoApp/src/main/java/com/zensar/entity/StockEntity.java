package com.zensar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This entity class will communicate with database table
@Entity//This will persist this class into the database
@Table(name = "STOCKS")//This will persist data into STOCKS table
public class StockEntity {
	
	@Id//Primary key
	@GeneratedValue
	private int id;
	
	private String name;
	private String marketName;
	
	@Column(name = "stock_price")//This will create a table in database according to the name
	private int price;

	public StockEntity() {
		super();
	}

	public StockEntity(int id, String name, String marketName, int price) {
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
		return "StockEntity [id=" + id + ", name=" + name + ", marketName=" + marketName + ", price=" + price + "]";
	}
	
}
