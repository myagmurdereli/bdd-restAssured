package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.Main;
import org.junit.Assert;
import pojo.BookingDates;
import pojo.CreateBookingRequestBody;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingSteps {
    CreateBookingRequestBody cbrb = new CreateBookingRequestBody();
    BookingDates bd = new BookingDates();

    @Given("^User Prepare Booking Request Body$")
    public void user_Prepare_Booking_Request_Body(DataTable dt) throws Throwable{
        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        if (data.get(0).get("checkin") == null) {
        } else bd.setCheckin(Main.dataConvertString(data.get(0).get("checkin")));
        if (data.get(0).get("checkout") == null) {
        } else bd.setCheckout(Main.dataConvertString(data.get(0).get("checkout")));
        if (data.get(0).get("checkin") == null) {
        } else cbrb.setBookingdates(bd);
        if (data.get(0).get("firstname") == null) {
        } else cbrb.setFirstname(Main.dataConvertString(data.get(0).get("firstname")));
        if (data.get(0).get("lastname") == null) {
        } else cbrb.setLastname(Main.dataConvertString(data.get(0).get("lastname")));
        if (data.get(0).get("totalprice") == null) {
        } else cbrb.setTotalprice(Main.dataConvertionInteger(data.get(0).get("totalprice")));
        if (data.get(0).get("depositpaid") == null) {
        } else cbrb.setDepositpaid(Main.dataConvertionBoolean(data.get(0).get("depositpaid")));
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
            case "PATCH":
                Main.response = given().header("Content-Type", "application/json")
                        .header("Cookie", Main.token)
                        .with().body(cbrb)
                        .when().log().all().patch("https://restful-booker.herokuapp.com" + path);
                System.out.println(Main.response.asString());
                break;
            case "DELETE":
                Main.response = given().header("Content-Type", "application/json")
                        .header("Cookie", Main.token)
                        .when().log().all().delete("https://restful-booker.herokuapp.com" + path);
                break;
            default:
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
