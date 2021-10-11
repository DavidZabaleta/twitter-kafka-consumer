package co.berako.elasticsearch.repository.weather.data;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class LocationData {
    @Field(type = FieldType.Text)
    private String city;
    @Field(type = FieldType.Text)
    private String region;
    @Field(type = FieldType.Text)
    private String country;
}
