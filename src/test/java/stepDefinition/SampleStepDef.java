package stepDefinition;

import Utilities.OperationalUtility;
import io.cucumber.java.en.Given;
import pageClasses.GooglePage;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class SampleStepDef {

    //initializing page class
    GooglePage objGooglePage = new GooglePage();

    @Given("the user able to navigate to Google and provide data for {String},{String}")
    public void the_user_able_to_navigate_to_Google(String strTestCase, String strDataSheet) throws DataFormatException, IOException {
        OperationalUtility.getRowNum(strDataSheet,strTestCase);
        objGooglePage.searchGoogle();
    }
}
