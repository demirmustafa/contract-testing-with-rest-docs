package io.github.demirmustafa.producer;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
@AutoConfigureMockMvc
public class ProducerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void should_get_all_products() {

        mockMvc
                .perform(get("/producer/api/v1/products"))
                .andDo(document("GET_PRODUCTS"));

    }

    @Test
    @SneakyThrows
    public void should_get_product_by_id(){
        mockMvc
                .perform(get("/producer/api/v1/products/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                ).andDo(document("GET_PRODUCT"));
    }

}
