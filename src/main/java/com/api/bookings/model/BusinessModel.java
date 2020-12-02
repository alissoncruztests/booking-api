package com.api.bookings.model;

import com.api.bookings.model.Enums.StatusEnum;
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
@Document("BusinessDocument")
public class BusinessModel {

    @Id
    private String id;

    private String name;
    private String address;
    private StatusEnum statusEnum;
    private DateTime createdDate;
    private DateTime updatedDate;
}
