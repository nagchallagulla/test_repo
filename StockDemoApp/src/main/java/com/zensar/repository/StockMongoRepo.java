package com.zensar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zensar.entity.StockDocument;

public interface StockMongoRepo extends MongoRepository<StockDocument,Integer> {

}
