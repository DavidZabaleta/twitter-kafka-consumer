package co.berako.config;

import co.berako.model.weather.gateway.LocationWeatherRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.berako.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

    private final LocationWeatherRepository weatherRepository = locationWeather -> null;

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
