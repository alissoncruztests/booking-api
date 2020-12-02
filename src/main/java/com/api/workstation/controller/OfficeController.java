package com.api.workstation.controller;

import com.api.workstation.api.OfficeApi;
import com.api.workstation.model.ApiOfficeResponseTO;
import com.api.workstation.model.ApiOfficeTO;
import com.api.workstation.model.ApiOfficesResponseTO;
import com.api.workstation.model.OfficeModel;
import com.api.workstation.service.impl.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Collection;

import static com.api.workstation.controller.mapper.OfficeMapper.*;

@RestController
public class OfficeController extends BaseController implements OfficeApi {

    private OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @Override
    public ResponseEntity<ApiOfficeTO> createOffice(@Valid ApiOfficeTO office) {
        OfficeModel model = marshallOfficeTo(office);
        ApiOfficeTO response = unmarshallOfficeTo(officeService.createOffice(model));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteOffice(String id) {
        Boolean status = officeService.deleteOffice(id);
        if (!status){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ApiOfficesResponseTO> getOffice() {
        Collection<OfficeModel> models = officeService.findAll();
        if(models.size() == 0){
            return ResponseEntity.notFound().build();
        }
        ApiOfficesResponseTO response = unmarshallAll(models);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiOfficeResponseTO> getOfficeById(String id) {
        OfficeModel model = null;

        model = officeService.findById(id);

        if(model == null){
            return ResponseEntity.notFound().build();
        }

        ApiOfficeResponseTO response = unmarshallOfficeResposeTo(model);
        return ResponseEntity.ok(response);
    }
}
