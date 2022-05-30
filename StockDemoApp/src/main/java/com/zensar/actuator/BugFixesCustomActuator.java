package com.zensar.actuator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;


@Component
@Endpoint(id = "bug-fixes")
public class BugFixesCustomActuator {

	Map<String, List<String>> bugFixesByVersionMap = new HashMap<>();
	
	@PostConstruct
	public void init() {
		bugFixesByVersionMap.put("v1", Arrays.asList("Invalid status issue", "Application closed"));
		bugFixesByVersionMap.put("v2", Arrays.asList("Window resize now working", "Window size not changing"));
	}
	
	@DeleteOperation
	public boolean deleteBug(@Selector String id) {
		if(bugFixesByVersionMap.containsKey(id)){
			bugFixesByVersionMap.remove(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	@WriteOperation
	public void addBug(@Selector String id, String bugs) {
		bugFixesByVersionMap.put(id, Arrays.asList(bugs.split(",")));
	}
	
	@ReadOperation
	public List<String> findById(@Selector String id){
		return bugFixesByVersionMap.get(id);
	}
	
	@ReadOperation
	public Map<String, List<String>> getAllBugFixes(){
		return this.bugFixesByVersionMap;
	}
}
