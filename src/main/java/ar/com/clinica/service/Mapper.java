package ar.com.clinica.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

@Component
public class Mapper extends ObjectMapper {

    public static ObjectMapper getMapper(boolean failOnBeans, boolean unknownProperties) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, failOnBeans);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, unknownProperties);
        return mapper;
    }
}
