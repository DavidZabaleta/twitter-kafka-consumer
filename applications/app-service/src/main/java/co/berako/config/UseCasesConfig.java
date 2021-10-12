package co.berako.config;

import co.berako.model.weather.LocationWeather;
import co.berako.model.weather.gateway.LocationWeatherRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@ComponentScan(basePackages = "co.berako.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

    private final LocationWeatherRepository weatherRepository = new LocationWeatherRepository() {
        @Override
        public Mono<LocationWeather> saveLocationWeather(LocationWeather locationWeather) {
            return null;
        }

        @Override
        public Flux<LocationWeather> getAllLocationWeatherByUser(String email) {
            return null;
        }
    };

    @Bean
    @ConditionalOnMissingBean()
    public LocationWeatherRepository weatherRepository() {
        return weatherRepository;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }
}
