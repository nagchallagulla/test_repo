package com.zensar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "Stock DTO")
public class Stock //dto stands for data transfer object
{
	@ApiModelProperty(value = "Stock Identifier")
	private int Id;
	
	@ApiModelProperty(value = "Stock Name")
	private String name;
	
	@ApiModelProperty(value = "Stock Market")
	private String market;
	
	@ApiModelProperty(value = "Stock Price")
	private int price;
	
	//getter and setters methods and constructr and tostring will be written by (@Data)LOMBOK library
	public Stock() {
		super();
	}
	
	public Stock(int id, String name, String market, int price) {
		super();
		Id = id;
		this.name = name;
		this.market = market;
		this.price = price;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Stock [Id=" + Id + ", name=" + name + ", market=" + market + ", price=" + price + "]";
	}

}