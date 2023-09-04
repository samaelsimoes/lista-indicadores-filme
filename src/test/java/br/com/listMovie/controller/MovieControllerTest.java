package br.com.listMovie.controller;

import br.com.listMovie.listMovie.dataDto.DataResponse;
import br.com.listMovie.listMovie.dataDto.ProducerInterval;
import br.com.listMovie.listMovie.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private final String URL_INTERVAL = "/movie/producers/interval";

    private final String JSON_INTERVAL_EXPECTED = "{min:[{'producer':'Jorge Solomon','interval':1,'previousWin':1999,'followingWin':2000}]"
            + ",max:[{'producer':'Andrey Solomon','interval':30,'previousWin':1970,'followingWin':2000}]}";
    @Test
    public void shouldGetWinnerProducersMaxAndMinInterval() throws Exception {
        ProducerInterval min = new ProducerInterval("Jorge Solomon", 1, 1999L, 2000L);
        ProducerInterval max = new ProducerInterval("Andrey Solomon", 30, 1970L, 2000L);
        DataResponse response = new DataResponse();
        response.addMin(min);
        response.addMax(max);
        Mockito.when(movieService.getProducersIntervalMinMax()).thenReturn(response);
        mockMvc.perform(get(URL_INTERVAL)).andExpect(status().isOk())
                .andExpect(content().json(JSON_INTERVAL_EXPECTED));
    }

}