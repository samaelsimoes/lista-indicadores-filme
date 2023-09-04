package br.com.listMovie.listMovie.repository;


import br.com.listMovie.listMovie.entity.Movie;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select  c " +
            "from   MovieProducer a, Producer b, Movie c " +
            "where  c.id = a.movie.id " +
            "and    b.id = a.producer.id " +
            "and    a.producer.id = :producerId and c.winner = 'S' " +
            "ORDER  BY c.year")
    List<Movie> getMoviesWinnersByProducerId(@Param("producerId") Long producerId);

}
