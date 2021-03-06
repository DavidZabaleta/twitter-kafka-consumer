package co.berako.events.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LocationWeatherDTO {
    private String idLocationWeather;
    private LocationDTO location;
    private CurrentDTO current;
    private UserDTO user;
}
