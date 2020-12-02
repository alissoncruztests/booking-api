package com.api.bookings.controller;

import com.api.bookings.api.BookingApi;
import com.api.bookings.controller.mapper.BookingMapper;
import com.api.bookings.model.*;
import com.api.bookings.service.IBookingService;
import com.api.bookings.service.utils.ParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

import static com.api.bookings.controller.mapper.BookingMapper.marshallToModels;
import static com.api.bookings.controller.mapper.BookingMapper.unmarshallTo;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class BookingController extends BaseController implements BookingApi {


    private IBookingService bookingService;

    @Autowired
    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }


    @Override
    public ResponseEntity<ApiBookingResponseTO> createBookings(@Valid ApiBookingsTO booking) {
        Collection<BookingModel> models = marshallToModels(booking);

        models.stream().forEach( model ->{
            BookingModel bookingSaved = bookingService.createBooking(model);
            System.out.println("Id booking: "+bookingSaved.getId());
        });

        return new ResponseEntity<>(CREATED);
    }

    @Override
    public ResponseEntity<Void> createCheckin(String id, @Valid ApiCheckinCheckoutTO booking) {
        BookingModel model = bookingService.findById(id);
        model.setCodeBar(booking.getQrCode());
        bookingService.createCheckin(id, model);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> createCheckout(String id, @Valid ApiCheckinCheckoutTO booking) {
        BookingModel model = bookingService.findById(id);
        bookingService.createCheckout(id, model);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<ApiBookingResponseTO> getBookingsById(String id) {
        BookingModel booking = bookingService.findById(id);
        ApiBookingResponseTO response = BookingMapper.unmarshallTo(booking);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiBookingsResponseTO> getBookingsByIdUser(String idUser) {
        Collection<BookingModel> models = bookingService.findByIdUser(idUser);

        ApiBookingsResponseTO responseTO =   new ApiBookingsResponseTO();

        models.stream().forEach(m -> {
            responseTO.addBookingsItem(unmarshallTo(m));
        });

        return ResponseEntity.ok(responseTO);
    }

    @Override
    public ResponseEntity<Void> updateBooking(String id, @Valid ApiBookingTO booking) {
        BookingModel model = ParserUtil.convertValue(booking, BookingModel.class);
        bookingService.updateBooking(id, model);

        return ResponseEntity.ok().build();
    }
}
