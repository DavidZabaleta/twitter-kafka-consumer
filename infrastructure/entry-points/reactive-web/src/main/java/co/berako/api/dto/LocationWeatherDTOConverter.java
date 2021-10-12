package co.berako.api.dto;

import co.berako.model.weather.Current;
import co.berako.model.weather.Location;
import co.berako.model.weather.LocationWeather;
import co.berako.model.weather.User;

public interface LocationWeatherDTOConverter {
    static LocationWeatherDTO convertLocationWeatherToDTO(LocationWeather locationWeather) {
        return LocationWeatherDTO.builder()
                .idLocationWeather(locationWeather.getIdLocationWeather())
                .current(convertCurrentToDTO(locationWeather.getCurrent()))
                .location(convertLocationToDTO(locationWeather.getLocation()))
                .user(convertUserToDTO(locationWeather.getUser()))
                .build();
    }

    static UserDTO convertUserToDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .timestamp(user.getTimestamp())
                .build();
    }

    static LocationDTO convertLocationToDTO(Location location) {
        return LocationDTO.builder()
                .country(location.getCountry())
                .region(location.getRegion())
                .city(location.getCity())
                .build();
    }

    static CurrentDTO convertCurrentToDTO(Current current) {
        return CurrentDTO.builder()
                .tempCelsius(current.getTempCelsius())
                .humidity(current.getHumidity())
                .feelsLikeCelsius(current.getFeelsLikeCelsius())
                .build();
    }
}
