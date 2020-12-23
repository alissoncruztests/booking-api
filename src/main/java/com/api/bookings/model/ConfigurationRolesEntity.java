package com.api.bookings.model;

import javax.persistence.*;

@Entity
@Table(name = "ConfigurationRolesEntity")
public class ConfigurationRolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
