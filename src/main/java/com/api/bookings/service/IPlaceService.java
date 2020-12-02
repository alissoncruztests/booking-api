package com.api.bookings.service;

import com.api.bookings.model.PlaceModel;

import java.util.Collection;

public interface IPlaceService {

    PlaceModel createPlace(PlaceModel model);
    PlaceModel findById(String id);

    Collection<PlaceModel> findAll();

    void delete(String id);

    void update(String id);

    void unBlockedPlace(String id);

    PlaceModel blockedPlace(String id);
}
