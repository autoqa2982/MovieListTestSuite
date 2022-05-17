package library;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.Config;
import core.DriverFactory;
import core.JSONDataProvider;
import core.RestSession;

public class TestBase {

    private RestSession session;
    private WebDriver driver;
	private DriverFactory df;

    @BeforeSuite(alwaysRun=true)
    public void initSuite() throws Exception {
    	Config.initProperties(System.getenv("env"));
    }
    
    @BeforeClass(groups= {"ui"})
	public void initDriver() {
		String browser =  System.getenv("browser");
		df = new DriverFactory();
		driver = df.getDriver(browser);
	}
	
	@AfterClass(groups= {"ui"})
	public void quitDriver() {
		if(driver!=null) {
			df.quitDriver();
		}		
	}
	
	protected WebDriver driver() {
		return driver;
	}

    @DataProvider
    public Object[][] getData(Method method){
       try{
           
    	   List<String> testGroups = new ArrayList<>();
    	   Object[][] finalData = null;
    	   Test testClass = method.getAnnotation(Test.class);
    	   for (int i = 0; i < testClass.groups().length; i++) {
    		   testGroups.add(testClass.groups()[i]);               
           }
           JSONDataProvider data = new JSONDataProvider();
           
           if(testGroups.contains("api")) {
        	  HashMap<String,Map> testData = data.getAPIData(method.getName());
        	  finalData = new Object[testData.size()][1];
        	  int counter=0;
	          for (Map.Entry td : testData.entrySet()) {
	                finalData[counter][0] = ((Map) td.getValue());
	                counter++;
	          }
	          return finalData;
           }else if(testGroups.contains("ui")) {
        	   List<Map<String,String>> testData = data.getUIData(method.getName());
        	   finalData = new Object[testData.size()][1];
        	   int count =0;
   			
	   		   for(Map<String,String> map :  testData) {
	   			    finalData[count][0] = map;
	   				count++;
	   		   }
	   		   return finalData;
           }
          
       }catch(Exception e){
           e.printStackTrace();
       }
       return null;
    }
    

}
