package com.api.bookings.service;

import com.api.bookings.model.BusinessModel;

import java.util.Collection;

public interface IBusinessService {

    BusinessModel createBusiness(BusinessModel model);
    void updateBusiness(BusinessModel model);
    Boolean deleteBusiness(String id);
    BusinessModel findById(String id);
    Collection<BusinessModel> findAll();

}
