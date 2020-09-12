package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePojo;
import pojo.LocationPojo;

public class TestDataBuild {
	
	public AddPlacePojo addPlaceLoad()
	{
		
		AddPlacePojo p = new AddPlacePojo();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");

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
