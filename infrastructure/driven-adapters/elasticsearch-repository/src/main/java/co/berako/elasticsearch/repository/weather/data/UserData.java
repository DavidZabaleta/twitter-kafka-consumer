package co.berako.elasticsearch.repository.weather.data;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
public class UserData {
    @Field(type = FieldType.Text)
    private String email;
    @Field(type = FieldType.Date)
    private Date timestamp;
}
