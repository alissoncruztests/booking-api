package com.api.bookings.service.impl;

import com.api.bookings.model.BookingModel;
import com.api.bookings.model.Enums.StatusBookingEnum;
import com.api.bookings.repository.BookingRepository;
import com.api.bookings.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static org.joda.time.DateTime.now;

@Service
public class BookingServiceImpl implements IBookingService {


    @Autowired
    private BookingRepository bookingRepository;


    @Override
    public BookingModel createBooking(BookingModel bookingModel) {
        if (bookingModel == null){
            throw new RuntimeException();
        }
        bookingModel.setCreatedDate(now());
        bookingModel.setStatusBookingEnum(StatusBookingEnum.RESERVED);

        return bookingRepository.save(bookingModel);
    }

    @Override
    public BookingModel findById(String id) {
        verifyIfPersonExists(id);
        Optional<BookingModel> model = bookingRepository.findById(id);
        return model.get();
    }

    @Override
    public void updateBooking(String id, BookingModel model) {
        verifyIfPersonExists(id);
        bookingRepository.save(model);
    }

    @Override
    public void createCheckin(String id, BookingModel model) {
        model.setCheckInDate(now());
        model.setStatusBookingEnum(StatusBookingEnum.IN_USE);
        model.setUpdatedDate(now());
        bookingRepository.save(model);
    }

    @Override
    public void createCheckout(String id, BookingModel model) {
        model.setCheckoutDate(now());
        model.setStatusBookingEnum(StatusBookingEnum.FINISHED);
        model.setUpdatedDate(now());
        bookingRepository.save(model);

    }

    @Override
    public Collection<BookingModel> findByIdUser(String idUser) {
        return bookingRepository.findByIdUser(idUser);
    }


    private void verifyIfPersonExists(String id){
        if (!bookingRepository.findById(id).isPresent()){
            throw new RuntimeException();
        }
    }
}