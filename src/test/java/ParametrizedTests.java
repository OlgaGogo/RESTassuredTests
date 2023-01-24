import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;

@RunWith(DataProviderRunner.class)
public class ParametrizedTests {
    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"},
                {"nl", "1001", "Amsterdam"},
        };
    }

    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills(String countryCode, String zipCode, String expectedPlaceName){

        given().
                pathParams("countryCode", countryCode).pathParams("zipCode", zipCode).
                when().
                get("http://api.zippopotam.us/{countryCode}/{zipCode}").
                then().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }
}
