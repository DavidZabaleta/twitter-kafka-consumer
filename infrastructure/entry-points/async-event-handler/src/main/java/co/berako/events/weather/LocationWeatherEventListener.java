package co.berako.events.weather;

import co.berako.events.validations.EntryMessageValidation;
import co.berako.events.weather.dto.LocationWeatherDTO;
import co.berako.events.weather.dto.LocationWeatherDTOConverter;
import co.berako.usecase.weather.ElasticsearchUseCase;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LocationWeatherEventListener {

    private static final String WEATHER_TOPIC = "weather-topic";

    private final ElasticsearchUseCase elasticsearchUseCase;

    @KafkaListener(topics = WEATHER_TOPIC)
    public void locationWeatherEventListener(String eventMessage) {
        Mono.just(eventMessage)
                .flatMap(EntryMessageValidation::validateLocationWeatherMessage)
                .map(message -> new Gson().fromJson(message, LocationWeatherDTO.class))
                .map(LocationWeatherDTOConverter::convertLocationWeatherDTOToDomain)
                .flatMap(elasticsearchUseCase::saveLocationWeather)
                .onErrorContinue((throwable, o) -> log.error(throwable.getMessage()))
                .subscribe();
    }
}
