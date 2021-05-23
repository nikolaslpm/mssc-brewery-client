package guru.springframework.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jt on 2019-08-08.
 */
//@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final ClientConnectionConfig connectionConfig;

    public BlockingRestTemplateCustomizer(ClientConnectionConfig connectionConfig) {
        this.connectionConfig = connectionConfig;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(connectionConfig.getMaxPoolConnection());
        connectionManager.setDefaultMaxPerRoute(connectionConfig.getDefaultMaxPerRoute());

        RequestConfig requestConfig = RequestConfig
            .custom()
            .setConnectionRequestTimeout(connectionConfig.getConnectionTimeout())
            .setSocketTimeout(connectionConfig.getSocketTimeout())
            .build();

        CloseableHttpClient httpClient = HttpClients
            .custom()
            .setConnectionManager(connectionManager)
            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
            .setDefaultRequestConfig(requestConfig)
            .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
