import config.ConfigRepository;
import config.NewConfig;
import config.Configuration;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryConfigRepository implements ConfigRepository {
    private static final Map CONFIG_STORE = new ConcurrentHashMap();
    @Override
    public String create(NewConfig newConfig) {
        String id = UUID.randomUUID().toString();
        Configuration conf = Configuration.builder()
                .id(id)
                .temperature(newConfig.getTemperature())
                .description(newConfig.getDescription())
                .build();
        CONFIG_STORE.put(newConfig.getTemperature(), conf);

        return id;
    }
}
