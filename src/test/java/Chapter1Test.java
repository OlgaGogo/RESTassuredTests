import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

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
                statusCode(200);
    }

    @Test
    public void test3(){

        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));
    }

}
