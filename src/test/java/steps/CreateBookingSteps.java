package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import main.Main;
import pojo.BookingDates;
import pojo.CreateBookingRequestBody;

import static io.restassured.RestAssured.given;

public class CreateBookingSteps {
    CreateBookingRequestBody cbrb = new CreateBookingRequestBody();
    BookingDates bd = new BookingDates();
    @Given("^User Prepare Booking Request Body$")
    public void userPrepareBookingRequestBody() {
        bd.setCheckin("2018-01-01");
        bd.setCheckout("2018-01-11");
        cbrb.setBookingdates(bd);
        cbrb.setFirstname("yagmur");
        cbrb.setLastname("Dereli");
        cbrb.setAdditionalneeds("Breakfast");
        cbrb.setTotalprice(111);
        cbrb.setDepositpaid(true);
    }

    @When("^User Send Create Booking Request$")
    public void userSendCreateBookingRequest() {
        Main.response = given()
                .with().body(cbrb).

                when().post("https://restful-booker.herokuapp.com/booking");
        System.out.println(Main.response.asString());

    }


}
