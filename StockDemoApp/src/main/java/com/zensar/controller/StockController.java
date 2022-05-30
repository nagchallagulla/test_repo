package com.zensar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zensar.dto.Stock;
import com.zensar.service.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/stockapp")
@CrossOrigin(origins = "*")
public class StockController {
	
	@Autowired
	StockService stockService;
	@Qualifier("Mongo_SERVICE")
	
	@GetMapping(value = "/employee")
	public boolean testRequestParam(@RequestParam(value = "eid", required = true) int empId) {
		System.out.println("Emp Id : " + empId);
		return true;
	}
	
	@GetMapping(value = "/employee2")
	public boolean testHeaderParam(@RequestHeader(value = "auth-token")String authToken) {
		System.out.println("Auth token : " + authToken);
		return true;
	}
	
	@DeleteMapping(value = "/stock")
	public boolean deleteAllStocks() {
		return stockService.deleteAllStocks();
	}
	
	@DeleteMapping(value = "/stock/{id}")
	public boolean deleteStockById(@PathVariable("id") int stockId) {
		return stockService.deleteStockById(stockId);
	}
	
	@PostMapping(value = "/stock/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Stock updateStock(@PathVariable("id") int stockId, @RequestBody Stock updatedStock) {
		return stockService.updateStock(stockId, updatedStock);
	}
	
	@PostMapping(value="/stock",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Stock createNewStock(@RequestBody Stock stock) {
		return stockService.createNewStock(stock);
	}
	
	@GetMapping(value="/stock/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Reads stock by id", notes = "This REST API returns a stok by id")
	public Stock getStockById(@ApiParam(value = "Stock id", name = "id") 
	@PathVariable("id") int id){
		return stockService.getStockById(id);
	}
	
	@GetMapping(value="/stock",produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Reads all stocks", notes = "This REST API returns all stocks")
	public List<Stock> getAllStocks(){
		return stockService.getAllStocks();
	}
	
	@GetMapping(value="/stock/name/{name}", produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Stock>> getStocksByName(@PathVariable("name") String stockName) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksByName(stockName), HttpStatus.OK);
	}
	
	@GetMapping(value="/stock/sort/{sortType}", produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Stock>> getStocksSortedByName(@PathVariable("sortType") String sortType) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksSortedByName(sortType), HttpStatus.OK);
	}


	@GetMapping(value="/stock/{startIndex}/{records}", produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Stock>> getStocksByPage(@PathVariable("startIndex") int startIndex,
	@PathVariable("records") int records) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksByPage(startIndex, records), HttpStatus.OK);
	}



	@GetMapping(value="/stock/like/{nameLike}", produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Stock>> getStocksByNameLike(@PathVariable("nameLike") String nameLike) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksByNameLike(nameLike), HttpStatus.OK);
	}
	
	
}