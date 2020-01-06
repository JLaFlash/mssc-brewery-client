package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BreweryCustomerClientTest {
    @Autowired
    BreweryCustomerClient client;


    @Test
    void getCustomerById() {
        CustomerDto customerDto;
        customerDto = client.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);

    }

    @Test
    void saveNewCustomer() {
        CustomerDto customerDto = CustomerDto.builder().name("Jody LaFlash").build();
        URI uri = client.saveNewCustomer(customerDto);
        assertNotNull(uri);

    }

    @Test
    void updateCustomer() {
        CustomerDto customerDto = CustomerDto.builder().name("Jody LaFlash").build();

        client.updateCustomer(UUID.randomUUID(), customerDto);
    }

    @Test
    void deleteCustomer() {
        CustomerDto customerDto = CustomerDto.builder().name("Jody LaFlash").build();

        client.deleteCustomer(UUID.randomUUID());
    }
}