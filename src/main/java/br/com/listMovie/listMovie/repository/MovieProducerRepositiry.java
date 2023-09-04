package br.com.listMovie.listMovie.repository;
import br.com.listMovie.listMovie.entity.MovieProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MovieProducerRepositiry extends JpaRepository<MovieProducer, Long> {}