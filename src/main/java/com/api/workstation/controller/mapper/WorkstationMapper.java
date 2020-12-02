package com.api.workstation.controller.mapper;

import com.api.workstation.model.ApiWorkstationTO;
import com.api.workstation.model.WorkstationModel;

import static com.api.workstation.service.utils.ParserUtil.convertValue;

public class WorkstationMapper {

    public static ApiWorkstationTO marshall(WorkstationModel document){

        return convertValue(document, ApiWorkstationTO.class);
    }

    public static WorkstationModel unmarshall(ApiWorkstationTO to){

        return convertValue(to, WorkstationModel.class);
    }
}
