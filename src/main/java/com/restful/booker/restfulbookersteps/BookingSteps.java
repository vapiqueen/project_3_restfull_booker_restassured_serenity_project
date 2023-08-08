package com.restful.booker.restfulbookersteps;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.BookingPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class BookingSteps {

    static String token;


    @Step("Get all bookings information")
    public ValidatableResponse getAllBooking() {
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_BOOKING)
                .then();


    }

    @Step("Creating booking with firstname:{0}, lastname:{1}, totalprice:{2}, depositepaid:{3}, bookingdate:{4}, additionalneed:{5}, checkin:{6},checkout:{7}")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, boolean depositepaid,
                                             String additionalneed, BookingPojo.BookingDates bookingdates) {
        // BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname,lastname,totalprice,depositepaid,additionalneed,checkin,checkout);
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositepaid);
        bookingPojo.setAdditionalneeds(additionalneed);
      bookingPojo.setBookingdates(bookingdates);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .when()
                .body(bookingPojo)
                .post(EndPoints.CREATE_BOOKING)
                .then();

    }

    @Step("Verify Newly Create Booking")
    public ValidatableResponse VerifyBooking(int bookingid) {
        System.out.println(bookingid);
        return SerenityRest.given().log().all()
                //.header("Authorization","Bearer f4a966cfb8b1f8762386c84c483edfe3e4d73f9b92bd3e88426ca701e310525b")
                .pathParam("bookingID", bookingid)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }

    public ValidatableResponse updateBooking(int bookingid, String firstname, String lastname, int totalprice, boolean depositpaid,
                                             BookingPojo.BookingDates bookingdates, String additionalneed) {


        BookingPojo bookingPojoFinal = new BookingPojo();

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setAdditionalneeds(additionalneed);
        bookingPojo.setBookingdates(bookingdates);


        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("bookingID", bookingid)
                .when()
                .body(bookingPojoFinal)
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step("Deleting booking information with bookingId:{0}")
    public ValidatableResponse deleteBooking(int bookingId) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("bookingID", bookingId)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }


}


