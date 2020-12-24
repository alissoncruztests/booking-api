package com.api.bookings.repository;

import com.api.bookings.model.BookingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookingJpaRepository extends CrudRepository<BookingModel, String> {
    Collection<BookingModel> findByIdUser(String idUser);
}
