import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigRepository;
import config.ConfigService;
import errors.GlobalExceptionHandler;


class Configuration {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ConfigRepository CONFIG_REPOSITORY = new InMemoryConfigRepository();
    private static final ConfigService CONFIG_SERVICE = new ConfigService(CONFIG_REPOSITORY);
    private static final GlobalExceptionHandler GLOBAL_ERROR_HANDLER = new GlobalExceptionHandler(OBJECT_MAPPER);

    static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    static ConfigService getConfigService() {
        return CONFIG_SERVICE;
    }

    static ConfigRepository getConfigRepository() {
        return CONFIG_REPOSITORY;
    }

    public static GlobalExceptionHandler getErrorHandler() {
        return GLOBAL_ERROR_HANDLER;
    }
}
