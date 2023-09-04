package br.com.listMovie.repository;

import br.com.listMovie.listMovie.entity.Movie;
import br.com.listMovie.listMovie.entity.MovieProducer;
import br.com.listMovie.listMovie.entity.Producer;
import br.com.listMovie.listMovie.repository.MovieRepository;
import br.com.listMovie.listMovie.service.MovieProducerService;
import br.com.listMovie.listMovie.service.ProducerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieProducerService movieProducerService;

    @Autowired
    private ProducerService producerService;

    @Test
    public void shouldGetMoviesWinnersByProducerId() {
        Movie movie1 = movieRepository.save(new Movie(1973L, "Operação Dragão", "Warner Bross", "S"));
        Movie movie2 = movieRepository.save(new Movie(1998L, "Titanic", "Warner Bross", "S"));

        Producer producer1 = producerService.save(new Producer("Bruce Lee"));

        movieProducerService.save(new MovieProducer(movie1, producer1));
        movieProducerService.save(new MovieProducer(movie2, producer1));

        List<Movie> movies = movieRepository.getMoviesWinnersByProducerId(producer1.getId());

        Assertions.assertEquals(2, movies.size());

        Movie movieRecord1 = movies.stream().filter(movie -> movie.getId().equals(movie1.getId())).findFirst().orElse(new Movie());
        Movie movieRecord2 = movies.stream().filter(movie -> movie.getId().equals(movie2.getId())).findFirst().orElse(new Movie());

        Assertions.assertEquals(movieRecord1.getId(), movie1.getId());
        Assertions.assertEquals(movieRecord1.getYear(), movie1.getYear());
        Assertions.assertEquals(movieRecord1.getStudios(), movie1.getStudios());
        Assertions.assertEquals(movieRecord1.getTitle(), movie1.getTitle());
        Assertions.assertEquals(movieRecord1.getWinner(), movie1.getWinner());


        Assertions.assertEquals(movieRecord2.getId(), movie2.getId());
        Assertions.assertEquals(movieRecord2.getYear(), movie2.getYear());
        Assertions.assertEquals(movieRecord2.getStudios(), movie2.getStudios());
        Assertions.assertEquals(movieRecord2.getTitle(), movie2.getTitle());
        Assertions.assertEquals(movieRecord2.getWinner(), movie2.getWinner());

    }

}