package com.api.workstation.service;

import com.api.workstation.model.BookingModel;

import java.util.Collection;

public interface IBookingService {
    BookingModel createBooking(BookingModel bookingModel);

    BookingModel findById(String id);

    void updateBooking(String id, BookingModel model);

    void createCheckin(String id, BookingModel booking);

    void createCheckout(String id, BookingModel booking);

    Collection<BookingModel> findByIdUser(String idUser);
}
