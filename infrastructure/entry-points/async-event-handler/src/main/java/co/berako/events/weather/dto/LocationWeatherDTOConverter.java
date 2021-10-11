package co.berako.events.weather.dto;

import co.berako.model.weather.Current;
import co.berako.model.weather.Location;
import co.berako.model.weather.LocationWeather;

public interface LocationWeatherDTOConverter {
    static LocationWeather convertLocationWeatherDTOToDomain(LocationWeatherDTO locationWeatherDTO) {
        return LocationWeather.builder()
                .idLocationWeather(locationWeatherDTO.getIdLocationWeather())
                .current(convertCurrentDTOToDomain(locationWeatherDTO.getCurrent()))
                .location(convertLocationDTOToDomain(locationWeatherDTO.getLocation()))
                .dataRetrievedAt(locationWeatherDTO.getDataRetrievedAt())
                .build();
    }

    static Location convertLocationDTOToDomain(LocationDTO locationDTO) {
        return Location.builder()
                .country(locationDTO.getCountry())
                .region(locationDTO.getRegion())
                .city(locationDTO.getCity())
                .build();
    }

    static Current convertCurrentDTOToDomain(CurrentDTO currentDTO) {
        return Current.builder()
                .tempCelsius(currentDTO.getTempCelsius())
                .humidity(currentDTO.getHumidity())
                .feelsLikeCelsius(currentDTO.getFeelsLikeCelsius())
                .build();
    }
}
