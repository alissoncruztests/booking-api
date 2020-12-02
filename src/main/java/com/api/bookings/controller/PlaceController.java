package com.api.bookings.controller;


import com.api.bookings.api.PlaceApi;
import com.api.bookings.controller.mapper.PlaceMapper;
import com.api.bookings.model.ApiPlaceCodeTO;
import com.api.bookings.model.ApiPlaceTO;
import com.api.bookings.model.ApiPlacesResponseTO;
import com.api.bookings.model.PlaceModel;
import com.api.bookings.service.IPlaceService;
import com.api.bookings.service.utils.ParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class PlaceController extends BaseController implements PlaceApi {

    private IPlaceService workstationService;

    @Autowired
    public PlaceController(IPlaceService IPlaceService) {
        this.workstationService = IPlaceService;
    }

    @Override
    public ResponseEntity<Void> blockedPlace(String id) {
        PlaceModel model = workstationService.blockedPlace(id);
        if (model == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ApiPlaceCodeTO> createPlace(@Valid ApiPlaceTO newPlace) {

        PlaceModel model =  ParserUtil.convertValue(newPlace, PlaceModel.class);

        PlaceModel response =  workstationService.createPlace(model);

        ApiPlaceCodeTO to = ParserUtil.convertValue(response, ApiPlaceCodeTO.class);

        return ResponseEntity.ok(to);

    }

    @Override
    public ResponseEntity<ApiPlacesResponseTO> getPlace() {

        ApiPlacesResponseTO to = new ApiPlacesResponseTO();

        Collection<PlaceModel> list = workstationService.findAll();
        list.stream().forEach(w -> {
            ApiPlaceTO values = new ApiPlaceTO();
            values = ParserUtil.convertValue(w, ApiPlaceTO.class);
            to.addPlacesItem(values);
        });

        return ResponseEntity.ok(to);
    }

    @Override
    public ResponseEntity<ApiPlaceTO> getPlaceById(String id) {

        PlaceModel document = workstationService.findById(id);

        if (document == null){
            return ResponseEntity.notFound().build();
        }
        ApiPlaceTO to = PlaceMapper.marshall(document);

        return ResponseEntity.ok(to);
    }

    @Override
    public ResponseEntity<Void> unblockedPlace(String id) {
        workstationService.unBlockedPlace(id);
        return ResponseEntity.noContent().build();
    }


}


