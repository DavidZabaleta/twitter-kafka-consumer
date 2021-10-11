package co.berako.elasticsearch.repository.weather.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "weather")
public class LocationWeatherData {
    @Id
    private String idLocationWeather;
    @Field(type = FieldType.Object)
    private LocationData location;
    @Field(type = FieldType.Object)
    private CurrentData current;
    @Field(type = FieldType.Date)
    private Date dataRetrievedAt;
}
