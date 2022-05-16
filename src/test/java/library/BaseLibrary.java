package library;

import core.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseLibrary {

    private RestSession session;


    @BeforeSuite
    public void initTest() throws Exception {
        Environments.load();
    }

    @DataProvider
    public Object[][] getApiData(Method method){
       try{
           HashMap<String,Map> testData;
           Data data = new Data("api");
           testData =  data.getData(method.getName());
           Object[][] finalData = new Object[testData.size()][1];
           int counter=0;
           for (Map.Entry td : testData.entrySet()){
               finalData[counter][0] = ((Map) td.getValue());
               counter++;
           }
           return finalData;
       }catch(Exception e){
           e.printStackTrace();
       }
       return null;
    }



}
