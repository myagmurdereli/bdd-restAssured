package pojo;

import lombok.Data;

@Data
public class CreateBookingRequestBody {

    public  String firstname;
    public  String lastname;
    public  Integer totalprice;
    public  boolean depositpaid;
    public  String  additionalneeds = null;
    public  BookingDates  bookingdates;

}