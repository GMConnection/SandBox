package gm.sandbox.websandbox.config;

import gm.sandbox.websandbox.client.GameRestClient;
import gm.sandbox.websandbox.client.RestClientGame;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public RestClientGame gameRestClient(@Value("${ServerPorts.uri:http://localhost:8000}")String baseUrl) {
        return new RestClientGame(RestClient.builder()
                .baseUrl(baseUrl)
                .build());
    }
}
