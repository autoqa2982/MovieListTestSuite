package library;

import org.reflections.Reflections;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TestRunnerXMLGenerator implements IAlterSuiteListener {

    @Override
    public void alter(List suites) {
        XmlSuite suite = (XmlSuite) suites.get(0);
        XmlTest xmlTest = new XmlTest(suite);
        xmlTest.setName("Tests");
        List<XmlClass> listTestClasses = new ArrayList<XmlClass>();

        try{
            String groups = System.getenv("groups");
            if(!groups.equals("")){
                if(!groups.contains(",")){ groups = groups+",";}
                List<String> groupList = Arrays.asList(groups.split(","));
                for(String group : groupList){
                    xmlTest.addIncludedGroup(group);
                }
            }
        }catch(Exception e){ }

        //Getting all Test Classes of Type TestBase
        Reflections reflections = new Reflections("tests");
        Set<Class<? extends BaseLibrary>> classes = reflections.getSubTypesOf(BaseLibrary.class);

        for(Class clazz : classes){
            listTestClasses.add(new XmlClass(clazz.getCanonicalName()));
        }
        xmlTest.setXmlClasses(listTestClasses);

    }

}
