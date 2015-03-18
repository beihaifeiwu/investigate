package com.freetmp.investigate.atmosphere;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Decoder;

import java.io.IOException;

/**
 * Created by LiuPin on 2015/3/18.
 */
public class JacksonDecoder implements Decoder<String,Data> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Data decode(String data) {
        try {
            return mapper.readValue(data,Data.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
