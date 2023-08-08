package com.restful.booker.constants;

public class EndPoints {
    /**
     * This is Endpoints of Auth
     */
    public static final String GET_ALL_AUTHORISATION = "/authorisation";


    /**
     * This is Endpoints of Booking
     */
    public static final String GET_ALL_BOOKING = "/bookings";

    public static final String CREATE_BOOKING ="/booking";
    public static final String GET_SINGLE_BOOKING_BY_ID = "/{bookingsID}";
    public static final String UPDATE_BOOKING_BY_ID = "/{bookingsID}";
    public static final String DELETE_BOOKING_BY_ID = "/{bookingsID}";
}
