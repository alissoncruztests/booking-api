package com.api.bookings.controller;

import com.api.bookings.api.BusinessApi;
import com.api.bookings.controller.mapper.BusinessMapper;
import com.api.bookings.model.ApiBusinessResponseTO;
import com.api.bookings.model.ApiBusinessTO;
import com.api.bookings.model.ApiBusinessesResponseTO;
import com.api.bookings.model.BusinessModel;
import com.api.bookings.service.impl.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

import static com.api.bookings.controller.mapper.BusinessMapper.*;

@RestController
public class BusinessController extends BaseController implements BusinessApi {

    private BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @Override
    public ResponseEntity<ApiBusinessResponseTO> createBusiness(@Valid ApiBusinessTO office) {
        BusinessModel model = marshallBusinessTo(office);
        ApiBusinessTO response = BusinessMapper.unmarshallBusinessTo(businessService.createBusiness(model));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Void> deleteBusiness(String id) {
        Boolean status = businessService.deleteBusiness(id);
        if (!status){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ApiBusinessesResponseTO> getBusiness() {
        Collection<BusinessModel> models = businessService.findAll();
        if(models.size() == 0){
            return ResponseEntity.notFound().build();
        }
        ApiBusinessesResponseTO response = unmarshallAll(models);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiBusinessResponseTO> getBusinessById(String id) {
        BusinessModel model = null;

        model = businessService.findById(id);

        if(model == null){
            return ResponseEntity.notFound().build();
        }

        ApiBusinessResponseTO response = unmarshallBusinessResposeTo(model);
        return ResponseEntity.ok(response);
    }

}
