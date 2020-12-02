package com.api.workstation.controller;



import com.api.workstation.api.WorkstationApi;
import com.api.workstation.model.ApiWorkstationCodeTO;
import com.api.workstation.model.ApiWorkstationTO;
import com.api.workstation.model.ApiWorkstationsResponseTO;
import com.api.workstation.controller.mapper.WorkstationMapper;
import com.api.workstation.model.Enums.StatusEnum;
import com.api.workstation.model.WorkstationModel;
import com.api.workstation.service.IWorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Collection;

import static com.api.workstation.service.utils.ParserUtil.convertValue;

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

        WorkstationModel model =  convertValue(newWorkstation, WorkstationModel.class);

        WorkstationModel response =  workstationService.createWorkstation(model);

        ApiWorkstationCodeTO to = convertValue(response, ApiWorkstationCodeTO.class);

        return ResponseEntity.ok(to);

    }

    @Override
    public ResponseEntity<ApiWorkstationsResponseTO> getWorkstation() {

        ApiWorkstationsResponseTO to = new ApiWorkstationsResponseTO();

        Collection<WorkstationModel> list = workstationService.findAll();
        list.stream().forEach(w -> {
            ApiWorkstationTO values = new ApiWorkstationTO();
            values = convertValue(w, ApiWorkstationTO.class);
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


