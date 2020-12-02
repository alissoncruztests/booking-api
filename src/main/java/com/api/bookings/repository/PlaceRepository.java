package com.api.bookings.repository;


import com.api.bookings.model.PlaceModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends MongoRepository<PlaceModel, String> {

}
