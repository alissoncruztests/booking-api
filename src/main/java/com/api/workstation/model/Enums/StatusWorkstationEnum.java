package com.api.workstation.model.Enums;

public enum StatusWorkstationEnum {
    ACTIVE(1L,"ACTIVE"),
    INACTIVE(2L,"INACTIVE"),
    CANCELED(3L,"CANCELED"),
    FINISHED(4L,"FINISHED"),
    IN_USE(5L,"IN_USE"),
    RESERVED(6L,"RESERVED"),
    BLOCKED(7L,"BLOCKED"),
    UNBLOCKED(7L,"UNBLOCKED"),
    OTHERS(7L,"OTHERS");


    private Long code;
    private String name;

    StatusWorkstationEnum(Long code, String name){
        this.code = code;
        this.name = name;
    }


    public static StatusWorkstationEnum getByCode(Long code){
        if (code == null)
            return null;
        for (StatusWorkstationEnum e : StatusWorkstationEnum.values()){
            if (e.code.equals(code))
                return e;
        }
        return null;
    }
}
