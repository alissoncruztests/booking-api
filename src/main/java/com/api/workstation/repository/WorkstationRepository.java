package com.api.workstation.repository;


import com.api.workstation.model.WorkstationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkstationRepository extends MongoRepository<WorkstationModel, String> {

}
