package com.example.carpark.employee.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EmployeeSexEnum {
    NOT_KNOWN,
    MALE,
    FEMALE;

//    uncomment if we want to parse 0, 1, 2 in JSON to enum
//    @JsonCreator
//    public static EmployeeSexEnum fromString(String value) {
//        if (value == null) {
//            throw new IllegalArgumentException();
//        }
//        for(EmployeeSexEnum e  : values()) {
//            if (Integer.parseInt(value) == e.ordinal()) {
//                return e;
//            }
//        }
//        return null;
//    }
}
