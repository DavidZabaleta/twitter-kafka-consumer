package co.berako.elasticsearch.repository.weather;

import co.berako.elasticsearch.repository.weather.data.CurrentData;
import co.berako.elasticsearch.repository.weather.data.LocationData;
import co.berako.elasticsearch.repository.weather.data.LocationWeatherData;
import co.berako.model.weather.Current;
import co.berako.model.weather.Location;
import co.berako.model.weather.LocationWeather;
import co.berako.model.weather.gateway.LocationWeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LocationWeatherDataAdapter implements LocationWeatherRepository {

    private final LocationWeatherDataRepository repository;

    @Override
    public Mono<LocationWeather> saveLocationWeather(LocationWeather locationWeather) {
        LocationWeatherData locationWeatherData = new LocationWeatherData();
        LocationData locationData = new LocationData();
        CurrentData currentData = new CurrentData();

        currentData.setHumidity(locationWeather.getCurrent().getHumidity());
        currentData.setTempCelsius(locationWeather.getCurrent().getTempCelsius());
        currentData.setFeelsLikeCelsius(locationWeather.getCurrent().getFeelsLikeCelsius());

        locationData.setCity(locationWeather.getLocation().getCity());
        locationData.setRegion(locationWeather.getLocation().getRegion());
        locationData.setCountry(locationWeather.getLocation().getCountry());

        locationWeatherData.setIdLocationWeather(locationWeather.getIdLocationWeather());
        locationWeatherData.setCurrent(currentData);
        locationWeatherData.setLocation(locationData);
        locationWeatherData.setDataRetrievedAt(locationWeather.getDataRetrievedAt());

        return repository.save(locationWeatherData)
                .map(data -> LocationWeather.builder()
                        .idLocationWeather(data.getIdLocationWeather())
                        .location(Location.builder()
                                .city(data.getLocation().getCity())
                                .region(data.getLocation().getRegion())
                                .country(data.getLocation().getCountry())
                                .build())
                        .current(Current.builder()
                                .humidity(data.getCurrent().getHumidity())
                                .tempCelsius(data.getCurrent().getTempCelsius())
                                .feelsLikeCelsius(data.getCurrent().getFeelsLikeCelsius())
                                .build())
                        .dataRetrievedAt(data.getDataRetrievedAt())
                        .build());
    }

}
