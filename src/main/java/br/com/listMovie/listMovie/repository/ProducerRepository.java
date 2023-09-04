package br.com.listMovie.listMovie.repository;

import br.com.listMovie.listMovie.entity.Producer;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducerRepository  extends JpaRepository<Producer, Long> {

    @Query("select  p " +
            "from   Producer p " +
            "where  upper(name) = upper(:name)")
    Producer getEntityByName(@Param("name") String name);

    @Query("select  distinct p " +
            "from   MovieProducer v, Producer p, Movie m " +
            "where  m.id = v.movie.id " +
            "and    p.id = v.producer.id " +
            "and    m.winner = 'S' " +
            "order by   p.name")
    List<Producer> getProducersWinners();

}
