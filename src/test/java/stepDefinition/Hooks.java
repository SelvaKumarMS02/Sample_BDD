package stepDefinition;

import Utilities.ApplicationUtility;
import org.junit.After;
import org.junit.Before;

public class Hooks {
    @Before
    public void setUp{
        ApplicationUtility.intiDriver();
    }

    @After
    public void tearDown(){
        ApplicationUtility.driver.close();
        ApplicationUtility.driver.quit();
    }
}
