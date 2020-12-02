package com.api.bookings.repository;


import com.api.bookings.model.BusinessModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends MongoRepository<BusinessModel, String> {
    BusinessModel findByName(String name);
}
