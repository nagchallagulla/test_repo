package com.zensar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CItyServiceStub implements CityService,LoginService {

	public List<String> findCitiesByCountries(String country) {
		List<String> cities = new ArrayList<String>();
		if(country.equalsIgnoreCase("INDIA")) {
			cities=Arrays.asList("Pune","Mumbai","Delhi");
		}
		else if(country.equalsIgnoreCase("USA")) {
			cities=Arrays.asList("NEW york","Washington");
		}
			return cities;
	}

	@Override
	public boolean authenticate(String username, String password) {
	   
		return false;
	}	
}
