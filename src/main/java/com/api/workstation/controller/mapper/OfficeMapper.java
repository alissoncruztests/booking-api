package com.api.workstation.controller.mapper;

import com.api.workstation.model.ApiOfficeResponseTO;
import com.api.workstation.model.ApiOfficeTO;
import com.api.workstation.model.ApiOfficesResponseTO;
import com.api.workstation.model.Enums.StatusEnum;
import com.api.workstation.model.OfficeModel;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.api.workstation.model.Enums.StatusEnum.ACTIVE;

public class OfficeMapper {

    private OfficeMapper(){
        super();
    }


    public static Collection<OfficeModel> marshall(Collection<ApiOfficeTO> tos){
        return tos.stream().map(OfficeMapper::marshallOfficeTo).collect(Collectors.toList());
    }

    public static Collection<ApiOfficeTO> unmarshall(Collection<OfficeModel> models){
        return models.stream().map(OfficeMapper::unmarshallOfficeTo).collect(Collectors.toList());
    }

    public static ApiOfficesResponseTO unmarshallAll(Collection<OfficeModel> models){
        ApiOfficesResponseTO to = new ApiOfficesResponseTO();
        models.stream().forEach(model -> {
            ApiOfficeResponseTO to1 = new ApiOfficeResponseTO();
            to1 = unmarshallOfficeResposeTo(model);
            to.addOfficesItem(to1);
        });

        return to;
    }


    public static OfficeModel marshallOfficeTo(ApiOfficeTO to){

        return OfficeModel.builder()
                .id(to.getId())
                .address(to.getAddress())
                .name(to.getName())
                .statusEnum(to.getStatus().name().equals("ACTIVE") ? ACTIVE : StatusEnum.INACTIVE)
                .build();
    }

    public static ApiOfficeTO unmarshallOfficeTo(OfficeModel model){
        ApiOfficeTO to = new ApiOfficeTO();
        to.setName(model.getName());
        to.setAddress(model.getAddress());
        to.setId(model.getId());
        to.setStatus(model.getStatusEnum().name().equals("ACTIVE") ? ApiOfficeTO.StatusEnum.ACTIVE : ApiOfficeTO.StatusEnum.INACTIVE);

        return to;

    }

    public static ApiOfficeResponseTO unmarshallOfficeResposeTo(OfficeModel model){
        ApiOfficeResponseTO to = new ApiOfficeResponseTO();
        to.setName(model.getName());
        to.setAddress(model.getAddress());
        to.setId(model.getId());
        to.setCreatedDate(model.getCreatedDate());
        to.setStatus(model.getStatusEnum().name().equals("ACTIVE") ? ApiOfficeResponseTO.StatusEnum.ACTIVE : ApiOfficeResponseTO.StatusEnum.INACTIVE);
        return to;

    }
}
