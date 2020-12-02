package com.api.bookings.repository;


import com.api.bookings.model.WorkstationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkstationRepository extends MongoRepository<WorkstationModel, String> {

}
