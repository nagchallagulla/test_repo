package com.zensar.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.zensar.dto.Stock;
import com.zensar.entity.StockDocument;
import com.zensar.entity.StockEntity;
import com.zensar.repository.StockMongoRepo;

@Service(value="Mongo_SERVICE")
//@Primary
public class StockServiceMongoImpl implements StockService {

	@Autowired
	StockMongoRepo stockMongoRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public boolean deleteAllStocks() {
		stockMongoRepo.deleteAll();
		return true;
	}

	@Override
	public boolean deleteStockById(int stockId) {
		if(stockMongoRepo.existsById(stockId)) {
			stockMongoRepo.deleteById(stockId);
			return true;
		}
		return false;
		
	}

	@Override
	public Stock updateStock(int stockId, Stock updatedStock) {
		Optional<StockDocument> opstockEntity = stockMongoRepo.findById(stockId);	
		if(opstockEntity.isPresent()) {
			StockDocument stockDocument = opstockEntity.get();
			stockDocument.setMarketName(updatedStock.getMarket());
			stockDocument.setName(updatedStock.getName());
			stockDocument.setPrice(updatedStock.getPrice());
			stockMongoRepo.save(stockDocument);
		}
	return null;
	}

	@Override
	public Stock createNewStock(Stock stock) {
		StockDocument stockDocument = convertDTOIntoEntity(stock);
		stockDocument = stockMongoRepo.save(stockDocument);
		return convertEntityIntoDTO(stockDocument);
		
	}

	@Override
	public Stock getStockById(int stockId) {
		Optional<StockDocument > opstockDocument = stockMongoRepo.findById(stockId);	
		if(opstockDocument.isPresent()) {
			StockDocument  stockDocument = opstockDocument.get();
			return convertEntityIntoDTO(stockDocument);
		}
		return null;
	}

	@Override
	public List<Stock> getAllStocks() {
		//List<StockDocument> stockDocument = stockMongoRepo.findAll();
		//List<Stock> stockDto = convertEntityIntoDTOList(stockDocument);
	return null;
	}

	@Override
	public List<Stock> getStocksByName(String stockName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> getStocksSortedByName(String sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> getStocksByPage(int startIndex, int records) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> getStocksByNameLike(String namelike) {
		// TODO Auto-generated method stub
		return null;
	}
	private StockDocument convertDTOIntoEntity(Stock stock) {
		/*return new StockEntity(stock.getId(), 
				stock.getName(), 
				stock.getMarket(), 
				stock.getPrice());*/
		TypeMap<Stock, StockDocument> typeMap = 
				modelMapper.typeMap(Stock.class, StockDocument.class);
		typeMap.addMappings(mapper->{
			mapper.map(stockDto->stockDto.getMarket(), StockDocument::setMarketName);
		});
		StockDocument stockDocument = modelMapper.map(stock, StockDocument.class);
		return stockDocument;
	}
	
	private Stock convertEntityIntoDTO(StockDocument stockDocument) {
		TypeMap<StockDocument, Stock> typeMap = 
				modelMapper.typeMap(StockDocument.class, Stock.class);
		typeMap.addMappings(mapper->{
			mapper.map(stockentity->stockDocument.getMarketName(), Stock::setMarket);
		});
		Stock stock = modelMapper.map(stockDocument, Stock.class);
		return stock;
	}

}
