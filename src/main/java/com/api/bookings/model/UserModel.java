package com.api.bookings.model;

import com.api.bookings.model.Enums.StatusEnum;
import com.api.bookings.model.Enums.TypeUserEnum;
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
@Document("UserDocument")
public class UserModel {

    @Id
    private String id;

    private String name;

    private String user;

    private String password;

    private String email;

    private DateTime createdDate;

    private DateTime updatedDate;

    private StatusEnum statusEnum;

    private TypeUserEnum type;

    private LoginModel loginModel;
}