package main;

import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class Main {
public static Response response;
    @Then("^Expected to see (\\d+) status code$")
    public void expectedToSeeStatusCode(int code ) {
        Assert.assertEquals(response.statusCode(), code);
    }
}
