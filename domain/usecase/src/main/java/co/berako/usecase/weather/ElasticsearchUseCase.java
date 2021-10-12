package co.berako.usecase.weather;

import co.berako.model.weather.LocationWeather;
import co.berako.model.weather.gateway.LocationWeatherRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ElasticsearchUseCase {

    private final LocationWeatherRepository weatherRepository;

    public Mono<LocationWeather> saveLocationWeather(LocationWeather locationWeather) {
        return weatherRepository.saveLocationWeather(locationWeather)
                .doOnSuccess(locationWeatherSaved -> System.out.printf("Message %s saved", locationWeatherSaved.getIdLocationWeather()));
    }

    public Flux<LocationWeather> getAllLocationWeatherByUser(String email) {
        return weatherRepository.getAllLocationWeatherByUser(email);
    }
}

