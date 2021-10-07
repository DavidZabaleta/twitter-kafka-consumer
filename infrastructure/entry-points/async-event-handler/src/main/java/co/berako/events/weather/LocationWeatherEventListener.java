package co.berako.events.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LocationWeatherEventListener {

    private static final String WEATHER_TOPIC = "weather-topic";

    @KafkaListener(topics = WEATHER_TOPIC)
    public void locationWeatherEventListener(String eventMessage) {
        log.info("Evento recibido: ");
        log.info(eventMessage);
    }
}
