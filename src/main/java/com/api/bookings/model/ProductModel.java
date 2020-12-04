package com.api.bookings.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductModel {

    @Id
    private String code;

    private String name;

    private String description;

    private String category;

    private BigDecimal price;

    private String user;

    private DateTime date;


}
