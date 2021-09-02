package pojo;

import lombok.Data;

@Data
public class CreateBookingRequestBody {

    public  String firstname;
    public  String lastname;
    public  int totalprice;
    public  boolean depositpaid;
    public  String  additionalneeds;
    public  BookingDates  bookingdates;

}