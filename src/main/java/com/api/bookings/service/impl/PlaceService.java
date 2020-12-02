package com.api.bookings.service.impl;

import com.api.bookings.model.PlaceModel;
import com.api.bookings.repository.PlaceRepository;
import com.api.bookings.service.IPlaceService;
import com.api.bookings.service.exception.PlaceNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.joda.time.DateTime.now;

@Service
public class PlaceService implements IPlaceService {


    private PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public PlaceModel createPlace(PlaceModel model) {

        if(model == null){
            throw new RuntimeException();
        }

        verifyWorkStationName(model);

        model.setCreatedDate(now());

        return placeRepository.save(model);
    }

    @Override
    public PlaceModel findById(String id) {

        Optional<PlaceModel> model = placeRepository.findById(id);
        if(!model.isPresent()){
            return  null;
        }

        return model.get();
    }

    @Override
    public Collection<PlaceModel> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public void delete(String id) {
        placeRepository.delete(findById(id));

    }

    @Override
    public void update(String id) {
        placeRepository.save(findById(id));
    }

    @Override
    public void unBlockedPlace(String id) {
        PlaceModel model = findById(id);
        model.setBlocked(Boolean.FALSE);
        model.setUpdatedDate(now());
        placeRepository.save(model);
    }

    @Override
    public PlaceModel blockedPlace(String id) {
        PlaceModel model = findById(id);
        if (model == null){
            return null;
        }
        model.setBlocked(Boolean.TRUE);
        model.setUpdatedDate(now());
        return placeRepository.save(model);
    }

    private void verifyWorkStationName(PlaceModel model){
        Collection<PlaceModel> placeModelList = this.findAll();

        List<PlaceModel> filterGroup = placeModelList.stream()
                .filter(placeModel -> placeModel.getGroup().equals(model.getGroup()))
                .collect(Collectors.toList());

        long count = filterGroup.stream()
                .filter(placeModel -> placeModel.getName().equals(model.getName()))
                .count();

        if (count >= 1){
            throw new PlaceNameException();
        }
    }
}