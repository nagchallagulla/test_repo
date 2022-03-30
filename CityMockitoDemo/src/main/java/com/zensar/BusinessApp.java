package com.zensar;

import java.util.List;

public class BusinessApp {
	CityService cityService;
	LoginService loginService;
	
	public BusinessApp(CityService cityService , LoginService loginService) {
		this.cityService = cityService;
		this.loginService=loginService;
	}
	
	public List<String> retrieveCityListByCountry(String country) {
		List<String> cities =  this.cityService.findCitiesByCountries(country);
		return cities;
	}
	  public boolean login(String username, String password) {
	  return loginService.authenticate(username,password);
}
}