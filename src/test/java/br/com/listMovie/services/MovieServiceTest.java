package br.com.listMovie.services;

import br.com.listMovie.listMovie.dataDto.DataResponse;
import br.com.listMovie.listMovie.dataDto.ProducerInterval;
import br.com.listMovie.listMovie.entity.Movie;
import br.com.listMovie.listMovie.entity.MovieProducer;
import br.com.listMovie.listMovie.entity.Producer;
import br.com.listMovie.listMovie.service.MovieProducerService;
import br.com.listMovie.listMovie.service.MovieService;
import br.com.listMovie.listMovie.service.ProducerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieProducerService movieProducerService;

    @Autowired
    private ProducerService producerService;

    private final Long INITIAL_LONG_YEAR = 1900L;

    private final Long FINAL_YEAR = 2023L;

    private final Long INITIAL_SHORT_YEAR = 2023L;

    private final int MIN_INTERVAL = FINAL_YEAR.intValue() - INITIAL_SHORT_YEAR.intValue();

    private final int MAX_INTERVAL = FINAL_YEAR.intValue() - INITIAL_LONG_YEAR.intValue();

    @Test
    public void shouldSaveMovie() {
        Movie movie = new Movie(2011L, "2011", "Universal", "N");

        Movie record = this.movieService.save(movie);

        Assertions.assertNotNull(record.getId());
        Assertions.assertEquals(record.getStudios(), movie.getStudios());
        Assertions.assertEquals(record.getTitle(), movie.getTitle());
        Assertions.assertEquals(record.getWinner(), movie.getWinner());
    }


    @Test
    public void shouldGetWinnerProducersWithIntervalsMinAndMax() {
        Movie movieLongDate = new Movie(INITIAL_LONG_YEAR, "Reza a Lenda", "Ortiz Studio", "S");
        Movie movieActual = new Movie(FINAL_YEAR, "Homem Aranha", "Marvel", "S");
        Producer maxProducer = new Producer("Arnold Maximum");

        movieLongDate = this.movieService.save(movieLongDate);
        movieActual = this.movieService.save(movieActual);
        maxProducer = this.producerService.save(maxProducer);
        this.movieProducerService.save(new MovieProducer(movieLongDate, maxProducer));
        this.movieProducerService.save(new MovieProducer(movieActual, maxProducer));

        Movie movieShortDate = new Movie(INITIAL_SHORT_YEAR, "Estamos em Guerra", "Studio Bellatore", "S");
        Movie movieActualShort = new Movie(FINAL_YEAR, "Perdidos na Ilha", "Oster Studio", "S");
        Producer minProducer = new Producer("Arnold Minimum");

        movieShortDate = this.movieService.save(movieShortDate);
        movieActualShort = this.movieService.save(movieActualShort);
        minProducer = this.producerService.save(minProducer);
        this.movieProducerService.save(new MovieProducer(movieShortDate, minProducer));
        this.movieProducerService.save(new MovieProducer(movieActualShort, minProducer));

        DataResponse data = this.movieService.getProducersIntervalMinMax();
        List<ProducerInterval> maximumInterval = data.getMax();
        List<ProducerInterval> minimumInterval = data.getMin();

        String maxName = maxProducer.getName();
        String minName = minProducer.getName();
        ProducerInterval max = maximumInterval.stream().filter(prodInterval ->
                prodInterval.getProducer().equalsIgnoreCase(maxName)).findFirst().orElse(new ProducerInterval());
        ProducerInterval min = minimumInterval.stream().filter(prodInterval ->
                prodInterval.getProducer().equalsIgnoreCase(minName)).findFirst().orElse(new ProducerInterval());

        Assertions.assertEquals(MIN_INTERVAL, min.getInterval());
        Assertions.assertEquals(MAX_INTERVAL, max.getInterval());

        Assertions.assertEquals(min.getProducer(), minProducer.getName());
        Assertions.assertEquals(min.getFollowingWin(), movieActualShort.getYear());
        Assertions.assertEquals(min.getPreviousWin(), movieShortDate.getYear());

        Assertions.assertEquals(max.getProducer(), maxProducer.getName());
        Assertions.assertEquals(max.getFollowingWin(), movieActual.getYear());
        Assertions.assertEquals(max.getPreviousWin(), movieLongDate.getYear());
    }
}