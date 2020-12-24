package com.api.bookings.repository;

import com.api.bookings.model.EndpointsConfigurationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointsConfigurationRepository extends CrudRepository<EndpointsConfigurationEntity, Long> {
}
