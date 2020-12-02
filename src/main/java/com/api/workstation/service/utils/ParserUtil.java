package com.api.workstation.service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.util.Objects;
import java.util.TimeZone;

public class ParserUtil {
    //private static final Logger log = ESAPI.getLogger(ParserUtil.class);

    private ParserUtil() {
    }

    public static String convertToJSON(Object objeto) {
        try {
            return (new ObjectMapper()).writeValueAsString(objeto);
        } catch (JsonProcessingException var2) {
      //      log.error(Logger.EVENT_FAILURE, var2.getMessage(), var2);
            throw new IllegalArgumentException(var2);
        }
    }

    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        T valueConverted = null;
        if (Objects.nonNull(fromValue)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
            mapper.setTimeZone(TimeZone.getTimeZone("PST"));
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.registerModule(new JodaModule());
                valueConverted = mapper.convertValue(fromValue, toValueType);
        }

        return valueConverted;
    }
}
