package com.zensar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class BusinessAppMockTest {
	
    // @Test
	//public void testMockVsSpy() {
    	 /*
		List<String> mockList=mock(List.class);
		mockList.add("ABC");
		mockList.add("xyz");
		System.out.println("Mock" +mockList.get(1));
		
		List<String> list=new ArrayList<>();
		List<String> spyList=spy(list);
		spyList.add("abc");
		spyList.add("xyz");
		System.out.println(spyList.get(1));
	} */
	
	
	
	
	@Test(expected=NullPointerException.class)
	public void testBusinessApp() {
		CityService cityService=mock(CityService.class);
		LoginService loginService=mock(LoginService.class);
		when(cityService.findCitiesByCountries("INDIA")).
		thenReturn(Arrays.asList("Pune","Mumbai","Delhi"));
		when(cityService.findCitiesByCountries("USA")).
		thenReturn(Arrays.asList("NEW york","Washington"));
		
		when(cityService.findCitiesByCountries(anyString())).
		thenReturn(Arrays.asList("city1","city2","city3"));
		
		
		when(cityService.findCitiesByCountries("Sweden")).thenThrow(NullPointerException.class);
		
		BusinessApp businessApp=new BusinessApp(cityService, loginService);
		businessApp.retrieveCityListByCountry("Sweden");
		//assertEquals(businessApp.retrieveCityListByCountry("INDIA").size(),3);
	}

}
