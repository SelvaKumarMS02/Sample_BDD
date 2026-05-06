package stepDefinition;

import Utilities.OperationalUtility;
import io.cucumber.java.en.Given;
import pageClasses.GooglePage;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class SampleStepDef {

    //initializing page class
    GooglePage objGooglePage = new GooglePage();

    @Given("the user able to navigate to Google and provide data for {string},{string}")
    public void the_user_able_to_navigate_to_Google_and_provide_data_for (String strTestCase, String strDataSheet) throws DataFormatException, IOException {
        OperationalUtility.getRowNum(strTestCase,strDataSheet);
        objGooglePage.searchGoogle();
    }
}

