package com.api.bookings.controller;


import com.api.bookings.api.WorkstationApi;
import com.api.bookings.controller.mapper.WorkstationMapper;
import com.api.bookings.model.ApiWorkstationCodeTO;
import com.api.bookings.model.ApiWorkstationTO;
import com.api.bookings.model.ApiWorkstationsResponseTO;
import com.api.bookings.model.WorkstationModel;
import com.api.bookings.service.IWorkstationService;
import com.api.bookings.service.utils.ParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class WorkstationController extends BaseController implements WorkstationApi {

    private IWorkstationService workstationService;

    @Autowired
    public WorkstationController(IWorkstationService IWorkstationService) {
        this.workstationService = IWorkstationService;
    }

    @Override
    public ResponseEntity<Void> blockedWorkstation(String id) {
        WorkstationModel model = workstationService.blockedWorkstation(id);
        if (model == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ApiWorkstationCodeTO> createWorkstation(@Valid ApiWorkstationTO newWorkstation) {

        WorkstationModel model =  ParserUtil.convertValue(newWorkstation, WorkstationModel.class);

        WorkstationModel response =  workstationService.createWorkstation(model);

        ApiWorkstationCodeTO to = ParserUtil.convertValue(response, ApiWorkstationCodeTO.class);

        return ResponseEntity.ok(to);

    }

    @Override
    public ResponseEntity<ApiWorkstationsResponseTO> getWorkstation() {

        ApiWorkstationsResponseTO to = new ApiWorkstationsResponseTO();

        Collection<WorkstationModel> list = workstationService.findAll();
        list.stream().forEach(w -> {
            ApiWorkstationTO values = new ApiWorkstationTO();
            values = ParserUtil.convertValue(w, ApiWorkstationTO.class);
            to.addWorkstationsItem(values);
        });

        return ResponseEntity.ok(to);
    }

    @Override
    public ResponseEntity<ApiWorkstationTO> getWorkstationById(String id) {

        WorkstationModel document = workstationService.findById(id);

        if (document == null){
            return ResponseEntity.notFound().build();
        }
        ApiWorkstationTO to = WorkstationMapper.marshall(document);

        return ResponseEntity.ok(to);
    }

    @Override
    public ResponseEntity<Void> unblockedWorkstation(String id) {
        workstationService.unBlockedWorkstation(id);
        return ResponseEntity.noContent().build();
    }


}


