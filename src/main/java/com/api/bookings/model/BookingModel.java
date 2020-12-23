package com.api.bookings.model;

import com.api.bookings.model.Enums.StatusBookingEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("BookingDocument")
public class BookingModel {

    @Id
    private String id;

    private String idUser;
    private String idPlace;
    private String idBusiness;
    private DateTime bookingDate;
    private DateTime checkInDate;
    private DateTime checkoutDate;
    private String startedTime;
    private String finishedTime;
    private Integer sizeHours;
    private StatusBookingEnum statusBookingEnum;
    private DateTime createdDate;
    private DateTime updatedDate;
    private String codeBar;
}