package com.freetmp.investigate.atmosphere;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Encoder;

/**
 * Created by LiuPin on 2015/3/18.
 */
public class JacksonEncoder implements Encoder<Data,String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String encode(Data data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
