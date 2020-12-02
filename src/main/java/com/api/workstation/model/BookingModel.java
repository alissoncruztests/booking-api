package com.api.workstation.model;

import com.api.workstation.model.Enums.StatusBookingEnum;
import com.api.workstation.model.Enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("BookingDocument")
public class BookingModel {

    @Id
    private String id;
    private String idUser;
    private String idWorkstation;
    private String idOffice;
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