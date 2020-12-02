package com.api.workstation.service.impl;

import com.api.workstation.model.WorkstationModel;
import com.api.workstation.repository.WorkstationRepository;
import com.api.workstation.service.IWorkstationService;
import com.api.workstation.service.exception.WorkstationNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.joda.time.DateTime.now;

@Service
public class WorkstationService implements IWorkstationService {


    private WorkstationRepository workstationRepository;

    @Autowired
    public WorkstationService(WorkstationRepository workstationRepository) {
        this.workstationRepository = workstationRepository;
    }

    @Override
    public WorkstationModel createWorkstation(WorkstationModel model) {

        if(model == null){
            throw new RuntimeException();
        }

        verifyWorkStationName(model);

        model.setCreatedDate(now());

        return workstationRepository.save(model);
    }

    @Override
    public WorkstationModel findById(String id) {

        Optional<WorkstationModel> model = workstationRepository.findById(id);
        if(!model.isPresent()){
            return  null;
        }

        return model.get();
    }

    @Override
    public Collection<WorkstationModel> findAll() {
        return workstationRepository.findAll();
    }

    @Override
    public void delete(String id) {
        workstationRepository.delete(findById(id));

    }

    @Override
    public void update(String id) {
        workstationRepository.save(findById(id));
    }

    @Override
    public void unBlockedWorkstation(String id) {
        WorkstationModel model = findById(id);
        model.setBlocked(Boolean.FALSE);
        model.setUpdatedDate(now());
        workstationRepository.save(model);
    }

    @Override
    public WorkstationModel blockedWorkstation(String id) {
        WorkstationModel model = findById(id);
        if (model == null){
            return null;
        }
        model.setBlocked(Boolean.TRUE);
        model.setUpdatedDate(now());
        return workstationRepository.save(model);
    }

    private void verifyWorkStationName(WorkstationModel model){
        Collection<WorkstationModel> workstationModelList = this.findAll();

        List<WorkstationModel> filterGroup = workstationModelList.stream()
                .filter(workstationModel -> workstationModel.getGroup().equals(model.getGroup()))
                .collect(Collectors.toList());

        long count = filterGroup.stream()
                .filter(workstationModel -> workstationModel.getName().equals(model.getName()))
                .count();

        if (count >= 1){
            throw new WorkstationNameException();
        }
    }
}