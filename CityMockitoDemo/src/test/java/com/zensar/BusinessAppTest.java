package com.zensar;

import static org.junit.Assert.*;

import org.junit.Test;

public class BusinessAppTest {

	@Test
	public void testBusinessApp() {
		CityService cityService=new CItyServiceStub();
		LoginService loginService=new CItyServiceStub();
		BusinessApp businessApp=new BusinessApp(cityService,loginService);
		assertEquals(businessApp.retrieveCityListByCountry("INDIA").size(),3);
	}

}
