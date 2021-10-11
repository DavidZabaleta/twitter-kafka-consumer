package co.berako.elasticsearch.repository.weather;

import co.berako.elasticsearch.repository.weather.data.LocationWeatherData;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface LocationWeatherDataRepository extends ReactiveElasticsearchRepository<LocationWeatherData, String> {
}
