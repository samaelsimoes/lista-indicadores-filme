package br.com.listMovie.services;

import br.com.listMovie.listMovie.entity.MovieProducer;
import br.com.listMovie.listMovie.repository.MovieProducerRepositiry;
import br.com.listMovie.listMovie.service.FileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Autowired
    private MovieProducerRepositiry movieProducerRepositiry;

    @Test
    public void shouldCreateRecordsIntoDataBase() {
        this.fileService.buildDataFile();

        List<MovieProducer> all = this.movieProducerRepositiry.findAll();
        Assertions.assertEquals( 2, all.size());
        Assertions.assertEquals("Saminha simõesr", all.get(0).getProducer().getName());
        Assertions.assertEquals("A vida de um viciado por carros da AUDI", all.get(0).getMovie().getTitle());
        Assertions.assertEquals(1980L, all.get(0).getMovie().getYear());
        Assertions.assertEquals("Audi e sua evolucao", all.get(0).getMovie().getStudios());
        Assertions.assertEquals("S", all.get(0).getMovie().getWinner());
    }
}