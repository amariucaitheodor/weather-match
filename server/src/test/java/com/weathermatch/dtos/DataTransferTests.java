package com.weathermatch.dtos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class DataTransferTests {

    @Test
    public void basicTransferTest() throws IOException {
        String resource = "{\"coord\":{\"lon\":145.77,\"lat\":-16.92},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"base\":\"stations\",\"main\":{\"temp\":300.15,\"pressure\":1007,\"humidity\":74,\"temp_min\":300.15,\"temp_max\":300.15},\"visibility\":10000,\"wind\":{\"speed\":3.65,\"deg\":160},\"clouds\":{\"all\":40},\"dt\":1485790200,\"sys\":{\"type\":1,\"id\":8166,\"message\":0.2064,\"country\":\"AU\",\"sunrise\":1485720272,\"sunset\":1485766550},\"id\":2172797,\"name\":\"Cairns\",\"cod\":200}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OpenWeatherMapDto openWeatherMapDto = mapper.readValue(resource, OpenWeatherMapDto.class);
        assertEquals(2172797L, (long) openWeatherMapDto.getId());
        assertEquals(145.77, (Object) openWeatherMapDto.getCoord().getLon());
        assertEquals(-16.92, (Object) openWeatherMapDto.getCoord().getLat());
        assertEquals("Clouds", openWeatherMapDto.getWeather().getMain());
        assertEquals(27.0, (Object) openWeatherMapDto.getMain().getTemp());
        assertEquals(74.0, (Object) openWeatherMapDto.getMain().getHumidity());
        assertEquals("AU", openWeatherMapDto.getSys().getCountry());
        assertEquals("Cairns", openWeatherMapDto.getName());
        assertEquals(3.65, (Object) openWeatherMapDto.getWind().getSpeed());
    }

}
