package co.berako.model.weather.gateway;

import co.berako.model.weather.LocationWeather;
import reactor.core.publisher.Mono;

public interface LocationWeatherRepository {

    Mono<LocationWeather> saveLocationWeather(LocationWeather locationWeather);
}
