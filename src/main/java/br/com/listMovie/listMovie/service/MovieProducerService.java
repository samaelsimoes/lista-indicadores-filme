package br.com.listMovie.listMovie.service;

import br.com.listMovie.listMovie.entity.MovieProducer;
import br.com.listMovie.listMovie.repository.MovieProducerRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieProducerService {

    @Autowired
    private MovieProducerRepositiry movieProducerRepositiry;

    public MovieProducer save(MovieProducer movieProducer) {
        return this.movieProducerRepositiry.save(movieProducer);
    }
}