package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
	
		AddPlace  m = new AddPlace ();
		if(m.place_id==null)
		{
			m.add_place_payload_with("ramu", "odia", "bbsr");
			m.user_calls_with_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("ramu", "getPlaceAPI");
		}
		
	}
	
	
	

}
