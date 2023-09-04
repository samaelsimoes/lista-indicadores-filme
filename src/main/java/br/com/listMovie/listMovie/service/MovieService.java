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
        List<Producer> producersWinner = this.producerRepositiry.getProducersWinners();
        DataResponse response = new DataResponse();
        for (Producer producer : producersWinner) {
            List<Movie> movies = this.movieRepository.getMoviesWinnersByProducerId(producer.getId());
            Movie lastMovie = null;
            for (Movie movie : movies) {
                if (lastMovie != null) {
                    ProducerInterval producerInterval = new ProducerInterval(producer.getName(),
                            movie.getYear().intValue() - lastMovie.getYear().intValue(),
                            lastMovie.getYear(),
                            movie.getYear());
                    response.addMax(producerInterval);
                    response.addMin(producerInterval);
                }
                lastMovie = movie;
            }
        }

        return response;
    }
}
