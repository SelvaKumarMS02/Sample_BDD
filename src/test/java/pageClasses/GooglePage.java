package pageClasses;

import Utilities.ApplicationUtility;
import Utilities.OperationalUtility;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage {
    WebDriver driver;
    //Constructor
    public GooglePage(){
        this.driver = ApplicationUtility.driver;
        PageFactory.initElements(driver,this);
    }

    //Webelement declaration and initiazion
    @FindBy(xpath="//*[@name=\"q\"]")
    WebElement txtGoogleSearchBox;

    @FindBy(xpath="(//input[@value='Google Search'])[2]")
    WebElement btnGoogleSearch;

    @FindBy(xpath="//*[contains(text(),'Web result with site links')]//..//*[@lang='en']")
    WebElement lnkFirstResult;

    /*
    Author: Selva
    Date: 05-05-26
     */
    public void searchGoogle(){
        ApplicationUtility.explicitWait_Visibility(txtGoogleSearchBox);
        txtGoogleSearchBox.sendKeys(OperationalUtility.getData("Search"));
        txtGoogleSearchBox.sendKeys(Keys.RETURN);
        OperationalUtility.takeScreenshot("Screenshot Info","Screenshot Message","Screenshot Info");
        //lnkFirstResult.click();
        //String strTemp = driver.getTitle();
        //System.out.println(strTemp);
        //Assert.isTrue(strTemp=="Google");
        //btnGoogleSearch.click();
    }

}
