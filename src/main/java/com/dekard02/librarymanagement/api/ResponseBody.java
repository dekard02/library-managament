package com.dekard02.librarymanagement.api;

import java.util.LinkedHashMap;

import org.springframework.core.Conventions;

public class ResponseBody extends LinkedHashMap<String, Object> {
    private ResponseBody() {
    }

    public static ResponseBody create() {
        return new ResponseBody();
    }

    public ResponseBody addField(String fieldName, Object value) {
        this.put(fieldName, value);
        return this;

    }

    public ResponseBody addField(Object value) {
        var fieldName = Conventions.getVariableName(value);
        this.put(fieldName, value);
        return this;
    }
}
