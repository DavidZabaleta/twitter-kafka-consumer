package co.berako.elasticsearch.repository.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@Configuration
@EnableReactiveElasticsearchRepositories
public class ReactiveElasticsearchClientConfig extends AbstractReactiveElasticsearchConfiguration {

    @Value(value = "${spring.data.elasticsearch.client.reactive.endpoints}")
    private String endpoints;

    @Value(value = "${spring.data.elasticsearch.client.reactive.username}")
    private String username;

    @Value(value = "${spring.data.elasticsearch.client.reactive.password}")
    private String password;

    @SneakyThrows
    @Override
    @Bean
    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(endpoints)
                .withBasicAuth(username, password)
                .build();

        return ReactiveRestClients.create(clientConfiguration);
    }

    @Bean
    public ReactiveElasticsearchOperations reactiveElasticsearchOperations() {
        return new ReactiveElasticsearchTemplate(reactiveElasticsearchClient());
    }
}
