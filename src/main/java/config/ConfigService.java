package config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfigService {
    private  final ConfigRepository configRepository;
    public String create (NewConfig config){
        return configRepository.create(config);
    }
}
