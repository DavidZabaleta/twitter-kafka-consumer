package co.berako.api;

import co.berako.api.dto.LocationWeatherDTOConverter;
import co.berako.usecase.weather.ElasticsearchUseCase;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final ElasticsearchUseCase elasticsearchUseCase;

    public @NotNull
    Mono<ServerResponse> getAllLocationWeatherByUser(ServerRequest serverRequest) {
        return elasticsearchUseCase.getAllLocationWeatherByUser(serverRequest.pathVariable("email"))
                .map(LocationWeatherDTOConverter::convertLocationWeatherToDTO)
                .collectList()
                .flatMap(locationWeather -> ServerResponse.ok().body(BodyInserters.fromValue(locationWeather)));
    }
}
