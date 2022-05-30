package com.zensar.service;


import java.util.List;
import com.zensar.dto.Stock;


public interface StockService {
	public boolean deleteAllStocks();
	public boolean deleteStockById(int stockId);
	public Stock updateStock(int stockId, Stock updatedStock);
	public Stock createNewStock(Stock stock);
	public Stock getStockById(int stockId);
	public List<Stock> getAllStocks();
	
	public List<Stock> getStocksByName(String stockName);
	public List<Stock> getStocksSortedByName(String sortType);
	public List<Stock> getStocksByPage(int startIndex, int records);
	public List<Stock> getStocksByNameLike(String namelike);
	
	
}
