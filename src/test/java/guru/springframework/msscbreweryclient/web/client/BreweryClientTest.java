package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {
        BeerDto dto = client.getBeerById(UUID.randomUUID());

        assertNotNull(dto);

    }

    // todo Fix this tests

//    @Test
//    void testSaveNewBeer(){
//        //given
//        BeerDto beerDto = createValidBeerDto();
//        URI uri = client.saveNewBeer(beerDto);
//        System.out.println(uri.toString());
//        assertNotNull(uri);
//
//    }
//
//    @Test
//    void testupdateBeer() {
//        //given
//        BeerDto beerDto = createValidBeerDto();
//        client.updateBeer(UUID.randomUUID(), beerDto);
//    }

    @Test
    void deleteBeer() {
        client.deleteBeer(UUID.randomUUID());
    }

    BeerDto createValidBeerDto(){
        return BeerDto.builder()
                .beerName("Miller")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .upc(123123123123L)
                .build();
    }
}