package io.github.demirmustafa.consumer;

import io.github.demirmustafa.consumer.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@AutoConfigureWireMock(stubs = "classpath:/stubs/product", port = 8090)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductContractVerifierTest {


    @Autowired
    private WebTestClient client;

    @Test
    public void should_get_all_products() {

        Product[] products = client.get()
                .uri("http://localhost:8090/producer/api/v1/products")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Product[].class)
                .returnResult()
                .getResponseBody();

        assertThat(products.length).isEqualTo(2);
    }

    @Test
    public void should_get_product_by_id() {

        Product product = client.get()
                .uri("http://localhost:8090/producer/api/v1/products/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Product.class)
                .returnResult()
                .getResponseBody();

        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("Introduction to Java Programming");
    }


}
