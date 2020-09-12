package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePojo;
import pojo.LocationPojo;

public class TestDataBuild {
	
	public AddPlacePojo addPlaceLoad(String name,String language,String address)
	{
		
		AddPlacePojo p = new AddPlacePojo();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("http://google.com");
		p.setLanguage(language);

		LocationPojo l = new LocationPojo();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);

		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		
		return p;
	}

}
