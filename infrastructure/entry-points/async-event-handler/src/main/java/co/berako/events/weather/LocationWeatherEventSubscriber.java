package co.berako.events.weather;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;

@RequiredArgsConstructor
public class LocationWeatherEventSubscriber {

    private final KafkaConsumer<String, String> kafkaConsumer;

    public void locationWeatherEventSubscriber() {

    }
}
