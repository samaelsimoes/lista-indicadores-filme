package br.com.listMovie.repository;

import br.com.listMovie.listMovie.entity.Movie;
import br.com.listMovie.listMovie.entity.MovieProducer;
import br.com.listMovie.listMovie.entity.Producer;
import br.com.listMovie.listMovie.repository.MovieRepository;
import br.com.listMovie.listMovie.repository.ProducerRepository;
import br.com.listMovie.listMovie.service.MovieProducerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ProducerRepositoryTest {

    @Autowired
    private MovieProducerService movieProducerService;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void shouldGetProducerByName() {
        Producer producer = new Producer("Jackie Chan");
        producer = producerRepository.save(producer);

        Producer producerRecord = producerRepository.getEntityByName(producer.getName());

        Assertions.assertEquals(producer.getId(), producerRecord.getId());
        Assertions.assertEquals(producer.getName(), producer.getName());

    }

    @Test
    public void shouldGetJustWinnersProducers() {
        Movie movie1 = movieRepository.save(new Movie(2000L, "Senhor dos Aneis", "Warner Bross", "S"));
        Movie movie2 = movieRepository.save(new Movie(1998L, "Titanic", "Warner Bross", "N"));

        Producer producer1 = producerRepository.save(new Producer("J. R. R. Tolkien"));
        Producer producer2 = producerRepository.save(new Producer("James Paul"));

        movieProducerService.save(new MovieProducer(movie1, producer1));
        movieProducerService.save(new MovieProducer(movie2, producer2));

        List<Producer> winners = producerRepository.getProducersWinners();

        Producer producerRecord = winners.stream().filter(producer -> producer.getId().equals(producer1.getId())).findFirst().orElse(new Producer());

        Assertions.assertTrue( winners.size() > 0);

        Assertions.assertEquals(producer1.getId(), producerRecord.getId());
        Assertions.assertEquals(producer1.getName(), producerRecord.getName());
    }
}