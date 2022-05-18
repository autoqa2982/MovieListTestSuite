package library;

import java.util.List;
import java.util.Map;

import core.APIMethods;
import core.RestSession;
import io.restassured.response.Response;

public class ApiLibrary {
	
	public static Response deleteMovies() throws Exception {
		RestSession session = new RestSession();
        Response resp = session.sendRequest(APIMethods.GET_MOVIE);
        
        List<Map<String,String>> movieList = resp.body().jsonPath().getList("data");
        if(movieList != null) {
        	for(Map<String,String> movie : movieList) {
            	APIMethods.DELETE_MOVIE.addMethodParam(movie.get("_id"));
            	resp = session.sendRequest(APIMethods.DELETE_MOVIE);            	            
            } 	
        }         
        return resp;
	}
	

}
