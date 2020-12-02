package com.api.workstation.repository;


import com.api.workstation.model.OfficeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends MongoRepository<OfficeModel, String> {
    OfficeModel findByName(String name);
}
