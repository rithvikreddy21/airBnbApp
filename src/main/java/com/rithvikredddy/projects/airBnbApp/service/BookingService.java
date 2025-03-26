package com.rithvikredddy.projects.airBnbApp.service;

import com.rithvikredddy.projects.airBnbApp.dto.BookingDto;
import com.rithvikredddy.projects.airBnbApp.dto.BookingRequest;
import com.rithvikredddy.projects.airBnbApp.dto.GuestDto;
import com.rithvikredddy.projects.airBnbApp.dto.HotelReportDto;
import com.stripe.model.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {

    BookingDto initializeBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayment(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    String getBookingStatus(Long bookingId);

    List<BookingDto> getAllBookingsByHotelId(Long hotelId);

    HotelReportDto getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    List<BookingDto> getMyBookings();
}
