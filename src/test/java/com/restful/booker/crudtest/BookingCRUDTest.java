package com.restful.booker.crudtest;

import com.restful.booker.restfulbookersteps.BookingSteps;
import com.restful.booker.testbase.BookingTestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class BookingCRUDTest extends BookingTestBase {
    static String firstname = "Prime" + TestUtils.getRandomValue();
    static String lastname = "Testing" + TestUtils.getRandomValue();
    static int totalPrice = 111;
    static boolean depositpaid = true;
    static String additionalneed = "Bed and Breakfast";
    static String checkin = "2022-01-07";
    static String checkout = "2022-01-20";
    static int bookingId;

    @Steps
    BookingSteps bookingSteps;

    @Title("This will create a new booking")
    @Test
    public void test001() {
        ValidatableResponse response = bookingSteps.createBooking(firstname, lastname, totalPrice, depositpaid, checkin, checkout, additionalneed);
        response.log().all().statusCode(200);
        bookingId = response.extract().path("bookingid");
        System.out.println("NEWLY CREATED STORE ID IS:" + bookingId);
    }

    @Title("Verify if the booking was added to the application")
    @Test
    public void test002() {
        ValidatableResponse response=bookingSteps.VerifyBooking(bookingId);
        response.log().all().statusCode(200);


    }

    @Title("Update the booking information and verify the update information")
    @Test
    public void test003() {
        firstname = firstname + "_update";
        bookingSteps.updateBooking(bookingId, firstname, lastname, totalPrice, depositpaid, additionalneed, checkin, checkout).statusCode(200);
        ValidatableResponse response=bookingSteps.updateBooking(bookingId,firstname,lastname,totalPrice,depositpaid,checkin,checkout,additionalneed);
        response.log().all().statusCode(200);
    }

    @Title("Delete the booking and verify if the booking is deleted!")
    @Test
    public void test004() {

        ValidatableResponse response=bookingSteps.deleteBooking(bookingId);
        response.log().all().statusCode(201);
    }
}

