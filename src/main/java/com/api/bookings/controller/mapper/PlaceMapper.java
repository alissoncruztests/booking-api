package com.api.bookings.controller.mapper;

import com.api.bookings.model.ApiPlaceTO;
import com.api.bookings.model.PlaceModel;
import com.api.bookings.service.utils.ParserUtil;

public class PlaceMapper {

    public static ApiPlaceTO marshall(PlaceModel document){

        return ParserUtil.convertValue(document, ApiPlaceTO.class);
    }

    public static PlaceModel unmarshall(ApiPlaceTO to){

        return ParserUtil.convertValue(to, PlaceModel.class);
    }
}
