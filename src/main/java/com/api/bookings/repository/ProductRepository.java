package com.api.bookings.repository;



import com.api.bookings.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductModel, String> {

}
