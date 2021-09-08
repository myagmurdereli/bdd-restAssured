package main;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.LoginRequestBody;

import static io.restassured.RestAssured.given;

public class Main {
    public static Response response;
    public static String token;

    @Then("^Expected to see (\\d+) status code$")
    public void expectedToSeeStatusCode(int code) {
        Assert.assertEquals(code, response.statusCode());
    }

    public static String dataConvertString(String value) {
        switch (value) {
            case "null":
                return null;
            case "whiteSpace":
                return "   ";
            case "noString":
                return "";
        }
        return value;
    }

    public static Integer dataConvertionInteger(String value) {
        switch (value) {
            case "null":
                return null;

        }
        return Integer.valueOf(value);

    }

    public static Boolean dataConvertionBoolean(String value) {
        switch (value) {
            case "null":
                return null;
            case "true":
                return true;
            case "false":
                return false;

        }
        return Boolean.valueOf(value);

    }

    public static Double dataConvertionDouble(String value) {
        switch (value) {
            case "null":
                return null;

        }
        return Double.valueOf(value);

    }

    @Then("Check {string} Is Equal {string}")
    public void checkIsEqual(String path, String value) {
        Assert.assertEquals(value, response.jsonPath().get(path));
    }

    @Then("Check {string} Is Equal {int}")
    public void checkIsEqual(String path, int value) {
        Assert.assertEquals(response.jsonPath().getInt(path), value);
    }


    @Then("Check {string} Is Equals {string}")
    public void checkIsEquals(String path, String value) {
        Assert.assertEquals(response.jsonPath().getBoolean(path), dataConvertionBoolean(value));
    }

    @Then("Check Response {string} Fields Not Null")
    public void checkResponseFieldsNotNull(String path) {
        Assert.assertNotNull(response.jsonPath().get(path));
    }

    @Then("Array Response {string} Fields Are Not Null")
    public void arrayResponseFieldsAreNotNull(String path) {
        for (int i = 0; i < response.jsonPath().getList("").size(); i++) {
            Assert.assertNotNull(response.jsonPath().get("[" + i + "]." + path));
        }
    }

    @Then("Response Size Is Equal {int}")
    public void responseSizeIsEqual(int size) {
        Assert.assertEquals(size, response.jsonPath().getList("").size());
    }

    @When("Success Login")
    public void successLogin() {
        LoginRequestBody l = new LoginRequestBody();
        l.setPassword("password123");
        l.setUsername("admin");
        response = given().header("Content-Type", "application/json").
                with().body(l).when().post("https://restful-booker.herokuapp.com/auth");
        token = "token=" + response.jsonPath().get("token");
    }
}

