package com.api.workstation.controller.mapper;

import com.api.workstation.model.*;
import com.api.workstation.model.Enums.StatusEnum;
import com.api.workstation.model.Enums.TypeUserEnum;
import com.api.workstation.model.UserModel;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper(){
        super();
    }

    public static Collection<UserModel> marshall(Collection<ApiUserTO> tos){
        return tos.stream().map(UserMapper::marshallUserTo).collect(Collectors.toList());
    }

    public static Collection<ApiUserResponseTO> unmarshall(Collection<UserModel> models){
        return models.stream().map(UserMapper::unmarshallUserTo).collect(Collectors.toList());
    }

    public static UserModel marshallUserTo(ApiUserTO to){

        return UserModel.builder()
                .name(to.getName())
                .user(to.getUser())
                .email(to.getEmail())
                .statusEnum(to.getStatus().name().equals("ACTIVE") ? StatusEnum.ACTIVE : StatusEnum.INACTIVE)
                .type(to.getType().name().equals("ADMIN") ? TypeUserEnum.ADMIN : TypeUserEnum.CLIENT)
                .build();
    }

    public static ApiUserResponseTO unmarshallUserTo(UserModel model){
        ApiUserResponseTO to = new  ApiUserResponseTO();
        to.setId(model.getId());
        to.setName(model.getName());
        to.setUser(model.getUser());
        to.setEmail(model.getEmail());
        to.setCreatedDate(model.getCreatedDate());
        to.setUpdateDate(model.getUpdatedDate());
        to.setStatus(model.getStatusEnum().name().equals("ACTIVE") ? ApiUserResponseTO.StatusEnum.ATIVO : ApiUserResponseTO.StatusEnum.INATIVO);
        to.setType(model.getType().name().equals("ADMIN") ? ApiUserResponseTO.TypeEnum.ADMIN : ApiUserResponseTO.TypeEnum.CLIENTE);
        return to;
    }


    public static ApiUserCodeTO unmarshallApiUserCodeTO(UserModel model){
        ApiUserCodeTO to = new  ApiUserCodeTO();
        to.setId(model.getId());
        return to;
    }
}