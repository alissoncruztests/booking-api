package com.api.bookings.controller.mapper;

import com.api.bookings.model.ApiWorkstationTO;
import com.api.bookings.model.WorkstationModel;
import com.api.bookings.service.utils.ParserUtil;

public class WorkstationMapper {

    public static ApiWorkstationTO marshall(WorkstationModel document){

        return ParserUtil.convertValue(document, ApiWorkstationTO.class);
    }

    public static WorkstationModel unmarshall(ApiWorkstationTO to){

        return ParserUtil.convertValue(to, WorkstationModel.class);
    }
}
