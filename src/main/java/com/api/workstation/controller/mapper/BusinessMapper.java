package com.api.workstation.controller.mapper;

import com.api.workstation.model.*;
import com.api.workstation.model.Enums.StatusEnum;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.api.workstation.model.Enums.StatusEnum.ACTIVE;

public class BusinessMapper {

    private BusinessMapper(){
        super();
    }


    public static Collection<BusinessModel> marshall(Collection<ApiBusinessTO> tos){
        return tos.stream().map(BusinessMapper::marshallBusinessTo).collect(Collectors.toList());
    }

    public static Collection<ApiBusinessTO> unmarshall(Collection<BusinessModel> models){
        return models.stream().map(BusinessMapper::unmarshallBusinessTo).collect(Collectors.toList());
    }

    public static ApiBusinessesResponseTO unmarshallAll(Collection<BusinessModel> models){
        ApiBusinessesResponseTO to = new ApiBusinessesResponseTO();
        models.stream().forEach(model -> {
            ApiBusinessResponseTO to1 = new ApiBusinessResponseTO();
            to1 = unmarshallBusinessResposeTo(model);
            to.addOfficesItem(to1);
        });

        return to;
    }


    public static BusinessModel marshallBusinessTo(ApiBusinessTO to){

        return BusinessModel.builder()
                .id(to.getId())
                .address(to.getAddress())
                .name(to.getName())
                .statusEnum(to.getStatus().name().equals("ACTIVE") ? ACTIVE : StatusEnum.INACTIVE)
                .build();
    }

    public static ApiBusinessTO unmarshallBusinessTo(BusinessModel model){
        ApiBusinessTO to = new ApiBusinessTO();
        to.setName(model.getName());
        to.setAddress(model.getAddress());
        to.setId(model.getId());
        to.setStatus(model.getStatusEnum().name().equals("ACTIVE") ? ApiBusinessTO.StatusEnum.ACTIVE : ApiBusinessTO.StatusEnum.INACTIVE);

        return to;

    }

    public static ApiBusinessResponseTO unmarshallBusinessResposeTo(BusinessModel model){
        ApiBusinessResponseTO to = new ApiBusinessResponseTO();
        to.setName(model.getName());
        to.setAddress(model.getAddress());
        to.setId(model.getId());
        to.setCreatedDate(model.getCreatedDate());
        to.setStatus(model.getStatusEnum().name().equals("ACTIVE") ? ApiBusinessResponseTO.StatusEnum.ACTIVE : ApiBusinessResponseTO.StatusEnum.INACTIVE);
        return to;

    }
}
