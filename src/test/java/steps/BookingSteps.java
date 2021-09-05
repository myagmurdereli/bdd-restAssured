package steps;

import com.sun.codemodel.JCase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import main.Main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import pojo.BookingDates;
import pojo.CreateBookingRequestBody;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingSteps {
    CreateBookingRequestBody cbrb = new CreateBookingRequestBody();
    BookingDates bd = new BookingDates();

    @Given("^User Prepare Booking Request Body$")
    public void userPrepareBookingRequestBody(DataTable dt) {
        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        bd.setCheckin(Main.dataConvertString(data.get(0).get("checkin")));
        bd.setCheckout(Main.dataConvertString(data.get(0).get("checkout")));
        cbrb.setBookingdates(bd);
        cbrb.setFirstname(Main.dataConvertString(data.get(0).get("firstname")));
        cbrb.setLastname(Main.dataConvertString(data.get(0).get("lastname")));
        // cbrb.setAdditionalneeds(Main.dataConvertString(data.get(0).get("additionalneeds")));
        cbrb.setTotalprice(Main.dataConvertionInteger(data.get(0).get("totalprice")));
        cbrb.setDepositpaid(Main.dataConvertionBoolean(data.get(0).get("depositpaid")));
        if (data.get(0).get("additionalneeds") == null) {
        } else cbrb.setAdditionalneeds(Main.dataConvertString(data.get(0).get("additionalneeds")));
    }

    @When("^User Send Create Booking Request$")
    public void userSendCreateBookingRequest() {
        Main.response = given().header("Content-Type", "application/json")
                .with().body(cbrb).
                when().log().all().post("https://restful-booker.herokuapp.com/booking");
    }

    @When("User Send Booking Request Method {string} with {string} path")
    public void userSendBookingRequestMethod(String method, String path) {
        switch (method) {
            case "POST":
                Main.response = given().header("Content-Type", "application/json")
                        .with().body(cbrb).
                        when().log().all().post("https://restful-booker.herokuapp.com" + path);
                break;
            case "GET":
                Main.response = given().header("Content-Type", "application/json")
                        .when().log().all().get("https://restful-booker.herokuapp.com" + path);
                System.out.println(Main.response.asString());
                break;
            default:
                // code block
        }

    }

    @Then("Check Response Not Include {string}")
    public void checkResponseNotInclude(String path) {
        if (Main.response.jsonPath().get(path) == null)
            Assert.assertTrue(true);
        else
            Assert.assertTrue(false);
    }
}
