package com.api.workstation.service;

import com.api.workstation.model.OfficeModel;

import java.util.Collection;

public interface IOfficeService {

    OfficeModel createOffice(OfficeModel model);
    void updateOffice(OfficeModel model);
    Boolean deleteOffice(String id);
    OfficeModel findById(String id);
    Collection<OfficeModel> findAll();

}
