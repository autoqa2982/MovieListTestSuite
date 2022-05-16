package core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static core.Config.ENV;

public class Data {

    private static final String path = "src/test/java/data";
    private static Reader reader;
    private static Gson gson;
    String datatype;
    
    public Data(String datatype) {
    	this.datatype = datatype;
    }

    public HashMap<String,Map> getData(String testName) throws Exception {
    	Type type = null;
    	if(datatype.equals("api")) {
    		type = new TypeToken<HashMap<String, HashMap<String, Map<String,String>>>>(){}.getType();
            reader = new FileReader(path+"/data.api."+ENV.toLowerCase()+".json");
    	}else if(datatype.equals("ui")) {
    		type = new TypeToken<HashMap<String, HashMap<String, Map<String,String>>>>(){}.getType();
            reader = new FileReader(path+"/data."+ENV.toLowerCase()+".json");
    	}
    	
        gson = new Gson();
        Map<String,String> data = gson.fromJson(reader,type);
        return (HashMap<String, Map>) ((HashMap) data).get(testName);
    }
}
