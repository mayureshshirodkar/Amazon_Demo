import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;

public class TestDataProvider {

    /**
     * Provides test data to the test scripts
     * @param method_name Methods names for the tests
     */
    @DataProvider(name="scenario_details")
    public static Object[][] provideScenarioDetails(Method method_name) {

        if (method_name.getName().equals("AmazonLogin"))
            return new Object[][]{{"7020214690", "test123"}};

        else if (method_name.getName().equals("AmazonSearchItem"))
            return new Object[][]{{"65-inch tv"}};

        else if (method_name.getName().equals("AmazonProceedToCheckOut"))
            return new Object[][]{{"Net Banking - ICICI Bank"}};

        else
            return new Object[][]{};

    }
}
