package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    private CustomerClient customerClient;

    @Test
    void getCustomerById() {
        CustomerDto dto = customerClient.getCustomerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void createCustomer() {
        CustomerDto dto = CustomerDto.builder().name("Shazam").build();
        URI uri = customerClient.createCustomer(dto);
        assertNotNull(uri);
        System.out.println(uri);
    }

    @Test
    void updateCustomerById() {
        CustomerDto dto = CustomerDto.builder().id(UUID.randomUUID()).name("Louis").build();
        customerClient.updateCustomer(dto.getId(), dto);
    }

    @Test
    void deleteCustomerById() {
        CustomerDto dto = CustomerDto.builder().id(UUID.randomUUID()).name("Louis").build();
        customerClient.deleteCustomer(dto.getId());
    }
}