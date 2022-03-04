package api.conf;

import api.Constants;
import api.Handler;
import api.ResponseEntity;
import api.StatusCode;
import com.sun.net.httpserver.HttpExchange;
import config.ConfigService;
import config.NewConfig;
import errors.ApplicationExceptions;
import errors.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RegistrationHandler extends Handler {

    private final ConfigService configService;



    public RegistrationHandler(ConfigService configService, ObjectMapper objectMapper,
                               GlobalExceptionHandler exceptionHandler) {
        super(objectMapper, exceptionHandler);
        this.configService = configService;
    }
        @Override
        protected void execute(HttpExchange exchange) throws IOException {
            byte[] response;
            if ("POST".equals(exchange.getRequestMethod())) {
                ResponseEntity e = doPost(exchange.getRequestBody());
                exchange.getResponseHeaders().putAll(e.getHeaders());
                exchange.sendResponseHeaders(e.getStatusCode().getCode(), 0);
                response = super.writeResponse(e.getBody());
            } else {
                throw ApplicationExceptions.methodNotAllowed(
                        "Method " + exchange.getRequestMethod() + " is not allowed for " + exchange.getRequestURI()).get();
            }

            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        }

        private ResponseEntity<RegistrationResponse> doPost(InputStream is) {
            RegistrationRequest registerRequest = super.readRequest(is, RegistrationRequest.class);

            NewConfig config = NewConfig.builder()

                   .temperature(registerRequest.getTemperature())
                    //.login(registerRequest.getLogin())
                    //.password(PasswordEncoder.encode(registerRequest.getPassword()))
                    .description(registerRequest.getDescription())
                    .build();

            String confId = configService.create(config);

            RegistrationResponse response = new RegistrationResponse(confId);

            return new ResponseEntity<>(response,
                    getHeaders(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON), StatusCode.OK);
        }

    }


