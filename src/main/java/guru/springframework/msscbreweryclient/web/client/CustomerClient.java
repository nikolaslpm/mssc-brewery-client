package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "client")
public class CustomerClient {

    private String hostName;
    private final String API_BASE_PATH = "/api/v1/customer/";
    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(hostName + API_BASE_PATH + uuid, CustomerDto.class);
    }

    public URI createCustomer(CustomerDto dto) {
        return restTemplate.postForLocation(hostName + API_BASE_PATH, dto);
    }

    public void updateCustomer(UUID uuid, CustomerDto dto) {
        restTemplate.put(hostName + API_BASE_PATH + uuid, dto);
    }

    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(hostName + API_BASE_PATH + uuid);
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
