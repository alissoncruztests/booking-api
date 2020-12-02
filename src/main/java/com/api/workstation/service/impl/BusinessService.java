package com.api.workstation.service.impl;

import com.api.workstation.model.Enums.StatusEnum;
import com.api.workstation.model.BusinessModel;
import com.api.workstation.repository.OfficeRepository;
import com.api.workstation.service.IBusinessService;
import com.api.workstation.service.exception.OfficeNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static org.joda.time.DateTime.now;

@Service
public class BusinessService implements IBusinessService {

    private OfficeRepository officeRepository;

    @Autowired
    public BusinessService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public BusinessModel createBusiness(BusinessModel model) {
        if(model == null){
            throw new RuntimeException();
        }
        model.setCreatedDate(now());
        if(model.getStatusEnum() == null){
            model.setStatusEnum(StatusEnum.ACTIVE);
        }

        verifyOfficerName(model.getName());

        return officeRepository.save(model);
    }

    private void verifyOfficerName(String name) {
        if (officeRepository.findByName(name) != null){
            throw new OfficeNameException();
        }
    }

    @Override
    public void updateBusiness(BusinessModel model) {
        BusinessModel update = findById(model.getId());
        update.setUpdatedDate(now());
        officeRepository.save(update);
    }

    @Override
    public Boolean deleteBusiness(String id) {
        BusinessModel deleteOffice = findById(id);
        if(deleteOffice != null){
            officeRepository.delete(deleteOffice);
            return true;
        }
        return false;
    }

    @Override
    public BusinessModel findById(String id) {
        Optional<BusinessModel> result = officeRepository.findById(id);
        if (!result.isPresent()){
            return null;
        }
        return result.get();
    }

    @Override
    public Collection<BusinessModel> findAll() {
        return officeRepository.findAll();
    }

}
