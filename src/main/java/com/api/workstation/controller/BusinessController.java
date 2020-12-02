package com.api.workstation.controller;

import com.api.workstation.api.BusinessApi;
import com.api.workstation.controller.mapper.BusinessMapper;
import com.api.workstation.model.ApiBusinessResponseTO;
import com.api.workstation.model.ApiBusinessTO;
import com.api.workstation.model.ApiBusinessesResponseTO;
import com.api.workstation.model.BusinessModel;
import com.api.workstation.service.impl.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;
import java.util.Collection;

import static com.api.workstation.controller.mapper.BusinessMapper.*;

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
