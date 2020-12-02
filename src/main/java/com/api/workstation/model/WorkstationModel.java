package com.api.workstation.model;

import com.api.workstation.model.Enums.StatusEnum;
import com.api.workstation.model.Enums.TypeWorkstationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("WorkstationDocument")
public class WorkstationModel {

    @Id
    private String id;
    @UniqueElements
    private String name;
    private String idOffice;
    private StatusEnum status;
    private String description;
    private DateTime createdDate;
    private DateTime updatedDate;
    private String group;
    private Boolean blocked;
    private TypeWorkstationEnum typeWorkstation;

}
