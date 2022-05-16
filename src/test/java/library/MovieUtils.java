package library;

import datamodel.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieUtils {

    public static Movie getMovieBody(Map<String,String> data){
        Movie mov = new Movie();
        List<String> time = new ArrayList<>();       
        mov.setName(data.get("name"));
        mov.setRating(data.get("rating"));
        time.add(data.get("time"));
        mov.setTime(time);
        return mov;
    }


}
