package config;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Configuration {
    String id;
    String temperature;
    String description;

}
