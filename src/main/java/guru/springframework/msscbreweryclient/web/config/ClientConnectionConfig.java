package guru.springframework.msscbreweryclient.web.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ClientConnectionConfig {

    @Value("${sfg.brewery.client.config.maxpoolconnection}")
    int maxPoolConnection;
    @Value("${sfg.brewery.client.config.defaultmaxperroute}")
    int defaultMaxPerRoute;
    @Value("${sfg.brewery.client.config.connectiontimeout}")
    int connectionTimeout;
    @Value("${sfg.brewery.client.config.sockettimeout}")
    int socketTimeout;
    @Value("${sfg.brewery.client.config.threadcount}")
    int threadCount;
}
