package co.berako.elasticsearch.repository.weather;

import co.berako.elasticsearch.repository.weather.data.CurrentData;
import co.berako.elasticsearch.repository.weather.data.LocationData;
import co.berako.elasticsearch.repository.weather.data.LocationWeatherData;
import co.berako.elasticsearch.repository.weather.data.UserData;
import co.berako.model.weather.Current;
import co.berako.model.weather.Location;
import co.berako.model.weather.LocationWeather;
import co.berako.model.weather.User;
import co.berako.model.weather.gateway.LocationWeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
        UserData userData = new UserData();

        userData.setEmail(locationWeather.getUser().getEmail());
        userData.setTimestamp(locationWeather.getUser().getTimestamp());

        currentData.setHumidity(locationWeather.getCurrent().getHumidity());
        currentData.setTempCelsius(locationWeather.getCurrent().getTempCelsius());
        currentData.setFeelsLikeCelsius(locationWeather.getCurrent().getFeelsLikeCelsius());

        locationData.setCity(locationWeather.getLocation().getCity());
        locationData.setRegion(locationWeather.getLocation().getRegion());
        locationData.setCountry(locationWeather.getLocation().getCountry());

        locationWeatherData.setIdLocationWeather(locationWeather.getIdLocationWeather());
        locationWeatherData.setCurrent(currentData);
        locationWeatherData.setLocation(locationData);
        locationWeatherData.setUser(userData);

        return repository.save(locationWeatherData)
                .map(this::convertDataToDomain);
    }

    @Override
    public Flux<LocationWeather> getAllLocationWeatherByUser(String email) {
        return repository.getAllByUserEmail(email)
                .map(this::convertDataToDomain);
    }

    private LocationWeather convertDataToDomain(LocationWeatherData locationWeatherData) {
        return LocationWeather.builder()
                .idLocationWeather(locationWeatherData.getIdLocationWeather())
                .location(Location.builder()
                        .city(locationWeatherData.getLocation().getCity())
                        .region(locationWeatherData.getLocation().getRegion())
                        .country(locationWeatherData.getLocation().getCountry())
                        .build())
                .current(Current.builder()
                        .humidity(locationWeatherData.getCurrent().getHumidity())
                        .tempCelsius(locationWeatherData.getCurrent().getTempCelsius())
                        .feelsLikeCelsius(locationWeatherData.getCurrent().getFeelsLikeCelsius())
                        .build())
                .user(User.builder()
                        .email(locationWeatherData.getUser().getEmail())
                        .timestamp(locationWeatherData.getUser().getTimestamp())
                        .build())
                .build();
    }

}
