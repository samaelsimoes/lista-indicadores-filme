package br.com.listMovie.listMovie.service;

import br.com.listMovie.listMovie.dataDto.DataResponse;
import br.com.listMovie.listMovie.dataDto.ProducerInterval;
import br.com.listMovie.listMovie.entity.Movie;
import br.com.listMovie.listMovie.entity.Producer;
import br.com.listMovie.listMovie.repository.MovieRepository;
import br.com.listMovie.listMovie.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

        List<ProducerInterval> oneYearIntervals = new ArrayList<>();
        List<ProducerInterval> maxIntervals = new ArrayList<>();

        Map<String, Integer> maxIntervalCount = new HashMap<>();

        for (Producer producer : producersWinner) {
            List<Movie> movies = movieRepository.getMoviesWinnersByProducerId(producer.getId());

            if (!movies.isEmpty()) {
                Collections.sort(movies, Comparator.comparingLong(Movie::getYear)); // Ordenar por ano

                Movie previousMovie = movies.get(0);
                int intervalCount = 1; // Inicialize o contador de intervalo para 1

                for (int i = 1; i < movies.size(); i++) {
                    Movie currentMovie = movies.get(i);
                    long interval = currentMovie.getYear() - previousMovie.getYear();

                    if (interval == 1) {
                        // Intervalo de 1 ano
                        oneYearIntervals.add(new ProducerInterval(producer.getName(), (int) interval,
                                previousMovie.getYear(), currentMovie.getYear()));
                    } else if (interval >= 13) {
                        // Intervalo de pelo menos 13 anos
                        maxIntervals.add(new ProducerInterval(producer.getName(), (int) interval,
                                previousMovie.getYear(), currentMovie.getYear()));

                        // Verifique se o produtor tem pelo menos dois intervalos de 13 anos
                        maxIntervalCount.put(producer.getName(), maxIntervalCount.getOrDefault(producer.getName(), 0) + 1);
                    } else {
                        // Saída de depuração para intervalos diferentes de 1 ou 13 anos
                        System.out.println("Intervalo não esperado para " + producer.getName() +
                                ": " + interval + " anos");
                    }

                    previousMovie = currentMovie;
                }
            }
        }

        // Filtrar os produtores com pelo menos dois intervalos de 13 anos
        List<ProducerInterval> maxThirteenYearIntervals = new ArrayList<>();
        for (ProducerInterval interval : maxIntervals) {
            if (maxIntervalCount.getOrDefault(interval.getProducer(), 0) >= 2) {
                maxThirteenYearIntervals.add(interval);
            }
        }

        // Adicione as listas de intervalos encontradas à resposta
        response.setMinIntervals(oneYearIntervals);
        response.setMaxIntervals(maxThirteenYearIntervals);

        return response;
    }


}
