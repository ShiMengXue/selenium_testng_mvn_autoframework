package SET.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.ho.yaml.Yaml;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class YamlTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public YamlTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( YamlTest.class );
    }
    
    public String updatestring(String... args){
    	String value=args[0];
    	int length=args.length;
    	if (length > 1) {
			for (int i = 1; i < length; i++) {
				value = value.replace("{" + (i-1) + "}", args[i]);
			}
			return value;
		}else{
			System.out.println("the string has no args to change");
			return value;
		}
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	
        try {
        	HashMap ml = Yaml.loadType(new FileInputStream(new File("E:/workapace-ccm-ui/test/data/ele.yaml")), HashMap.class);
        	System.out.println(ml.size());  
        	System.out.println(((HashMap) ml.get("page1")).get("url"));
        	System.out.println(((HashMap) ((HashMap) ml.get("page1")).get("elename1")).get("id"));
        	System.out.println(((HashMap) ((HashMap) ((HashMap) ml.get("page1")).get("elename1")).get("text")).get("fr"));

        	String va1=(String) ((HashMap) ((HashMap) ((HashMap) ml.get("page2")).get("elename")).get("text")).get("fr");
        	String va="teststring {0} {1}mb";
        	System.out.println(updatestring(va1,"88"));
			assertTrue( true );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
