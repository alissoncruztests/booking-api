package com.api.bookings.controller.mapper;

import com.api.bookings.model.*;
import com.api.bookings.model.BookingModel;
import com.api.bookings.model.Enums.StatusBookingEnum;

import java.util.ArrayList;
import java.util.Collection;

import static org.joda.time.DateTime.now;

public class BookingMapper {

    public static BookingModel marshallToModel(ApiBookingTO booking){

        return null;
    }

    public static Collection<BookingModel> marshallToModels(ApiBookingsTO booking){
        Collection<BookingModel> models = new ArrayList<>();

        booking.getBookings().stream().forEach(b ->{
            BookingModel m = BookingModel.builder()
                    .idPlace(b.getIdPlace())
                    .idBusiness(b.getIdBusiness())
                    .bookingDate(b.getBookingDate())
                    .idUser(b.getIdUser())
                    .sizeHours(Integer.valueOf(b.getSizeHours()))
                    .startedTime(b.getStartedTime())
                    .finishedTime(b.getFinishedTime())
                    .createdDate(now())
                    .statusBookingEnum( statusBooking(b.getStatus()) )
                    .build();
            models.add(m);
        });

        return models;
    }

    private static StatusBookingEnum statusBooking(ApiBookingTO.StatusEnum status) {
        switch (status.ordinal()){
            case 1 :
                return  StatusBookingEnum.ACTIVE;
            case 2 :
                return  StatusBookingEnum.INACTIVE;

            case 3 :
                return  StatusBookingEnum.CANCELED;

            case 4 :
                return  StatusBookingEnum.FINISHED;

            case 5 :
                return  StatusBookingEnum.IN_USE;

            case 6 :
                return  StatusBookingEnum.RESERVED;

            case 7 :
                return  StatusBookingEnum.BLOCKED;

            default:
                return  StatusBookingEnum.OTHERS;
        }

    }

    private static ApiBookingResponseTO.StatusEnum statusBookingTo(StatusBookingEnum status) {
        switch (status.name()){
            case "ACTIVE" :
                return  ApiBookingResponseTO.StatusEnum.ACTIVE;
            case "INACTIVE" :
                return  ApiBookingResponseTO.StatusEnum.INACTIVE;

            case "CANCELED" :
                return  ApiBookingResponseTO.StatusEnum.CANCELED;

            case "FINISHED" :
                return  ApiBookingResponseTO.StatusEnum.FINISHED;

            case "IN_USE" :
                return  ApiBookingResponseTO.StatusEnum.IN_USE;

            case "RESERVED" :
                return  ApiBookingResponseTO.StatusEnum.RESERVED;

            case "BLOCKED" :
                return  ApiBookingResponseTO.StatusEnum.BLOCKED;

            default:
                return  ApiBookingResponseTO.StatusEnum.OTHERS;
        }

    }

    public static ApiBookingResponseTO unmarshallTo(BookingModel model){

        ApiBookingResponseTO to = new ApiBookingResponseTO();
        to.setId(model.getId());
        to.setIdUser(model.getIdUser());
        to.setBookingDate(model.getBookingDate());
        to.setCheckinDate(model.getCheckInDate());
        to.setCheckoutDate(model.getCheckoutDate());
        to.setIdPlace(model.getIdPlace());
        to.setStatus(statusBookingTo(model.getStatusBookingEnum()));
        to.setSizeHours(model.getSizeHours().toString());
        to.setStartedDatetime(model.getStartedTime());
        to.setFinishedDatetime(model.getFinishedTime());
        to.setIdBusiness(model.getIdBusiness());
        to.setCodeBar(model.getCodeBar());
        return to;
    }

}
