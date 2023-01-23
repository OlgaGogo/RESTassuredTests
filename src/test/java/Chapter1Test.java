import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;



public class Chapter1Test {

    @Test
    public void test1(){

        Response response = get("http://api.zippopotam.us/us/90210");

        System.out.println("RESPONSE: " + response.asString());
        System.out.println("STATUS CODE: " + response.getStatusCode());
        System.out.println("BODY: " + response.getBody().asString());
        System.out.println("TIME TAKEN: " + response.getTime());
        System.out.println("HEADER: " + response.getHeader("content-type"));

        int statusCode = response.statusCode();
        Assert.assertEquals(200, statusCode);

    }

    @Test
    public void test2(){

        given().get("http://api.zippopotam.us/us/90210").
                then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void test3(){

        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void test4(){

        given().
                log().all().
                when().
                    get("http://api.zippopotam.us/us/90210").
                then().
                    log().body();
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills(){

        given().
                when().
                    get("http://api.zippopotam.us/us/90210").
                then().
                    assertThat().
                    body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_checkStateNameInResponseBody_expectCalifornia(){

        given().
                when().
                    get("http://api.zippopotam.us/us/90210").
                then().
                    assertThat().
                    body("places[0].state", equalTo("California"));

    }

    @Test
    public void requestUsZipCode90210_checkListOfPlaceNamesInResponseBody_expectContainsBeverlyHills(){

        given().
                when().
                    get("http://api.zippopotam.us/us/90210").
                then().
                    assertThat().
                    body("places.'place name'", not(hasItem("Toronto")));

    }

    @Test
    public void requestUsZipCode90210_checkNumberOfPlaceNamesInResponseBody_expectOne(){

        given().
                when().
                    get("http://api.zippopotam.us/us/90210").
                then().
                assertThat().
                    body("places.'place name'", hasSize(1));

    }

}
