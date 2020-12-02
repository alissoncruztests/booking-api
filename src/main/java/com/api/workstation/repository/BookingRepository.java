package com.api.workstation.repository;

import com.api.workstation.model.BookingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookingRepository extends MongoRepository<BookingModel, String> {
    Collection<BookingModel> findByIdUser(String idUser);
}
