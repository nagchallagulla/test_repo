package com.zensar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.entity.StockEntity;

//StockRepo will be implemented by Spring data and persist the entities into database
public interface StockRepo extends JpaRepository<StockEntity, Integer>{
	
	List<StockEntity> findByName(String stockName);
	
	@Query(value = "SELECT se FROM StockEntity AS se WHERE se.marketName=':stockName'")//JPQL
	List<StockEntity> getStocksByName(String marketName);
	
	@Query(value = "SELECT * FROM STOCKS WHERE name=:stockName", nativeQuery = true)
	List<StockEntity> getStocksByNameSQL(String stockName);
	
	//Contains
	List<StockEntity> findByNameContains(String nameLike);
	
	//Containing
	List<StockEntity> findByNameContaining(String nameLike);
	
	//IsContaining
	List<StockEntity> findByNameIsContaining(String nameLike);
	
	//Using JPQL
	@Query(value = "SELECT se FROM StockEntity AS se WHERE se.name LIKE %:nameLike%")
	List<StockEntity> getByNameLikeQuery(String nameLike);
	
	//Using SQL
}
