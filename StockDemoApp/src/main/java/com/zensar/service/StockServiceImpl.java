package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.zensar.dto.Stock;
import com.zensar.entity.StockEntity;
import com.zensar.exception.InvalidStockIdException;
import com.zensar.repository.StockRepo;


@Service(value="RDMS_SERVICE")
@Primary
public class StockServiceImpl implements StockService{

	 @Autowired
	StockRepo stockRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public boolean deleteAllStocks() {
		stockRepo.deleteAll();
		return true;
	}

	@Override
	public boolean deleteStockById(int stockId) {
		if(stockRepo.existsById(stockId)) {
			stockRepo.deleteById(stockId);
			return true;
		}
		return false;
	}

	@Override
	public Stock updateStock(int stockId, Stock updatedStock) {
			Optional<StockEntity> opstockEntity = stockRepo.findById(stockId);	
			if(opstockEntity.isPresent()) {
				StockEntity stockEntity = opstockEntity.get();
				stockEntity.setMarketName(updatedStock.getMarket());
				stockEntity.setName(updatedStock.getName());
				stockEntity.setPrice(updatedStock.getPrice());
				stockRepo.save(stockEntity);
			}
		return null;
	}

	@Override
	public Stock createNewStock(Stock stock) {
		StockEntity stockEntity = convertDTOIntoEntity(stock);
		stockEntity = stockRepo.save(stockEntity);
		return convertEntityIntoDTO(stockEntity);
	}

	@Override
	public Stock getStockById(int StockId) {
		Optional<StockEntity> opstockEntity = stockRepo.findById(StockId);	
		if(opstockEntity.isPresent()) {
			StockEntity stockEntity = opstockEntity.get();
			return convertEntityIntoDTO(stockEntity);
		}
		throw new InvalidStockIdException(""+StockId);
	}

	@Override
	public List<Stock> getAllStocks() {
		List<StockEntity> stockEntity = stockRepo.findAll();
		List<Stock> stockDto = convertEntityListIntoDTOList(stockEntity);
		return stockDto;
	}
	
	private List<Stock> convertEntityListIntoDTOList(List<StockEntity> stockEntityList) {
		List<Stock> stockDtoList = new ArrayList<Stock>();
			for(StockEntity stockEntity: stockEntityList) {
				Stock stockDto = convertEntityIntoDTO(stockEntity);
				stockDtoList.add(stockDto);
			}
		return stockDtoList;
	}
	
	private StockEntity convertDTOIntoEntity(Stock stock) {
		/*return new StockEntity(stock.getId(), 
				stock.getName(), 
				stock.getMarket(), 
				stock.getPrice());*/
		TypeMap<Stock, StockEntity> typeMap = 
				modelMapper.typeMap(Stock.class, StockEntity.class);
		typeMap.addMappings(mapper->{
			mapper.map(stockDto->stockDto.getMarket(), StockEntity::setMarketName);
		});
		StockEntity stockEntity = modelMapper.map(stock, StockEntity.class);
		return stockEntity;
	}
	
	private Stock convertEntityIntoDTO(StockEntity stockEntity) {
		TypeMap<StockEntity, Stock> typeMap = 
				modelMapper.typeMap(StockEntity.class, Stock.class);
		typeMap.addMappings(mapper->{
			mapper.map(stockentity->stockEntity.getMarketName(), Stock::setMarket);
		});
		Stock stock = modelMapper.map(stockEntity, Stock.class);
		return stock;
	}

	@Override
	public List<Stock> getStocksByName(String stockName) {
		List<StockEntity> stockEntityList = stockRepo.getStocksByNameSQL(stockName);
		List<Stock> stockDtoList = new ArrayList<Stock>();
		for(StockEntity stockEntity: stockEntityList) {
			Stock stockDto = convertEntityIntoDTO(stockEntity);
			stockDtoList.add(stockDto);
		}
		return stockDtoList;
	}

	@Override
	public List<Stock> getStocksSortedByName(String sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> getStocksByPage(int startIndex, int records) {
		
		return null;
	}

	@Override
	public List<Stock> getStocksByNameLike(String nameLike) {
		List<StockEntity> stockEntityList = stockRepo.getByNameLikeQuery(nameLike);
		List<Stock> stockDtoList = new ArrayList<Stock>();
		for(StockEntity stockEntity: stockEntityList) {
			Stock stockDto = convertEntityIntoDTO(stockEntity);
			stockDtoList.add(stockDto);
		}
		return stockDtoList;
	}

}
