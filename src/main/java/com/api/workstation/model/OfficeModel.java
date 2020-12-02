package com.api.workstation.model;

import com.api.workstation.model.Enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("OfficeDocument")
public class OfficeModel {

    @Id
    private String id;

    private String name;
    private String address;
    private StatusEnum statusEnum;
    private DateTime createdDate;
    private DateTime updatedDate;
}
