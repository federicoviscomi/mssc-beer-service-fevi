package guru.springframework.msscbeerservicefevi.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservicefevi.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = BeerDto.builder().build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        ResultMatcher expectedStatus = status().isCreated();
        String url = "/api/v1/beer/";
        MockHttpServletRequestBuilder content = post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson);
        mockMvc.perform(content)
                .andExpect(expectedStatus);
    }

    @Test
    void updateBeerId() throws Exception {
        BeerDto beerDto = BeerDto.builder().build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        String url = "/api/v1/beer/" + UUID.randomUUID().toString();
        MockHttpServletRequestBuilder content = put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson);
        ResultMatcher expectedStatus = status().isNoContent();
        mockMvc.perform(content).andExpect(expectedStatus);
    }
}