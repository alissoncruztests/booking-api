package com.api.bookings.model.Enums;

public enum StatusBookingEnum {
    ACTIVE(1L,"ACTIVE"),
    INACTIVE(2L,"INACTIVE"),
    CANCELED(3L,"CANCELED"),
    FINISHED(4L,"FINISHED"),
    IN_USE(5L,"IN_USE"),
    RESERVED(6L,"RESERVED"),
    BLOCKED(7L,"BLOCKED"),
    OTHERS(7L,"OTHERS");


    private Long code;
    private String name;

    StatusBookingEnum(Long code, String name){
        this.code = code;
        this.name = name;
    }


    public static StatusBookingEnum getByCode(Long code){
        if (code == null)
            return null;
        for (StatusBookingEnum e : StatusBookingEnum.values()){
            if (e.code.equals(code))
                return e;
        }
        return null;
    }
}
