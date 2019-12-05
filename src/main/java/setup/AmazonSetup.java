package setup;

import base.Base;
import base.Utils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AmazonSetup extends Base {


    @BeforeTest(alwaysRun = true)
    public void beforeClass(){
        System.out.println("In Before Class");
        Utils.readEntirePropertyFile(working_directory + "/config/configuration.properties");
        intializeDriver();
    }

    @AfterTest(alwaysRun = true)
    public void afterClass(){
        System.out.println("In Before Class");
        quitAppium();
    }
}
