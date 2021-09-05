package main;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class Main {
    public static Response response;

    @Then("^Expected to see (\\d+) status code$")
    public void expectedToSeeStatusCode(int code) {
        Assert.assertEquals(response.statusCode(), code);
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
        Assert.assertEquals(response.jsonPath().get(path), value);
    }

    @Then("Check {string} Is Equal {int}")
    public void checkIsEqual(String path, int value) {
        Assert.assertEquals(response.jsonPath().getInt(path), value);
    }


    @Then("Check {string} Is Equals {string}")
    public void checkIsEquals(String path, String value) {
        Assert.assertEquals(response.jsonPath().getBoolean(path), dataConvertionBoolean(value));
    }
}

