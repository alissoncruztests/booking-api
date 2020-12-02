package com.api.bookings.service;

import com.api.bookings.model.WorkstationModel;

import java.util.Collection;

public interface IWorkstationService {

    WorkstationModel createWorkstation(WorkstationModel model);
    WorkstationModel findById(String id);

    Collection<WorkstationModel> findAll();

    void delete(String id);

    void update(String id);

    void unBlockedWorkstation(String id);

    WorkstationModel blockedWorkstation(String id);
}
