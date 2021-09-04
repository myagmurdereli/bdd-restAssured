package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import main.Main;
import pojo.BookingDates;
import pojo.CreateBookingRequestBody;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateBookingSteps {
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
        cbrb.setAdditionalneeds(Main.dataConvertString(data.get(0).get("additionalneeds")));
        cbrb.setTotalprice(Main.dataConvertionInteger(data.get(0).get("totalprice")));
        cbrb.setDepositpaid(Main.dataConvertionBoolean(data.get(0).get("depositpaid")));
    }
    @When("^User Send Create Booking Request$")
    public void userSendCreateBookingRequest() {
        System.out.println(cbrb);
        Main.response = given().header("Content-Type", "application/json")
                .with().body(cbrb).
                when().log().all().post("https://restful-booker.herokuapp.com/booking");
        System.out.println(Main.response.asString());
    }
}
