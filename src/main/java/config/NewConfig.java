package config;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewConfig {
    String id;
    String temperature;
    String description;
}
