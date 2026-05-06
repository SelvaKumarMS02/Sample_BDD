package stepDefinition;

import Utilities.ApplicationUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void setUp(){
        ApplicationUtility.intiDriver();
    }

    @After
    public void tearDown(){
        ApplicationUtility.driver.close();
        ApplicationUtility.driver.quit();
    }
}
