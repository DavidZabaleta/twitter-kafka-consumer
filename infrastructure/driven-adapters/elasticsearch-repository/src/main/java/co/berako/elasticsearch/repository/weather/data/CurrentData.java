package co.berako.elasticsearch.repository.weather.data;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class CurrentData {
    @Field(type = FieldType.Text)
    private String tempCelsius;
    @Field(type = FieldType.Text)
    private String humidity;
    @Field(type = FieldType.Text)
    private String feelsLikeCelsius;
}
