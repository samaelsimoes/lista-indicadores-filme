package br.com.listMovie.listMovie.service;

import br.com.listMovie.listMovie.dataDto.DataResponse;
import br.com.listMovie.listMovie.dataDto.ProducerInterval;
import br.com.listMovie.listMovie.entity.Movie;
import br.com.listMovie.listMovie.entity.Producer;
import br.com.listMovie.listMovie.repository.MovieRepository;
import br.com.listMovie.listMovie.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepositiry;

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public DataResponse getProducersIntervalMinMax() {
        List<Producer> producersWinner = producerRepositiry.getProducersWinners();
        DataResponse response = new DataResponse();

        for (Producer producer : producersWinner) {
            List<Movie> movies = movieRepository.getMoviesWinnersByProducerId(producer.getId());

            if (!movies.isEmpty()) {
                Movie firstMovie = movies.get(0);
                Movie lastMovie = firstMovie;

                for (int i = 1; i < movies.size(); i++) {
                    Movie currentMovie = movies.get(i);
                    int interval = currentMovie.getYear().intValue() - lastMovie.getYear().intValue();

                    ProducerInterval producerInterval = new ProducerInterval(producer.getName(), interval, lastMovie.getYear(), currentMovie.getYear());
                    response.addMax(producerInterval);
                    response.addMin(producerInterval);

                    lastMovie = currentMovie;
                }
            }
        }

        return response;
    }
}
