package com.api.workstation.service.impl;

import com.api.workstation.model.Enums.StatusEnum;
import com.api.workstation.model.OfficeModel;
import com.api.workstation.repository.OfficeRepository;
import com.api.workstation.service.IOfficeService;
import com.api.workstation.service.exception.OfficeNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static org.joda.time.DateTime.now;

@Service
public class OfficeService implements IOfficeService {

    private OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public OfficeModel createOffice(OfficeModel model) {
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
    public void updateOffice(OfficeModel model) {
        OfficeModel update = findById(model.getId());
        update.setUpdatedDate(now());
        officeRepository.save(update);
    }

    @Override
    public Boolean deleteOffice(String id) {
        OfficeModel deleteOffice = findById(id);
        if(deleteOffice != null){
            officeRepository.delete(deleteOffice);
            return true;
        }
        return false;
    }

    @Override
    public OfficeModel findById(String id) {
        Optional<OfficeModel> result = officeRepository.findById(id);
        if (!result.isPresent()){
            return null;
        }
        return result.get();
    }

    @Override
    public Collection<OfficeModel> findAll() {
        return officeRepository.findAll();
    }

}
